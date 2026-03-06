import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TreasureCardTests {

    @Test
    public void testTreasureCardInitialization() {
        int validTreasureCount = 10;
        TreasureCard card = new TreasureCard(validTreasureCount);
        assertEquals( validTreasureCount, card.getTreasureCount(), "Treasure count should be set to " + validTreasureCount);

        int invalidTreasureCountLessThan = -1;
        assertThrows(IllegalArgumentException.class,
                () -> new TreasureCard(invalidTreasureCountLessThan),
                "Constructor should throw IllegalArgumentException for treasure count less than 1.");


        int invalidTreasureCountGreaterThan = 16;
        assertThrows(IllegalArgumentException.class,
                () -> new TreasureCard(invalidTreasureCountGreaterThan),
                "Constructor should throw IllegalArgumentException for treasure count greater than 15.");
    }

    @Test
    public void getTreasureCountsTests(){
        int treasureCount = 5;
        TreasureCard card = new TreasureCard(treasureCount);
        int gottenTreasureCount = card.getTreasureCount();
        assertEquals(treasureCount, gottenTreasureCount, "getTreasureCount should return the same value as set in the constructor.");
    }

    @Test
    public void toStringTests(){
        int treasureCount = 15;
        TreasureCard card = new TreasureCard(treasureCount);
        String expectedString = "A treasure card with a value of "+ treasureCount;
        String gottenString = card.toString();
        assertEquals(expectedString,gottenString);
    }

}