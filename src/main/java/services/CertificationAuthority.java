package services;

import data.Nif;
import data.PINcode;
import data.Password;
import exceptions.*;

import java.net.ConnectException;
import java.sql.Date;

public interface CertificationAuthority {
    boolean sendPIN(Nif nif, Date date)
            throws NifNotRegisteredException, IncorrectValDateException,
                    AnyMobileRegisteredException, ConnectException;

    boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException;

    byte checkCredentials(Nif nif, Password password)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
                    ConnectException;
}
