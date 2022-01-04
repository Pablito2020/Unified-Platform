package data;

public final class EncryptedData {

    private final byte[] data;

    public EncryptedData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
