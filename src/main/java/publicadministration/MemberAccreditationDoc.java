package publicadministration;

import data.AccreditationNumb;
import data.Nif;

import java.util.Objects;

public class MemberAccreditationDoc extends PDFDocument {

    // Represents the member accreditation document
    private final Nif nif;
    private final AccreditationNumb affiliationNumber;

    // Initialize attributes
    public MemberAccreditationDoc(Nif nif, AccreditationNumb affiliationNumber) {
        Objects.requireNonNull(nif, "Nif can't be null");
        Objects.requireNonNull(affiliationNumber, "Affiliation Number can't be null");
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
