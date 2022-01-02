package services;

import data.EncryptedData;
import data.EncryptingKey;
import data.Nif;
import exceptions.DecryptationException;

public interface Decryptor {
    Nif decryptIDdata(EncryptedData encryptedData, EncryptingKey privateKey) throws DecryptationException;
}
