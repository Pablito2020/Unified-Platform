package publicadministration;

import java.util.Date;

public class QuotePeriod { // Represents a quote period as a registered worker

    private final Date initDay;
    private final int numDays;

    public QuotePeriod(Date date, int numberDays) {
        throw new RuntimeException("TODO");
    }

    // TODO: Are all the getters completely necessary?

    public Date getInitDay() {
        return initDay;
    }

    public int getNumDays() {
        return numDays;
    }

    @Override
    public String toString() {
        throw new RuntimeException("TODO");
    }
}
