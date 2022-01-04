package publicadministration.interfaces;

import enums.CertificationReport;
import exceptions.AnyKeyWordProcedureException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import publicadministration.UnifiedPlatform;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface UnifiedPlatformTest {

    PrintStream outStream = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    UnifiedPlatform unifiedPlatform = new UnifiedPlatform();

    @BeforeEach
    default void initializeOutput() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    default void finish() {
        outContent.reset();
        System.setOut(outStream);
    }

    @Test
    @DisplayName("Input events without clicking on process searcher")
    default void inputEventsTestWithoutProcess() {
        String expectedOutput =
                "Selected SS AAPP\n"
                        + "Selected Citizens\n"
                        + "Report Options: \n"
                        + "Report: LABORAL_LIFE_DOC has byte: 0Report: MEMBER_ACCREDITATION_DOC has"
                        + " byte: 1\n";
        unifiedPlatform.selectSS();
        unifiedPlatform.selectCitizens();
        unifiedPlatform.selectReports();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Input events clicking on process searcher and searching Laboral Life Doc")
    default void inputEventsTestLaboral() throws AnyKeyWordProcedureException {
        String expectedOutput =
                "Loading text message for introducing the key words\n"
                        + "Opening portal: SS\n"
                        + "Selected Citizens\n"
                        + "Report Options: \n"
                        + "Report: LABORAL_LIFE_DOC has byte: 0Report: MEMBER_ACCREDITATION_DOC has"
                        + " byte: 1\n";
        unifiedPlatform.processSearcher();
        unifiedPlatform.enterKeyWords("Laboral Life Doc");
        unifiedPlatform.selectCitizens();
        unifiedPlatform.selectReports();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Input events clicking on process searcher and searching Member Accreditation Doc")
    default void inputEventsTestMember() throws AnyKeyWordProcedureException {
        String expectedOutput =
                "Loading text message for introducing the key words\n"
                        + "Opening portal: SS\n"
                        + "Selected Citizens\n"
                        + "Report Options: \n"
                        + "Report: LABORAL_LIFE_DOC has byte: 0Report: MEMBER_ACCREDITATION_DOC has"
                        + " byte: 1\n";
        unifiedPlatform.processSearcher();
        unifiedPlatform.enterKeyWords("Member Accreditation Doc");
        unifiedPlatform.selectCitizens();
        unifiedPlatform.selectReports();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Faulty key word on process searcher")
    default void faultyInputEvent() {
        assertThrows(
                AnyKeyWordProcedureException.class,
                () -> {
                    unifiedPlatform.processSearcher();
                    unifiedPlatform.enterKeyWords("Invalid Key Word");
                    unifiedPlatform.selectCitizens();
                    unifiedPlatform.selectReports();
                });
    }

    @Test
    default void selectCertificationReportLaboralLife() {
        String expectedOutput =
                "Authentication Method: CLAVE_PIN has byte value: 0Authentication Method:"
                    + " CLAVE_PERMANENTE has byte value: 1Authentication Method:"
                    + " CERTIFICADO_DIGITAL has byte value: 2\n";
        unifiedPlatform.selectCertificationReport(CertificationReport.LABORAL_LIFE_DOC.getByte());
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    default void selectCertificationReportMembers() {
        String expectedOutput =
                "Authentication Method: CLAVE_PIN has byte value: 0Authentication Method:"
                    + " CLAVE_PERMANENTE has byte value: 1Authentication Method:"
                    + " CERTIFICADO_DIGITAL has byte value: 2\n";
        unifiedPlatform.selectCertificationReport(CertificationReport.LABORAL_LIFE_DOC.getByte());
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void selectAuthenticationMethod();
}
