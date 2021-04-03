import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ThirdRating {
    private ArrayList<Rater> myRaters;

    public ThirdRating() {
        myRaters = new ArrayList<>();
        FirstRating fr = new FirstRating();
        myRaters = fr.loadRaters("data/ratings.csv");
    }

    public ThirdRating(String ratingfile) {
        myRaters = new ArrayList<>();
        FirstRating fr = new FirstRating();
        myRaters = fr.loadRaters(ratingfile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters){
        double avgRating  = 0.0;
        int howMany = 0;
        for(Rater rater: myRaters) {
            if(rater.hasRating(id)) {
                avgRating += rater.getRating(id);
                howMany += 1;
            }
        }
        if (howMany >= minimalRaters) {
            return avgRating / howMany;
        } else {
            return 0.0;
        }
    }

    private ArrayList<Rating> sorter(ArrayList<Rating> ratings){
        ArrayList<Rating> out = new ArrayList<>(ratings);
        Collections.sort(out);
        return out;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for (String movie : myMovies){
            double avgRating = getAverageByID(movie, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movie, avgRating);
                ratings.add(rating);
            }
        }
        return ratings;
    }

    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());

        for (String movie : myMovies) {
            double avgRating = getAverageByID(movie, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movie, avgRating);
                ratings.add(rating);
            }
            //ratings = sorter(ratings);
        }
        return ratings;
    }

    public int getSize(){
        return myRaters.size();
    }
}
