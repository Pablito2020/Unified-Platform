package publicadministration;

import data.*;
import enums.AuthenticationMethod;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CertificationAuthority;
import services.SS;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UnifiedPlatformClavePINTest implements UnifiedPlatformTest {

    SS ss;
    CertificationAuthority certificationAuthority;
    Citizen citizen;
    AuthenticationMethod clavePIN;
    PINcode correctPin;
    UnifiedPlatform unifiedPlatform;

    @BeforeEach
    public void createSS()
            throws BadFormatNifException, BadFormatPinException, InvalidTelephoneFormat {
        clavePIN = AuthenticationMethod.CLAVE_PIN;
        citizen =
                new Citizen(new DNI(new Date(), new Nif("48059123C")), new Telephone("678546755"));
        correctPin = new PINcode("123");

        ss =
                new SS() {
                    @Override
                    public LaboralLifeDoc getLaboralLife(Nif nif)
                            throws NotAffiliatedException, ConnectException {
                        if (!citizen.getDni().getNif().equals(nif) || !citizen.isAffiliated())
                            throw new NotAffiliatedException();
                        return new LaboralLifeDoc(nif, new QuotePeriodsCollection());
                    }

                    @Override
                    public MemberAccreditationDoc getMembAccred(Nif nif)
                            throws NotAffiliatedException, ConnectException,
                                    BadFormatAccreditationNumberException {
                        if (!citizen.getDni().getNif().equals(nif) || !citizen.isAffiliated())
                            throw new NotAffiliatedException();
                        return new MemberAccreditationDoc(nif, new AccreditationNumb("1234"));
                    }
                };

        certificationAuthority =
                new CertificationAuthority() {
                    @Override
                    public boolean sendPIN(Nif nif, Date date)
                            throws NifNotRegisteredException, IncorrectValDateException,
                                    AnyMobileRegisteredException, ConnectException {
                        if (citizen.getTelephoneNumber() == null)
                            throw new AnyMobileRegisteredException();
                        if (!citizen.getDni().getNif().equals(nif))
                            throw new NifNotRegisteredException();
                        else if (!date.equals(citizen.getDni().getValDate()))
                            throw new IncorrectValDateException();
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
                            throws NifNotRegisteredException, NotValidCredException,
                                    AnyMobileRegisteredException, ConnectException {
                        throw new UnsupportedOperationException("Not supported by clave pin");
                    }

                    @Override
                    public EncryptedData sendCertfAuth(EncryptingKey pubKey) throws NotValidCertificateException, ConnectException {
                        throw new UnsupportedOperationException("Not supported by clave pin");
                    }
                };

        unifiedPlatform = new UnifiedPlatform();
        unifiedPlatform.setAuthMethod(clavePIN);
        unifiedPlatform.setCertificationAuthority(certificationAuthority);
        unifiedPlatform.setSecuritySocial(ss);
        unifiedPlatform.setCitizen(citizen);
    }

    @Test
    public void getLaboralLifeNotAffiliated()
            throws BadFormatNifException, NotAffiliatedException, ConnectException {
        assertThrows(
                NotAffiliatedException.class,
                () -> {
                    PDFDocument laboralLife = ss.getLaboralLife(new Nif("45678909C"));
                });
    }

    @Test
    public void getMemberAccNotAffiliated()
            throws BadFormatNifException, NotAffiliatedException, ConnectException {
        assertThrows(
                NotAffiliatedException.class,
                () -> {
                    PDFDocument laboralLife = ss.getMembAccred(new Nif("45678909C"));
                });
    }

    @Test
    public void sendPinIncorrectNif() {
        assertThrows(
                NifNotRegisteredException.class,
                () -> {
                    certificationAuthority.sendPIN(new Nif("43234563C"), new Date());
                });
    }

    @Test
    public void sendPinIncorrectDate() {
        assertThrows(
                IncorrectValDateException.class,
                () -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(2020, 3, 14);
                    certificationAuthority.sendPIN(citizen.getDni().getNif(), new Date(cal.getTimeInMillis()));
                });
    }

    @Test
    public void checkPinBadPin() {
        assertThrows(
                NotValidPINException.class,
                () -> {
                    certificationAuthority.checkPIN(citizen.getDni().getNif(), new PINcode("125"));
                });
    }

    @Test
    public void enterPinTest()
            throws NotValidPINException, NotAffiliatedException, ConnectException,
                    IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException {
        unifiedPlatform.enterNIFPINobt(citizen.getDni().getNif(), citizen.getDni().getValDate());
        unifiedPlatform.setSecuritySocial(ss);
        unifiedPlatform.enterPIN(correctPin);
        assertEquals(
                unifiedPlatform.getCitizen().getDocument().toString(),
                new LaboralLifeDoc(citizen.getDni().getNif(), new QuotePeriodsCollection())
                        .toString());
    }

    @Test
    public void enterNIFPINObtTest()
            throws IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException {
        unifiedPlatform.enterNIFPINobt(citizen.getDni().getNif(), citizen.getDni().getValDate());
        assertEquals(unifiedPlatform.getCitizen().getDni(), citizen.getDni());
        assertTrue(unifiedPlatform.getCitizen().isAffiliated());
    }

    @Test
    public void enterNotRegisteredMobileNIFPInObtTest() {
        Citizen newCitizen = new Citizen(citizen.getDni(), null);
        unifiedPlatform.setCitizen(newCitizen);
        assertThrows(
                AnyMobileRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFPINobt(
                            citizen.getDni().getNif(), citizen.getDni().getValDate());
                });
    }

    @Test
    public void enterBadNifNIFPINObtTest() {
        assertThrows(
                NifNotRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFPINobt(new Nif("34567898Y"), new Date());
                });
    }

    @Test
    public void enterBadDateNIFPINObtTest() {
        assertThrows(
                IncorrectValDateException.class,
                () -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(2020, 3, 14);
                    unifiedPlatform.enterNIFPINobt(citizen.getDni().getNif(), new Date(cal.getTimeInMillis()));
                });
    }

    @Test
    public void selectAuthMethodTest() {
        unifiedPlatform.selectAuthMethod((byte) 0);
        assertEquals(unifiedPlatform.getAuthMethod(), clavePIN);
    }

    @Test
    public void badSelectAuthMethodTest() {
        unifiedPlatform.selectAuthMethod((byte) 1);
        assertNotEquals(unifiedPlatform.getAuthMethod(), clavePIN);
    }
}