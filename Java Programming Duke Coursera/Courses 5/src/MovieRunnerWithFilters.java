import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Xiaoqiang on 2017/2/24.
 */
public class MovieRunnerWithFilters {

    public void printAverageRatings(){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatings(35);
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear (){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        YearAfterFilter yearAfterFilter = new YearAfterFilter(2000);
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatingsByFilter(20,yearAfterFilter);

        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre  (){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new GenreFilter("Comedy");
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatingsByFilter(20,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes (){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new MinutesFilter(105,135);
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatingsByFilter(5,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" Time:"+MovieDatabase.getMinutes(rating.getItem())+" "+
                    MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors (){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatingsByFilter(4,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre (){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatingsByFilter(8,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes (){
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(90,180));
        filter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> averageRating = thirdRatings.getAverageRatingsByFilter(3,filter);
        System.out.println("Number of movies:"+MovieDatabase.size());
        System.out.println("Number of raters:"+RaterDatabase.size());
        Collections.sort(averageRating);
        System.out.println("Found "+averageRating.size()+" movies");
        for (Rating rating:averageRating){
            System.out.println(rating.getValue()+" Time:"+MovieDatabase.getMinutes(rating.getItem())+" "+
                    MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(rating.getItem()));
        }
    }

//    public void getAverageRatingOneMovie(){
//        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
//        String title = "Vacation";
//        String id  = secondRatings.getID(title);
//        ArrayList<Rating> averageRating = secondRatings.getAverageRatings(3);
//        System.out.print(title+" is rated: ");
//        for (int i=0;i<averageRating.size();i++){
//            if (id.equals(averageRating.get(i).getItem())){
//                System.out.println(averageRating.get(i).getValue());
//            }
//        }
//    }

    public static void main(String[] args){
        double begin = System.nanoTime();
        MovieRunnerWithFilters movieRunnerAverage = new MovieRunnerWithFilters();
//        double begin = System.nanoTime();
        movieRunnerAverage.printAverageRatingsByYearAfterAndGenre();
        double end = System.nanoTime();
        double stime = (end-begin)/1e9;
        System.out.println("Time spent:"+stime+" seconds.");

    }


}
