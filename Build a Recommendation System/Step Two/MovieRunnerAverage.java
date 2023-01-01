import java.util.ArrayList;
import java.util.HashMap;

public class MovieRunnerAverage {

    public void printAverageRatings(int minimumRatings){

        SecondRating sr = new SecondRating();

        ArrayList<Rating> ratings = sr.getAverageRatings(minimumRatings);
        for (int i = 0; i < ratings.size(); i++) {
            String title = sr.getTitle(ratings.get(i).getItem());
            System.out.println("Rating: " + ratings.get(i).getValue() + " Title: " + title);
        }
    }

    public void getAverageRatingOneMovie(String movieTitle){

        SecondRating sr = new SecondRating();
        String movieID = sr.getID(movieTitle);
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        for (int i = 0; i < ratings.size(); i++) {
            if (ratings.get(i).getItem().equals(movieID)) {
                System.out.println("Rating: " + ratings.get(i).getValue() + " Title: " + movieTitle);
            }
        }
    }


    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        mra.printAverageRatings(12);
//        mra.getAverageRatingOneMovie("Vacation");
    }
}

