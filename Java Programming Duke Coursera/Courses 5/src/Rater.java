import java.util.ArrayList;

/**
 * Created by Xiaoqiang on 2017/2/23.
 */
public interface Rater {

    public void addRating(String item, double rating);

    public boolean hasRating(String item) ;

    public String getID();

    public double getRating(String item) ;

    public int numRatings();

    public ArrayList<String> getItemsRated() ;

}
