public class DirectorsFilter implements Filter{

    private String myDirectors;

    public DirectorsFilter(String directors) { myDirectors = directors; }

    @Override
    public boolean satisfies(String id) {
        String[] directorsList = myDirectors.split(",+");
        for (String s : directorsList) {
            if (MovieDatabase.getDirector(id).contains(s)) {
                return true;
            }
        }
        return false;
    }
}
