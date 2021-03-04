
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int minIdx = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() < quakeData.get(minIdx).getDepth())
                minIdx = i;
        }
        return minIdx;
    }

    public int sortByLargestDepth(ArrayList<QuakeEntry> in){
        int passes = 0;
        for (int i = 0; i < in.size(); i++) {
            int minIdx = getLargestDepth(in, i);
            QuakeEntry qe = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qe);
            passes += 1;
            if (passes == 70) break;
        }
        return passes;
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 0; i < numSorted; i++) {
            int j = i +1;
            if (quakeData.get(i).getMagnitude() > quakeData.get(j).getMagnitude()){
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qj = quakeData.get(j);
                quakeData.set(j, qi);
                quakeData.set(i, qj);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData){
        for (int i = quakeData.size() -1; i > 0; i--) {
            onePassBubbleSort(quakeData, i);

            /* Bubble sort testing
            for(QuakeEntry qe : quakeData){
                System.out.println(qe);
            }
            System.out.println("*********************************");
             */
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData){
        for (int i = 0; i < quakeData.size() -1; i++) {
            int j = i +1;
            if (quakeData.get(i).getMagnitude() > quakeData.get(j).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData){
        int howMany = 0;
        for (int i = quakeData.size() -1; i > 0; i--) {
             while (!checkInSortedOrder(quakeData)) {
                onePassBubbleSort(quakeData, i);
                howMany += 1;
            }
        }
        System.out.println("Number of passes: " + howMany);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakeData){
        System.out.println("Number of passes: " + sortByMagnitudePassesCounter(quakeData));
    }

    public int sortByMagnitudePassesCounter(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            passes += 1;
            if (checkInSortedOrder(in)) {
                return passes;
            }
        }
        return passes;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println(sortByLargestDepth(list));
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

    public static void main(String[] args) {
        QuakeSortInPlace qsip = new QuakeSortInPlace();
        qsip.testSort();
    }
}
