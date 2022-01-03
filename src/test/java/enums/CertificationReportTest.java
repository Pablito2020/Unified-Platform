package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificationReportTest {

    @Test
    public void getReportsTest() {
        String expectedOutput =
                "Report: LABORAL_LIFE_DOC has byte: 0Report: MEMBER_ACCREDITATION_DOC has byte: 1";
        assertEquals(CertificationReport.getReports(), expectedOutput);
    }

    @Test
    public void getLaboralLifeDoc() {
        assertEquals(CertificationReport.valueOf((byte) 0), CertificationReport.LABORAL_LIFE_DOC);
    }

    @Test
    public void getMemberAccreditation() {
        assertEquals(
                CertificationReport.valueOf((byte) 1),
                CertificationReport.MEMBER_ACCREDITATION_DOC);
    }
}
