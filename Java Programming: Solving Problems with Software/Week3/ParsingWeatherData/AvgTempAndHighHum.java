
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class AvgTempAndHighHum {
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        // This function return the average temperature of a day given a certain humidity value
        double avgTemp;
        int count = 0;
        double tempSum = 0;
        for (CSVRecord currentRow : parser) {
            double humidityValue = Double.parseDouble(currentRow.get("Humidity"));
            if (humidityValue >= value) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                tempSum += currentTemp;
                count += 1;
            }
        }
        avgTemp = tempSum / count;
        return avgTemp;
    }
    
    
    public void testaverageTemperatureWithHighHumidityInFile() {
        // We test averageTemperatureWithHighHumidityInFile passing a record file as argument of fileResource and a humidity value
        FileResource file = new FileResource();
        // Changing the umidity value, most likely the temperature average will also change
        int humidityValue = 80;
        double avgTemp = averageTemperatureWithHighHumidityInFile(file.getCSVParser(), humidityValue);
        if (Double.isNaN(avgTemp)) {
            System.out.println("No temperatures with that humidity");
        }  
        else {
            System.out.println("Average temperature when high Humidity is: " + avgTemp);
        }
    }
}
