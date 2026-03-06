import java.util.ArrayList;
import java.util.Arrays;

public class TrapCard extends Cards{
    ArrayList<String> trapTypes = new ArrayList<String>(
            Arrays.asList("Snake", "Lava pit", "Rolling boulder", "Giant spider", "Battering ram"));
    private String trapType;

    /**
     * Construsts a trap card
     * @param inTrapType type of trap
     */
    public TrapCard(String inTrapType){
        if(!trapTypes.contains(inTrapType)) {
            throw new IllegalArgumentException("Trap card must be of types Snake or Lava pit or Rolling boulders orvGiant spider or Battering rams");
        }
        this.trapType = inTrapType;
    }


    /**
     * getter for the trap type
     * @return trap type
     */
    public String getTrapType() {

        return trapType;
    }


    /**
     *
     * @return A string representation of a trap card with its trap type.
     */
    @Override
    public String toString() {
        return "A " + getTrapType() + " Trap";
    }
}
