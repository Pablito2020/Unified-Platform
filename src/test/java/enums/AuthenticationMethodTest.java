package enums;

import exceptions.NotAffiliatedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthenticationMethodTest {

    @Test
    public void getAuthOptionsTest() {
        String expectedOutput =
                "Authentication Method: CLAVE_PIN has byte value: "
                        + "0Authentication Method: CLAVE_PERMANENTE has byte value: "
                        + "1Authentication Method: CERTIFICADO_DIGITAL has byte value: 2";
        assertEquals(AuthenticationMethod.getAuthOptions(), expectedOutput);
    }

    @Test
    public void getClavePIN() {
        assertEquals(AuthenticationMethod.valueOf((byte) 0), AuthenticationMethod.CLAVE_PIN);
    }

    @Test
    public void getClavePermanente() {
        assertEquals(AuthenticationMethod.valueOf((byte) 1), AuthenticationMethod.CLAVE_PERMANENTE);
    }

    @Test
    public void getCertificadoDigital() {
        assertEquals(
                AuthenticationMethod.valueOf((byte) 2), AuthenticationMethod.CERTIFICADO_DIGITAL);
    }

    @Test
    public void invalidAuthentication() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AuthenticationMethod.valueOf((byte) -1));
    }
}
