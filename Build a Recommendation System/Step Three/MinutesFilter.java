
public class MinutesFilter implements Filter {
    private int myMinutesMin;
    private int myMinutesMax;


    public MinutesFilter(int min, int max) {
        myMinutesMin = min;
        myMinutesMax = max;
    }

    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return (minutes >= myMinutesMin && minutes <= myMinutesMax);
    }
}
