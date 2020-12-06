

public class Part3 {
    
    public boolean twoOccurrencies(String stringA, String stringB) {
        // Initialize variables to use in the method logic
        boolean result = false;
        int index;
        int occurencies = 0;
        // Find out if stringA is present inside stringB
        index = stringB.indexOf(stringA);
        // Using a while loop, we determine how many time stringA is present inside stringB
        while (index >= 0) {
            index = stringB.indexOf(stringA, (index + stringA.length()));
            occurencies += 1;
            if (occurencies >= 2) {
                result = true;
            }
            else{
                result = false;
            }
        }
        // Return either true or false
        return result;
    }
    
    
    public String lastPart(String stringA, String stringB) {
        // Initialize result as an empty string
        String result = "";
        // Find out if stringA is present inside stringB
        int start = stringB.indexOf(stringA);
        // If stringA is not present inside stringB, result is equal to string B
        if (start == -1) {
            result = stringB;
        } 
        else {
            //if stringA is present in stringB, result is equal to the part of stingB that follows stringA 
            result = stringB.substring(start, stringB.length());
        }
        return result;
    }
    
    
    public void testTwoOccurrencies() {
        String[] test1 = {"on", "Bourbon, Orleans, Downtown, Online, honor"};
        String[] test2 = {"it", "Italian, Spanish, Irish, city"};
        String[] test3 = {"bee", "computer programming"};
        String[] test4 = {"an", "programming language"};
        
        boolean result1 = twoOccurrencies(test1[0], test1[1]);
        String lastString1 = lastPart(test3[0], test3[1]);
        System.out.println("Result: " + result1); 
        System.out.println("Last part: " + lastString1); 
        boolean result2 = twoOccurrencies(test2[0], test2[1]);
        String lastString2 = lastPart(test4[0], test4[1]);
        System.out.println("Result: " + result2);
        System.out.println("Last part: " + lastString2);
    }
    
      
     public static void main (String[] args) {
     Part3 occurrencies = new Part3();
     occurrencies.testTwoOccurrencies();
    }        
}
