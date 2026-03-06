

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class represents each player in the game, and their functionalities
 */
public class Player implements Comparable<Player> {
    private final String playerName;
    private int currRubieCount = 0; // rubie collected in the round
    private int chestRubies = 0; // rubies in the chest
    private int chestDiamond = 0; // diamonds in the chest
    private boolean hasRelic = false; //false if player doesn't have relic and true if they do
    int relicCount = 0; // number of relics
    private boolean continueGame = true; //choice to continue or leave game
    boolean inGame = true; // true if in the round, false if not in round


    /**
     * Constructs a new player with the given name of the player.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        playerName = name;
    }


    /**
     * getter for player name
     * @return playername
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * changes the number of rubies the player has outised their chest to zero
     */
    public void resetCurrRubieCount() {
        this.currRubieCount = 0;
    }

    /**
     * adds rubies to the players current rubie count
     * @param currRubieCount rubies to add
     */
    public void setCurrRubieCount(int currRubieCount) {
        if(currRubieCount<0){
            throw new IllegalArgumentException();
        }

        this.currRubieCount += currRubieCount;
    }

    /**
     * @return the number of rubies the player currently has outside their chest
     */
    public int getCurrRubieCount() {
        return currRubieCount;
    }

    /**
     * adds rubies to the players chest
     * @param rubies rubies to add
     */
    public void setChestRubies(int rubies) {
        if(rubies<0){
            throw new IllegalArgumentException();
        }
        this.chestRubies += rubies;
    }


    /**
     * getter for chest rubies
     * @return number of rubies in the chest
     */
    public int getChestRubies() {
        return chestRubies;
    }


    /**
     * setter for diamond chest
     * @param i the number of diamonds to add to chest
     */
    public void setChestDiamonds(int i) {
        if(i<0){
            throw new IllegalArgumentException();
        }
        chestDiamond+=i;
    }



    /**
     * setter for continue game
     * @param continueGame
     */
    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }


    /**
     * resets the players choice to continue or leave game to true
     */
    public void resetContinueGame() {
        this.continueGame = true;
    }

    /**
     * a method that makes the player randomly choose to continue or leave the game
     */
    public String chooseContinueGame(){
        Random rd = new Random();
        this.continueGame = rd.nextBoolean();
        if(this.continueGame){
            return "Yes";
        }
        else{
            return "No";
        }
    }

    /**
     * returns the player's choice to continue or leave the game
     * @return
     */
    public boolean isContinueGame() {
        return continueGame;
    }

    /**
     * set's the player's in game status
     * @param inGame true for in game and false for not in game
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * A method that makes sets the player's in game status to reflect their choice to continue or leave
     */
    public void setInGame() {
        this.inGame = this.isContinueGame();
    }

    /**
     * A function to test each player's choice to leave and adjusts their in Game status accordingly
     *
     */
    public void exitPlay() {
        if (!this.continueGame) {
            setInGame(false);
        }
    }

    /**
     * A player strategy, which is just leaving the game
     */
    public void exitGame() {
        exitPlay();

    }

    /**
     * A player strategy, which is leaving the game after the first play of cards in the round
     * @param plays number of cards played in the round
     */
    public void exitOnFirstPlay(int plays) {
        if (plays == 1) {
            exitPlay();
        }

    }

    /**
     * A player strategy, which is leaving the game after the first trap has been played in the round
     * @param trapcount number of traps played in the round
     */
    public void exitOnFirstTrap(int trapcount, boolean answer) {
        if (trapcount == 1 && answer) {
            exitPlay();
        }
    }


    public void exitOnFristTreasure(int treasureCount, boolean answer){
        if (treasureCount == 1 && answer){
            exitPlay();
        }
    }

    /**
     * A player strategy, which is leaving the game with a relic
     * @param relicOnBoard number of relics on the game board
     * @param playersLeaving number of players leaving
     * @param relicsInGame total number of relics left in the game
     * @return true if the player left with relic, false if the player didn't leave with relic
     */
    public boolean exitWithRelic(int relicOnBoard, int playersLeaving, int relicsInGame) {
        if (relicOnBoard > 0 && playersLeaving == 1) {
            exitPlay();
            hasRelic = true;
            relicCount += relicOnBoard;
            if(relicsInGame>2){
                chestDiamond += (relicCount);

            }
            else {
                chestDiamond += (2*relicCount);
            }
            return true;
        }
        return false;
    }

    /**
     * This method adds a certain number of rubies to a players chest of rubies
     *
     * @param playerExitingRubies the number of rubies to add to the chest
     */
    public void addToChest(int playerExitingRubies){
        if(playerExitingRubies<0){
            throw new IllegalArgumentException();
        }
        int chestRubieAddition = playerExitingRubies + this.getCurrRubieCount();
        this.setChestRubies(chestRubieAddition);
        this.resetCurrRubieCount();
        System.out.println(this.playerName + " added " + chestRubieAddition + " rubie(s) to their chest");

    }

    /**
     * This method returns the winner of the game from an array of players, by
     * getting the player(s) with the highest points.
     *
     * @param players an array of all the players in the game
     */
    public static void winner(Player[] players){
        ArrayList<Player> winners = new ArrayList<>();
        Arrays.sort(players);
        int maxPoints = (players[players.length-1]).finalPoints();
        for(Player player : players){
            if(player.finalPoints() == maxPoints){
                winners.add(player);
            }
        }
        for(Player player : winners){
            System.out.println(player.playerName + " ");
        }
    }

    /**
     * This method calculates the final points of the player based on their rubies
     * and diamonds.
     *
     * @return the total points of the player
     */
    public int finalPoints() {
        int points = 0;
        points += chestRubies;
        points += chestDiamond*5;
        return points;
    }


    /**
     * A string representation of the player and their possessions
     *
     */
    public void playerStatus(){
        System.out.println( "Player: " + playerName +"\n" +

                            "Chest: " + chestRubies + " rubie(s) and " + chestDiamond +" diamond(s)\n" +
                            "Relics: "+ relicCount);

    }

    /**
     *
     * This method compares this players final points with another player's.
     *
     * @param p a player to compare final points with.
     * @return a negative integer if this player's points are less than that of the other player,
     * zero if this player's points are equal to that of the other player and a positive integer
     * if this player's points are greater than that of the other player.
     */
    public int compareTo(Player p) {
        if (this.finalPoints() != p.finalPoints()) {
            return this.finalPoints() - p.finalPoints();
        }
        return 0;
    }

    /**
     * This method allows the player to swap out 5 rubies from their chest with 1 diamond from
     * the game.
     *
     * @return true if they swapped, false if they didn't.
     */
    public boolean swapRubiesForDiamond(){
        if(this.chestRubies<5){
            return false;
        }
        Random rd = new Random();
        boolean choice = rd.nextBoolean();

        if(choice) {
            chestDiamond += 1;
            chestRubies -= 5;
        }
        return choice;
    }


    /**
     * Provides a string representation of the player
     *
     * @return name of the player.
     */
    @Override
    public String toString() {
        return this.playerName;
    }

    public boolean isInGame() {
        return inGame;
    }



}
