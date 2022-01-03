package data;

import exceptions.InvalidTelephoneFormat;

public final class Telephone {

    private static final String TELEPHONE_REGEX = "(\\d[\\s-]*){8}\\d";
    private final String number;

    public Telephone(String number) throws InvalidTelephoneFormat {
        if (number == null) throw new NullPointerException("number string can't be null");
        if (!number.matches(TELEPHONE_REGEX))
            throw new InvalidTelephoneFormat("The phone number has to have 9 numbers");
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
