package data;

import exceptions.BadFormatAccreditationNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
