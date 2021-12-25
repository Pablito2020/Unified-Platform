package publicadministration;

import data.DocPath;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PDFDocument {
    private final Date creationDate;
    private final DocPath path;
    private final File file;

    // Initialize attributes and emulates the document download at a default path
    public PDFDocument() {
        throw new RuntimeException("TODO: Implement constructor");
    }

    // TODO: Getters

    // Converts to String members Date and DocPath
    @Override
    public String toString() {
        throw new RuntimeException("TODO");
    }

    // To implement only optionally

    // Moves the document to the destination path indicated
    public void moveDoc(DocPath destPath) throws IOException {
        throw new RuntimeException("TODO");
    }

    // Opens the document at the path indicated
    public void openDoc(DocPath path) throws IOException {
        throw new RuntimeException("TODO");
    }
}
