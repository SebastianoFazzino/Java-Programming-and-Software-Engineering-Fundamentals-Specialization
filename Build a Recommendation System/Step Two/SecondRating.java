import java.util.*;

public class SecondRating {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private HashMap<String,Integer> myMap;

    public SecondRating() {
        myMovies = new ArrayList<>();
        myRaters = new ArrayList<>();
        myMap = new HashMap<>();
    }

    public SecondRating(String moviefile, String ratingfile) {
        FirstRating fr = new FirstRating();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingfile);
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
        for (int i = 0; i < myRaters.size(); i++) {
            ArrayList<String> movieID = myRaters.get(i).getItemsRated();
            for (String s : movieID) {
                if (s.equals(ID)) {
                    howMany += 1;
                    double rate = myRaters.get(i).getRating(ID);
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
        for (int i = 0; i < rating.size(); i++) {
            if (rating.get(i).getItem().equals(movieID)){
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

    public ArrayList<Rating> getAverageRatings(int mininmalRaters){
        ArrayList<Rating> rating = new ArrayList<>();
        for (int i = 0; i < myRaters.size(); i++) {
            ArrayList<String> list = myRaters.get(i).getItemsRated();
            for (String movieID : list){
                double rate = getAverageByID(movieID, mininmalRaters);
                if (rate != 0.0 && !containsID(rating, movieID)) {
                    Rating current = new Rating(movieID, rate);
                    rating.add(current);
                }
            }
        }
        rating = sorter(rating);
        //System.out.println(rating.size());
        return rating;
    }

    public String getTitle(String ID){
        for (int i = 0; i < myMovies.size(); i++) {
            String movieID = myMovies.get(i).getID();
            if (movieID.equals(ID)) {
                return myMovies.get(i).getTitle();
            }
        }
       return "Movie ID not found";
    }

    public String getID(String movie){
        for (int i = 0; i < myMovies.size(); i++) {
            String title = myMovies.get(i).getTitle();
            if (title.equals(movie)){
                return myMovies.get(i).getID();
            }
        }
        return "No such title";
    }
}