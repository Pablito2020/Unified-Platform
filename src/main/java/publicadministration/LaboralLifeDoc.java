package publicadministration;

import data.Nif;

public class LaboralLifeDoc extends PDFDocument {
    private final Nif nif;
    private final QuotePeriodsCollection quotePds;

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
