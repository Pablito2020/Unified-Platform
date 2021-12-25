package publicadministration;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuotePeriodTest {

    @Test
    public void dateNullTest() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new QuotePeriod(null, 20);
                        });
        assertEquals("Date can't reference to null", ex.getMessage());
    }

    @Test
    public void negativeDaysTest() {
        Throwable ex =
                assertThrows(
                        NumberFormatException.class,
                        () -> {
                            new QuotePeriod(new Date(), -1);
                        });
        assertEquals("Days should be bigger or equal than 1", ex.getMessage());
    }

    @Test
    public void getDateTest() {
        Date date = new Date();
        QuotePeriod quote = new QuotePeriod(date, 20);
        assertEquals(date, quote.getInitDay());
    }

    @Test
    public void getNumberDaysTest() {
        QuotePeriod quote = new QuotePeriod(new Date(), 10);
        assertEquals(10, quote.getNumDays());
    }
}
