package data;

import java.util.Date;
import java.util.Objects;

public class DNI {

    private Date valDate;
    private final Nif nif;

    public DNI(Date valDate, Nif nif) {
        this.valDate = valDate;
        this.nif = nif;
    }

    public Date getValDate() {
        return valDate;
    }

    public Nif getNif() {
        return nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DNI dni = (DNI) o;
        return Objects.equals(valDate, dni.valDate) && Objects.equals(nif, dni.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valDate, nif);
    }
}
