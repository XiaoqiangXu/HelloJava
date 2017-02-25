import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Xiaoqiang on 2017/2/20.
 */
public class FirstRatings {

    public ArrayList<Movie> loadMovies (String filename){
        ArrayList<Movie> movieArrayList= new ArrayList<>();
//        FileResource fr= new FileResource("data"+"\\"+filename);
        FileResource fr= new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record:parser){
            Movie curMovie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),
                    record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
            movieArrayList.add(curMovie);
        }
        return  movieArrayList;
    }

    public ArrayList<String> sepDirectors(String director){
        int idx1 = 0;
        int idx2 = director.indexOf(",",idx1);
        ArrayList<String> result = new ArrayList<>();
        if (idx2==-1){
             result.add(director);
             return result;
        }else{
            while (idx2!=-1){
                String curDirector = director.substring(idx1,idx2);
                idx1 = idx2+2;
                idx2 = director.indexOf(",",idx1);
                result.add(curDirector);
            }
            result.add(director.substring(idx1));
            return  result;
        }
    }

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raterList= new ArrayList<>();
//        FileResource fr= new FileResource("data"+"\\"+filename);
        FileResource fr= new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record:parser){
            boolean exist = false;
            for (int i=0;i<raterList.size();i++){
                if (record.get("rater_id").equals(raterList.get(i).getID())){
                    raterList.get(i).addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                    exist = true;
                    break;
                }
            }
            if (exist==false){
//********************************************************************************************
                Rater rater = new EfficientRater(record.get("rater_id"));
//********************************************************************************************
                rater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                raterList.add(rater);
            }
        }
        return raterList;
    }

    public void testLoadMovies (){
        ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
//        for (Movie movie:movieList){
//            System.out.println(movie);
//        }
        System.out.println(movieList.size());
        int numComedyMovie = 0;
        int numMinutesLength = 0;
        int maxMovieFromDirectors = 0;
        HashMap<String,Integer> directorMap = new HashMap<>();
        for (Movie movie:movieList){
            if (movie.getGenres().indexOf("Comedy")!=-1) {numComedyMovie++;}
            if (movie.getMinutes()>150){numMinutesLength++;}
            ArrayList<String> directors = sepDirectors(movie.getDirector());
            for (String eachDirector:directors){
                if (!directorMap.containsKey(eachDirector)){
                    directorMap.put(eachDirector,1);
                }else{
                    directorMap.put(eachDirector,directorMap.get(eachDirector)+1);
                }
            }
        }
        System.out.println("Number of movies include the Comedy genre :"+numComedyMovie);
        System.out.println("Number of movies greater than 150 minutes in length :"+numMinutesLength);
        for (String eachDirector:directorMap.keySet()){
            if (directorMap.get(eachDirector)>maxMovieFromDirectors){
                maxMovieFromDirectors=directorMap.get(eachDirector);
            }
        }
        System.out.println("Maximum number of movies by any director : "+maxMovieFromDirectors);
        System.out.print("The directors that directed such movie : ");
        for (String eachDirector:directorMap.keySet()){
            if (directorMap.get(eachDirector)==maxMovieFromDirectors){
                System.out.print(eachDirector+"; ");
            }
        }
        System.out.println();
//        System.out.println(directorMap);
    }

    public void testLoadRaters(){
        ArrayList<Rater> raterList = loadRaters("ratings.csv");
        int maxNumRating = 0;
//
        for (int i=0;i<raterList.size();i++){
            ArrayList<String> movieId =  raterList.get(i).getItemsRated();
            System.out.print(raterList.get(i).getID()+" : ");
            if (movieId.size()>maxNumRating){
                maxNumRating = movieId.size();
            }
            for (int j=0;j<movieId.size();j++){
                System.out.print(movieId.get(j)+" ");
                System.out.print(raterList.get(i).getRating(movieId.get(j))+"; ");
            }
            System.out.println();
        }
        System.out.println("Number of raters: "+raterList.size());
//
        String raterID = "193";
        for (int i=0;i<raterList.size();i++) {
            if (raterList.get(i).getID().equals(raterID)){
                ArrayList<String> movieId = raterList.get(i).getItemsRated();
                System.out.print("RaterID_"+raterID + " have "+ movieId.size()+ " ratings:");
                for (int j = 0; j < movieId.size(); j++) {
                    System.out.print(movieId.get(j) + " ");
                    System.out.print(raterList.get(i).getRating(movieId.get(j)) + "; ");
                }
                System.out.println();
            }
        }
//

        for (int i=0;i<raterList.size();i++){
            ArrayList<String> movieId=  raterList.get(i).getItemsRated();
            if (movieId.size()==maxNumRating){
                System.out.print("RaterID_"+raterList.get(i).getID()+" has the maximum number of "+movieId.size()+" ratings :");
                for (int j=0;j<movieId.size();j++){
                    System.out.print(movieId.get(j)+" ");
                    System.out.print(raterList.get(i).getRating(movieId.get(j))+"; ");
                }
                System.out.println();
            }
        }
//
        int numOfRaters = 0;
        String specifiedMovId = "1798709";
        for (int i=0;i<raterList.size();i++){
            ArrayList<String> movieId=  raterList.get(i).getItemsRated();
            if (movieId.contains(specifiedMovId)){
                numOfRaters++;
            }
        }
        System.out.println("Movie ID_"+specifiedMovId+" was rated by "+numOfRaters+" raters.");
//
        ArrayList<String> uniqueMovieIDs = new ArrayList<>();
        for (int i=0;i<raterList.size();i++){
            ArrayList<String> movieId=  raterList.get(i).getItemsRated();
            for (int j=0;j<movieId.size();j++){
                if (!uniqueMovieIDs.contains(movieId.get(j))){
                    uniqueMovieIDs.add(movieId.get(j));
                }
            }
        }
        System.out.println(uniqueMovieIDs.size()+" different movies have been rated by all these raters.");
    }

    public static void main(String[] args){

        double begin = System.nanoTime();
        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.testLoadMovies();
        firstRatings.testLoadRaters();
        double end = System.nanoTime();
        double stime = (end-begin)/1e9;
        System.out.println("Time spent:"+stime+" seconds.");

    }

}
