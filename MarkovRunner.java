
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {

	public String generateTrainingString(){
		// this function generates a training string from a given file
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		return st;
	}

    public void runMarkovZero() {
		MarkovOne markov = new MarkovOne();
		markov.setTraining(generateTrainingString());
		markov.setRandom(101);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovOne() {
		MarkovOne markov = new MarkovOne();
		markov.setTraining(generateTrainingString());
		//markov.setRandom(42);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovFour() {
		MarkovFour markov = new MarkovFour();
		markov.setTraining(generateTrainingString());
		markov.setRandom(25);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovModel() {
		MarkovModel markov = new MarkovModel(6);
		markov.setTraining(generateTrainingString());
		markov.setRandom(38);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunner mr = new MarkovRunner();
		//mr.runMarkovZero();
		//mr.runMarkovOne();
		//mr.runMarkovFour();
		mr.runMarkovModel();
	}
	
}
