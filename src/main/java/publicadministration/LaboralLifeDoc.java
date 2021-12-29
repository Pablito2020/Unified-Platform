package publicadministration;

import data.Nif;

import java.util.Objects;

public class LaboralLifeDoc extends PDFDocument {
    private final Nif nif;
    private final QuotePeriodsCollection quoteCollection;

    public LaboralLifeDoc(Nif nif, QuotePeriodsCollection quoteCollection) {
        Objects.requireNonNull(nif, "Nif can't be null");
        Objects.requireNonNull(quoteCollection, "Quote Collection can't be null");
        this.nif = nif;
        this.quoteCollection = quoteCollection;
    }

    public Nif getNif() {
        return nif;
    }

    public QuotePeriodsCollection getQuoteCollection() {
        return quoteCollection;
    }
}
