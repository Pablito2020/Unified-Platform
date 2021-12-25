package data;

import exceptions.BadFormatPinException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PINcodeTest {

    @Test
    public void nullPin() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new PINcode(null);
                        });
        assertEquals("PIN shouldn't reference to null.", ex.getMessage());
    }

    @Test
    public void notNumberPin() {
        Throwable ex =
                assertThrows(
                        BadFormatPinException.class,
                        () -> {
                            new PINcode("28L39");
                        });
        assertEquals("Pin should be in numeric format", ex.getMessage());
    }

    @Test
    public void notLength3Pin() {
        Throwable ex =
                assertThrows(
                        BadFormatPinException.class,
                        () -> {
                            new PINcode("2839");
                        });
        assertEquals("Pin should be 3 digits", ex.getMessage());
    }

    @Test
    public void correctPin() throws BadFormatPinException {
        PINcode pin = new PINcode("234");
        assertEquals("234", pin.getPin());
    }
}
