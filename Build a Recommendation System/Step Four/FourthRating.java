
import java.util.*;

public class FourthRating {
    private double getAverageByID(String id, int minimalRaters) {
        double avgRating = 0.0;
        ArrayList<Rater> whoRated = new ArrayList<Rater>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        int totalRating = 0;

        for (Rater rater : raters) {
            if (rater.getRating(id) != -1) {
                totalRating += rater.getRating(id);
                whoRated.add(rater);
            }
        }

        if (whoRated.size() >= minimalRaters) {
            avgRating = (double)totalRating / whoRated.size();
        }
        return avgRating;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String id : movies) {
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(id, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String id : movies) {
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(id, avgRating);
                avgRatings.add(rating);
            }
        }

        return avgRatings;
    }

    private double dotProduct(Rater me, Rater r) {
        double product = 0.0;
        String meId = me.getID();
        String rId = r.getID();
        Rater meNew = RaterDatabase.getRater(meId);
        Rater rNew = RaterDatabase.getRater(rId);
        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter());
        for (String movieId : movieIds) {
            if (meNew.hasRating(movieId) && rNew.hasRating(movieId)) {
                double meScore = meNew.getRating(movieId) - 5.0;
                double rScore = rNew.getRating(movieId) - 5.0;
                product += meScore * rScore;
            }
        }
        return product;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarRaters = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            double prod = 0.0;
            if (!r.getID().equals(id)) {
                prod = dotProduct(me, r);
            }
            if (prod > 0.0) {
                Rating rating = new Rating(r.getID(), prod);
                similarRaters.add(rating);
            }
        }
        Collections.sort(similarRaters, Collections.reverseOrder());
        return similarRaters;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieId : movies) {
            double avgRating = 0.0;
            int countRating = 0;

            for (int i = 0; i < numSimilarRaters; i++) {
                Rating similarRater = similarRaters.get(i);
                String similarRaterId = similarRater.getItem();
                Rater actualSimilarRater = RaterDatabase.getRater(similarRaterId);
                if (actualSimilarRater.hasRating(movieId)) {
                    countRating += 1;
                    double similarRating = similarRater.getValue();
                    double actualRating = actualSimilarRater.getRating(movieId);
                    double closeness = similarRating * actualRating;
                    avgRating += closeness;
                }
            }

            if (countRating >= minimalRaters) {
                double weightedAvg = avgRating / countRating;
                Rating newRating = new Rating(movieId, weightedAvg);
                similarRatings.add(newRating);
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieId : movies) {
            double avgRating = 0.0;
            int countRating = 0;

            for (int i = 0; i < numSimilarRaters; i++) {
                Rating similarRater = similarRaters.get(i);
                String similarRaterId = similarRater.getItem();
                Rater actualSimilarRater = RaterDatabase.getRater(similarRaterId);
                if (actualSimilarRater.hasRating(movieId)) {
                    countRating += 1;
                    double similarRating = similarRater.getValue();
                    double actualRating = actualSimilarRater.getRating(movieId);
                    double closeness = similarRating * actualRating;
                    avgRating += closeness;
                }
            }

            if (countRating >= minimalRaters) {
                double weightedAvg = avgRating / countRating;
                Rating newRating = new Rating(movieId, weightedAvg);
                similarRatings.add(newRating);
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }
}