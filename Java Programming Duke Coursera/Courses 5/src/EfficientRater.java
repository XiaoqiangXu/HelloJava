import javax.sql.rowset.serial.SerialStruct;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;

/**
 * Created by Xiaoqiang on 2017/2/23.
 */
public class EfficientRater implements Rater {

    private String myID;
    private HashMap<String,Double> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,rating);
    }

    public boolean hasRating(String item) {
        if (myRatings.keySet().contains(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (hasRating(item)){
            return myRatings.get(item);
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();
        for (String item:myRatings.keySet()){
            list.add(item);
        }
        return list;
    }
}
