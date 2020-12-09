
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class AverageTemp {
    
    public double averageTemperatureInFile(CSVParser parser) {
        // This method returns the average temperature value in a given day
        double avgTemp;
        int count = 0;
        double tempSum = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            tempSum += currentTemp;
            count += 1;
        }
        avgTemp = tempSum / count;
        return avgTemp;
    }
    
    
    public void testaverageTemperatureInFile() {
        // We test averageTemperatureInFile passing a record file as argument of fileResource
        FileResource file = new FileResource();
        System.out.println("Average temperature: " + averageTemperatureInFile(file.getCSVParser()));
        
    }
}
