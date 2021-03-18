
import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
			//System.out.println("Following words given the key " + key + ": " + follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (pos < myText.length) {
	    	int start = indexOf(myText, key, pos);
	    	if (start == -1 || start >= myText.length){
	    		break;
			}
	    	int nextIndex = start +1;
	    	if (nextIndex >= myText.length) {
	    		break;
			}
	    	String nextWord = myText[nextIndex];
	    	follows.add(nextWord);
	    	pos = nextIndex;
	    }
	    return follows;
    }

    public int indexOf(String[] words, String key, int start){
		for (int i = start; i < words.length; i++) {
			if (words[i].equals(key)) {
				return i;
			}
		}
		return -1;
	}

	public void testIndexOf(){
    	String[] text = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(indexOf(text, "this", 2));
	}

	public static void main(String[] args) {
		MarkovWordOne mwo = new MarkovWordOne();
		//mwo.testIndexOf();
	}

}
