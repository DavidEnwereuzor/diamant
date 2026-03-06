
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TrapCardTests {
    @Test
    public void testTrapCardInitialization() {
        String trapTypeA = "Snake";
        TrapCard cardA = new TrapCard(trapTypeA);
        assertEquals(trapTypeA, cardA.getTrapType(), "Constructor should set the trap type correctly.");

        String trapTypeB = "Fire";
        assertThrows(IllegalArgumentException.class,
                () -> new TrapCard(trapTypeB),
                "Constructor should throw IllegalArgumentException for invalid trap type.");
    }

    @Test
    public void getTrapTypeTests() {

        String trapType = "Lava pit";
        TrapCard card = new TrapCard(trapType);
        String returnedType = card.getTrapType();

        assertEquals(trapType, returnedType,
                "getTrapType should return the same type as set in the constructor.");
    }

    @Test
    public void toStringTestsg() {

        String trapType = "Rolling boulder";
        TrapCard card = new TrapCard(trapType);
        String expectedString = "A " + trapType + " Trap";
        String gottenString = card.toString();

        assertEquals(expectedString, gottenString,
                "toString should return the correct string representation of the trap card.");
    }
}
