package enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CertificationReport {
    LABORAL_LIFE_DOC((byte) 0),
    MEMBER_ACCREDITATION_DOC((byte) 1);

    private final byte value;

    CertificationReport(byte value) {
        this.value = value;
    }

    public static String getReports() {
        return Arrays.stream(CertificationReport.values())
                .map(CertificationReport::toString)
                .collect(Collectors.joining());
    }

    public static CertificationReport valueOf(byte value) {
        for (CertificationReport report : CertificationReport.values()) {
            if (report.value == value) return report;
        }
        throw new IllegalArgumentException(value + " isn't a Report Byte Value");
    }

    public byte getByte() {
        return value;
    }

    @Override
    public String toString() {
        return "Report: " + name() + " has byte: " + this.value;
    }
}
