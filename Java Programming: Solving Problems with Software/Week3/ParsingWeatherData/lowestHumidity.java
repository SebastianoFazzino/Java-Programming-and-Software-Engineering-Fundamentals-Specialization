
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class lowestHumidity {

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        // This method finds the lowest humidity value in a given day
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if ( lowestSoFar == null && currentRow.get("Humidity") != "N/A" ) {
                lowestSoFar = currentRow;
            }
            else {
                if ( currentRow.get("Humidity") != "N/A" ) {
                    double currentHum = Double.parseDouble(currentRow.get("Humidity"));
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
