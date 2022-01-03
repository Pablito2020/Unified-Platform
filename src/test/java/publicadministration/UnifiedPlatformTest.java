package publicadministration;

import exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface UnifiedPlatformTest {

    @Test
    default void enterKeyWordTestLaboralLife() throws AnyKeyWordProcedureException {
        UnifiedPlatform unifiedPlatform = new UnifiedPlatform();
        PrintStream outStream = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        unifiedPlatform.enterKeyWords("Laboral Life Doc");
        assertEquals("SS\n", outContent.toString());
        System.setOut(outStream);
    }

    @Test
    default void enterKeyWordTestMemberAcc() throws AnyKeyWordProcedureException {
        UnifiedPlatform unifiedPlatform = new UnifiedPlatform();
        PrintStream outStream = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        unifiedPlatform.enterKeyWords("Member Accreditation Doc");
        assertEquals("SS\n", outContent.toString());
        System.setOut(outStream);
    }

    @Test
    default void eventsTest() {
        UnifiedPlatform unifiedPlatform = new UnifiedPlatform();
        PrintStream outStream = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        String expectedOutput =
                "Loading text message for introducing the key words\n"
                        + "Selected SS AAPP\n"
                        + "Selected Citizens\n"
                        + "Report Options: \n"
                        + "Report: LABORAL_LIFE_DOC has byte: 0Report: MEMBER_ACCREDITATION_DOC has"
                        + " byte: 1\n";

        System.setOut(new PrintStream(outContent));
        unifiedPlatform.processSearcher();
        unifiedPlatform.selectSS();
        unifiedPlatform.selectCitizens();
        unifiedPlatform.selectReports();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(outStream);
    }

    void enterNIFPINObtTest()
            throws IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException;

    void enterPinTest()
            throws NotValidPINException, NotAffiliatedException, ConnectException,
                    IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException, BadFormatAccreditationNumberException;

    void enterBadNifNIFPINObtTest();

    void enterBadDateNIFPINObtTest();

    void selectAuthMethodTest();
}
