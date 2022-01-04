package publicadministration;

import data.*;
import enums.*;
import exceptions.*;
import services.CertificationAuthority;
import services.Decryptor;
import services.SS;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ConnectException;
import java.util.Date;
import java.util.Random;

public class UnifiedPlatform {

    private EncryptingKey privateKey;
    private EncryptingKey publicKey;
    private SS securitySocial;
    private CertificationAuthority certificationAuthority;
    private CertificationReport reportType;
    private AuthenticationMethod authMethod;
    private DigitalCertificate digitalCertificate;
    private Decryptor decryptor;
    private Citizen citizen;

    public UnifiedPlatform() {
        this.privateKey =
                new EncryptingKey(new BigInteger(BigInteger.TEN.bitCount(), new Random()));
        this.publicKey = new EncryptingKey(new BigInteger(BigInteger.TEN.bitCount(), new Random()));
    }

    // Input events

    public void processSearcher() {
        System.out.println("Loading text message for introducing the key words");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String service = searchKeyWords(keyWord);
        System.out.println("Opening portal: "  + service);
    }

    public void selectSS() {
        System.out.println("Selected SS AAPP");
    }

    public void selectCitizens() {
        System.out.println("Selected Citizens");
    }

    public void selectReports() {
        System.out.println("Report Options: ");
        System.out.println(CertificationReport.getReports());
    }

    public void selectCertificationReport(byte opc) {
        this.reportType = CertificationReport.valueOf(opc);
        System.out.println(AuthenticationMethod.getAuthOptions());
    }

    public void selectAuthMethod(byte opc) {
        this.authMethod = AuthenticationMethod.valueOf(opc);
        System.out.println("Showing authentication form");
    }

    public void enterNIFPINobt(Nif nif, Date valDate)
            throws NifNotRegisteredException, IncorrectValDateException,
                    AnyMobileRegisteredException, ConnectException {
        if (this.certificationAuthority.sendPIN(nif, valDate))
            System.out.println("PIN has been sent correctly");
    }

    public void enterPIN(PINcode pin)
            throws NotValidPINException, NotAffiliatedException, ConnectException,
                    BadFormatAccreditationNumberException {
        if (this.certificationAuthority.checkPIN(citizen.getDni().getNif(), pin))
            System.out.println("PIN checked correctly");
        PDFDocument document = getReport();
        citizen.setDocument(document);
    }

    private PDFDocument getReport()
            throws NotAffiliatedException, ConnectException, BadFormatAccreditationNumberException {
        switch (reportType) {
            case LABORAL_LIFE_DOC:
                return securitySocial.getLaboralLife(citizen.getDni().getNif());
            case MEMBER_ACCREDITATION_DOC:
                return securitySocial.getMembAccred(citizen.getDni().getNif());
            default:
                throw new IllegalArgumentException("Unsupported report");
        }
    }

    public void enterCred(Nif nif, Password password)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
                    ConnectException, IncorrectValDateException, NotAffiliatedException,
                    BadFormatAccreditationNumberException {
        ClaveUserStatus claveOption =
                ClaveUserStatus.valueOf(
                        this.certificationAuthority.checkCredentials(nif, password));
        if (claveOption == ClaveUserStatus.REGISTERED_REINFORCED) {
            this.certificationAuthority.sendPIN(nif, citizen.getDni().getValDate());
        } else if (claveOption == ClaveUserStatus.NOT_REGISTERED) {
            throw new NifNotRegisteredException();
        } else if (claveOption == ClaveUserStatus.REGISTERED_NO_REINFORCED) {
            citizen.setDocument(getReport());
        }
    }

    private void printDocument() throws BadPathException, PrintingException {
        printDocument(citizen.getDocument().getPath());
    }

    private void downloadDocument() throws BadPathException {
        downloadDocument(citizen.getDocument().getPath());
    }

    private void selectPath(DocPath path) throws BadPathException, IOException {
        citizen.getDocument().moveDoc(path);
    }

    // Other operations

    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        for (Procedures service : Procedures.values()) {
            if (service.getKeyWord().equals(keyWord)) return service.getAAPPName();
        }
        throw new AnyKeyWordProcedureException(keyWord + " isn't an available action");
    }

    private void openDocument(DocPath path) throws BadPathException {
        try {
            citizen.getDocument().openDoc(path);
        } catch (IOException e) {
            throw new BadPathException();
        }
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
        System.out.println("Printing document on path: " + path.getPath());
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        System.out.println("Downloading Document on path: " + citizen.getDocument().getPath());
    }

    // Optional operations

    public void selectCertificate(byte opc) {
        digitalCertificate = DigitalCertificate.valueOf(opc);
    }

    public void enterPassword(Password password)
            throws NotValidPasswordException, NotValidCertificateException, ConnectException,
                    DecryptationException {
        if (!citizen.getPassword().equals(password)) throw new NotValidPasswordException();
        EncryptedData result = certificationAuthority.sendCertfAuth(publicKey);
        Nif resultNif = decryptIDdata(result);
        System.out.println("received nif: " + resultNif.toString());
    }

    private Nif decryptIDdata(EncryptedData encryptData) throws DecryptationException {
        return decryptor.decryptIDdata(encryptData, privateKey);
    }

    public void setDecryptor(Decryptor decryptor) {
        this.decryptor = decryptor;
    }

    public void setSecuritySocial(SS securitySocial) {
        this.securitySocial = securitySocial;
    }

    public void setCertificationAuthority(CertificationAuthority certificationAuthority) {
        this.certificationAuthority = certificationAuthority;
    }

    public void setReportType(CertificationReport reportType) {
        this.reportType = reportType;
    }

    public DigitalCertificate getDigitalCertificate() {
        return digitalCertificate;
    }

    public AuthenticationMethod getAuthMethod() {
        return authMethod;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public void setPublicKey(EncryptingKey key) {
        this.publicKey = key;
    }

    public void setPrivateKey(EncryptingKey key) {
        this.privateKey = key;
    }
}
