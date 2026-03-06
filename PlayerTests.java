package upei.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PlayerTests {


    @Test
    public void testPlayerInitialization() {
        Player player = new Player("TestPlayer");
        assertEquals("TestPlayer", player.getPlayerName(), "Player name should be set correctly by constructor.");
    }

    @Test
    void CurrRubieCountTest() {
        Player player = new Player("TestPlayer");
        player.setCurrRubieCount(5);
        assertEquals(5, player.getCurrRubieCount(), "Should add 5 rubies.");
        assertThrows(IllegalArgumentException.class, () -> player.setCurrRubieCount(-2),
                "Cannot add negative amount to rubie count");
    }

    @Test
    public void resetCurrRubieCountTest() {
        Player player = new Player("TestPlayer");
        player.setCurrRubieCount(10);
        assertEquals(10, player.getCurrRubieCount(), "resetCurrRubieCount should reset rubie count to zero.");
        player.resetCurrRubieCount();
        assertEquals(0, player.getCurrRubieCount(), "resetCurrRubieCount should reset rubie count to zero.");
    }

    @Test
    public void addToChestTest() {
        Player player = new Player("TestPlayer");
        player.setCurrRubieCount(5);
        player.addToChest(0);
        assertEquals(5, player.getChestRubies(), "addToChest should add the current rubie count to chest.");
        assertEquals(0, player.getCurrRubieCount(), "addToChest should rest the players current rubie count.");

        assertThrows(IllegalArgumentException.class, () -> player.addToChest(-2),
                "Cannot add negative amount to chest");



    }

    @Test
    public void chooseContinueGameTest() {
        Player player = new Player("TestPlayer");
        String choice = player.chooseContinueGame();
        assertTrue(choice.equals("Yes") || choice.equals("No"),
                "chooseContinueGame should return 'Yes' or 'No'.");
    }


    @Test
    void setChestRubiesTest() {
        Player player = new Player("TestPlayer");
        player.setChestRubies(10);
        assertEquals(10, player.getChestRubies(), "Should have 10 rubies.");

        assertThrows(IllegalArgumentException.class, () -> player.setChestRubies(-3),
                "Cannot add negative amount of rubies");
    }

    @Test
    void resetContinueGameTest() {
        Player player = new Player("TestPlayer");
        player.resetContinueGame();
        assertTrue(player.isContinueGame(), "resetContinueGame should reset continueGame to true.");
    }

    @Test
    void setInGameTest() {
        Player player = new Player("TestPlayer");
        player.setInGame(false);
        assertFalse(player.isInGame(), "setInGame should update the in-game status correctly, to false.");
        player.setInGame(true);
        assertTrue(player.isInGame(), "setInGame should update the in-game status correctly, to true.");
    }

    @Test
    void exitGameTest() {
        Player player = new Player("TestPlayer");
        player.exitGame();
        assertTrue(player.isInGame(), "exitGame should set inGame to false.");
    }

    @Test
    void exitOnFirstPlayTest() {
        Player playera = new Player("TestPlayera");
        Player playerb = new Player("TestPlayerb");
        playera.exitOnFirstPlay(1);
        assertTrue(playera.isInGame(), "exitOnFirstPlay should set inGame to false on the first play.");
        playerb.exitOnFirstPlay(5);
        assertTrue(playerb.isInGame(), "exitOnFirstPlay should set inGame to false only on first play.");
    }

    @Test
    void exitWithRelicTest() {
        Player player = new Player("TestPlayer");
        player.setContinueGame(false);
        boolean result = player.exitWithRelic(1, 1, 5);
        assertTrue(result, "exitWithRelic should return true only when conditions are met. ");
        assertTrue(player.relicCount>0, "Player should have a relic after exiting with one.");


        Player playerb = new Player("TestPlayerb");
        playerb.setContinueGame(false);
        boolean resultb = player.exitWithRelic(1, 1, 2);
        assertTrue(resultb, "exitWithRelic should return true only when conditions are met. ");
        assertTrue(player.relicCount>0, "Player should have a relic after exiting with one.");


        Player playerc = new Player("TestPlayerb");
        playerc.setContinueGame(false);
        boolean resultc = player.exitWithRelic(0, 5, 2);
        assertFalse(resultc, "exitWithRelic should return true only when conditions are met. ");
        assertTrue(playerc.relicCount ==0, "Player should not have a relic.");

    }

    @Test
    void finalPointsTest() {
        Player player = new Player("TestPlayer");
        player.setChestRubies(10);
        player.setChestDiamonds(2);
        assertEquals(20, player.finalPoints(), "finalPoints should return the correct total points.");

        Player playerb = new Player("TestPlayer");
        playerb.setChestRubies(0);
        playerb.setChestDiamonds(0);
        assertEquals(0, playerb.finalPoints(), "finalPoints should return the correct total points.");
    }

    @Test
    public void toStringTest() {
        Player player = new Player("TestPlayer");
        assertEquals("TestPlayer", player.toString(), "toString should return the player's name.");
    }

    @Test
    void compareToTest() {
        Player player = new Player("TestPlayer");
        Player otherPlayer = new Player("OtherPlayer");
        player.setChestRubies(5);
        otherPlayer.setChestRubies(10);
        assertTrue(player.compareTo(otherPlayer) < 0, "compareTo should return a negative integer when this player has fewer points.");


        Player playerb= new Player("TestPlayerb");
        Player otherPlayerb = new Player("OtherPlayerb");
        playerb.setChestRubies(4);
        otherPlayerb.setChestRubies(4);
        assertEquals(0, playerb.compareTo(otherPlayerb), "compareTo should return a zero when they have the same points.");


        Player playerc = new Player("TestPlayerc");
        Player otherPlayerc = new Player("OtherPlayerc");
        playerc.setChestRubies(15);
        otherPlayerc.setChestRubies(10);
        assertTrue(playerc.compareTo(otherPlayerc) > 0, "compareTo should return a positive integer when this player has more points.");
    }
}
