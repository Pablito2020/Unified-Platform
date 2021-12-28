package publicadministration;

import data.Nif;

// Represents the laboral life
public class LaboralLifeDoc extends PDFDocument {
    private final Nif nif;
    private final QuotePeriodsCollection quotePds;

    // Initialize attributes
    public LaboralLifeDoc(Nif nif, QuotePeriodsCollection qtP) {
        this.nif = nif;
        this.quotePds = qtP;
    }

    public Nif getNif() {
        return nif;
    }

    public QuotePeriodsCollection getQuotePds() {
        return quotePds;
    }
}
