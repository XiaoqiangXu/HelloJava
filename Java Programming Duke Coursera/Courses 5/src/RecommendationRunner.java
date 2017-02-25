import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/2/25.
 */
public class RecommendationRunner implements  Recommender {

    ArrayList<String> ratedMoviesID;

    public RecommendationRunner(){
        ratedMoviesID = new ArrayList<>();
    }

    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movisID = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> result = new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<15;i++){
            result.add(movisID.get(random.nextInt(movisID.size())));
        }
        ratedMoviesID = result;
        return result;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        ArrayList<Rating> averageRating = fourthRatings.getSimilarRatings(webRaterID,20,5);
        ArrayList<Rating> finalAverageRating = new ArrayList<>();
        int numOfRecommendMovies = 0;
        for (Rating rating:averageRating){
            if ((!ratedMoviesID.contains(rating.getItem()))&&numOfRecommendMovies<15){
                finalAverageRating.add(rating);
                numOfRecommendMovies++;
            }
//            if (numOfRecommendMovies<15){
//                finalAverageRating.add(rating);
//                numOfRecommendMovies++;
//            }
        }
        System.out.println("Found "+finalAverageRating.size()+" movies");
        for (Rating rating:finalAverageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
}
