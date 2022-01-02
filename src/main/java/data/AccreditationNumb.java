package data;

import exceptions.BadFormatAccreditationNumberException;

public final class AccreditationNumb {

    private static final String NUMBER_REGEX = "^[0-9]{12}$";

    private final String number;

    public AccreditationNumb(String number) throws BadFormatAccreditationNumberException {
        if (number == null) throw new NullPointerException("number shouldn't reference to null");
        if (!number.matches(NUMBER_REGEX))
            throw new BadFormatAccreditationNumberException(
                    "Accreditation number must have 12 digits");
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccreditationNumb that = (AccreditationNumb) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
