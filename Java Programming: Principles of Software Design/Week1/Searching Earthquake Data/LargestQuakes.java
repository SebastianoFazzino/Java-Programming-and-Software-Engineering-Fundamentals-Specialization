import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        //for (int i = 0; i < list.size(); i++) {
        //    System.out.println(list.get(i));
        //}

        //int index = indexOfLargest(list);
        //System.out.println("Index of largest quake: " + index);
        //System.out.println(list.get(index));

        int howMany = 10;
        ArrayList<QuakeEntry> largest = getLargest(list, howMany);
        for (int i = 0; i < largest.size(); i++) {
            System.out.println(largest.get(i));
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
       int index = -1;
       double magnitude = 0.0;

        for (int i = 0; i < data.size(); i++) {
            double current = data.get(i).getMagnitude();

            if (current > magnitude){
                magnitude = current;
                index = i;
            }
        }
       return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        int index = -1;

        for (int i = 0; i < howMany; i++) {
            for (QuakeEntry qe : copy) {
                index = indexOfLargest(copy);
            }
            answer.add(copy.get(index));
            copy.remove(index);
        }
        return answer;
    }
}
