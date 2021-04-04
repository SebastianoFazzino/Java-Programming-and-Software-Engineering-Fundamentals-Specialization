import java.util.ArrayList;

public class MovieRunnerWithFilters {
    final String moviesFull, moviesShort, ratersFull, ratersShort;

    public MovieRunnerWithFilters(){
        moviesFull = "data/ratedmoviesfull.csv";
        moviesShort = "data/ratedmovies_short.csv";
        ratersFull = "data/ratings.csv";
        ratersShort = "data/ratings_short.csv";
        MovieDatabase.initialize(moviesFull);
   }

    public void testingThirdRating(){
        ThirdRating tr = new ThirdRating();
        //System.out.println(tr.getRaterSize());
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatings(1);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            System.out.println(rate + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByYear(){
        ThirdRating tr = new ThirdRating();
        Filter yearFilter = new YearAfterFilter(2000);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, yearFilter);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            int year = MovieDatabase.getYear(ratings.get(i).getItem());
            System.out.println(rate + " " + year + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByGenre(){
        ThirdRating tr = new ThirdRating();
        Filter genreFilter = new GenreFilter("Crime");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, genreFilter);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String genres = MovieDatabase.getGenres(ratings.get(i).getItem());
            System.out.println(rate + " " + genres + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByMinutes(){
        ThirdRating tr = new ThirdRating();
        Filter minutesFilter = new MinutesFilter(110, 170);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, minutesFilter);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            int minutes = MovieDatabase.getMinutes(ratings.get(i).getItem());
            System.out.println(rate + " " + minutes + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByDirectors(){
        ThirdRating tr = new ThirdRating();
        Filter directorsFilter = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, directorsFilter);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String directors = MovieDatabase.getDirector(ratings.get(i).getItem());
            System.out.println(rate + " \"" + movie + "\"\n" + directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRating tr = new ThirdRating();
        Filter yearFilter = new YearAfterFilter(1980);
        Filter genreFilter = new GenreFilter("Romance");
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(yearFilter);
        multipleFilter.addFilter(genreFilter);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, multipleFilter);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String genre = MovieDatabase.getGenres(ratings.get(i).getItem());
            int year = MovieDatabase.getYear(ratings.get(i).getItem());
            System.out.println(rate + " " + year + " \""  + movie + "\"\n" + genre);
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRating tr = new ThirdRating();
        Filter minutesFilter = new MinutesFilter(30, 170);
        Filter directorsFilter = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(minutesFilter);
        multipleFilter.addFilter(directorsFilter);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, multipleFilter);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String directors = MovieDatabase.getDirector(ratings.get(i).getItem());
            int minutes = MovieDatabase.getMinutes(ratings.get(i).getItem());
            System.out.println(rate + " " + minutes + " \""  + movie + "\"\n\t" +  directors);
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters test = new MovieRunnerWithFilters();
        //test.testingThirdRating();
        //test.printAverageRatingsByYear();
        //test.printAverageRatingsByGenre();
        //test.printAverageRatingsByMinutes();
        //test.printAverageRatingsByDirectors();
        //test.printAverageRatingsByYearAfterAndGenre();
        test.printAverageRatingsByDirectorsAndMinutes();
     }
}
