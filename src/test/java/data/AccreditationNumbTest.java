package data;

import exceptions.BadFormatAccreditationNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccreditationNumbTest {

    @Test
    public void goodTest() throws Exception {
        AccreditationNumb numb = new AccreditationNumb("1234");
        assertEquals("1234", numb.getNumber());
    }

    @Test
    public void badFormatNumbTest() {
        Throwable ex =
                Assertions.assertThrows(
                        BadFormatAccreditationNumberException.class,
                        () -> {
                            new AccreditationNumb("123L");
                        });
        assertEquals("only numbers are allowed.", ex.getMessage());
    }

    @Test
    public void nullParameterTest() {
        Throwable ex =
                Assertions.assertThrows(
                        NullPointerException.class,
                        () -> {
                            new AccreditationNumb(null);
                        });
        assertEquals("number shouldn't reference to null", ex.getMessage());
    }

    @Test
    @DisplayName("Equals on different reference with same values")
    public void equalsDiffObjectTest() throws BadFormatAccreditationNumberException {
        AccreditationNumb accNumb1 = new AccreditationNumb("123412341234");
        AccreditationNumb accNumb2 = new AccreditationNumb("123412341234");
        assertTrue(accNumb1.equals(accNumb2));
    }

    @Test
    @DisplayName("Equals on same reference")
    public void equalsSameObjectTest() throws BadFormatAccreditationNumberException {
        AccreditationNumb accNumb1 = new AccreditationNumb("123412341234");
        assertTrue(accNumb1.equals(accNumb1));
    }

    @Test
    @DisplayName("Equals on different reference with different values")
    public void notEqualsTest() throws BadFormatAccreditationNumberException {
        AccreditationNumb accNumb1 = new AccreditationNumb("123413241234");
        AccreditationNumb accNumb2 = new AccreditationNumb("432143214321");
        assertFalse(accNumb1.equals(accNumb2));
    }
}
