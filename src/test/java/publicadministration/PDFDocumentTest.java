package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PDFDocumentTest {

    private PDFDocument document;

    @BeforeEach
    public void createPDFDocument() {
        document = new PDFDocument();
    }

    @Test
    public void getPathTest() {
        DocPath path = new DocPath("src/main/res/informe.pdf");
        assertEquals(path, document.getPath());
    }

    @Test
    public void moveDocTest() throws IOException {
        DocPath newPath = new DocPath("src/main/informe.pdf");
        document.moveDoc(newPath);
        assertEquals(newPath, document.getPath());
        // PDF has to be moved back to the default path
        newPath = new DocPath(PDFDocument.DEFAULT_PATH);
        document.moveDoc(newPath);
    }

    @Test
    public void moveDocNullTest() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    document.moveDoc(null);
                });
    }

    @Test
    @DisplayName("Move a document that doesn't exist")
    public void moveDocNoExistsTest() {
        Throwable ex =
                assertThrows(
                        IOException.class,
                        () -> {
                            document.moveDoc(new DocPath("src/java/no-exists.txt"));
                        });
        assertEquals("Unable to rename file", ex.getMessage());
    }

    @Test
    public void openDocTest() {
        DocPath path = new DocPath("src/main/res/informe.pdf");
        assertDoesNotThrow(() -> document.openDoc(path));
    }

    @Test
    public void openDocNullTest() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            document.openDoc(null);
                        });
        assertEquals("Path can't be null", ex.getMessage());
    }

    @Test
    @DisplayName("Open a document that doesn't exist")
    public void openDocNoFileTest() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    document.openDoc(new DocPath("src/no-exists.pdf"));
                });
    }
}
