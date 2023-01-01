
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;
    public static final String RATINGS_FULL = "data/ratings.csv";


    public static void initialize() {
        if (ourRaters == null) {
            ourRaters= new HashMap<>();
            addRatings(RATINGS_FULL);
        }
    }

    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }

    public static Rater getRater(String id) {
        initialize();

        return ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters() {
        initialize();
        return new ArrayList<Rater>(ourRaters.values());
    }

    public static int size() {
        initialize();
        return ourRaters.size();
    }

}