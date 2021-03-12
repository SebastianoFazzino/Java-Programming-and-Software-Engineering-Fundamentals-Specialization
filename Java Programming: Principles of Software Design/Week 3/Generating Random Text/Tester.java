import java.util.ArrayList;
import edu.duke.*;

public class Tester {

    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        // we test the method getFollows with a sample string
        mo.setTraining("this is a test yes this is a test.");
        ArrayList<String> ch = mo.getFollows("th");
        System.out.println(ch.size());
        System.out.println(ch);

    }

    public void testGetFollowsWithFile(){
        // we import an external text file
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne mo = new MarkovOne();
        // we set the file as training string for markovOne
        mo.setTraining(st);
        // we set the key
        String key = "t";
        // we test getFollows method on the file
        ArrayList<String> follows = mo.getFollows(key);
        // we print the total number of following characters found for a given key
        System.out.println(follows.size());
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        //test.testGetFollows();
        test.testGetFollowsWithFile();

    }
}
