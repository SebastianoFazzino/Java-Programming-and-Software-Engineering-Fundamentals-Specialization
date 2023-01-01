import java.util.ArrayList;

public class MovieRunnerWithFilters {

    public MovieRunnerWithFilters(){
        MovieDatabase.initialize();
   }

    public void printAverageRatings(int minimumRaters){
        ThirdRating tr = new ThirdRating();
        System.out.println(tr.getRaterSize());
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatings(minimumRaters);
        System.out.println("Found " + ratings.size() + " movies");
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            System.out.println(rate + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByYear(int yearAfter, int minimumRaters){
        ThirdRating tr = new ThirdRating();
        Filter yearFilter = new YearAfterFilter(yearAfter);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimumRaters, yearFilter);
        System.out.println("Found " + ratings.size() + " movies released after " + yearAfter);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            int year = MovieDatabase.getYear(ratings.get(i).getItem());
            System.out.println(rate + " " + year + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByGenre(String genre, int minimumRaters){
        ThirdRating tr = new ThirdRating();
        Filter genreFilter = new GenreFilter(genre);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimumRaters, genreFilter);
        System.out.println("Found " + ratings.size() + " movies for genre " + genre);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String genres = MovieDatabase.getGenres(ratings.get(i).getItem());
            System.out.println(rate + " " + genres + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByMinutes(int minLength, int maxlength, int minimumRaters){
        ThirdRating tr = new ThirdRating();
        Filter minutesFilter = new MinutesFilter(minLength, maxlength);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimumRaters, minutesFilter);
        System.out.println("Found " + ratings.size() + " movies with length between " + minLength + " and " + maxlength);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            int minutes = MovieDatabase.getMinutes(ratings.get(i).getItem());
            System.out.println(rate + " " + minutes + " \"" + movie + "\"");
        }
    }

    public void printAverageRatingsByDirectors(String directedBy, int minimumRaters){
        ThirdRating tr = new ThirdRating();
        Filter directorsFilter = new DirectorsFilter(directedBy);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimumRaters, directorsFilter);
        System.out.println("Found " + ratings.size() + " movies directed by " + directedBy);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String directors = MovieDatabase.getDirector(ratings.get(i).getItem());
            System.out.println(rate + " \"" + movie + "\"\n" + directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int yearAfter, String genre, int minimumRaters){
        ThirdRating tr = new ThirdRating();
        Filter yearFilter = new YearAfterFilter(yearAfter);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(yearFilter);
        multipleFilter.addFilter(genreFilter);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimumRaters, multipleFilter);
        System.out.println("Found " + ratings.size() + " movies of genre " + genre + " released after " + yearAfter);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String genres = MovieDatabase.getGenres(ratings.get(i).getItem());
            int year = MovieDatabase.getYear(ratings.get(i).getItem());
            System.out.println(rate + " " + year + " \""  + movie + "\"\n" + genres);
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(String directedBy, int minLength, int maxlength, int minimumRaters){
        ThirdRating tr = new ThirdRating();
        Filter minutesFilter = new MinutesFilter(minLength, maxlength);
        Filter directorsFilter = new DirectorsFilter(directedBy);
        AllFilters multipleFilter = new AllFilters();
        multipleFilter.addFilter(minutesFilter);
        multipleFilter.addFilter(directorsFilter);
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Read data for " + tr.getSize() + " raters");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimumRaters, multipleFilter);
        System.out.println("Found " + ratings.size() + " movies directed by " + directedBy + "with length between " + minLength + " and " + maxlength);
        for (int i = 0; i < ratings.size(); i++) {
            double rate = ratings.get(i).getValue();
            String movie = MovieDatabase.getTitle(ratings.get(i).getItem());
            String directors = MovieDatabase.getDirector(ratings.get(i).getItem());
            int minutes = MovieDatabase.getMinutes(ratings.get(i).getItem());
            System.out.println(rate + " " + minutes + " \""  + movie + "\"\n\t" +  directors);
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters mr = new MovieRunnerWithFilters();
//        mr.printAverageRatings(35);
//        mr.printAverageRatingsByYear(2000, 20);
//        mr.printAverageRatingsByGenre("Comedy", 20);
//        mr.printAverageRatingsByMinutes(105, 135, 5);
//        mr.printAverageRatingsByDirectors("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack", 4);
//        mr.printAverageRatingsByYearAfterAndGenre(1990, "Drama", 8);
        mr.printAverageRatingsByDirectorsAndMinutes("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 90, 180, 3);
     }
}
