
public class Part3 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != - 1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        if (currIndex == -1) {
            return -1;
        }
        
        return currIndex;
   }
      
   
   public String findGene(String dna, int staringPoint) {
       int startCodon = dna.toUpperCase().indexOf("ATG", staringPoint);
       if (startCodon == -1) {
           return "";
       }
       int taaIndex = findStopCodon(dna.toUpperCase(), startCodon + 3, "TAA");
       int tagIndex = findStopCodon(dna.toUpperCase(), startCodon + 3, "TAG");
       int tgaIndex = findStopCodon(dna.toUpperCase(), startCodon + 3, "TGA");
       int minIndex = 0;
       
       if (taaIndex == -1 || tgaIndex != -1 && tgaIndex < taaIndex) {
           minIndex = tgaIndex;
       }
       else {
           minIndex = taaIndex;
       }
       
       if (minIndex == -1 || tagIndex != -1 && tagIndex < minIndex) {
           minIndex = tagIndex;
       }
       
       if (minIndex == -1) {
           return "";
       }
       return dna.substring(startCodon, minIndex + 3);
   }
   
   
   public void findAllGenes(String dna) {
       int startIndex = 0;
       while (true) {
           String currentGene = findGene(dna, startIndex);
           
           if (currentGene.isEmpty()) {
               break;
           }
           System.out.println(currentGene);
           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
       }
   }
    
   
   public int countGenes(String dna) {
       int count = 0;
       int startIndex = 0;
       while (true) {
           String currentGene = findGene(dna, startIndex);
           
           if (currentGene.isEmpty()) {
               break;
           }
           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
           count += 1;
       }
       return count;
   }
   
   
   public void testCountGenes() {
        String[] tests = {"ATGCGGTAACATGGCAGACTAGA", "GCACATGCGGTGAAGATGCGATAAG", "AGCAtgCggAcatagg", "TAATAGATGAGGTCTAAG", "atatagcggatgcgataag"};
        for (int i = 0; i < tests.length; i ++) {
            System.out.println("number of genes found on DNA strand number " + (i + 1) + ":");
            System.out.println(countGenes(tests[i]));
            System.out.println();
        }     
   }
   
   
   public static void main (String[] args) {
        Part3 test = new Part3();
        test.testCountGenes();
   }
}
