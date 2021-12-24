package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
