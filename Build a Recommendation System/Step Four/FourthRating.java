import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRating {

    private double getAverageByID(String id, int minimalRaters){
        double avgRating  = 0.0;
        int howMany = 0;
        for (Rater rater: RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
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

    public ArrayList<Rating> getAverageRatings (int minimumRaters) {
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());

        for (String movie : myMovies) {
            double avgRating = getAverageByID(movie, minimumRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movie, avgRating);
                ratings.add(rating);
            }
            ratings = sorter(ratings);
        }
        return ratings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimumRaters, Filter filterCriteria){
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for (String movie : myMovies){
            double avgRating = getAverageByID(movie, minimumRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movie, avgRating);
                ratings.add(rating);
            }
        }
        return ratings;
    }

    public ArrayList<Rating> getSimilarRatings(String raterId, int numSimilarRaters, int minimumRaters) {

        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<Rating> similarities =  getSimilarities(raterId);
        ArrayList<Rating> similarRaters =  new ArrayList<>();

        for (int i = 0; i < minimumRaters; i++) {
            similarRaters.add(similarities.get(i));
        }

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movie : movies) {

            double avg = this.calculateWeightAvg(movie, similarRaters, minimumRaters);

            if (avg > 0){
                Rating rating = new Rating(movie, avg);
                ratings.add(rating);
            }
        }

        ratings.sort(Collections.reverseOrder());
        return ratings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(
            String raterId, int numSimilarRaters, int minimumRaters, Filter filterCriteria
    ) {

        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<Rating> similarities =  getSimilarities(raterId);
        ArrayList<Rating> similarRaters =  new ArrayList<>();

        for (int i = 0; i < numSimilarRaters; i++) {
            similarRaters.add(similarities.get(i));
        }

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movie : movies) {

            double avg = this.calculateWeightAvg(movie, similarRaters, minimumRaters);

            if (avg > 0){
                Rating rating = new Rating(movie, avg);
                ratings.add(rating);
            }
        }

        ratings.sort(Collections.reverseOrder());
        return ratings;
    }


    private ArrayList<Rating> getSimilarities(String raterId) {

        ArrayList<Rating> similarities = new ArrayList<>();
        Rater me = RaterDatabase.getRater(raterId);

        for (Rater rater : RaterDatabase.getRaters()) {
            double dotProduct = 0;

            if (!rater.getID().equals(raterId)) {
                dotProduct = this.calculateDotProduct(me, rater);
            }

            if (dotProduct > 0) {
                Rating rating = new Rating(rater.getID(), dotProduct);
                similarities.add(rating);
            }
        }
        similarities.sort(Collections.reverseOrder());
        return similarities;
    }

    private ArrayList<Rating> sorter(ArrayList<Rating> ratings){
        ArrayList<Rating> out = new ArrayList<>(ratings);
        Collections.sort(out);
        return out;
    }

    private double calculateDotProduct(Rater me, Rater other){

        double dotProduct = 0;

        for (String item : me.getItemsRated()) {
            if (other.hasRating(item)) {
                dotProduct += (me.getRating(item) -5) * (other.getRating(item) -5);
            }
        }
        return dotProduct;
    }

    private double calculateWeightAvg(String movieId, ArrayList<Rating> similarRaters, int minimalRaters){

        int count = 0;
        double totalRatings = 0.0;

        for (Rating rating : similarRaters) {

            Rater current = RaterDatabase.getRater(rating.getItem());

            try {

                double rate = current.getRating(movieId);

                if (rate != -1) {
                    totalRatings = totalRatings + rate * rating.getValue();
                    count++;
                }
            } catch (Exception ignore) {}

        }

        return count >= minimalRaters ? totalRatings / count : 0.0;
    }

}
