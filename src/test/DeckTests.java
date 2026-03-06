
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTests {

    @Test
    public void deckInitializationTest() {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty(), "A new deck should be empty.");
    }

    @Test
    public void pushCardTest() {
        Deck deck = new Deck();
        Cards card = new TreasureCard(5);
        assertTrue(deck.isEmpty(), "A new deck should be empty.");
        deck.push(card);
        assertFalse(deck.isEmpty(), "Deck should not be empty after pushing a card.");
        assertEquals(1, deck.getLength(), "Deck length should be 1 after pushing a card.");
    }

    @Test
    public void popCardTest() {
        Deck deck = new Deck();
        Cards card = new TreasureCard(5);
        deck.push(card);
        Cards poppedCard = deck.pop();
        assertTrue(deck.isEmpty(), "Deck should be empty after popping the only card.");
        assertEquals(card, poppedCard, "Popped card should be the same as the pushed card.");
    }

    @Test
    public void popEmptyDeckTest() {
        Deck deck = new Deck();
        assertThrows(IllegalStateException.class, () -> deck.pop(),
                "Pop method should throw IllegalStateException on an empty deck.");
    }

    @Test
    public void getLengthTest() {
        Deck deck = new Deck();
        assertEquals(0, deck.getLength(), "Length of a new deck should be 0.");
        deck.push(new TreasureCard(5));
        deck.push(new TreasureCard(10));
        assertEquals(2, deck.getLength(), "Length should be 2 after pushing two cards.");
    }

}