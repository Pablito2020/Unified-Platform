package data;

import exceptions.BadFormatNifException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void equalsDiffTest() throws BadFormatNifException {
        Nif nif1 = new Nif("12345678A");
        Nif nif2 = new Nif("12345678A");
        assertTrue(nif1.equals(nif2));
    }

    @Test
    public void equalsSameTest() throws BadFormatNifException {
        Nif nif1 = new Nif("12345678A");
        assertTrue(nif1.equals(nif1));
    }

    @Test
    public void notEqualsTest() throws BadFormatNifException {
        Nif nif1 = new Nif("12345678A");
        Nif nif2 = new Nif("87654321Z");
        assertFalse(nif1.equals(nif2));
    }
}
