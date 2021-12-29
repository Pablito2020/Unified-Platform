package data;

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
    public void correctDocument() {
        Password password = new Password("fakePassword");
        assertEquals("fakePassword", password.getPassword());
    }

    @Test
    @DisplayName("Equals on different reference with same values")
    public void equalsDiffTest() {
        Password password1 = new Password("fakePassword");
        Password password2 = new Password("fakePassword");
        assertTrue(password1.equals(password2));
    }

    @Test
    @DisplayName("Equals on same reference")
    public void equalsSameTest() {
        Password password1 = new Password("fakePassword");
        assertTrue(password1.equals(password1));
    }

    @Test
    @DisplayName("Equals on different reference with different values")
    public void notEqualsTest() {
        Password password1 = new Password("fakePassword");
        Password password2 = new Password("realPassword");
        assertFalse(password1.equals(password2));
    }
}
