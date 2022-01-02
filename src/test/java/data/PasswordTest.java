package data;

import exceptions.BadFormatPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    public void nullNif() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new Password(null);
                        });
        assertEquals("Password shouldn't reference to null", ex.getMessage());
    }

    @Test
    @DisplayName("Empty password")
    public void badFormatPassword1() {
        Throwable ex =
                assertThrows(
                        BadFormatPasswordException.class,
                        () -> {
                            new Password("");
                        });
        assertEquals("Password must be 8-20 characters long", ex.getMessage());
    }

    @Test
    @DisplayName("Password length bigger than 20")
    public void badFormatPassword2() {
        Throwable ex =
                assertThrows(
                        BadFormatPasswordException.class,
                        () -> {
                            new Password("qwertyuiopasdfghjklñz"); // 21 characters password
                        });
        assertEquals("Password must be 8-20 characters long", ex.getMessage());
    }

    @Test
    public void goodTest() throws BadFormatPasswordException {
        Password password = new Password("fakePassword");
        assertEquals("fakePassword", password.getPassword());
    }

    @Test
    @DisplayName("Equals on different reference with same values")
    public void equalsDiffTest() throws BadFormatPasswordException {
        Password password1 = new Password("fakePassword");
        Password password2 = new Password("fakePassword");
        assertTrue(password1.equals(password2));
    }

    @Test
    @DisplayName("Equals on same reference")
    public void equalsSameTest() throws BadFormatPasswordException {
        Password password1 = new Password("fakePassword");
        assertTrue(password1.equals(password1));
    }

    @Test
    @DisplayName("Equals on different reference with different values")
    public void notEqualsTest() throws BadFormatPasswordException {
        Password password1 = new Password("fakePassword");
        Password password2 = new Password("realPassword");
        assertFalse(password1.equals(password2));
    }
}
