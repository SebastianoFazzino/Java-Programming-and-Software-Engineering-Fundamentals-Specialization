

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
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
}
