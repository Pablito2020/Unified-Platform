package publicadministration;

import data.DocPath;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class PDFDocument {
    private final Date creationDate;
    private final DocPath path;
    private final File file;

    public PDFDocument(DocPath path) throws IOException {
        if (path == null || path.getPath() == null ) throw new NullPointerException("Path can't be NULL");
        this.path = path;
        this.file = new File(path.getPath());
        if (!file.exists())
            throw new FileNotFoundException("Path is incorrect, no file on this path");
        this.creationDate = new Date();

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public DocPath getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    // Converts to String members Date and DocPath
    @Override
    public String toString() {
        return "PDFDocument{"
                + "File creation date:'"
                + creationDate.toString()
                + '\''
                + "path of the file: '"
                + path.getPath()
                + '\''
                + '}';
    }

    public void moveDoc(DocPath destPath) throws IOException {
        if (destPath == null || destPath.getPath() == null) throw new NullPointerException("Path can't be NULL");
        file.renameTo(new File(destPath.getPath()));
    }

    public void openDoc(DocPath path) throws IOException {
        try {
            File toOpenFile = new File (path.getPath());
            Desktop.getDesktop().open(toOpenFile);
        } catch (IOException ex) {
            throw new IOException("File can't be opened");
        }
    }
}
