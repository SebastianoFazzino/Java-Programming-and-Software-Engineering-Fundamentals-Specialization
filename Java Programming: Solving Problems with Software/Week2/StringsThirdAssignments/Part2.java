public class Part2 {
    
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
   
   
   public int countCTG(String dna) {
       int index = 0;
       int ctgCount = 0;
       while ( index != -1 ) {
           index = dna.toUpperCase().indexOf("CTG", index);
           if (index != -1) {
               ctgCount += 1;
               index = dna.toUpperCase().indexOf("CTG", index + 3);
           }
       }
       return ctgCount;
   }

   
   public void tests() {
       String[] dnaStrands = {"ATctgAaaCTGcCTGCGCTGT","ACTGCGGTAACACTGGCAGACTAGA", "GCACAcTGCGGTGAAGATGCGATAAG", "AGCACtgCtggAcatagg", "TAATAGAcTGAGGTCTAAG", "atatagTcggactgcgataag"};
       for (int i = 0; i < dnaStrands.length; i++) {
           System.out.println("DNA Strand tested: " + dnaStrands[i]);
           System.out.println("CTG Codon Ratio: " + countCTG(dnaStrands[i]));
           System.out.println("CG Ratio: " + cgCount(dnaStrands[i]));
           System.out.println();
       }
   }
   
   public static void main (String[] args) {
        Part2 test = new Part2();
        test.tests();
   }
}