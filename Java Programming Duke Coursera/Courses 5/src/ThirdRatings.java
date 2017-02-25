import java.util.ArrayList;

/**
 * Created by Xiaoqiang on 2017/2/24.
 */
public class ThirdRatings {

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
//        MovieDatabase.initialize("ratedmoviesfull.csv");
    }

    public ThirdRatings(String ratingsfile){
//        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize(ratingsfile);
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> result = new ArrayList<>();
        for (int i=0;i<movies.size();i++){
            String currMovieID = movies.get(i);
            int numOfRaters = 0;
            double sumOfRating = 0;
            for (int j=0;j<myRaters.size();j++){
                if (myRaters.get(j).hasRating(currMovieID)){
                    numOfRaters++;
                    sumOfRating = sumOfRating+myRaters.get(j).getRating(currMovieID);
                }
            }
            if (numOfRaters>=minimalRaters){
                result.add(new Rating(currMovieID,sumOfRating/numOfRaters));
            }
        }
        return result;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<Rating> result = new ArrayList<>();
        for (int i=0;i<movies.size();i++){
            String currMovieID = movies.get(i);
            int numOfRaters = 0;
            double sumOfRating = 0;
            for (int j=0;j<myRaters.size();j++){
                if (myRaters.get(j).hasRating(currMovieID)){
                    numOfRaters++;
                    sumOfRating = sumOfRating+myRaters.get(j).getRating(currMovieID);
                }
            }
            if (numOfRaters>=minimalRaters){
                result.add(new Rating(currMovieID,sumOfRating/numOfRaters));
            }
        }
        return result;
    }
}
