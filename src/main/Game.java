import java.util.*;
public class Game {

    /**
     * The class represents the parts and processes of the actual game. It handles the initialization
     * of the game, and includes rounds and interactions between the players and cards
     */

    private int rounds = 1; // the rounds of the game
    private int rubiesInGame = 100;
    private int diamondsInGame = 100;
    int leftOverRubies = 0; // rubies left on the board after sharing to players
    private int relicsInGame = 5; // total number of relics left in the game
    boolean endOfRound = false; //true if the round is complete , false if it's not
    private final int noOfPlayers; // number of players in the game
    Player[] players; // array to hold the players
    ArrayList<Cards> board; // holds all the cards that have been played so far
    Deck deck; // the deck of cards
    private final String[] trapType = {"Snake", "Lava pit", "Rolling boulder", "Giant spider", "Battering ram"};



    public Game(int noOfPlayers) {
        if(noOfPlayers<2 || noOfPlayers>8){
            throw new IllegalArgumentException();
        }

        this.noOfPlayers = noOfPlayers;
        this.players = new Player[noOfPlayers];

        for (int i = 0; i < this.noOfPlayers; i++) {// creating the player objects
            String name = "Player " + (i + 1);
            players[i] = new Player(name);
        }
        this.board = new ArrayList<>();
        this.deck = new Deck();

    }

    /**
     * getter for the current round
     * @return rounds
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * increases the rounds by 1, symbolizing proceeding to the next round
     */
    public void setRounds() {
        this.rounds += 1;
    }

    /**
     * getter for the number of rubies left in the game
     * @return rubies in game
     */
    public int getRubiesInGame() {
        return rubiesInGame;
    }

    /**
     * adds rubies back to the game
     * @param rubies rubie to add
     */
    public void addRubiesInGame(int rubies){
        this.rubiesInGame+= rubies;
    }

    /**
     * getter for the diamonds in game
     * @return diamonds in the game
     */
    public int getDiamondsInGame() {
        return diamondsInGame;
    }



    /**
     * removes from the diamonds in the game
     * @param relicCount amount to remove
     */
    public void removeDiamonds(int relicCount) {
        diamondsInGame -= relicCount;
    }


    /**
     * getter for the number of relics left in the game
     * @return relics in game
     */
    public int getRelicsInGame(){
        return relicsInGame;
    }

    /**
     * reduces the number of relics left in the game
     * @param numOfRelics number of relicsto remove
     */
    public void removeRelicsInGame(int numOfRelics){
        this.relicsInGame -= numOfRelics;
    }

    /**
     * swaps adds 5 rubies to the game and removes 1 diamond when a player swaps rubies for a diamond
     */
    public void diamondRubieSwap(){
        this.rubiesInGame +=5;
        this.diamondsInGame -=1;
    }


    /**
     * A getter for the number of players
     * @return noOfPlayers
     */
    public int getNoOfPlayers() {
        return noOfPlayers;
    }


    /**
     * A getter for the deck
     * @return deck
     */
    public Deck getDeck() {
        return deck;
    }


    /**
     * A getter for the board
     * @return board
     */
    public ArrayList<Cards> getBoard() {
        return board;
    }

    /**
     * creates the deck of cards used at the start of the game
     */
    public void makeDeck(){
        ArrayList<Cards> tempDeck = new ArrayList<>();//a list of all the cards acting as a temporary deck of cards
        for (int i = 0; i < 15; i++) {
            tempDeck.add(new TreasureCard(i + 1));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                tempDeck.add(new TrapCard(trapType[j]));
            }
        }
        for (int i = 0; i < 5; i++) {
            tempDeck.add(new RelicCard());
        }
        Collections.shuffle(tempDeck);//shuffle the temporary list of cards
        for (Cards card : tempDeck) {
            this.deck.push(card);//this is the official deck of shuffled cards
        }
    }

    /**
     * remakes the deck with the cards currently in the deck and the cards on the board
     */
    public void remakeDeck(){
        ArrayList<Cards> tempDeck = new ArrayList<>(board);
        board.clear();
        int deckSize = deck.getLength();
        for(int i=0; i<deckSize;i++){
            tempDeck.add(deck.pop());
        }
        Collections.shuffle(tempDeck);
        for (Cards card : tempDeck){
            deck.push(card);
        }

    }

    /**
     * this picking a card from a deck. certain implementation are done depending on the type of card
     * @return the card that was picked
     */
    public Cards drawCard(){
        Cards currCard = deck.pop();//the card that is pulled from the deck

        if (currCard.getClass() == TreasureCard.class) {
            handleTreasureCard(currCard,board);

        } else if (currCard.getClass() == TrapCard.class) {
            handleTrapCard(currCard,board);

        } else {//if the card is a relic
            handleRelicCard(currCard,board);

        }
        return currCard;
    }

    /**
     * when a treasure card is picked, its added to the board and rubies are taken from the game and divided amongst
     * the players in the game.
     * @param currCard card picked
     * @param board board
     */
    public void handleTreasureCard(Cards currCard, ArrayList<Cards> board){
        board.add(currCard);//the card if placed on the deck if it's a treasure card
        System.out.printf(currCard.toString() + " was pulled from the deck and placed on the game board.\n");
        System.out.println("");

        int rubieCount = ((TreasureCard) currCard).getTreasureCount();
        if(rubieCount>=getPlayersNamesInGame().size()) {
            System.out.println(rubieCount + " rubie(s) will be shared to player(s): " + this.getPlayersNamesInGame());
            int playerShare = rubieCount / this.getPlayersNamesInGame().size();
            for (Player player : this.getPlayersObjInGame()) {
                if (this.rubiesInGame >= rubieCount) {
                    player.setCurrRubieCount(playerShare);
                    System.out.println(player.getPlayerName() + " recieved " + playerShare + " rubie(s)");
                }
            }
            System.out.println("");
            int remaining = rubieCount - (playerShare * this.getPlayersNamesInGame().size());
            this.leftOverRubies += remaining;
            System.out.println(remaining + " rubie(s) placed on the card on the board. "+ leftOverRubies+ " rubies" +
                    " total on the board");
            this.rubiesInGame -= rubieCount;
            System.out.println("There are " + this.rubiesInGame + " rubies left in the game");
            System.out.println("");
        }
        else {
            System.out.println("Players won't recieve any rubie");
            this.leftOverRubies += rubieCount;
            System.out.println(rubieCount + " rubie(s) placed on the game board. " + leftOverRubies + " rubie(s)" +
                    " total on the board");
            this.rubiesInGame -= rubieCount;
            System.out.println("There are " + this.rubiesInGame + " rubie(s) left in the game");
            System.out.println("");
        }
        System.out.println("");


    }

    /**
     * if a trap card is picked, it checks if a card of the same type is already on the board.
     * if there is, then the round is over. If there isn't, then the card is placed on the board
     * @param currCard card picked
     * @param board board
     */
    public void handleTrapCard(Cards currCard, ArrayList<Cards> board){
        String currTrapType = ((TrapCard) currCard).getTrapType();
        for (Cards card : board) {
            if (card.getClass() == TrapCard.class && (((TrapCard) card).getTrapType()).equals(currTrapType)) {
                endOfRound = true;
                System.out.println("Uh Oh! " + currCard.toString() + " was pulled from the deck and is already on the board");
                System.out.println("");
                break;
            }
        }
        if (!endOfRound) {
            board.add(currCard);
            System.out.printf(currCard.toString() + " was pulled from the deck and placed on the game board. ");
            System.out.println();
            System.out.println("Everyone is safe, but if another " + currTrapType + " card is drawn, then the round is over");
            System.out.println();
        }
    }

    /**
     * if a relic card is picked, then its placed on the board, and its position is recorded
     * @param currCard card picked
     * @param board board
     */
    public void handleRelicCard(Cards currCard, ArrayList<Cards> board){
        board.add(currCard);
        System.out.printf(currCard.toString() + " was pulled from the deck and placed on the game board.");
        System.out.println("");

    }

    /**
     *
     * @return an arraylist containing the names of the players in the game
     */
    public ArrayList<String> getPlayersNamesInGame(){
        ArrayList<String> playersNamesInGame = new ArrayList<>();
        for (Player player : players) {
            if (player.inGame) {
                playersNamesInGame.add(player.getPlayerName());
            }
        }
        return playersNamesInGame;
    }

    /**
     *
     * @return an arraylist of players in the game
     */
    public ArrayList<Player> getPlayersObjInGame(){
        ArrayList<Player> playersObjInGame = new ArrayList<>();
        for (Player player : players) {
            if (player.inGame) {
                playersObjInGame.add(player);
            }
        }
        return playersObjInGame;
    }

    /**
     * removes the relics from the board
     */
    public void removeRelicFromBoard() {
        board.removeIf(card -> card.getClass() == RelicCard.class);

    }

    /**
     * returns all the rubies left over back to the game and remakes the deck
     */
    public void resetBoard(){
        rubiesInGame += leftOverRubies;
        System.out.println("The rubies on the game cards are returned.");


        this.remakeDeck();
        System.out.println("The game cards are returned to the deck");
        System.out.println("");
    }


}
