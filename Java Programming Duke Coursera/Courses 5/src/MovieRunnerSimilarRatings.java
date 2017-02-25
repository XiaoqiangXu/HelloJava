import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Xiaoqiang on 2017/2/24.
 */
public class MovieRunnerSimilarRatings {

    public void printAverageRatings(){

        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        ArrayList<Rating> averageRating = fourthRatings.getAverageRatings(35);
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre (){

        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> averageRating = fourthRatings.getAverageRatingsByFilter(8,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatings(){

        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        ArrayList<Rating> averageRating = fourthRatings.getSimilarRatings("71",20,5);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre (){
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
//        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new GenreFilter("Mystery"));
        ArrayList<Rating> averageRating = fourthRatings.getSimilarRatingsByFilter("964",20,5,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector  (){
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
//        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        ArrayList<Rating> averageRating = fourthRatings.getSimilarRatingsByFilter("120",10,2,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes  (){
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(80,160));
        filter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> averageRating = fourthRatings.getSimilarRatingsByFilter("168",10,3,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes (){
        FourthRatings fourthRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(70,200));
        filter.addFilter(new YearAfterFilter(1975));
        ArrayList<Rating> averageRating = fourthRatings.getSimilarRatingsByFilter("314",10,5,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public static void main(String[] args){
        double begin = System.nanoTime();
        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
//        double begin = System.nanoTime();
        movieRunnerSimilarRatings.printSimilarRatings();
        double end = System.nanoTime();
        double stime = (end-begin)/1e9;
        System.out.println("Time spent:"+stime+" seconds.");

    }


}
