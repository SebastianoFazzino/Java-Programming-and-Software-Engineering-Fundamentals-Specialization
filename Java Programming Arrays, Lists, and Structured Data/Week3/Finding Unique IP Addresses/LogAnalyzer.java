

import java.util.*;
import edu.duke.*;

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
         for (LogEntry le : records){
             if (le.getStatusCode() > number){
                 System.out.println(le);
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


     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
}
