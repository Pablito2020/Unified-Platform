package publicadministration;

import data.AccreditationNumb;
import data.Nif;
import exceptions.BadFormatAccreditationNumberException;
import exceptions.BadFormatNifException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberAccreditationDocTest {

    @Test
    public void sameNifReference()
            throws BadFormatNifException, BadFormatAccreditationNumberException {
        Nif nif = new Nif("49263972L");
        MemberAccreditationDoc memberAccreditationDoc =
                new MemberAccreditationDoc(nif, new AccreditationNumb("123456789102"));
        assertEquals(nif, memberAccreditationDoc.getNif());
    }

    @Test
    public void sameAccreditationNumberReference()
            throws BadFormatNifException, BadFormatAccreditationNumberException {
        AccreditationNumb accreditationNumb = new AccreditationNumb("123456789102");
        MemberAccreditationDoc memberAccreditationDoc =
                new MemberAccreditationDoc(new Nif("49263972L"), accreditationNumb);
        assertEquals(accreditationNumb, memberAccreditationDoc.getAffiliationNumber());
    }
}
