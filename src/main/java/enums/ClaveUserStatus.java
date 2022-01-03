package enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ClaveUserStatus {
    NOT_REGISTERD((byte) 0),
    REGISTERED_NO_REINFORCED((byte) 1),
    REGISTERED_REINFORCED((byte) 2);


    private final byte value;

    ClaveUserStatus(byte value){
        this.value = value;
    }

    public static String getStatusOptions() {
        return Arrays.stream(ClaveUserStatus.values())
                .map(ClaveUserStatus::toString)
                .collect(Collectors.joining());
    }

    public static ClaveUserStatus valueOf(byte value) {
        for (ClaveUserStatus status : ClaveUserStatus.values()) {
            if (status.value == value) return status;
        }
        throw new IllegalArgumentException(value + " isn't a ClaveUserStatus");
    }

    @Override
    public String toString() {
        return "Clave User Status: " + name() + " has byte value: " + value;
    }
}
