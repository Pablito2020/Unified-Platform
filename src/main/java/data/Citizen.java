package data;

import publicadministration.PDFDocument;

public class Citizen {

    private final Nif nif;
    private boolean affiliated;
    private PDFDocument document;

    public Citizen(Nif nif) {
        this.nif = nif;
    }

    public Nif getNif() {
        return nif;
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
}
