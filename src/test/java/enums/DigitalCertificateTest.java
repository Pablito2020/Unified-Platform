package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitalCertificateTest {

    @Test
    public void getDigitalCertificateOptions() {
        String expectedOutput = "DigitalCertificate 0 DigitalCertificate 1 ";
        assertEquals(DigitalCertificate.getDigitalCertificateOptions(), expectedOutput);
    }

    @Test
    public void getDniE() {
        assertEquals(DigitalCertificate.valueOf((byte) 0), DigitalCertificate.DNI_E);
    }

    @Test
    public void getFNMT() {
        assertEquals(DigitalCertificate.valueOf((byte) 1), DigitalCertificate.FNMT);
    }
}
