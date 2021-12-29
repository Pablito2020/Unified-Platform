package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeEach;
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
        newPath = new DocPath("src/main/res/informe.pdf");
        document.moveDoc(newPath);
        assertEquals(newPath, document.getPath());
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
    public void moveDocFailTest() {
        Throwable ex =
                assertThrows(
                        IOException.class,
                        () -> {
                            document.moveDoc(new DocPath("src/java/lablifedoc.txt"));
                        });
        assertEquals("Unable to rename file", ex.getMessage());
    }

    @Test
    public void openDocTest() {
        DocPath path = new DocPath("src/main/res/informe.pdf");
        try {
            document.openDoc(path);
        } catch (IOException
                | NullPointerException
                | UnsupportedOperationException
                | SecurityException
                | IllegalArgumentException ex) {
            fail("An exception was thrown");
        }
    }
}
