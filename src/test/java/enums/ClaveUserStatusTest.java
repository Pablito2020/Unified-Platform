package enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClaveUserStatusTest {

    @Test
    public void getStatusOptionsTest() {
        String expectedOutput =
                "Clave User Status: NOT_REGISTERED has byte value: "
                        + "0Clave User Status: REGISTERED_NO_REINFORCED has byte value: "
                        + "1Clave User Status: REGISTERED_REINFORCED has byte value: 2";
        Assertions.assertEquals(ClaveUserStatus.getStatusOptions(), expectedOutput);
    }

    @Test
    public void getNotRegistered() {
        Assertions.assertEquals(
                ClaveUserStatus.valueOf((byte) 0), ClaveUserStatus.NOT_REGISTERED);
    }

    @Test
    public void getRegisteredNotReinforced() {
        Assertions.assertEquals(
                ClaveUserStatus.valueOf((byte) 1),
                ClaveUserStatus.REGISTERED_NO_REINFORCED);
    }

    @Test
    public void getRegisteredReinforced() {
        Assertions.assertEquals(
                ClaveUserStatus.valueOf((byte) 2), ClaveUserStatus.REGISTERED_REINFORCED);
    }

    @Test
    public void invalidClaveUser() {
        assertThrows(IllegalArgumentException.class, () -> ClaveUserStatus.valueOf((byte) -1));
    }
}
