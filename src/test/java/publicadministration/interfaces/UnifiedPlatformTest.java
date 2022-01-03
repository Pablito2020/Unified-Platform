package publicadministration.interfaces;

import exceptions.AnyMobileRegisteredException;
import exceptions.IncorrectValDateException;
import exceptions.NifNotRegisteredException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.UnifiedPlatform;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        System.setOut(outStream);
    }

    @Test
    default void eventsTest() {
        String expectedOutput =
                "Loading text message for introducing the key words\n"
                        + "Selected SS AAPP\n"
                        + "Selected Citizens\n"
                        + "Report Options: \n"
                        + "Report: LABORAL_LIFE_DOC has byte: 0Report: MEMBER_ACCREDITATION_DOC has"
                        + " byte: 1\n";
        unifiedPlatform.processSearcher();
        unifiedPlatform.selectSS();
        unifiedPlatform.selectCitizens();
        unifiedPlatform.selectReports();
        assertEquals(expectedOutput, outContent.toString());
    }

    void enterNifPinBadNif();

    void enterNifPinBadDate();

    void enterNifPinBadMobile();

    void correctNifPin() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException;

    void enterPinInvalidPin();

    void getLaboralLifeNotAffiliated();

    void getMemberAccNotAffiliated();
}
