package exceptions;

public class BadFormatPasswordException extends Exception {
    public BadFormatPasswordException(String message) {
        super(message);
    }
}
