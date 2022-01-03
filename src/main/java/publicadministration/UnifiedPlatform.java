package publicadministration;

import data.*;
import enums.AuthenticationMethod;
import enums.CertificationReport;
import enums.ClaveUserStatus;
import enums.Procedures;
import exceptions.*;
import services.CertificationAuthority;
import services.SS;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;

public class UnifiedPlatform {

    private SS securitySocial;
    private CertificationAuthority certificationAuthority;
    private CertificationReport reportType;
    private AuthenticationMethod authMethod;
    private Citizen citizen;

    public UnifiedPlatform() {}

    public UnifiedPlatform(SS securitySocial) {
        this.securitySocial = securitySocial;
    }

    // Input events

    public void processSearcher() {
        System.out.println("Loading text message for introducing the key words");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String action = searchKeyWords(keyWord);
        System.out.println(action);
        // TODO: This needs to instantiate a SS instance, but how? We need an anonymous class or
        // something!
        // this.securitySocial = Procedures.valueOf(action).getInstance();
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
        this.citizen.setAffiliated(this.certificationAuthority.sendPIN(nif, valDate));
        if (citizen.getTelephoneNumber() == null) throw new AnyMobileRegisteredException();
    }

    public void enterPIN(PINcode pin)
            throws NotValidPINException, NotAffiliatedException, ConnectException {
        if (!citizen.isAffiliated()) throw new NotAffiliatedException();
        this.certificationAuthority.checkPIN(citizen.getDNI().getNif(), pin);
        // TODO: Check which document we need to use
        citizen.setDocument(securitySocial.getLaboralLife(citizen.getDNI().getNif()));
    }

    public void enterCred(Nif nif, Password password)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
                    ConnectException, IncorrectValDateException {
        ClaveUserStatus claveOption =
                ClaveUserStatus.valueOf(
                        this.certificationAuthority.checkCredentials(nif, password));
        if (claveOption == ClaveUserStatus.REGISTERED_REINFORCED)
            this.certificationAuthority.sendPIN(nif, citizen.getDNI().getValDate());
    }

    private void printDocument() throws BadPathException, PrintingException {
        throw new UnsupportedOperationException("No implemented");
    }

    private void downloadDocument() {
        throw new UnsupportedOperationException("No implemented");
    }

    private void selectPath(DocPath path) throws BadPathException {
        throw new UnsupportedOperationException("No implemented");
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
        System.out.println("Printing document");
        throw new UnsupportedOperationException("No implemented");
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        System.out.println("Downloading Document");
        throw new UnsupportedOperationException("No implemented");
    }

    public SS getSecuritySocial() {
        return securitySocial;
    }

    public void setSecuritySocial(SS securitySocial) {
        this.securitySocial = securitySocial;
    }

    public CertificationAuthority getCertificationAuthority() {
        return certificationAuthority;
    }

    public void setCertificationAuthority(CertificationAuthority certificationAuthority) {
        this.certificationAuthority = certificationAuthority;
    }

    public CertificationReport getReportType() {
        return reportType;
    }

    public void setReportType(CertificationReport reportType) {
        this.reportType = reportType;
    }

    public AuthenticationMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(AuthenticationMethod authMethod) {
        this.authMethod = authMethod;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
}
