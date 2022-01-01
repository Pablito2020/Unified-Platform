package enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum AuthenticationMethod {
    CLAVE_PIN((byte) 0),
    CLAVE_PERMANENTE((byte) 1),
    CERTIFICADO_DIGITAL((byte) 2);

    private final byte value;

    AuthenticationMethod(byte value) {
        this.value = value;
    }

    public static String getAuthOptions() {
        return Arrays.stream(AuthenticationMethod.values())
                .map(AuthenticationMethod::toString)
                .collect(Collectors.joining());
    }

    public static AuthenticationMethod valueOf(byte value) {
        for (AuthenticationMethod method : AuthenticationMethod.values()) {
            if (method.value == value) return method;
        }
        throw new IllegalArgumentException(value + " isn't an AuthenticationMethod");
    }

    @Override
    public String toString() {
        return "Authentication Method: " + name() + " has byte value: " + value;
    }
}
