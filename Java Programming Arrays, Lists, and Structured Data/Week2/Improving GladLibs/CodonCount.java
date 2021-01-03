import java.util.*;

public class CodonCount {
    // We create a new HashMap to map dna codons to their count
    private HashMap<String,Integer> codonCountMap;
    // We write a constructor to initialize the HashMap variable.
    public CodonCount(){
        codonCountMap = new HashMap<String,Integer>();
    }


    public void buildCodonMap(int start, String dna){
        /*
        his method will build a new map of codons mapped to their counts from
        the string dna with the reading frame with the position start (a value of 0, 1, or 2)
         */
        codonCountMap.clear();
        String dnaUpper = dna.toUpperCase().trim();
        while ( start + 3 <= dnaUpper.length() ) {
            String codon = dnaUpper.substring(start, start + 3);
            if (codonCountMap.keySet().contains(codon)) {
                codonCountMap.put(codon, codonCountMap.get(codon) + 1);
                start += 3;
            } else {
                codonCountMap.put(codon, 1);
                start += 3;
            }
        }
    }


    public void getMostCommonCodon(){
        // This method returns a String, the codon in a reading frame that has the largest count
        int count = 0;
        String codon = "";
        for ( String s : codonCountMap.keySet() ){
            if (codonCountMap.get(s) > count) {
                count = codonCountMap.get(s);
                codon = s;
            }
        }
        System.out.println("Most common codon: " + codon + ", found " + count + " times");
    }


    public void printCodonCounts(int start, int end){
        /*
        This method prints all the codons in the HashMap along with their counts 
        if their count is between start and end, inclusive
         */
        for ( String s : codonCountMap.keySet() ){
            int count = codonCountMap.get(s);
            if ( count >= start &&  count <= end) {
                System.out.println(s + ": " + count);
            }
        }
    }


    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        String dna =  "CGTTCAAGTTCAA ";
        for (int i = 0; i < 3; i++) {
            cc.buildCodonMap(i, dna);
            System.out.println("Dna Strand number: " + (i + 1));
            cc.getMostCommonCodon();
            cc.printCodonCounts(1, 5);
        }
    }

}

