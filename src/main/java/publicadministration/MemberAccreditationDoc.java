package publicadministration;

import data.AccreditationNumb;
import data.Nif;

public class MemberAccreditationDoc extends PDFDocument {

    // Represents the member accreditation document
    private final Nif nif;
    private final AccreditationNumb affiliationNumber;

    // Initialize attributes
    public MemberAccreditationDoc(Nif nif, AccreditationNumb affiliationNumber) {
        this.nif = nif;
        this.affiliationNumber = affiliationNumber;
    }

    public Nif getNif() {
        return nif;
    }

    public AccreditationNumb getAffiliationNumber() {
        return affiliationNumber;
    }
}
