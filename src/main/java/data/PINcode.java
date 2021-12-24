package data;

import exceptions.BadFormatPinException;

public final class PINcode {

    private static final String PIN_REGEX = "^[0-9]+$";
    private final String pin;

    public PINcode(String pin) throws BadFormatPinException {
        if (pin == null) throw new NullPointerException("PIN shouldn't reference to null.");
        if (!pin.matches(PIN_REGEX))
            throw new BadFormatPinException("Pin should be in numeric format");
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public int hashCode() {
        return pin.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PINcode piNcode = (PINcode) o;
        return pin.equals(piNcode.pin);
    }

    @Override
    public String toString() {
        return pin;
    }
}
