package publicadministration;

import java.util.*;

public class QuotePeriodsCollection {

    private final Collection<QuotePeriod> quotes;
    private final Comparator<QuotePeriod> comparator = Comparator.comparing(QuotePeriod::getInitDay);

    public QuotePeriodsCollection() {
        this.quotes = new TreeSet<>(comparator);
    }

    public boolean addQuotePeriod(QuotePeriod quote) {
        return quotes.add(quote);
    }

    public Collection<QuotePeriod> getQuotes() {
        return quotes;
    }

    @Override
    public String toString() {
        return Arrays.toString(quotes.toArray());
    }
}
