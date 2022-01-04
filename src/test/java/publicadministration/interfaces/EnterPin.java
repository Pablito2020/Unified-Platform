package publicadministration.interfaces;

import exceptions.*;

import java.net.ConnectException;

public interface EnterPin {

    void enterPinInvalidPin()
            throws NotValidCredException, NotAffiliatedException, IncorrectValDateException,
                    BadFormatAccreditationNumberException, NifNotRegisteredException,
                    AnyMobileRegisteredException, ConnectException;

    void getLaboralLifeNotAffiliated();

    void getMemberAccNotAffiliated();
}
