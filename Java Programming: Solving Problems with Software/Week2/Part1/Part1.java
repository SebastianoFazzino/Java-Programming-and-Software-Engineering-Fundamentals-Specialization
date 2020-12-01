public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int geneStart = dna.indexOf("ATG");
        if (geneStart == -1) {
            return "Invalid Gene Start";
        }
        int geneEnd = dna.indexOf("TAA", geneStart + 3);
        if (geneEnd == -1) {
            return "Invalid Gene End";
        }
        result = dna.substring (geneStart, geneEnd + 3);
        int resultLength = result.length();
        if (resultLength %  3 != 0) {
            result = "Invalid Gene Length";
        }
        return result;
      }

    public void testsFindSimpleGene() {
        String[] test = {"ATGATATAA", "TAGGCATT", "AATGTGACAGTAA", "TTAGTTGCGACTACATT", "CGATGATGGGCATAAA"};
        for (int i = 0; i < test.length; i++) {
            String result = findSimpleGene(test[i]);
            System.out.println(result);
        }
    }

    public static void main (String[] args) {
        Part1 dna = new Part1();
        dna.testsFindSimpleGene();
        
    }
}