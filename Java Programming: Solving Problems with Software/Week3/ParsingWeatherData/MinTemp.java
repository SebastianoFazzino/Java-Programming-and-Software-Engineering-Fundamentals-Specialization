import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MinTemp {
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // This method finds the coldest temperature in a given day
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (coldestSoFar == null && currentRow.get("TimeEST") != "-9999") {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp) {
                    coldestSoFar = currentRow;
                }
        }
        }
        return coldestSoFar;
    }
    
     /*
       We test coldestHourInFile method passing the records from a specific day as
       argument of FileResource
     */ 
    public void testcoldestHourInFile() {
        FileResource file = new FileResource("weather-2014-01-01.csv");
        CSVRecord coldest = coldestHourInFile(file.getCSVParser());
        System.out.println("Coldest temperature registered: " + coldest.get("TemperatureF")
                            + ", date and time: " + coldest.get("DateUTC"));
    }
}
