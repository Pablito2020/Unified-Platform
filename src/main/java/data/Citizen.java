package data;

import publicadministration.PDFDocument;

public class Citizen {

    private final DNI dni;
    private boolean affiliated;
    private PDFDocument document;
    private Password password;
    private Telephone telephoneNumber;

    public Citizen(DNI dni, Telephone telephoneNumber) {
        this.dni = dni;
        this.telephoneNumber = telephoneNumber;
    }

    public DNI getDni() {
        return dni;
    }

    public Telephone getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Telephone telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public boolean isAffiliated() {
        return affiliated;
    }

    public void setAffiliated(boolean affiliated) {
        this.affiliated = affiliated;
    }

    public PDFDocument getDocument() {
        return document;
    }

    public void setDocument(PDFDocument document) {
        this.document = document;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Citizen citizen = (Citizen) o;

        if (affiliated != citizen.affiliated) return false;
        if (dni != null ? !dni.equals(citizen.dni) : citizen.dni != null) return false;
        if (document != null ? !document.equals(citizen.document) : citizen.document != null) return false;
        if (password != null ? !password.equals(citizen.password) : citizen.password != null) return false;
        return telephoneNumber != null ? telephoneNumber.equals(citizen.telephoneNumber) : citizen.telephoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + (affiliated ? 1 : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        return result;
    }
}
