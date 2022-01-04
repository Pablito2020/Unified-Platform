package data;

import java.math.BigInteger;

public final class EncryptingKey {

    private final BigInteger key;

    public EncryptingKey(BigInteger key) {
        this.key = key;
    }

    public BigInteger getKey() {
        return key;
    }
}
