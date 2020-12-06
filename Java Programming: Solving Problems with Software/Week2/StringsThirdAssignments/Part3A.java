import edu.duke.*;

public class Part3A {
    
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
   
   
   public StorageResource getAllGenes(String dna) {
       StorageResource geneList = new StorageResource();
       int startIndex = 0;
       while (true) {
           String currentGene = findGene(dna, startIndex);
           
           if (currentGene.isEmpty()) {
               break;
           }
           geneList.add(currentGene);

           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
       }
       return geneList;
   }
    
   
   public float cgCount(String dna) {
       int indexC = 0;
       int indexG = 0;
       int cCount = 0;
       int gCount = 0;
       while ( indexC != -1 ) {
           indexC = dna.toUpperCase().indexOf("C", indexC);
           if (indexC != -1 ) {
               cCount += 1;
               indexC = dna.toUpperCase().indexOf("C", indexC + 1);
           }
       }
       
       while ( indexG != -1 ) {
           indexG = dna.toUpperCase().indexOf("G", indexG);
           if (indexG != -1 ) {
               gCount += 1;
               indexG = dna.toUpperCase().indexOf("G", indexG + 1);
           }
       }
       
       float ratio = ((float)cCount + gCount) / dna.length();
       return ratio;
   }
   
   
   public void processGenes(String[] DNAStrands) {
       int longerThanNine = 0;
       int highGCRatio = 0;
       int longestGene = 0;
       
       for (int i = 0; i < DNAStrands.length; i++) {
           StorageResource genes = getAllGenes(DNAStrands[i]);
           for (String gene : genes.data()) {
               
               if (gene.length() > 9) {
                   longerThanNine += 1;
                   System.out.println(gene);
                }
             
               if (cgCount(gene) > 0.35 ) {
                   highGCRatio += 1;
                   System.out.println(gene);
               }
               
               if (gene.length() > longestGene) {
                   longestGene = gene.length();
               }
           }
       }
       System.out.println("Number of genes longer than 9: " + longerThanNine);
       System.out.println("Number of genes with a GC Ratio higher than 0.35: " + highGCRatio);
       System.out.println("Length of largest gene found: " + longestGene);
   }
   
   
   public void testprocessGenes() {
       String[] tests = {"ATGCGGTAACATGGCAGACTAGA", "GCACATGCGGTGAAGATGCGATAAG", "AGCAtgCggAcatagg", "TAATAGATGAGGTCTAAG", "atatagcggatgcgataag", "catgatatacccttagcatggcagtttag"};
       processGenes(tests);
   }
   
  
   public static void main (String[] args) {
        Part3A test = new Part3A();
        test.testprocessGenes();
   }
}
