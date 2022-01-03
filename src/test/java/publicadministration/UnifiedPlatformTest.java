package publicadministration;

import exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface UnifiedPlatformTest {

    @Test
    default void enterKeyWordTest() {
        throw new RuntimeException("TODO");
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

    public void enterPinTest()
            throws NotValidPINException, NotAffiliatedException, ConnectException,
                    IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException;

    void enterBadNifNIFPINObtTest();

    void enterBadDateNIFPINObtTest();

    void selectAuthMethodTest();
}
