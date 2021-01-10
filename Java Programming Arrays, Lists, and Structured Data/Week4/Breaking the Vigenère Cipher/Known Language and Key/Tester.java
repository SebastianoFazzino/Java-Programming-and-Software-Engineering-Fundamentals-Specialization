import edu.duke.*;
 import java.io.File;

public class Tester {
    public void testCaesar(){
        // testing Caesar Cipher
        FileResource fr = new FileResource("titus-small.txt");
        String toEncrypt = fr.asString();
        CaesarCipher cc = new CaesarCipher(22);
        System.out.println(cc.encrypt(toEncrypt));

        // testing Caesar Cracker
        FileResource fr2 = new FileResource("titus-small_key5.txt");
        String toDecrypt = fr2.asString();
        CaesarCracker cc2 = new CaesarCracker();
        System.out.println(cc2.decrypt(toDecrypt));
        System.out.println("Key used: " + cc2.getKey(toDecrypt));
    }

    public void testVigenereCipher(){
        FileResource fr = new FileResource("titus-small.txt");
        String toEncrypt = fr.asString();
        int[] keys = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println(vc.encrypt(toEncrypt));
    }

    public void testVigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        //System.out.println(vb.sliceString("abcdefghijklm",2,5));

        FileResource fr = new FileResource("secretmessage1.txt");
        String toDecrypt = fr.asString();
        int[] key = vb.tryKeyLength(toDecrypt, 4, 'e');
        System.out.println("Keys used:");
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i] + " ");
        }
        System.out.println();
        vb.breakVigenere();
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        //test.testCaesar();
        //test.testVigenereCipher();
        test.testVigenereBreaker();
    }
}
