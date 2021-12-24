package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
