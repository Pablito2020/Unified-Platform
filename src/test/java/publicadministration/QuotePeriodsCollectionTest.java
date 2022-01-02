package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class QuotePeriodsCollectionTest {

    private QuotePeriodsCollection collection;

    @BeforeEach
    public void createCollection() {
        collection = new QuotePeriodsCollection();
    }

    @Test
    public void quoteNullTest() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    collection.addQuotePeriod(null);
                });
    }

    @Test
    public void addSameElement() {
        Date date = new Date(164105923); // Date -> Fri Jan 02 22:35:05 CET 1970
        QuotePeriod quote = new QuotePeriod(date, 20);
        assertTrue(collection.addQuotePeriod(quote));
        assertFalse(collection.addQuotePeriod(quote));
    }
}
