package data;

import exceptions.BadFormatNifException;

/** Essential data classes */
public final class Nif {

    private static final String NIF_REGEX = "^[0-9]{8}[A-Za-z]$";

    // The tax identification number in the Spanish state.

    private final String nif;

    public Nif(String code) throws BadFormatNifException {
        if (code == null) throw new NullPointerException("NIF code is null.");
        if (!code.matches(NIF_REGEX))
            throw new BadFormatNifException("Nif should be 8 numbers and 1 letter");
        this.nif = code;
    }

    public String getNif() {
        return nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif niff = (Nif) o;
        return nif.equals(niff.nif);
    }

    @Override
    public int hashCode() {
        return nif.hashCode();
    }

    @Override
    public String toString() {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    }
}
