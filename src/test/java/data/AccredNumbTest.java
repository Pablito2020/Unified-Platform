package data;

import exceptions.BadFormatAccredNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccredNumbTest {

    @Test
    public void goodTest() throws Exception {
        AccredNumb numb = new AccredNumb("1234");
        assertEquals(numb.getNumber(), "1234");
    }

    @Test
    public void badFormatNumbTest() throws Exception {
        Assertions.assertThrows(BadFormatAccredNumberException.class, () -> {
            new AccredNumb("123L");
        });
    }
}
