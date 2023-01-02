import java.util.ArrayList;
import java.util.Collections;

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

    public ArrayList<Rating> getSimilarRatings(
            String raterId, int numSimilarRaters, int numMinimumRaters
    ) {

        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        return this.computeSimilarities(similarRaters, movies, numSimilarRaters, numMinimumRaters);
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(
            String raterId, int numSimilarRaters, int numMinimumRaters, Filter filterCriteria
    ) {

        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        return this.computeSimilarities(similarRaters, movies, numSimilarRaters, numMinimumRaters);
    }

    private ArrayList<Rating> computeSimilarities(
            ArrayList<Rating> similarRaters, ArrayList<String> movies, int numSimilarRaters, int numMinimumRaters
    ) {

        ArrayList<Rating> similarRatings = new ArrayList<>();

        for (String movieId : movies) {

            double avgRating = 0;
            int count = 0;

            for (int i = 0; i < numSimilarRaters; i++) {

                Rating similarRater = similarRaters.get(i);
                Rater currentRater = RaterDatabase.getRater(similarRater.getItem());

                if (currentRater.hasRating(movieId)) {

                    avgRating += similarRater.getValue() * currentRater.getRating(movieId);
                    count++;
                }
            }

            if (count >= numMinimumRaters) {
                double weightedAvg = avgRating / count;
                Rating computedRating = new Rating(movieId, weightedAvg);
                similarRatings.add(computedRating);
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
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

    private double calculateDotProduct(Rater me, Rater other){

        double dotProduct = 0;

        for (String item : me.getItemsRated()) {
            if (other.hasRating(item)) {
                dotProduct += (me.getRating(item) -5) * (other.getRating(item) -5);
            }
        }
        return dotProduct;
    }

    private ArrayList<Rating> sorter(ArrayList<Rating> ratings){
        ArrayList<Rating> out = new ArrayList<>(ratings);
        Collections.sort(out);
        return out;
    }
}
