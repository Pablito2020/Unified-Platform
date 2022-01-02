package enums;

public enum DigitalCertificate {
    DNI_E((byte) 0),
    FNMT((byte) 1);

    byte value;

    DigitalCertificate(byte value) {
        this.value = value;
    }

    public static DigitalCertificate valueOf(byte value) {
        for (DigitalCertificate certificate : DigitalCertificate.values()) {
            if (certificate.value == value) return certificate;
        }
        throw new IllegalArgumentException(value + " unsupported byte");
    }

}
