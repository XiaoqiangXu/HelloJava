import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Xiaoqiang on 2017/2/21.
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        System.out.println("Number of movies:"+secondRatings.getMovieSize());
        System.out.println("Number of raters:"+secondRatings.getRaterSize());
        ArrayList<Rating> averageRating = secondRatings.getAverageRatings(12);
        Collections.sort(averageRating);
        System.out.println("Number of movies have over 50 ratings " +averageRating.size());
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+secondRatings.getTitle(rating.getItem()));
        }
    }
    public void getAverageRatingOneMovie(){
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        String title = "Vacation";
        String id  = secondRatings.getID(title);
        ArrayList<Rating> averageRating = secondRatings.getAverageRatings(3);
        System.out.print(title+" is rated: ");
        for (int i=0;i<averageRating.size();i++){
            if (id.equals(averageRating.get(i).getItem())){
                System.out.println(averageRating.get(i).getValue());
            }
        }

    }
    public static void main(String[] args){
        MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
        movieRunnerAverage.printAverageRatings();
        movieRunnerAverage.getAverageRatingOneMovie();
    }

}
