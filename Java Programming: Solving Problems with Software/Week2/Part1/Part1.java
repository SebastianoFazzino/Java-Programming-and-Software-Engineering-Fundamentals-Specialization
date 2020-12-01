public class Part1 {
    public String findSimpleGene(String dna) {
        // Initialize an empty string result
        String result = "";
        // Find the beginning of the gene in the dna string
        int geneStart = dna.indexOf("ATG");
        // If we can't find 'ATG' in the dna string, we know there's no gene present in that string
        if (geneStart == -1) {
            return "Invalid Gene Start";
        }
         // If we can't find 'TAA' in the dna string, we know there's no gene present in that string
        int geneEnd = dna.indexOf("TAA", geneStart + 3);
        if (geneEnd == -1) {
            return "Invalid Gene End";
        }
        // We check if the gene has a valid length
        result = dna.substring (geneStart, geneEnd + 3);
        int resultLength = result.length();
        if (resultLength %  3 != 0) {
            result = "Invalid Gene Length";
        }
        
        return "DNA Gene Found: " + result;
      }

    public void testsFindSimpleGene() {
        // Create an array of dna strings for testing our code
        String[] test = {"ATGATATAA", "TAGGCATT", "AATGTGACAGTAA", "TTAGTTGCGACTACATT", "CGATGATGGGCATAAA"};
        // Iterate through the strings in the array
        for (int i = 0; i < test.length; i++) {
            // Return a result string for each dna string
            String result = findSimpleGene(test[i]);
            System.out.println(result);
        }
    }

    public static void main (String[] args) {
        Part1 dna = new Part1();
        dna.testsFindSimpleGene();
        
    }
}
