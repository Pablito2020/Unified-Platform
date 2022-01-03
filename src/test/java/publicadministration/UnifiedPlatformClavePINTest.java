package publicadministration;

import data.*;
import enums.AuthenticationMethod;
import enums.ClaveUserStatus;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CertificationAuthority;
import services.SS;

import java.net.ConnectException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UnifiedPlatformClavePINTest implements UnifiedPlatformTest {

    SS ss;
    CertificationAuthority certificationAuthority;
    Citizen citizen;
    AuthenticationMethod clavePIN;
    PINcode correctPin;
    UnifiedPlatform unifiedPlatform;
    ClaveUserStatus registered;

    @BeforeEach
    public void createSS() throws BadFormatNifException, BadFormatPinException, InvalidTelephoneFormat {
        clavePIN = AuthenticationMethod.CLAVE_PIN;
        citizen = new Citizen(new DNI(new Date(), new Nif("48059123C")), new Telephone("678546755"));
        correctPin = new PINcode("123");
        registered = ClaveUserStatus.REGISTERED_REINFORCED;

        ss =
                new SS() {
                    @Override
                    public LaboralLifeDoc getLaboralLife(Nif nif)
                            throws NotAffiliatedException, ConnectException {
                        return new LaboralLifeDoc(nif, new QuotePeriodsCollection());
                    }

                    @Override
                    public MemberAccreditationDoc getMembAccred(Nif nif)
                            throws NotAffiliatedException, ConnectException,
                                    BadFormatAccreditationNumberException {
                        return new MemberAccreditationDoc(nif, new AccreditationNumb("1234"));
                    }
                };

        certificationAuthority =
                new CertificationAuthority() {
                    @Override
                    public boolean sendPIN(Nif nif, Date date)
                            throws NifNotRegisteredException, IncorrectValDateException,
                                    AnyMobileRegisteredException, ConnectException {
                        if (!citizen.getDNI().getNif().equals(nif))
                            throw new NifNotRegisteredException();
                        else if (!date.equals(citizen.getDNI().getValDate()))
                            throw new IncorrectValDateException();
                        return true;
                    }

                    @Override
                    public boolean checkPIN(Nif nif, PINcode pin)
                            throws NotValidPINException, ConnectException {
                        if (!citizen.getDNI().getNif().equals(nif)) throw new ConnectException();
                        else if (!correctPin.equals(pin)) throw new NotValidPINException();
                        return true;
                    }

                    @Override
                    public byte checkCredentials(Nif nif, Password password)
                            throws NifNotRegisteredException, NotValidCredException,
                                    AnyMobileRegisteredException, ConnectException {
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
    public void enterPinTest()
            throws NotValidPINException, NotAffiliatedException, ConnectException,
                    IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException {
        unifiedPlatform.enterNIFPINobt(citizen.getDNI().getNif(), citizen.getDNI().getValDate());
        unifiedPlatform.setSecuritySocial(ss);
        unifiedPlatform.enterPIN(correctPin);
        assertEquals(
                unifiedPlatform.getCitizen().getDocument().toString(),
                new LaboralLifeDoc(citizen.getDNI().getNif(), new QuotePeriodsCollection())
                        .toString());
    }

    @Test
    public void enterNIFPINObtTest()
            throws IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException {
        unifiedPlatform.enterNIFPINobt(citizen.getDNI().getNif(), citizen.getDNI().getValDate());
        assertEquals(unifiedPlatform.getCitizen().getDNI(), citizen.getDNI());
        assertTrue(unifiedPlatform.getCitizen().isAffiliated());
    }


    @Test
    public void enterNotRegisteredMobileNIFPInObtTest() {
        Citizen newCitizen = new Citizen(citizen.getDNI(), null);
        unifiedPlatform.setCitizen(newCitizen);
        assertThrows(
                AnyMobileRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFPINobt(citizen.getDNI().getNif(), citizen.getDNI().getValDate());
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
                    unifiedPlatform.enterNIFPINobt(citizen.getDNI().getNif(), new Date());
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
