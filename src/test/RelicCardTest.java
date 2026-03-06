import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelicCardTest {
    @Test
    public void toStringTest(){
        RelicCard card = new RelicCard();
        assertEquals("A Relic Card",card.toString(),"Should return the correct string representation of a relic card");

    }
}
