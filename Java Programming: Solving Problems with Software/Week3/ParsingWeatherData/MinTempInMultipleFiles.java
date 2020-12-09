import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MinTempInMultipleFiles {
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // This method finds the coldest temperature in a given day
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    
    public String fileWithColdestTemperature() {
        // Given many file records, this method return the name of the file where the coldest temperature has been found
        CSVRecord coldestSoFar = null;
        String coldestDay = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar); 
            coldestDay = f.getName();
        }
        return coldestDay;
    }
    
    
    public void printAllTemperaturesInFile(String fileName) {
        // This helper method is used to print all the temperatures with related date/time in a given file
        FileResource file = new FileResource(fileName);
        for(CSVRecord record : file.getCSVParser()){
            System.out.println(record.get("DateUTC") + " " +  record.get("TemperatureF"));
        }
    }
    
    
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar) {
        // This helper method is used to update the coldest temperature
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
        return coldestSoFar;
    }
    
    /* 
     * We test fileWithColdestTemperature passing the records from different days as argument of FileResource
     * the method return a filename and we print out all the temperatures with related date/time in that file
     */
    public void testfileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);
        System.out.println("All the Temperatures on the coldest day were:");
        printAllTemperaturesInFile(fileName);
    }
}
