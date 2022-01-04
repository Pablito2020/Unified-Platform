package publicadministration;

import data.*;
import enums.CertificationReport;
import enums.ClaveUserStatus;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.UnifiedPlatformTest;
import services.CertificationAuthority;
import services.SS;

import java.net.ConnectException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnifiedPlatformClavePermanenteTest implements UnifiedPlatformTest {
    SS ss;
    CertificationAuthority certificationAuthority;
    Citizen citizen;
    PINcode correctPin;
    UnifiedPlatform unifiedPlatform;
    CertificationReport reportType;

    @BeforeEach
    public void setUp()
            throws BadFormatNifException, BadFormatPinException, InvalidTelephoneFormat,
                    BadFormatPasswordException {
        correctPin = new PINcode("123");
        initializeCitizen();
        initializeSecuritySocial();
        initializeAuthority();
        initializeUnifiedPlatform();
    }

    private void initializeSecuritySocial() {
        ss =
                new SS() {
                    @Override
                    public LaboralLifeDoc getLaboralLife(Nif nif)
                            throws NotAffiliatedException, ConnectException {
                        if (!citizen.isAffiliated()) throw new NotAffiliatedException();
                        return new LaboralLifeDoc(nif, new QuotePeriodsCollection());
                    }

                    @Override
                    public MemberAccreditationDoc getMembAccred(Nif nif)
                            throws NotAffiliatedException, ConnectException,
                                    BadFormatAccreditationNumberException {
                        if (!citizen.isAffiliated()) throw new NotAffiliatedException();
                        return new MemberAccreditationDoc(nif, new AccreditationNumb("1234"));
                    }
                };
    }

    private void initializeAuthority() {
        certificationAuthority =
                new CertificationAuthority() {
                    @Override
                    public boolean sendPIN(Nif nif, Date date)
                            throws NifNotRegisteredException, IncorrectValDateException,
                                    AnyMobileRegisteredException, ConnectException {
                        if (!citizen.getDni().getNif().equals(nif))
                            throw new NifNotRegisteredException();
                        else if (!date.equals(citizen.getDni().getValDate()))
                            throw new IncorrectValDateException();
                        if (citizen.getTelephoneNumber() == null)
                            throw new AnyMobileRegisteredException();
                        return true;
                    }

                    @Override
                    public boolean checkPIN(Nif nif, PINcode pin)
                            throws NotValidPINException, ConnectException {
                        if (!correctPin.equals(pin)) throw new NotValidPINException();
                        return true;
                    }

                    @Override
                    public byte checkCredentials(Nif nif, Password password)
                            throws NotValidCredException, NifNotRegisteredException,
                                    AnyMobileRegisteredException {
                        if (!citizen.getPassword().equals(password))
                            throw new NotValidCredException();
                        if (!citizen.getDni().getNif().equals(nif))
                            throw new NifNotRegisteredException();
                        if (citizen.getTelephoneNumber() == null)
                            throw new AnyMobileRegisteredException();
                        System.out.println("Credentials are OK!");
                        return citizen.getClaveUserStatus().getByte();
                    }

                    @Override
                    public EncryptedData sendCertfAuth(EncryptingKey pubKey) {
                        throw new UnsupportedOperationException("Not supported by clave pin");
                    }
                };
    }

    private void initializeUnifiedPlatform() {
        unifiedPlatform = new UnifiedPlatform();
        unifiedPlatform.setCertificationAuthority(certificationAuthority);
        unifiedPlatform.setSecuritySocial(ss);
        unifiedPlatform.setCitizen(citizen);
        unifiedPlatform.setReportType(CertificationReport.LABORAL_LIFE_DOC);
    }

    private void initializeCitizen()
            throws InvalidTelephoneFormat, BadFormatNifException, BadFormatPasswordException {
        citizen =
                new Citizen(new DNI(new Date(), new Nif("48059123C")), new Telephone("678546755"));
        citizen.setAffiliated(true);
        citizen.setPassword(new Password("fakepassword"));
        citizen.setClaveUserStatus(ClaveUserStatus.REGISTERED_REINFORCED);
    }

    @Test
    public void enterNifPasswdBadNif() {
        assertThrows(
                NifNotRegisteredException.class,
                () -> unifiedPlatform.enterCred(new Nif("99999999C"), citizen.getPassword()));
    }

    @Test
    public void enterNifPasswdBadPasswd() {
        assertThrows(
                NotValidCredException.class,
                () ->
                        unifiedPlatform.enterCred(
                                citizen.getDni().getNif(), new Password("wrongpasswd")));
    }

    @Test
    public void enterNifPasswdBadMobile() {
        citizen.setTelephoneNumber(null);
        assertThrows(
                AnyMobileRegisteredException.class,
                () ->
                        unifiedPlatform.enterCred(
                                citizen.getDni().getNif(), new Password("fakepassword")));
    }

    @Test
    public void correctNifPasswd()
            throws BadFormatNifException, BadFormatPasswordException, NotValidCredException,
                    NotAffiliatedException, IncorrectValDateException,
                    BadFormatAccreditationNumberException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException {

        citizen.setClaveUserStatus(ClaveUserStatus.REGISTERED_REINFORCED);
        unifiedPlatform.setReportType(CertificationReport.MEMBER_ACCREDITATION_DOC);
        unifiedPlatform.enterCred(new Nif("48059123C"), new Password("fakepassword"));
        assertEquals("Credentials are OK!\n", outContent.toString());
    }

    @Test
    public void registeredReinforcedInvalidPin()
            throws NotValidCredException, IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException, NotAffiliatedException,
                    BadFormatAccreditationNumberException {
        citizen.setClaveUserStatus(ClaveUserStatus.REGISTERED_REINFORCED);
        unifiedPlatform.enterCred(citizen.getDni().getNif(), citizen.getPassword());
        assertThrows(
                NotValidPINException.class,
                () -> {
                    unifiedPlatform.enterPIN(new PINcode("125"));
                });
    }

    @Test
    public void notRegisteredClavePermanente(){
        citizen.setClaveUserStatus(ClaveUserStatus.NOT_REGISTERD);
        assertThrows(
                NifNotRegisteredException.class,
                () -> unifiedPlatform.enterCred(citizen.getDni().getNif(), citizen.getPassword())
        );
    }

    @Test
    public void getLaboralLifeNotAffiliated() {
        citizen.setAffiliated(false);
        citizen.setClaveUserStatus(ClaveUserStatus.REGISTERED_NO_REINFORCED);
        unifiedPlatform.setReportType(CertificationReport.LABORAL_LIFE_DOC);
        assertThrows(
                NotAffiliatedException.class,
                () -> unifiedPlatform.enterCred(citizen.getDni().getNif(), citizen.getPassword()));
    }

    @Test
    public void getMemberAccNotAffiliated() {
        citizen.setAffiliated(false);
        citizen.setClaveUserStatus(ClaveUserStatus.REGISTERED_NO_REINFORCED);
        unifiedPlatform.setReportType(CertificationReport.MEMBER_ACCREDITATION_DOC);
        assertThrows(
                NotAffiliatedException.class,
                () -> unifiedPlatform.enterCred(citizen.getDni().getNif(), citizen.getPassword()));
    }
}
