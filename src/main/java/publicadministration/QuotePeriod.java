package publicadministration;

import java.util.Date;

public class QuotePeriod {

    private final Date initDay;
    private final int numDays;

    public QuotePeriod(Date date, int numberDays) {
        if (date == null) throw new NullPointerException("Date can't reference to null");
        if (numberDays <= 0)
            throw new NumberFormatException("Days should be bigger or equal than 1");
        this.initDay = date;
        this.numDays = numberDays;
    }

    public Date getInitDay() {
        return initDay;
    }

    public int getNumDays() {
        return numDays;
    }

    @Override
    public String toString() {
        return "Quote{"
                + "initial day:'"
                + initDay.toString()
                + '\''
                + "number of days: '"
                + numDays
                + '\''
                + '}';
    }
}
