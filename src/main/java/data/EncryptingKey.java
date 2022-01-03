package data;

import java.math.BigInteger;

public final class EncryptingKey {

    private final BigInteger privateKey;
    private final BigInteger publicKey;

    public EncryptingKey(BigInteger privateKey, BigInteger publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }
}
