package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DocPathTest {

    @Test
    public void nullPath() {
        Throwable ex =
                assertThrows(
                        NullPointerException.class,
                        () -> {
                            new DocPath(null);
                        });
        assertEquals("Document Path shouldn't be null", ex.getMessage());
    }

    @Test
    public void correctDocument() {
        DocPath path = new DocPath("/test/path");
        assertEquals("/test/path", path.getPath());
    }

    @Test
    @DisplayName("Equals on different reference with same values")
    public void equalsDiffObjectTest() {
        DocPath path1 = new DocPath("./path/to/file.pdf");
        DocPath path2 = new DocPath("./path/to/file.pdf");
        assertTrue(path1.equals(path2));
    }

    @Test
    @DisplayName("Equals on same reference")
    public void equalsSameObjectTest() {
        DocPath path1 = new DocPath("./path/to/file.pdf");
        assertTrue(path1.equals(path1));
    }

    @Test
    @DisplayName("Equals on different reference with different values")
    public void notEqualsTest() {
        DocPath path1 = new DocPath("./path/to/file.pdf");
        DocPath path2 = new DocPath("./path/to/file/document.pdf");
        assertFalse(path1.equals(path2));
    }
}
