import edu.duke.*;

public class PerimeterRunner {
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
    
    
    public int getNumPoints(Shape s) {
        int totPoints = 0;
        for (Point currPt : s.getPoints()) {
        totPoints += 1;
        }
        return totPoints;
    }
    
    
    public double averageLength(Shape s) {
        double averageL = getPerimeter(s) / getNumPoints(s);
        return averageL;
    }
    
    
    public double getLargestSide (Shape s) {
        // Start with totalPerim = 0
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double side = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (side > largestSide) {
                largestSide = side;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return largestSide;
    }

    
    public int getLargestX(Shape s) {
        int largestX = 0;
        for (Point currPt : s.getPoints()) {
            int x = currPt.getX();
            if (x > largestX) {
                largestX = x;
            }
        }
        return largestX;
    }
    
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int totPoints = getNumPoints(s);
        double averageL = averageLength(s);
        double largestSide = getLargestSide(s);
        int largestX = getLargestX(s);
        System.out.println("Shape perimeter = " + length);
        System.out.println("Number of points = " + totPoints);
        System.out.println("Average side's length = " + averageL);
        System.out.println("Largest Side = " + largestSide);
        System.out.println("Largest x value = " + largestX);
    }

    
    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
