
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String phrase) {
        //This method counts the number of occurences of every letter of the alphabet in a given sentence "message" 
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < phrase.length(); i++){
           char ch = Character.toLowerCase(phrase.charAt(i));
           int index = alphabet.indexOf(ch);
           if(index != -1){
               counts[index] += 1;
           }
        }
        return counts;
    }
    
    
    public String decrypt(String encrypted) {
        // This method is used to decrypt one key messages
        CeasarCipher cc = new CeasarCipher();
        int dkey = getKey(encrypted);
        return cc.encrypt(encrypted, 26 - dkey);
    }
     
    
    public String decryptTwoKeys(String encrypted) {
        // This method is used to decrypt two keys messages
        CeasarCipher cc = new CeasarCipher();
        String halfOne = halfOfString(encrypted, 0);
        String halfTwo = halfOfString(encrypted, 1);
        int keyOne = getKey(halfOne); 
        int keyTwo = getKey(halfTwo);
        System.out.println("First Key: " + (26 - keyOne) + ", Second Key: " + (26 - keyTwo));
        String decrypted = cc.encryptTwoKeys(encrypted, keyOne, keyTwo);
 
        return decrypted;
    }
    
    
    public String halfOfString(String phrase, int start) {
        // This helper method, given a string a start index, return a new String that is every other character from message starting with the start position
        StringBuilder halfPhrase = new StringBuilder();
        for (int i = start; i < phrase.length(); i += 2 ) {
            halfPhrase.append(phrase.charAt(i));
        }
        return halfPhrase.toString();
    }
    
     
    public int indexOfMax(int[] counts){
        // This helper method given an integer array, returns the index with the highest value
        int maxIndex = 0;
        for(int i=0; i < counts.length; i++){
            if (counts[i] > counts[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
     }
     
    
    public int getKey(String s) {
        // This helper method takes a string as parameter and, assuming that 'e' is the most
        // common letter, it finds the key to decrypt the message
        int dkey;
        int[] counts = countLetters(s);
        int maxIndex = indexOfMax(counts);
        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
            return 26 - dkey;
        }    
        
        dkey = (4 - maxIndex) % 26;
        if(dkey < 0) {
           dkey *= -1;
        }
        return 26 - dkey;
    }
    
     
    public void testCountletters() {
        // We test countLetters method and print out every letter and its occurrencies
        int[] toPrint = countLetters("Qefp jbppxdb fp grpq x qbpq ql abzovmq jbqela, fq zbblkqxfkp jxkv bbbbbbbp, pl fq'p bxpfbo qlbb abzofmq.  23");
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < toPrint.length; i++) {
            System.out.println("Letter: " + alphabet.charAt(i) + " occurrencies: " + toPrint[i]);
        }
    }
    
  
    public void testDecrypt() {
        // We test all methos to make sure they work properly
        
        FileResource fr = new FileResource("test.txt");
        String message = fr.asString();

        System.out.println(decrypt("Ymnx rjxxflj nx ozxy f yjxy yt ijhwduy rjymti, ny htsyfnsx rfsd jjjjjjjx, xt ny'x jfxnjw yt ijhwnuy.  5"));
        System.out.println(decrypt("Bpqa umaaiom qa rcab i bmab bw lmkzgxb umbpwl, qb kwvbiqva uivg mmmmmmma, aw qb'a miaqmz bw lmkzqxb.  8"));
        System.out.println(decrypt("Guvf zrffntr vf whfg n grfg gb qrpelcg zrgubq, vg pbagnvaf znal rrrrrrrf, fb vg'f rnfvre gb qrpevcg.  13"));
        System.out.println(decrypt("Mabl fxlltzx bl cnlm t mxlm mh wxvkrim fxmahw, bm vhgmtbgl ftgr xxxxxxxl, lh bm'l xtlbxk mh wxvkbim.  19"));
        System.out.println(decrypt("Qefp jbppxdb fp grpq x qbpq ql abzovmq jbqela, fq zlkqxfkp jxkv bbbbbbbp, pl fq'p bxpfbo ql abzofmq.  23"));
        
        System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        System.out.println(decryptTwoKeys("Bcqn hmnavoz da rpao v omnb bj ymxztxo hmopjl, db kjvoidvn hiig mzmzmzmzmzmzmn, aj db'a mvadmm ow lzkmqkb"));
        
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    
    public static void main(String[] args) {
        CaesarBreaker cc = new CaesarBreaker();
        cc.testDecrypt();
        //cc.testCountletters();
    }
}
