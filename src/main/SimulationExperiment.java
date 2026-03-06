import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SimulationExperiment {
    /**
     * This class is the simulation of the game Diamant
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args){
        Random rd = new Random();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Diamant!");
        System.out.println("How many players?");
        int noOfPlayers = rd.nextInt(7);
        Game game = new Game((2));

        System.out.println("Our players are: "+ Arrays.toString(game.players));
        game.makeDeck();

        System.out.println("Let's Begin...");
        System.out.println("");

        do{
            System.out.println("Round " + game.getRounds() + " begins");
            System.out.println("");

            for (Player player: game.players){
                player.resetContinueGame();
                player.setInGame();
                player.playerStatus();
                System.out.println();
            }
            game.endOfRound = false;

            int playCount = 0;//tracks the number of cards played in each round
            int trapCount = 0;//tracks the number of traps played in each round
            int relicCount = 0;//tracks the number of relics played in each round
            int treasureCount = 0;//tracks the number of treasure cards played in each round

            while (!game.endOfRound){


                if (game.getPlayersObjInGame().size() > 0) {
                    if(playCount>1) {
                        System.out.print("Players in the game: ");
                        for (String player : game.getPlayersNamesInGame()) {
                            System.out.print(player + " ");
                        }
                    }

                    System.out.println("");
                    System.out.println("");

                    System.out.println("Rubies in the game: "+ game.getRubiesInGame());
                    System.out.println("Diamonds in the game: "+ game.getDiamondsInGame());
                    System.out.println();

                    Cards cardDrawn = game.drawCard();
                    playCount++;
                    if (cardDrawn.getClass() == TrapCard.class) {
                        trapCount++;
                    } else if (cardDrawn.getClass() == RelicCard.class) {
                        relicCount++;
                    }
                    else {
                        treasureCount++;
                    }
                }
                else {
                    System.out.println("All the players have left.");

                    game.endOfRound = true;
                }
                if (!game.endOfRound) {
                    System.out.println("");
                    System.out.println("Round " + game.getRounds() + " continues...");
                    System.out.println("");

                    for (Player player : game.getPlayersObjInGame()) {
                        if (player.swapRubiesForDiamond()) {
                            game.diamondRubieSwap();
                            System.out.println(player.getPlayerName() + " swapped 5 rubies for 1 diamond.");
                            System.out.println("There are "+ game.getDiamondsInGame()+ " diamond(s) left in the game");
                            System.out.println("There are "+ game.getRubiesInGame()+ " rubie(s) left in the game");
                            System.out.println();
                        }
                    }

                    ArrayList<Player> playersLeaving = new ArrayList<>();
                    ArrayList<Player> playersStaying = new ArrayList<>();
                    System.out.println("Do you want to continue the game?");
                    System.out.println("");


                    for (Player player : game.getPlayersObjInGame()) {
                        System.out.println(player.getPlayerName()+ ": " + player.chooseContinueGame());
                        if (!player.isContinueGame()) {//player says no
                            playersLeaving.add(player);
                        }
                        else {
                            playersStaying.add(player);
                        }
                    }

                    System.out.println("Players leaving: "+ playersLeaving);
                    System.out.println("Players staying: "+ playersStaying);
                    System.out.println("");

                    int playerExitingRubies;
                    if(!playersLeaving.isEmpty()) {

                        playerExitingRubies = (int) game.leftOverRubies / playersLeaving.size();
                        System.out.println("Each player takes " + playerExitingRubies + " rubie(s) from the game board");
                        System.out.println("");


                        for (Player player : playersLeaving) {
                            player.addToChest(playerExitingRubies);

                            boolean leavesWithRelic = player.exitWithRelic(relicCount, playersLeaving.size(), game.getRelicsInGame());
                            if (leavesWithRelic) {
                                game.removeRelicFromBoard();
                                game.removeDiamonds(relicCount);
                                player.relicCount += relicCount;
                                System.out.println(player.getPlayerName() + " left with " + relicCount + " relic card(s) and "+(relicCount)+" diamond(s)");
                                game.removeRelicsInGame(relicCount);

                                relicCount = 0;

                            }
                            else {
                                if (playCount == 1) {
                                    player.exitOnFirstPlay(playCount);
                                    System.out.println(player.getPlayerName()+" left after the first draw of cards");
                                } else if (trapCount == 1) {
                                    player.exitOnFirstTrap(trapCount, (game.board.get((game.board.size()-1)).getClass() == TrapCard.class));
                                    System.out.println(player.getPlayerName()+" left after the first trap was drawn");
                                }
                                else if(treasureCount ==1) {
                                    player.exitOnFristTreasure(treasureCount, (game.board.get((game.board.size()-1)).getClass() == TreasureCard.class));
                                    System.out.println(player.getPlayerName()+ " left after the first treasure card was drawn");
                                }else {
                                    player.exitGame();
                                }
                            }
                        }
                        System.out.println("");
                        game.leftOverRubies -= playerExitingRubies * playersLeaving.size();
                        if(!playersStaying.isEmpty()){
                            System.out.println(game.leftOverRubies + " rubie(s) are left on the board");
                        }
                        System.out.println("");

                        if(!playersStaying.isEmpty()) {
                            System.out.println("Let's continue the game");
                            System.out.println();
                        }
                    }
                    else {
                        System.out.println("No player is leaving. Let's continue");
                        System.out.println("");
                    }
                }

                else {
                    System.out.println("Round " + game.getRounds() + " is over");
                    System.out.println("");

                    game.resetBoard();

                    if(game.getPlayersNamesInGame().size()>0) {
                        for (Player player : game.getPlayersObjInGame()) {
                            game.addRubiesInGame(player.getCurrRubieCount());
                            player.resetCurrRubieCount();
                        }
                        System.out.println("Players, " + game.getPlayersNamesInGame() + " return their current rubies.");
                        System.out.println("");
                    }

                    game.setRounds();
                }
            }
        } while(game.getRounds()<6);

        System.out.println("The game is complete");
        System.out.println("");

        for (Player player: game.players){
            player.playerStatus();
            System.out.println();
        }

        for (Player player: game.players){
            int finalPoints = player.finalPoints();
            System.out.println(player+ " has "+ finalPoints + " points");
        }

        System.out.println("The winner of Diamant is...");
        Player.winner(game.players);
        System.out.println("!!!");

    }

}
