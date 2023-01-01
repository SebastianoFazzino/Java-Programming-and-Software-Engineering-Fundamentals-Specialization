import java.util.ArrayList;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings(int minimumRaters){
        FourthRating fr = new FourthRating();

        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        ArrayList<Rating> ratings = fr.getAverageRatings(minimumRaters);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            System.out.println(rate + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int yearAfter, String genre, int minimumRaters){
        FourthRating fr = new FourthRating();
        Filter yearFilter = new YearAfterFilter(yearAfter);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(yearFilter);
        multipleFilter.addFilter(genreFilter);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(minimumRaters, multipleFilter);
        System.out.println("Found " + ratings.size() + " movies of genre " + genre + " released after " + yearAfter);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String genres = MovieDatabase.getGenres(ratings.get(i).getItem());
            int year = MovieDatabase.getYear(ratings.get(i).getItem());
            System.out.println(rate + " " + year + " \""  + movie + "\"\n" + genres);
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mr = new MovieRunnerSimilarRatings();

        mr.printAverageRatings(20);
    }
}
