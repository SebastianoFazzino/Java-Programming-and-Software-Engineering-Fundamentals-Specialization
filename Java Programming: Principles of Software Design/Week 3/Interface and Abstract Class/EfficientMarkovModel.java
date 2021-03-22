import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int markovGrade;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        markovGrade = N;
        n = N;
        map = new HashMap<String, ArrayList<String>>();

    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }


    public void buildMap(){
        for (int i = 0; i < myText.length() - (markovGrade -1); i++) {
            String followingChar = "";
            String key = myText.substring(i, i + markovGrade);
            if( (i + markovGrade) < myText.length()) {
                followingChar = myText.substring(i + markovGrade, i + (markovGrade + 1));
            }
            if (map.containsKey(key)) {
                map.get(key).add(followingChar);
                //System.out.println(map.keySet());
            } else {
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(followingChar);
                map.put(key, follows);
            }
        }
    }

    public ArrayList<String> getFollows(String key){
        return map.get(key);
    }

    @Override
    public String getRandomText(int numChars){
        if (myText == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovGrade);
        String key = myText.substring(index, index + markovGrade);
        sb.append(key);
        for (int i = 0; i < numChars - markovGrade; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null) {
                continue;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }


    @Override
    public String toString(){
        return "EfficientMarkovModel of order " + n;
    }

    public int findLargestValueInMap() {
        int largest = 0;
        for (String s : map.keySet()) {
            int current = map.get(s).size();
            if ( current > largest ) {
                largest = current;
            }
        }
        return largest;
    }

    public ArrayList<String> findKeysWithLargestValue(){
        ArrayList<String> keys = new ArrayList<>();
        for (String s : map.keySet()) {
            int largest = findLargestValueInMap();
            if (map.get(s).size() == largest){
                keys.add(s);
            }
        }
        return keys;
    }

    public void printHashMapInfo(){
        int size = map.size();
        if (size < 30) {
            for (String s : map.keySet()) {
                System.out.println(s + " " + map.get(s));
            }
        }
        System.out.println("Number of keys in HashMap: " + size);
        System.out.println("Size of the largest value in Hashmap: " + findLargestValueInMap());
        System.out.println("Keys containing the largest value in HashMap: " + findKeysWithLargestValue());
    }
}
