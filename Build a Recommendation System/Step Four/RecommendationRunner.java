import java.util.ArrayList;

public class RecommendationRunner implements Recommender {


    @Override
    public ArrayList<String> getItemsToRate() {

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> moviesToShow = new ArrayList<>();

        int numberOfMovies = 20;
        int max = movies.size() -1;

        do {

            String movieId = movies.get((int) ((Math.random() * max)));

            if ( !moviesToShow.contains(movieId) ) {
                moviesToShow.add(movieId);
            }

        } while (moviesToShow.size() < numberOfMovies);

        return moviesToShow;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {

        FourthRating fourthRating = new FourthRating();
        ArrayList<Rating> recommendations = fourthRating.getSimilarRatings(webRaterID, 20, 5);

        if (recommendations.size() < 5) {
            System.out.println("<h2 style=\"color: red;\">Ops! We couldn't find any recommendation for you this time, come back later</h2>");

        } else {

            String intro = String.format("<h2>We found %s recommendations for you!</h2>", recommendations.size());
            System.out.println(intro);

            StringBuilder message = new StringBuilder();

            //style
            message.append("<style>\n" +
                    "table, th, td {\n" +
                    "  border:1px solid black;\n" +
                    "}\n" +
                    "</style>");

            //table head
            message.append("<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <th>Title</th>\n" +
                    "    <th>Genre</th>\n" +
                    "    <th>Director</th>\n" +
                    "    <th>Minutes</th>\n" +
                    "    <th>Poster</th>\n" +
                    "  </tr>");

            for ( var recommendation : recommendations ) {

                Movie movie = MovieDatabase.getMovie(recommendation.getItem());

                //table data
                message.append(String.format(
                        "<tr>\n" +
                                "    <td>%s</td>\n" +
                                "    <td>%s</td>\n" +
                                "    <td>%s</td>\n" +
                                "    <td>%s</td>\n" +
                                "    <td><img src=%s /></td>\n" +
                                "  </tr>",
                        movie.getTitle(), movie.getGenres(), movie.getDirector(),
                        movie.getMinutes(), movie.getPoster()

                ));
            }

            message.append("</table>");
            System.out.println(message);
        }
    }
}
