import java.util.ArrayList;
import java.util.HashMap;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRating sr = new SecondRating("data/ratedmoviesfull.csv", "data/ratings_short.csv");
        System.out.println("Number of movies: " + sr.getMovieSize() +
                "\nNumber of raters: " + sr.getRaterSize());
        /*System.out.println(sr.getAverageRatings(3));
        System.out.println(sr.getTitle("2726560"));*/
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        for (int i = 0; i < ratings.size(); i++) {
            String title = sr.getTitle(ratings.get(i).getItem());
            System.out.println("Rating: " + ratings.get(i).getValue() + " Title: " + title);
        }
    }

    public void getAverageRatingOneMovie(){
        SecondRating sr = new SecondRating("data/ratedmoviesfull.csv", "data/ratings_short.csv");
        String title = "The Godfather";
        String movieID = sr.getID(title);
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        for (int i = 0; i < ratings.size(); i++) {
            if (ratings.get(i).getItem().equals(movieID)) {
                System.out.println("Rating: " + ratings.get(i).getValue() + " Title: " + title);
            }
        }
    }


    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        //mra.printAverageRatings();
        mra.getAverageRatingOneMovie();
    }
}

