import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2){
        int last1 = q1.getInfo().lastIndexOf(" ") + 1;
        int last2 = q2.getInfo().lastIndexOf(" ") + 1;
        int output = (q1.getInfo().substring(last1)).compareTo(q2.getInfo().substring(last2));
        if (output == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return output;
    }
}
