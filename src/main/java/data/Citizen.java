package data;

import publicadministration.PDFDocument;

import java.util.Objects;

public class Citizen {

    private final DNI dni;
    private boolean affiliated;
    private PDFDocument document;
    private Telephone telephoneNumber;

    public Citizen(DNI dni, Telephone telephoneNumber) {
        this.dni = dni;
        this.telephoneNumber = telephoneNumber;
    }

    public DNI getDNI() {
        return dni;
    }

    public Telephone getTelephoneNumber() {
        return telephoneNumber;
    }

    public boolean isAffiliated() {
        return affiliated;
    }

    public PDFDocument getDocument() {
        return document;
    }

    public void setAffiliated(boolean affiliated) {
        this.affiliated = affiliated;
    }

    public void setDocument(PDFDocument document) {
        this.document = document;
    }

    public void setTelephoneNumber(Telephone telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return affiliated == citizen.affiliated
                && Objects.equals(dni, citizen.dni)
                && Objects.equals(document, citizen.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, affiliated, document);
    }
}
