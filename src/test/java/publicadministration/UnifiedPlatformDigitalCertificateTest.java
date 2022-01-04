package publicadministration;

import data.*;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.UnifiedPlatformTest;
import services.CertificationAuthority;
import services.SS;

import java.math.BigInteger;
import java.net.ConnectException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnifiedPlatformDigitalCertificateTest implements UnifiedPlatformTest {

    EncryptingKey privateKey;
    EncryptingKey publicKey;
    EncryptedData data;
    UnifiedPlatform unifiedPlatform;
    Citizen citizen;

    @BeforeEach
    public void setUp()
            throws BadFormatPasswordException, InvalidTelephoneFormat, BadFormatNifException {
        initializeUnifiedPlatform();
        setUpAndInjectKeys();
        injectSecuritySocial();
        injectAuthority();
        injectDecryptor();
        injectCitizen();
    }

    private void initializeUnifiedPlatform() {
        unifiedPlatform = new UnifiedPlatform();
    }

    private void setUpAndInjectKeys() {
        privateKey = publicKey = new EncryptingKey(new BigInteger("9"));
        BigInteger secret = new BigInteger(1, "29292929L".getBytes());
        byte[] dataEncrypted = (publicKey.getKey().multiply(secret)).toByteArray();
        data = new EncryptedData(dataEncrypted);
        unifiedPlatform.setPrivateKey(privateKey);
        unifiedPlatform.setPublicKey(publicKey);
    }

    private void injectSecuritySocial() {
        unifiedPlatform.setSecuritySocial(
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
                });
    }

    private void injectAuthority() {
        unifiedPlatform.setCertificationAuthority(
                new CertificationAuthority() {
                    @Override
                    public boolean sendPIN(Nif nif, Date date) {
                        throw new UnsupportedOperationException();
                    }

                    @Override
                    public boolean checkPIN(Nif nif, PINcode pin) {
                        throw new UnsupportedOperationException();
                    }

                    @Override
                    public byte checkCredentials(Nif nif, Password password) {
                        throw new UnsupportedOperationException();
                    }

                    @Override
                    public EncryptedData sendCertfAuth(EncryptingKey pubKey)
                            throws NotValidCertificateException {
                        if (!publicKey.getKey().equals(pubKey.getKey()))
                            throw new NotValidCertificateException();
                        return data;
                    }
                });
    }

    private void injectDecryptor() {
        unifiedPlatform.setDecryptor(
                (encryptedData, privateKey) -> {
                    BigInteger result = new BigInteger(1, encryptedData.getData());
                    byte[] dataDecrypted = (result.divide(privateKey.getKey())).toByteArray();
                    try {
                        return new Nif(new String(dataDecrypted));
                    } catch (BadFormatNifException e) {
                        throw new DecryptationException();
                    }
                });
    }

    private void injectCitizen()
            throws BadFormatPasswordException, InvalidTelephoneFormat, BadFormatNifException {
        citizen =
                new Citizen(new DNI(new Date(), new Nif("48059123C")), new Telephone("678546755"));
        citizen.setPassword(new Password("correctPassword"));
        unifiedPlatform.setCitizen(citizen);
    }

    @Test
    public void badPassword() {
        assertThrows(
                NotValidPasswordException.class,
                () -> {
                    unifiedPlatform.enterPassword(new Password("invalidPassword"));
                });
    }

    @Test
    public void badPublicKey() {
        unifiedPlatform.setPublicKey(new EncryptingKey(new BigInteger("11")));
        assertThrows(
                NotValidCertificateException.class,
                () -> {
                    unifiedPlatform.enterPassword(new Password("correctPassword"));
                });
    }

    @Test
    public void invalidDecrypt() {
        unifiedPlatform.setPrivateKey(new EncryptingKey((new BigInteger("11"))));
        assertThrows(
                DecryptationException.class,
                () -> {
                    unifiedPlatform.enterPassword(new Password("correctPassword"));
                });
    }

    @Test
    public void validDecrypt()
            throws BadFormatPasswordException, NotValidPasswordException,
                    NotValidCertificateException, DecryptationException, ConnectException {
        unifiedPlatform.enterPassword(new Password("correctPassword"));
        assertEquals("received nif: Nif{nif ciudadano='29292929L'}\n", outContent.toString());
    }
}
