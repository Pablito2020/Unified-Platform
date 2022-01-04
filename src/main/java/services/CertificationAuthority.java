package services;

import data.*;
import enums.ClaveUserStatus;
import exceptions.*;

import java.net.ConnectException;
import java.util.Date;

public interface CertificationAuthority {
    boolean sendPIN(Nif nif, Date date)
            throws NifNotRegisteredException, IncorrectValDateException,
                    AnyMobileRegisteredException, ConnectException;

    boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException;

    byte checkCredentials(Nif nif, Password password)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
                    ConnectException;

    EncryptedData sendCertfAuth(EncryptingKey pubKey)
            throws NotValidCertificateException, ConnectException;
}
