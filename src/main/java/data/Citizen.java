package data;

import publicadministration.PDFDocument;

import java.util.Date;
import java.util.Objects;

public class Citizen {

    private final DNI dni;
    private boolean affiliated;
    private PDFDocument document;

    public Citizen(DNI dni) {
        this.dni = dni;
    }

    public DNI getDNI() {
        return dni;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return affiliated == citizen.affiliated && Objects.equals(dni, citizen.dni) && Objects.equals(document, citizen.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, affiliated, document);
    }
}
