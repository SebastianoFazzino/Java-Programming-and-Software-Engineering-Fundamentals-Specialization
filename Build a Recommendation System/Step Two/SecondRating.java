import java.util.*;

public class SecondRating {
    private final ArrayList<Movie> myMovies;
    private final ArrayList<Rater> myRaters;


    public SecondRating() {
        FirstRating fr = new FirstRating();
        myMovies = fr.loadMovies(FirstRating.MOVIES_FULL);
        myRaters = fr.loadRaters(FirstRating.RATINGS_FULL);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private double getAverageByID(String ID, int minimalRaters){
        double avgRating = 0.0;
        int howMany = 0;
        for (Rater myRater : myRaters) {
            ArrayList<String> movieID = myRater.getItemsRated();
            for (String s : movieID) {
                if (s.equals(ID)) {
                    howMany += 1;
                    double rate = myRater.getRating(ID);
                    avgRating += rate;
                }
            }
        }
        if (howMany >= minimalRaters){
            avgRating = avgRating / howMany;
            return avgRating;
        }
        return 0.0;
    }

    private boolean containsID(ArrayList<Rating> rating, String movieID){
        for (Rating value : rating) {
            if (value.getItem().equals(movieID)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Rating> sorter(ArrayList<Rating> ratings){
        ArrayList<Rating> out = new ArrayList<>(ratings);
        Collections.sort(out);
        return out;
    }

    public ArrayList<Rating> getAverageRatings(int minimumRatings){
        ArrayList<Rating> rating = new ArrayList<>();
        for (Rater myRater : myRaters) {
            ArrayList<String> list = myRater.getItemsRated();
            for (String movieID : list) {
                double rate = this.getAverageByID(movieID, minimumRatings);
                if (rate != 0.0 && !this.containsID(rating, movieID)) {
                    Rating current = new Rating(movieID, rate);
                    rating.add(current);
                }
            }
        }
        rating = this.sorter(rating);
        return rating;
    }

    public String getTitle(String ID){
        for (Movie myMovie : myMovies) {
            String movieID = myMovie.getID();
            if (movieID.equals(ID)) {
                return myMovie.getTitle();
            }
        }
       return "Movie ID not found";
    }

    public String getID(String movie){
        for (Movie myMovie : myMovies) {
            String title = myMovie.getTitle();
            if (title.equals(movie)) {
                return myMovie.getID();
            }
        }
        return "No such title";
    }
}