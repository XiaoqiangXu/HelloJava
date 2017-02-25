import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Xiaoqiang on 2017/2/24.
 */
public class FourthRatings {

    public FourthRatings() {
        // default constructor
        this("ratings.csv");
//        MovieDatabase.initialize("ratedmoviesfull.csv");
    }

    public FourthRatings(String ratingsfile){
//        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize(ratingsfile);
    }

    private double getAverageByID(String id,int minimalRaters){
        int numOfRaters = 0;
        double sumOfRating = 0;
        for (Rater rater:RaterDatabase.getRaters()){
            ArrayList<String> movieId = rater.getItemsRated();
            if (movieId.contains(id)){
                numOfRaters++;
                sumOfRating=sumOfRating+rater.getRating(id);
            }
        }
        if (numOfRaters>=minimalRaters){
            return sumOfRating/numOfRaters;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> result = new ArrayList<>();
        for (int i=0;i<movies.size();i++){
            String currMovieID = movies.get(i);
            double currAverageByID = getAverageByID(currMovieID,minimalRaters);
            result.add(new Rating(currMovieID,currAverageByID));
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

    private double dotProduct(Rater me,Rater r){
        double result = 0.0;
        for (String movieID:me.getItemsRated()){
            if (r.hasRating(movieID)){
                result = result+(me.getRating(movieID)-5.0)*(r.getRating(movieID)-5.0);
            }
        }
        return result;
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> result = new ArrayList<>();
        for (Rater rater:RaterDatabase.getRaters()){
            if (!rater.getID().equals(id)){
                if (dotProduct(RaterDatabase.getRater(id),rater)>0){
                    result.add(new Rating(rater.getID(),dotProduct(RaterDatabase.getRater(id),rater)));
                }
            }
        }
        Collections.sort(result,Collections.reverseOrder());
        return result;
    }

    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> similarities = getSimilarities(id);
        for (String movieID:movies){
            int numOfRaters = 0;
            double score = 0.0;
            for (int i=0;i<numSimilarRaters&&i<similarities.size();i++){
                String raterID = similarities.get(i).getItem();
                double raterIDSimilarities = similarities.get(i).getValue();
                Rater rater = RaterDatabase.getRater(raterID);
                if (rater.hasRating(movieID)){
                    numOfRaters++;
                    score = score + rater.getRating(movieID)*raterIDSimilarities;
                }
            }
            if (numOfRaters>=minimalRaters){
                result.add(new Rating(movieID,score/numOfRaters));
            }
        }
        Collections.sort(result,Collections.reverseOrder());
        return result;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> similarities = getSimilarities(id);
        for (String movieID:movies){
            int numOfRaters = 0;
            double score = 0.0;
            for (int i=0;i<numSimilarRaters;i++){
                String raterID = similarities.get(i).getItem();
                double raterIDSimilarities = similarities.get(i).getValue();
                Rater rater = RaterDatabase.getRater(raterID);
                if (rater.hasRating(movieID)){
                    numOfRaters++;
                    score = score + rater.getRating(movieID)*raterIDSimilarities;
                }
            }
            if (numOfRaters>=minimalRaters){
                result.add(new Rating(movieID,score/numOfRaters));
            }
        }
        Collections.sort(result,Collections.reverseOrder());
        return result;
    }


}









