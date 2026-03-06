public class TreasureCard extends Cards {
    private final int treasureCount; // rubie value of each the card

    /**
     * Constructs a treasure card
     *
     * @param treasureCount the rubie value of the card
     */
    public TreasureCard(int treasureCount){
        if(treasureCount < 1 || treasureCount > 15) {
            throw new IllegalArgumentException("Treasure count must be between 1 and 15");
        }
        this.treasureCount = treasureCount;
    }


    /**
     * getter of the treasure card value
     * @return treasure count
     */
    public int getTreasureCount() {

        return treasureCount;
    }


    /**
     *
     * @return A string representation of the treasure card and i
     */
    @Override
    public String toString() {
        return "A treasure card with a value of " +String.valueOf(treasureCount);
    }
}
