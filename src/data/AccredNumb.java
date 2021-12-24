package data;

import exceptions.BadFormatAccredNumberException;

public class AccredNumb {

    private final static String NUMBER_REGEX = "^[0-9]+$";

    private final String number;

    public AccredNumb(String number) throws BadFormatAccredNumberException {
        if (number == null)
            throw new NullPointerException("number shouldn't reference to null");
        if (!number.matches(NUMBER_REGEX))
            throw new BadFormatAccredNumberException();
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccredNumb that = (AccredNumb) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
