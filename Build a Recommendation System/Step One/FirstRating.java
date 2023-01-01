import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRating {

    private final String MOVIES_FULL = "data/ratedmoviesfull.csv";
    private final String MOVIES_SHORT = "data/ratedmovies_short.csv";
    private final String RATINGS_SHORT = "data/ratings_short.csv";
    private final String RATINGS_FULL = "data/ratings.csv";

    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource file = new FileResource(filename);
        for (CSVRecord record : file.getCSVParser()) {

            Movie current = new Movie();
            current.setId(record.get(0));
            current.setTitle(record.get(1));
            current.setYear(Integer.parseInt(record.get(2)));
            current.setCountry(record.get(3));
            current.setGenres(record.get(4));
            current.setDirector(record.get(5));
            current.setMinutes(Integer.parseInt(record.get(6)));
            current.setPoster(record.get(7));

            movies.add(current);
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
                for (Rater rater : raters) {
                    if (rater.getID().equals(current.getID()) && !rater.hasRating(record.get(1))) {
                        rater.addRating(record.get(1), Double.parseDouble(record.get(2)));
                    }
                }
            }
        }
        return raters;
    }

    private boolean containsID(ArrayList<Rater> raters, Rater current) {
        for (Rater rater : raters) {
            if (Double.parseDouble(rater.getID()) == Double.parseDouble(current.getID())) {
                return true;
            }
        }
        return false;
    }

    private void findByGenre(String genre) {
        ArrayList<Movie> movies = this.loadMovies(MOVIES_FULL);
        long size = movies.stream().filter(movie -> movie.getGenres().contains(genre)).count();
        System.out.println("Number of movies of genre " + genre + ": " + size);
    }

    private void findByLength(int length) {
        ArrayList<Movie> movies = this.loadMovies(MOVIES_FULL);
        long size = movies.stream().filter(movie -> movie.getMinutes() > length).count();
        System.out.println("Number of movies of length greater than " + length + ": " + size);
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

    private int findRatersById(int ID, ArrayList<Rater> raters){
        for (Rater rater : raters) {
            if (Integer.parseInt(rater.getID()) == ID) {
                return rater.numRatings();
            }
        }
        return -1;
    }

    private int findMaxNumbOfRatings(ArrayList<Rater> raters){
        int max = 0;
        for (Rater rater : raters) {
            int current = rater.numRatings();
            if (current > max) {
                max = current;
            }
        }
        return max;
    }


    public void printingNumberOfMoviesByDirector(){
        HashMap<String,Integer> myMap = findMoviesForDirectors(MOVIES_FULL);
        for (String s : myMap.keySet()) {
            System.out.println("Director: " + s + "; Number of movies: " +  myMap.get(s));
        }
    }

    private void findDirectorsWithMostMovies(){
        int max = 0;
        HashMap<String,Integer> myMap = findMoviesForDirectors(MOVIES_FULL);
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

    public void findRatingsForRater(int id){
        ArrayList<Rater> raters = loadRaters(RATINGS_FULL);
        int numRatings = findRatersById(id, raters);
        System.out.println("Number of rating for rater with id " + id + ": " + numRatings);
    }

    public void findRaterWithMostRatings(){
        ArrayList<Rater> raters = loadRaters(RATINGS_FULL);
        int max = findMaxNumbOfRatings(raters);
        String id = "";
        for (Rater rater : raters) {
            if (rater.numRatings() == max) {
                max = rater.numRatings();
                id = rater.getID();
            }
        }
        System.out.println("Top Rater Id: " + id + " Number of ratings: " + max);
    }

    public void findNumberOfRatingsPerMovie(String movieID){
        int ratings = 0;
        ArrayList<Rater> raters = loadRaters(RATINGS_FULL);
        for (Rater rater : raters) {
            if (rater.hasRating(movieID)) {
                ratings += 1;
            }
        }
        System.out.println("Number of ratings for movie ID " + movieID + ": " + ratings);
    }

    public void findNumberOfMoviesRated(){
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<Rater> raters = loadRaters(RATINGS_FULL);
        for (Rater rater : raters) {
            ArrayList<String> current = rater.getItemsRated();
            for (String s : current) {
                if (!movies.contains(s)) {
                    movies.add(s);
                }
            }
        }
        System.out.println("Number of unique movies rated: " + movies.size());
    }

    public static void main(String[] args) {
        FirstRating fr = new FirstRating();

        fr.findByGenre("Comedy");
        fr.findByLength(150);
        fr.findDirectorsWithMostMovies();
        fr.findRatingsForRater(193);
        fr.findRaterWithMostRatings();
        fr.findNumberOfRatingsPerMovie("1798709");
        fr.findNumberOfMoviesRated();
    }
}
