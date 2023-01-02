import java.util.ArrayList;

public class MovieRunnerSimilarRatings {

    public void printSimilarRatings(
            String raterId, int numSimilarRaters, int numMinimumRaters) {

        FourthRating fr = new FourthRating();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterId, numSimilarRaters, numMinimumRaters);

        similarRatings.forEach(rating ->
                System.out.println("Movie: " + MovieDatabase.getTitle(rating.getItem())
                        + " Rating: " + rating.getValue()));
    }

    public void printSimilarRatingsByGenre(
            String raterId, int numSimilarRaters, int numMinimumRaters, GenreFilter filter) {

        FourthRating fr = new FourthRating();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, numMinimumRaters, filter);

        similarRatings.forEach(rating ->
                System.out.println("Movie: " + MovieDatabase.getTitle(rating.getItem())
                        + " Rating: " + rating.getValue()));
    }

    public void printSimilarRatingsByDirector(
            String raterId, int numSimilarRaters, int numMinimumRaters, DirectorsFilter filter) {

        FourthRating fr = new FourthRating();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, numMinimumRaters, filter);

        similarRatings.forEach(rating ->
                System.out.println("Movie: " + MovieDatabase.getTitle(rating.getItem())
                        + " Rating: " + rating.getValue()));
    }

    public void printSimilarRatingsByGenreAndMinutes(
            String raterId, int numSimilarRaters, int numMinimumRaters, String genre, int minLength, int maxLength) {

        Filter genreFilter = new GenreFilter(genre);
        Filter lenghtFilter = new MinutesFilter(minLength, maxLength);
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(lenghtFilter);
        multipleFilter.addFilter(genreFilter);

        FourthRating fr = new FourthRating();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, numMinimumRaters, multipleFilter);

        similarRatings.forEach(rating ->
                System.out.println("Movie: " + MovieDatabase.getTitle(rating.getItem())
                        + " Rating: " + rating.getValue()));
    }

    public void printSimilarRatingsByYearAfterAndMinutes(
            String raterId, int numSimilarRaters, int numMinimumRaters, int yearAfter, int minLength, int maxLength) {

        Filter yearFilter = new YearAfterFilter(yearAfter);
        Filter lenghtFilter = new MinutesFilter(minLength, maxLength);
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(lenghtFilter);
        multipleFilter.addFilter(yearFilter);

        FourthRating fr = new FourthRating();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, numMinimumRaters, multipleFilter);

        similarRatings.forEach(rating ->
                System.out.println("Movie: " + MovieDatabase.getTitle(rating.getItem())
                        + " Rating: " + rating.getValue()));
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mr = new MovieRunnerSimilarRatings();

        mr.printSimilarRatings("71", 20, 5);
        mr.printSimilarRatingsByGenre("964", 20, 5, new GenreFilter("Mystery"));
        mr.printSimilarRatingsByDirector("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        mr.printSimilarRatingsByGenreAndMinutes("168", 10, 3, "Drama", 80, 160);
        mr.printSimilarRatingsByYearAfterAndMinutes("314", 10, 5, 1975, 70, 200);

    }
}
