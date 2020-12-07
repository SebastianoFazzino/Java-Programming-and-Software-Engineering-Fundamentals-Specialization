import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryToPrint = record.get("Country");
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            if(countryToPrint.contains(country)) {
                return (countryToPrint + ": " + exports + ": " + value); 
            }
        }
        return "NOT FOUND";
    }
    
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }
    
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numberOfExporters = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                numberOfExporters += 1;
            }
        }
        return numberOfExporters;
    }
    
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            int amountLength = record.get("Value (dollars)").length();
            if (amountLength > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }        
    }
    
    
    public void tester() {
        FileResource file = new FileResource();
        
        // Testing countryInfo method
        CSVParser parser = file.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        System.out.println();
        
        // Testing listExportersTwoProducts method
        parser = file.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println();
        
        // Testing numberOfExporters method
        parser = file.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        System.out.println();
        
        // Testing bigExporters method
        parser = file.getCSVParser();
        bigExporters(parser, "$999,999,999");
        System.out.println();
    }
}
