package enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum DigitalCertificate {
    DNI_E((byte) 0),
    FNMT((byte) 1);

    byte value;

    DigitalCertificate(byte value) {
        this.value = value;
    }

    public static String getDigitalCertificateOptions() {
        return Arrays.stream(DigitalCertificate.values())
                .map(DigitalCertificate::toString)
                .collect(Collectors.joining());
    }

    public static DigitalCertificate valueOf(byte value) {
        for (DigitalCertificate certificate : DigitalCertificate.values()) {
            if (certificate.value == value) return certificate;
        }
        throw new IllegalArgumentException(value + " unsupported byte");
    }

    public byte getByte() {
        return value;
    }

    @Override
    public String toString() {
        return "DigitalCertificate " + +value + " ";
    }
}
