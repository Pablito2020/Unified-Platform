package publicadministration;

import data.DocPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PDFDocument {
    static final String DEFAULT_PATH = "src/main/res/informe.pdf";
    private final Date creationDate;
    private DocPath path;
    private File file;

    public PDFDocument() {
        this.path = new DocPath(DEFAULT_PATH);
        this.file = new File(path.getPath());
        this.creationDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public DocPath getPath() {
        return path;
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
        if (destPath == null) throw new NullPointerException("Path can't be NULL");
        if (!file.renameTo(new File(destPath.getPath())))
            throw new IOException("Unable to rename file");
        path = new DocPath(destPath.getPath());
        file = new File(destPath.getPath());
    }

    public void openDoc(DocPath path) throws IOException {
        if (path == null) throw new NullPointerException("Path can't be null");
        File toOpenFile = new File(path.getPath());
        Desktop.getDesktop().open(toOpenFile);
    }
}
