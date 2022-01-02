package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuotePeriodsCollectionTest {

    private QuotePeriodsCollection collection;

    @BeforeEach
    public void createCollection() {
        collection = new QuotePeriodsCollection();
    }

    @Test
    public void addNullQuoteTest() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    collection.addQuotePeriod(null);
                });
    }

    @Test
    @DisplayName("Add same element with same reference")
    public void addSameElement() {
        Date date = new Date(164105923);
        QuotePeriod quote = new QuotePeriod(date, 20);
        assertTrue(collection.addQuotePeriod(quote));
        assertFalse(collection.addQuotePeriod(quote));
    }

    @Test
    public void checkReverseOrder() {
        for (int i = 164105923 + (5 * 1000); i >= 164105923; i -= 1000) {
            Date date = new Date(i);
            collection.addQuotePeriod(new QuotePeriod(date, i % 5 + 1));
        }

        Comparator<QuotePeriod> comparator = Comparator.comparing(QuotePeriod::getInitDay);
        List<QuotePeriod> orderedCollection = new ArrayList<>(collection.getQuotes());
        orderedCollection.sort(comparator);
        List<QuotePeriod> result = new ArrayList<>(collection.getQuotes());
        assertEquals(orderedCollection, result);
    }

    @Test
    public void checkNaturalOrder() {
        for (int i = 164105923; i <= 164105923 + (5 * 1000); i += 1000) {
            Date date = new Date(i);
            collection.addQuotePeriod(new QuotePeriod(date, i % 5 + 1));
        }

        Comparator<QuotePeriod> comparator = Comparator.comparing(QuotePeriod::getInitDay);
        List<QuotePeriod> orderedCollection = new ArrayList<>(collection.getQuotes());
        orderedCollection.sort(comparator);
        List<QuotePeriod> result = new ArrayList<>(collection.getQuotes());
        assertEquals(orderedCollection, result);
    }

    @Test
    @DisplayName("Add same elements with different reference")
    public void addDifferentReference() {
        QuotePeriod quote = new QuotePeriod(new Date(164105923), 20);
        QuotePeriod quote2 = new QuotePeriod(new Date(164105923), 20);
        assertTrue(collection.addQuotePeriod(quote));
        assertFalse(collection.addQuotePeriod(quote2));
    }

    @Test
    @DisplayName("Check if collection is empty when we initialise QuotePeriodsCollection")
    public void isEmptyCollection() {
        List<QuotePeriod> collectionList = new ArrayList<>(collection.getQuotes());
        assertTrue(collectionList.isEmpty());
    }
}
