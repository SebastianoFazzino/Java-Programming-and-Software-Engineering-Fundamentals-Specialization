
public class Part2 {
    
    public int howMany(String stringA, String stringB) {
        int occurencies = 0;
        int index = 0;   
        while ( index != -1 ) {
            index = stringB.indexOf(stringA, index);
            if (index != -1 ) {
                occurencies += 1;
                index = stringB.indexOf(stringA, index + stringA.length());
            }
        }
        return occurencies;
     }
    
    
     public void testHowMany() {
        String[] stringsList = {"Hello world", "Java is cool!", "Python is easy", "I love programming"};
        for (int i = 0; i < stringsList.length; i++) {
            System.out.println("String: " + stringsList[i] + "; Results found:");
            System.out.println(howMany("o", stringsList[i]));
        }
        
     }
    
     public static void main (String[] args) {
        Part2 test = new Part2();
        test.testHowMany();
   }
}
