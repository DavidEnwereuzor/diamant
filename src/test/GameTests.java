import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @Test
    public void testGameInitialization() {
        int numberOfPlayers = 4;
        Game game = new Game(numberOfPlayers);

        assertNotNull(game, "Game should be initialized");
        assertEquals(numberOfPlayers, game.getNoOfPlayers(), "Should initialize correct number of players");
        assertNotNull(game.getDeck(), "Deck should be initialized");
        assertNotNull(game.getBoard(), "Board should be initialized");

        int numberOfPlayersb = 9;
        assertThrows(IllegalArgumentException.class, () -> new Game(numberOfPlayersb),
                "Cannot make a game with more than 8 players");

        int numberOfPlayersc = 1;
        assertThrows(IllegalArgumentException.class, () -> new Game(numberOfPlayersc),
                "Cannot make a game with less than players");
    }

    @Test
    public void setRoundsTest() {
        Game game = new Game(4);
        assertEquals(1, game.getRounds(), "Initial round should be 1");
        game.setRounds();
        assertEquals(2, game.getRounds(), "Round should increment to 2");
    }

    @Test
    public void rubiesInGameTest() {
        Game game = new Game(4);
        int initialRubies = game.getRubiesInGame();
        assertEquals(100,initialRubies, "Every game starts with 100 rubies");
        int addedRubies = 10;
        game.addRubiesInGame(addedRubies);

        assertEquals(initialRubies + addedRubies, game.getRubiesInGame(), "rubies in game should be 110");
    }


    @Test
    public void relicsInGameTest() {
        Game game = new Game(4);
        int initialRelics = game.getRelicsInGame();
        assertEquals(5,initialRelics, "Every game starts with 5 relics");
        game.removeRelicsInGame(1);
        assertEquals(initialRelics - 1, game.getRelicsInGame(), "Number of relics should be 4");

    }

    @Test
    public void makeDeckTest() {
        Game game = new Game(3);
        game.makeDeck();
        Deck deck = game.getDeck();
        assertNotNull(deck, "Deck should not be null after creation.");
        int deckSize = deck.getLength();
        assertEquals(35, deckSize, " Deck should have 35 cards");
    }

    @Test
    public void remakeDeckTest() {
        Game game = new Game(4);

        game.makeDeck();
        Deck deck = game.getDeck();
        ArrayList<Cards> board = game.getBoard();
        game.drawCard();
        game.drawCard();
        int boardSizeAfterDraw = board.size();
        int deckSizeAfterDraw = deck.getLength();
        int newDeckSize = boardSizeAfterDraw+deckSizeAfterDraw;

        game.remakeDeck();


        assertEquals(newDeckSize, deck.getLength(), "Deck should add the cards on the board");
        assertTrue(game.getBoard().isEmpty(), "Board should be empty after remaking the deck");
    }

    @Test
    public void drawCardTest() {
        Game game = new Game(2);
        game.makeDeck();
        Deck deck = game.getDeck();
        Cards drawnCard = game.drawCard();
        assertNotNull(drawnCard, "Drawn card should not be null");

        int deckSize = deck.getLength();
        assertEquals(34, deckSize, "It should take remove 1 card");
    }

    @Test
    public void handleTreasureCardTest() {
        Game game = new Game(8);
        Cards treasureCard = new TreasureCard(10);
        ArrayList<Cards> board = new ArrayList<>();

        game.handleTreasureCard(treasureCard, board);
        assertTrue(board.contains(treasureCard), "Board should contain the treasure card after handling");
    }

    @Test
    public void handleTrapCardTest() {
        Game game = new Game(8);
        Cards trapCard = new TrapCard("Lava pit");
        ArrayList<Cards> board = new ArrayList<>();

        game.handleTrapCard(trapCard, board);
        assertTrue(board.contains(trapCard), "Board should contain the treasure card after handling");
    }

    @Test
    public void handleRelicCardTest() {
        Game game = new Game(8);
        Cards relicCard = new RelicCard();
        ArrayList<Cards> board = new ArrayList<>();

        game.handleTreasureCard(relicCard, board);
        assertTrue(board.contains(relicCard), "Board should contain the treasure card after handling");
    }

    @Test
    public void resetBoardTest() {
        Game game = new Game(6);
        game.makeDeck();
        game.drawCard(); // Draw a card to change board state
        assertEquals(1,game.board.size(),"Drawing a card should add 1 card to the board");
        game.resetBoard();
        assertTrue(game.getBoard().isEmpty(), "Board should be empty after reset");
    }


}
