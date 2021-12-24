package data;

public final class DocPath {

    private final String path;

    public DocPath(String path) {
        if (path == null)
            throw new NullPointerException("Document Path shouldn't be null");
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPath docPath = (DocPath) o;
        return path.equals(docPath.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return path;
    }
}
