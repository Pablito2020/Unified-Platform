package publicadministration;

import data.*;
import enums.CertificationReport;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.EnterNifPin;
import publicadministration.interfaces.EnterPin;
import publicadministration.interfaces.UnifiedPlatformTest;
import services.CertificationAuthority;
import services.SS;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnifiedPlatformClavePINTest implements UnifiedPlatformTest, EnterNifPin, EnterPin {

    SS ss;
    CertificationAuthority certificationAuthority;
    Citizen citizen;
    PINcode correctPin;
    UnifiedPlatform unifiedPlatform;

    @BeforeEach
    public void setUp()
            throws BadFormatNifException, BadFormatPinException, InvalidTelephoneFormat {
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
                        System.out.println("Nif: " + nif.getNif() + "mine: " + citizen.getDni().getNif());
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
                    public byte checkCredentials(Nif nif, Password password) {
                        throw new UnsupportedOperationException("Not supported by clave pin");
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
    }

    private void initializeCitizen() throws InvalidTelephoneFormat, BadFormatNifException {
        citizen =
                new Citizen(new DNI(new Date(), new Nif("48059123C")), new Telephone("678546755"));
        citizen.setAffiliated(true);
    }

    // enterNIFPin method

    @Test
    public void enterNifPinBadNif() {
        assertThrows(
                NifNotRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFPINobt(new Nif("99999999C"), new Date());
                });
    }

    @Test
    public void enterNifPinBadDate() {
        assertThrows(
                IncorrectValDateException.class,
                () -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(2020, 3, 14);
                    unifiedPlatform.enterNIFPINobt(
                            citizen.getDni().getNif(), new Date(cal.getTimeInMillis()));
                });
    }

    @Test
    public void enterNifPinBadMobile() {
        citizen.setTelephoneNumber(null);
        assertThrows(
                AnyMobileRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFPINobt(
                            citizen.getDni().getNif(), citizen.getDni().getValDate());
                });
    }

    @Test
    public void correctNifPin() {
        assertDoesNotThrow(
                () ->
                        unifiedPlatform.enterNIFPINobt(
                                citizen.getDni().getNif(), citizen.getDni().getValDate()));
    }

    //  enterPIN methods

    @Test
    public void enterPinInvalidPin() {
        assertThrows(
                NotValidPINException.class,
                () -> {
                    unifiedPlatform.enterPIN(new PINcode("125"));
                });
    }

    @Test
    public void getLaboralLifeNotAffiliated() {
        citizen.setAffiliated(false);
        unifiedPlatform.setReportType(CertificationReport.LABORAL_LIFE_DOC);
        assertThrows(
                NotAffiliatedException.class,
                () -> {
                    unifiedPlatform.enterPIN(correctPin);
                });
    }

    @Test
    public void getMemberAccNotAffiliated() {
        citizen.setAffiliated(false);
        unifiedPlatform.setReportType(CertificationReport.MEMBER_ACCREDITATION_DOC);
        assertThrows(
                NotAffiliatedException.class,
                () -> {
                    unifiedPlatform.enterPIN(correctPin);
                });
    }

}
