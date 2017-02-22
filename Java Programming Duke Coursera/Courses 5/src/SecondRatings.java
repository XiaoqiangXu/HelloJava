
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private double getAverageRatings(String id,int minimalRaters){
        int numOfRaters = 0;
        double sumOfRating = 0;
        for (int i=0;i<myRaters.size();i++){
            ArrayList<String> movieId = myRaters.get(i).getItemsRated();
            if (movieId.contains(id)){
                numOfRaters++;
                sumOfRating=sumOfRating+myRaters.get(i).getRating(id);
            }
        }
        if (numOfRaters>=minimalRaters){
            return sumOfRating/numOfRaters;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<String> uniqueMovieIDs = new ArrayList<>();
        for (int i=0;i<myRaters.size();i++){
            ArrayList<String> movieId=  myRaters.get(i).getItemsRated();
            for (int j=0;j<movieId.size();j++){
                if (!uniqueMovieIDs.contains(movieId.get(j))){
                    uniqueMovieIDs.add(movieId.get(j));
                }
            }
        }
        for (int i=0;i<uniqueMovieIDs.size();i++){
            double averageRatings = getAverageRatings(uniqueMovieIDs.get(i),minimalRaters);
            if (averageRatings>0){
                Rating currRating = new Rating(uniqueMovieIDs.get(i),averageRatings);
                result.add(currRating);
            }
        }
        return result;
    }

    public String getTitle(String id){
        for (int i=0;i<myMovies.size();i++){
            if (myMovies.get(i).getID().equals(id)){
                return myMovies.get(i).getTitle();
            }
        }
        return null;
    }

    public String getID(String title){
        for (int i=0;i<myMovies.size();i++){
            if (title.equals(myMovies.get(i).getTitle())){
                return myMovies.get(i).getID();
            }
        }
        return "NO SUCH TITLE";
    }


}
