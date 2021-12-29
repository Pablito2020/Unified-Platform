package publicadministration;

import data.Nif;
import exceptions.BadFormatNifException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LaboralLifeDocTest {

    @Test
    public void nullNif() {
        Throwable exception =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new LaboralLifeDoc(null, new QuotePeriodsCollection());
                        });
        assertEquals("Nif can't be null", exception.getMessage());
    }

    @Test
    public void nullAccreditationNumber() {
        Throwable exception =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new LaboralLifeDoc(new Nif("49263972L"), null);
                        });
        assertEquals("Quote Collection can't be null", exception.getMessage());
    }

    @Test
    public void sameNifReference() throws BadFormatNifException {
        Nif nif = new Nif("49263972L");
        LaboralLifeDoc laboralLifeDoc = new LaboralLifeDoc(nif, new QuotePeriodsCollection());
        assertEquals(nif, laboralLifeDoc.getNif());
    }

    @Test
    public void sameQuoteCollection() throws BadFormatNifException {
        QuotePeriodsCollection quoteCollection = new QuotePeriodsCollection();
        LaboralLifeDoc laboralLifeDoc = new LaboralLifeDoc(new Nif("49263972L"), quoteCollection);
        assertEquals(quoteCollection, laboralLifeDoc.getQuoteCollection());
    }
}
