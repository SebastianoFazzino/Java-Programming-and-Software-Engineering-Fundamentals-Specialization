

import java.util.*;
import edu.duke.*;

import javax.print.DocFlavor;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }


     public void readFile(String filename) {
         records.clear();
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }


     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }


     public void printAllHigherThanNum(int number){
         System.out.println("Log entries with a status code greater than " + number + ":");
         int i = 1;
         for (LogEntry le : records){
             if (le.getStatusCode() > number){
                 System.out.println(i + ". " + le);
                 i++;
             }
         }
     }


    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
       ArrayList<String> IPsOnDay = new ArrayList<String>();
       for (LogEntry le : records){
           String day = le.getAccessTime().toString();
           if (day.indexOf(someday) != -1){
               String IP = le.getIpAddress();
               if (!IPsOnDay.contains(IP)) {
                   IPsOnDay.add(IP);
               }
           }
       }
       return IPsOnDay;
    }


    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> IPsOnDay = new ArrayList<String>();
         for (LogEntry le : records){
             int value = le.getStatusCode();
             if (value >= low && value <= high){
                 String IP = le.getIpAddress();
                if (!IPsOnDay.contains(IP)){
                    IPsOnDay.add(IP);
                }
             }
         }
         return IPsOnDay.size();
    }


    public HashMap<String,Integer> countVisitPerIP(){
         /*
         This method returns a HashMap<String, Integer> that maps an IP address to the number of times
         that IP address appears in records, meaning the number of times this IP address visited the website
          */
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             if (!counts.containsKey(IP)){
                 counts.put(IP, 1);
             }
             else {
                 counts.put(IP, counts.get(IP) + 1);
             }
         }
         return counts;
    }


    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         /*
          This method returns the maximum number of visits to this website by a single IP address
          */
         int max = 0;
         for(String IP : counts.keySet()){
             int value = counts.get(IP);
             if(value > max){
                 max = value;
             }
         }
         return max;

    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> IPsMap){
         /*
         This method returns an ArrayList of Strings of IP addresses that all
          have the maximum number of visits to this website
          */
         ArrayList<String> IPs = new ArrayList<String>();
         for(String s : IPsMap.keySet()){
             int max = mostNumberVisitsByIP(IPsMap);
             if( IPsMap.get(s) == max ){
                 IPs.add(s);
             }
         }
         return IPs;
     }



    public HashMap<String,ArrayList<String>> iPsForDays(){
         /*
         This method returns a HashMap<String, ArrayList<String>> that uses records and maps
         days from web logs to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses)
          */
         HashMap<String,ArrayList<String>> counts = new HashMap<String,ArrayList<String>>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString().substring(4, 10);
             String IP = le.getIpAddress();
             ArrayList<String> ipList = new ArrayList<String>();
             if(!counts.containsKey(date)){
                 ipList.add(IP);
                 counts.put(date, ipList);
             }
             else{
                 ipList = counts.get(date);
                 ipList.add(IP);
                 counts.put(date, ipList);
             }
         }
         return counts;
    }

     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
         /*
         This method returns the day that has the most IP address visits
          */
         String date = "";
         int max = 0;
         for(String s : map.keySet()){
             if(map.get(s).size() > max){
                 date = s;
             }
         }
         return date;
     }


    
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day){
        /*
        This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day
        */
         ArrayList<String> IPs = map.get(day);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();

         for (int i = 0; i < IPs.size(); i++) {
             String ipAddress = IPs.get(i);
             if (!counts.containsKey(ipAddress)) {
                 counts.put(ipAddress, 1);
             }
             else {
                 int currentIpCount = counts.get(ipAddress);
                 counts.put(ipAddress, currentIpCount + 1);
             }
         }
         return iPsMostVisits(counts);
     }


     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
}
