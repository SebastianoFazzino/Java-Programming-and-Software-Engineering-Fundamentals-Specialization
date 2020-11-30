import edu.duke.*;
import java.io.File;


public class MultipleShapesProcessing {
    
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
  
    
    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        // Initialize largestPerim with value 0.0
        double largestPerim = 0.0;
        // Iterate trhough the files selected
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            // For each file create a Shape object
            Shape s = new Shape(fr);
            // Calculate the perimeter of the shapes using getPerimeter() method
            double perim = getPerimeter(s);
            if (perim > largestPerim) {
                // Update largestPerim
                largestPerim = perim;
            }
        }
        // return the largest perimeter
        return largestPerim;
    }

    
    public void testGetLargestPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + largestPerimeter);
    }
    
    
    public static void main (String[] args) {
        MultipleShapesProcessing msp = new MultipleShapesProcessing();
        msp.testGetLargestPerimeterMultipleFiles();
   }
}
