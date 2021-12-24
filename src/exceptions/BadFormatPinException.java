package exceptions;

public class BadFormatPinException extends Exception {

    // TODO: Check whether this message should be here or in the Pin class.

    public BadFormatPinException() {
        super("Pin should be in numeric format");
    }
}
