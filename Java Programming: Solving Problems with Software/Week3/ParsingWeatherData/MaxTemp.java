
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MaxTemp {
    
    public CSVRecord hottestHourInFile(CSVParser parser) {
        // This method finds the hottest temperature in a given day
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);    
        }
        return largestSoFar;
    }
    
    
    public CSVRecord hottestInManyDays() {
        // This method finds the hottest temperature given different records from many days
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);    
        }
        return largestSoFar;
    }
        
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        // This helper method is used to update the largest temperature
        if(largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                
            if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
            }
        }  
        return largestSoFar;
    }
    
     /*
    we test hottestHourInFile method passing the records from a specific day as
    argument of FileResource
     */ 
    public void testhottestHourInFile() {
        FileResource file = new FileResource("weather-2014-01-01.csv");
        CSVRecord largest = hottestHourInFile(file.getCSVParser());
        System.out.println("Hottest temperature registered: " + largest.get("TemperatureF")
                            + ", date and time: " + largest.get("DateUTC"));
    }
        
     /*
    we test hottestHourIManyDays method passing the records from different days as
    argument of FileResource
     */
    public void testhottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature registered: " + largest.get("TemperatureF")
                            + ", date and time: " + largest.get("DateUTC"));
    }
}
