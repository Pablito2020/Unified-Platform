package publicadministration;

import data.Nif;

import java.util.Objects;

public class LaboralLifeDoc extends PDFDocument {
    private final Nif nif;
    private final QuotePeriodsCollection quoteCollection;

    public LaboralLifeDoc(Nif nif, QuotePeriodsCollection quoteCollection) {
        this.nif = nif;
        this.quoteCollection = quoteCollection;
    }

    public Nif getNif() {
        return nif;
    }

    public QuotePeriodsCollection getQuoteCollection() {
        return quoteCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaboralLifeDoc that = (LaboralLifeDoc) o;
        return Objects.equals(nif, that.nif)
                && Objects.equals(quoteCollection, that.quoteCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, quoteCollection);
    }
}
