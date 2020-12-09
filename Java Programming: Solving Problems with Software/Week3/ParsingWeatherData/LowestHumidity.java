import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class LowestHumidity {

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        // This method finds the lowest humidity value in a given day
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
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
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        // We test lowestHumidityInFile method passing the records from a specific day as argument of FileResource
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
}
