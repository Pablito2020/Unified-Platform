package publicadministration;

import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;
import exceptions.*;

import java.net.ConnectException;
import java.util.Date;

public class UnifiedPlatform {

    // Input events

    public void processSearcher() {
        throw new RuntimeException("Not implemented");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        throw new RuntimeException("Not implemented");
    }

    public void selectSS() {
        throw new RuntimeException("Not implemented");
    }

    public void selectCitizens() {
        throw new RuntimeException("Not implemented");
    }

    public void selectReports() {
        throw new RuntimeException("Not implemented");
    }

    public void selectCertificationReport(byte opc) {
        throw new RuntimeException("Not implemented");
    }

    public void selectAuthMethod(byte opc) {
        throw new RuntimeException("Not implemented");
    }

    public void enterNIFPINobt(Nif nif, Date valDate)
            throws NifNotRegisteredException, IncorrectValDateException,
                    AnyMobileRegisteredException, ConnectException {
        throw new RuntimeException("Not implemented");
    }

    public void enterPIN(PINcode pin)
            throws NotValidPINException, NotAffiliatedException, ConnectException {
        throw new RuntimeException("Not implemented");
    }

    public void enterCred(Nif nif, Password password)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
                    ConnectException {
        throw new RuntimeException("Not implemented");
    }

    private void printDocument() throws BadPathException, PrintingException {
        throw new RuntimeException("Not implemented");
    }

    private void downloadDocument() {
        throw new RuntimeException("Not implemented");
    }

    private void selectPath(DocPath path) throws BadPathException {
        throw new RuntimeException("Not implemented");
    }

    // Other operations

    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        throw new RuntimeException("Not implemented");
    }

    private void openDocument(DocPath path) throws BadPathException {
        throw new RuntimeException("Not implemented");
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
        throw new RuntimeException("Not implemented");
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        throw new RuntimeException("Not implemented");
    }

    // TODO: Possibly more operations

}
