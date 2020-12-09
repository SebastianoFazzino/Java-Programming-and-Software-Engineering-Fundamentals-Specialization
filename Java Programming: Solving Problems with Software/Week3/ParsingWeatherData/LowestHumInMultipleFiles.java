import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class LowestHumInMultipleFiles {
    
    public CSVRecord lowestHumidityInManyFiles() {
        // This method finds the lowest humidity value given many file records
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
        
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        // This method finds the lowest humidity value in a given day
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        // This helper method is used to update the lowest humidity value
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        else {
            String humidity = currentRow.get("Humidity");
            if (humidity.contains("N/A") == false) {
                double currentHum = Double.parseDouble(humidity);
                double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentHum < lowestHum) {
                        lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    

    public void testLowestHumidityInManyFiles() {
        // We test lowestHumidityInFile method passing multiple files as argument of fileResource
        CSVRecord lowestHum = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHum.get("Humidity") + " at " + lowestHum.get("DateUTC"));
    }
}
