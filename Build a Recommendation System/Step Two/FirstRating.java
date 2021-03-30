import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRating {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource file = new FileResource(filename);
        for (CSVRecord record : file.getCSVParser()) {
            Movie current = new Movie(record.get(0), record.get(1), record.get(2), record.get(3),
                    record.get(4), record.get(5), record.get(7), Integer.parseInt(record.get(6)));
            //if (record.get(4).indexOf("Comedy") != -1) {
            //if (Integer.parseInt(record.get(6)) > 150) {
                movies.add(current);
            //}
        }
        return movies;
    }

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource file = new FileResource(filename);
        for (CSVRecord record : file.getCSVParser()) {
            Rater current = new Rater(record.get(0));
            if (!containsID(raters, current)) {
                raters.add(current);
                current.addRating(record.get(1), Double.parseDouble(record.get(2)));
            } else {
                for (int i = 0; i < raters.size(); i++) {
                    if (raters.get(i).getID().equals(current.getID()) && !raters.get(i).hasRating(record.get(1))) {
                        raters.get(i).addRating(record.get(1), Double.parseDouble(record.get(2)));
                    }
                }
            }
        }
        return raters;
    }

    private boolean containsID(ArrayList<Rater> raters, Rater current) {
        for (int i = 0; i < raters.size(); i++) {
            if (Double.parseDouble(raters.get(i).getID()) == Double.parseDouble(current.getID())) {
                return true;
            }
        }
        return false;
    }

    private HashMap<String,Integer> findMoviesForDirectors(String filename){
        HashMap<String,Integer> myMap = new HashMap<String,Integer>();
        FileResource file = new FileResource(filename);
        for (CSVRecord record : file.getCSVParser()) {
            String current = record.get(5);
            for (String s : current.split(",+")) {
                if (!myMap.containsKey(s)) {
                    myMap.put(s, 1);
                }
                else {
                    myMap.put(s, myMap.get(s) +1) ;
                }
            }
        }
        return myMap;
    }

    private int findRatersByID(int ID, ArrayList<Rater> raters){
        for (int i = 0; i < raters.size(); i++) {
            if (Integer.parseInt(raters.get(i).getID()) == ID) {
                return raters.get(i).numRatings();
            }
        }
        return -1;
    }

    private int findMaxNumbOfRatings(ArrayList<Rater> raters){
        int max = 0;
        for (int i = 0; i < raters.size(); i++) {
            int current = raters.get(i).numRatings();
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        System.out.println(movies.size());
    }

    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("\nNumber of raters: " + raters.size());
        for (int i = 0; i < raters.size(); i++) {
            System.out.println("ID: " + raters.get(i).getID() + " Number of ratings: " + raters.get(i).numRatings());
        }
    }

    public void printingNumberOfMoviesByDirector(){
        HashMap<String,Integer> myMap = findMoviesForDirectors("data/ratedmoviesfull.csv");
        for (String s : myMap.keySet()) {
            System.out.println("Director: " + s + "; Number of movies: " +  myMap.get(s));
        }
    }

    private void findDirectorsWithMostMovies(){
        int max = 0;
        HashMap<String,Integer> myMap = findMoviesForDirectors("data/ratedmoviesfull.csv");
        for (String s : myMap.keySet()) {
            int current = myMap.get(s);
            if (current > max) {
                max = current;
            }
        }
        System.out.println("Directors with most movies:");
        for (String s : myMap.keySet()) {
            if (myMap.get(s) == max) {
                System.out.println("Director: " + s + "; Number of movies: " +  myMap.get(s));
            }
        }

    }

    public void testFindRatersByID(){
        ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        int numRatings = findRatersByID(4, raters);
        System.out.println(numRatings);
    }

    public void printRatersWithMostRatings(){
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        int max = findMaxNumbOfRatings(raters);
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).numRatings() == max) {
                Rater current = raters.get(i);
                System.out.println("Rater ID: " + current.getID() + " Number of ratings: " + current.numRatings());
            }
        }
    }

    public void findNumberOfRatingsPerMovie(String movieID){
        int ratings = 0;
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).hasRating(movieID)) {
                ratings +=1;
            }
        }
        System.out.println("Number of ratings for movie ID " + movieID + ": " + ratings);
    }

    public void findNumberOfMoviesRated(){
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        for (int i = 0; i < raters.size(); i++) {
            ArrayList<String> current = raters.get(i).getItemsRated();
            for (String s : current) {
                if (!movies.contains(s)) {
                    movies.add(s);
                }
            }
        }
        System.out.println(movies.size());
    }

    public static void main(String[] args) {
        FirstRating fr = new FirstRating();
        //fr.testLoadMovies();
        //fr.testLoadRaters();

        //fr.printingNumberOfMoviesByDirector();
        //fr.findDirectorsWithMostMovies();
        //fr.testFindRatersByID();
        //fr.printRatersWithMostRatings();
        //fr.findNumberOfRatingsPerMovie("1798709");
        fr.findNumberOfMoviesRated();
    }
}
