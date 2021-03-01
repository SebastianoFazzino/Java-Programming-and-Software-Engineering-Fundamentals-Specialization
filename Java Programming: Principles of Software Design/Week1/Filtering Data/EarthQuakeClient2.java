import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Location denver = new Location(39.7392, -104.9903);
        Filter f0 = new MinMagFilter(4);
        Filter f1 = new MagnitudeFilter(3.5, 4.5);
        Filter f2 = new DepthFilter(-20000, -55000);
        Filter f3 = new DistanceFilter( denver, 1000000);
        Filter f4 = new PhaseFilter("end", "a");

        ArrayList<QuakeEntry> data1  = filter(list, f1);
        ArrayList<QuakeEntry> data2 = filter(data1, f2);
        for (QuakeEntry qe: data2) {
            System.out.println(qe);
        }
        System.out.println("\n" + data2.size() + " quakes found");
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        Location denmark = new Location(55.7308, 9.1153);
        MatchAll ma = new MatchAll();
        ma.addFilter(new MagnitudeFilter(0.0, 5.0));
        ma.addFilter(new DistanceFilter(denmark, 3000000));
        ma.addFilter(new PhaseFilter("any", "e"));

        ArrayList<QuakeEntry> data = filter(list, ma);
        for (QuakeEntry qe : data){
            System.out.println(qe);
        }
        System.out.println("\n" + data.size() + " quakes found");
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        Location tulsa = new Location(36.1314, -95.9372);
        MatchAll ma = new MatchAll();
        ma.addFilter(new MagnitudeFilter(0.0, 3.0));
        ma.addFilter(new DistanceFilter(tulsa, 10000000));
        ma.addFilter(new PhaseFilter("any", "Ca"));

        ArrayList<QuakeEntry> data = filter(list, ma);
        for (QuakeEntry qe : data){
            System.out.println(qe);
        }
        System.out.println("\n" + ma.getName());
        System.out.println("\n" + data.size() + " quakes found");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
