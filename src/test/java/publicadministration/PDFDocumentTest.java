package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PDFDocumentTest {

    private PDFDocument document;

    @BeforeEach
    public void createPDFDocument() {
        document = new PDFDocument();
    }

    @Test
    public void getCreationDateTest() {
        Date date = new Date();
        assertEquals(date.toString(), document.getCreationDate().toString());
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
    public void openDocTest() {
        DocPath path = new DocPath("src/main/res/informe.pdf");
        try {
            document.openDoc(path);
        } catch (IOException | NullPointerException |
                UnsupportedOperationException | SecurityException |
                IllegalArgumentException ex) { fail("An exception was thrown"); }
    }
}
