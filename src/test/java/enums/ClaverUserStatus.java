package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaverUserStatus {

    @Test
    public void getStatusOptionsTest() {
        String expectedOutput =
                "Clave User Status: NOT_REGISTERD has byte value: "
                        + "0Clave User Status: REGISTERED_NO_REINFORCED has byte value: "
                        + "1Clave User Status: REGISTERED_REINFORCED has byte value: 2";
        assertEquals(ClaveUserStatus.getStatusOptions(), expectedOutput);
    }

    @Test
    public void getNotRegistered() {
        assertEquals(ClaveUserStatus.valueOf((byte) 0), ClaveUserStatus.NOT_REGISTERD);
    }

    @Test
    public void getRegisteredNotReinforced() {
        assertEquals(ClaveUserStatus.valueOf((byte) 1), ClaveUserStatus.REGISTERED_NO_REINFORCED);
    }

    @Test
    public void getRegisteredReinforced() {
        assertEquals(ClaveUserStatus.valueOf((byte) 2), ClaveUserStatus.REGISTERED_REINFORCED);
    }
}
