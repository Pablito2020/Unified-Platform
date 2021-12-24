package data;

import exceptions.BadFormatNifException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NifTest {

    @Test
    public void nullNif() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new Nif(null);
                        });
        assertEquals("NIF code is null.", ex.getMessage());
    }

    @Test
    public void badNIFFormat() {
        Throwable ex =
                assertThrows(
                        BadFormatNifException.class,
                        () -> {
                            new Nif("L2839");
                        });
        assertEquals("Nif should be 8 numbers and 1 letter", ex.getMessage());
    }

    @Test
    public void correctNif() throws BadFormatNifException {
        Nif nif = new Nif("49263972L");
        assertEquals("49263972L", nif.getNif());
    }
}
