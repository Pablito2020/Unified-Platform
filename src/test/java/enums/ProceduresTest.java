package enums;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProceduresTest {

    Procedures memberAccreditation;
    Procedures laboralLife;

    @BeforeEach
    public void defineProcedures() {
        laboralLife = Procedures.LABORAL_LIFE_DOC;
        memberAccreditation = Procedures.MEMBER_ACCREDITATION_DOC;
    }

    @Test
    public void getKeyWordTest() {
        assertEquals(laboralLife.getKeyWord(), "Laboral Life Doc");
        assertEquals(memberAccreditation.getKeyWord(), "Member Accreditation Doc");
    }

    @Test
    public void getAAPPName() {
        assertEquals(laboralLife.getAAPPName(), "SS");
        assertEquals(memberAccreditation.getAAPPName(), "SS");
    }
}
