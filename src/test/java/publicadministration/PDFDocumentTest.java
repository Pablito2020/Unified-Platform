package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        DocPath path = new DocPath("../../res/informe.pdf");
        assertEquals(path, document.getPath());
    }

    /*@Test
    public void moveDocTest() throws IOException {
        DocPath newPath = new DocPath("../../res/renamed.pdf");
        document.moveDoc(newPath);
        assertEquals(newPath, document.getPath());
    }*/
}
