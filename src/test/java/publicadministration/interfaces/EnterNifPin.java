package publicadministration.interfaces;

import exceptions.AnyMobileRegisteredException;
import exceptions.IncorrectValDateException;
import exceptions.NifNotRegisteredException;

import java.net.ConnectException;

public interface EnterNifPin {

    void enterNifPinBadNif();

    void enterNifPinBadDate();

    void enterNifPinBadMobile();

    void correctNifPin()
            throws IncorrectValDateException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException;
}
