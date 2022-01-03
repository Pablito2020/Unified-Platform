package data;

import exceptions.InvalidTelephoneFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TelephoneTest {

    @Test
    public void correctTelephoneTest() {
        Assertions.assertDoesNotThrow(() -> new Telephone("623454803"));
    }

    @Test
    public void invalidTelephone() {
        Throwable ex =
                assertThrows(
                        InvalidTelephoneFormat.class,
                        () -> {
                            new Telephone("182a234");
                        });
        assertEquals("The phone number has to have 9 numbers", ex.getMessage());
    }

    @Test
    public void nullTelephoneNumber() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new Telephone(null);
                        });
        assertEquals("number string can't be null", ex.getMessage());
    }

    @Test
    public void spacesTelephoneNumber() {
        Assertions.assertDoesNotThrow(() -> new Telephone("623 454 803"));
    }
}
