package data;

import exceptions.BadFormatPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Equals on different reference with same values")
    public void correctPin() throws BadFormatPinException {
        PINcode pin = new PINcode("234");
        assertEquals("234", pin.getPin());
    }

    @Test
    @DisplayName("Equals on same reference")
    public void equalsSameTest() throws BadFormatPinException {
        PINcode pin1 = new PINcode("123");
        PINcode pin2 = new PINcode("123");
        assertTrue(pin1.equals(pin2));
    }

    @Test
    @DisplayName("Equals on different reference with different values")
    public void equalsDiffTest() throws BadFormatPinException {
        PINcode pin1 = new PINcode("123");
        assertTrue(pin1.equals(pin1));
    }

    @Test
    public void notEqualsTest() throws BadFormatPinException {
        PINcode pin1 = new PINcode("123");
        PINcode pin2 = new PINcode("321");
        assertFalse(pin1.equals(pin2));
    }
}
