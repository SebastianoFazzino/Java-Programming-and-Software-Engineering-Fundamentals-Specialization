
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {  
    public void totalBirths(FileResource fr) {
        // This method prints out the total number of boy and girl names in a given dataset
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
           if (record.get(1).equals("M")) {
               totalBoys += 1;
           }
           else {
               totalGirls += 1;
           }
        }
        System.out.println("Total names: " + (totalGirls + totalBoys));
        System.out.println("Total girl names: " + totalGirls);
        System.out.println("Total boy names: " + totalBoys);
    }
    
    
    public int getRank(int year, String name, String gender) {
        // This method returns the rank of a baby name in a given year
        int rank = -1;
        int lines = 0;
        int currBirths;
        int prevBirths = 0;
        FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                lines += 1;
                currBirths = Integer.parseInt(record.get(2));
                if (record.get(0).equals(name)) {
                    if (prevBirths != currBirths) {
                         rank = lines;
                    }
                    else {
                        rank = lines - 1;
                    }
                }
                prevBirths = currBirths;
            }
        }
        return rank;
    }
    
    
    public String getName(int year, int rank, String gender) {
       // Given a year, rank and gender, this method returns the name related 
       String babyName = "NO NAME";
       FileResource fr = new FileResource("testing/yob" + year + "short.csv");
       for (CSVRecord record : fr.getCSVParser(false)) {
           String babyGender = record.get(1);
           if (babyGender.equals(gender)) {
               int babyRank = getRank(year, record.get(0), gender);
               if (babyRank == rank && babyRank != -1) {
                   babyName = record.get(0);
                   return babyName;
               } 
           }
       }
       return babyName;
    }
    
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
       // Given a name, a gender and two different years, this method print out what the baby's name would be in a different year, cosidering the name rank
       String whatName = "";
       FileResource fr = new FileResource("testing/yob" + year + "short.csv");
       for (CSVRecord record : fr.getCSVParser(false)) {
          int rank = getRank(year, name, gender);
          whatName = getName(newYear, rank, gender);
       }
       System.out.println(name + " born in " +  year + " would be " +  whatName + " if he/she was born in " + newYear + ".");
    }
    
    
    public void yearOfHighestRank(String name, String gender) {
       // Given multiple datasets, this method print out the best rank and year of a given name
       int bestRank = 0; 
       int currentYear = 0;
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            for (CSVRecord record : fr.getCSVParser(false)) {
                 String filename = f.getName();
                 String find = "yob";
                 int startPos = filename.indexOf(find);
                 int year = Integer.parseInt(filename.substring(startPos + 3, startPos + 7));
                 int rank = getRank(year, name, gender);
                 if (bestRank == 0) {
                     bestRank = rank;
                     currentYear = year;
                 }
                 else {
                     if (rank != -1 && rank < bestRank) {
                         bestRank = rank;
                         currentYear = year;
                     }
                 } 
            }
       }
       System.out.println("rank " + bestRank + " on " + currentYear);
    }
    
    
    public double getAverageRank(String name, String gender) {
        // Given multiple datasets, this method returns the average rank value of a given name
        double avgRank = -1.0;
        int totRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            for (CSVRecord record : fr.getCSVParser(false)) {
                 String filename = f.getName();
                 String find = "yob";
                 int startPos = filename.indexOf(find);
                 int year = Integer.parseInt(filename.substring(startPos + 3, startPos + 7));
                 int rank = getRank(year, name, gender);
                 if (rank != -1) {
                     totRank += rank;
                     count += 1;
                 }
            }
        }
        if (count != 0) {
           avgRank = (double)totRank / count;
        }
        return avgRank;
    }
    
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
       // Given a name, this method returns the sum of all births with a higher name ranking 
        int totBirths = 0;
        FileResource fr = new FileResource("testing/yob" + year + "short.csv");
       for (CSVRecord record : fr.getCSVParser(false)) {
           if (record.get(1).equals(gender)) {
               totBirths += Integer.parseInt(record.get(2));
               if (record.get(0).equals(name)) {
                   totBirths -= Integer.parseInt(record.get(2));
                   return totBirths;
               }
           } 
       }
       return totBirths;
    }
    
    //We test the methods
    public void tests() {
       
        //System.out.println("Rank: " + getRank(2014, "William", "M"));
        
        //System.out.println("Name: " + getName(2013, 7, "M"));
        
        //whatIsNameInYear("Mason", 2012, 2014, "M");
        
        //yearOfHighestRank("Ethan", "M");
        
        //yearOfHighestRank("Emma", "F");
        
        //System.out.println(getAverageRank("Jacob", "M"));
        
        //System.out.println(getTotalBirthsRankedHigher(2014, "Noah", "M"));
    }
    
    
    public static void main (String[] args) {
        BabyNames testBabyNames = new BabyNames();
        testBabyNames.tests();
    }
}



