

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        markov.setRandom(42);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size);

    }

    public void testHashMap(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		//st = "yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		int size = 100;
		int seed = 615;
		EfficientMarkovModel mTwo = new EfficientMarkovModel(5);
		mTwo.setTraining(st);
		mTwo.buildMap();
		mTwo.setRandom(seed);
		System.out.println("running with " + mTwo.toString());
		mTwo.getRandomText(size);
		mTwo.printHashMapInfo();
		printOut(mTwo.getRandomText(size));
	}

	public void compareMethod(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		//st = "yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		int order = 2;
		int size = 1000;

		MarkovModel mm = new MarkovModel(order);
		EfficientMarkovModel emm = new EfficientMarkovModel(order);

		// testing MarkovModel speed
		long start = System.nanoTime();
		runModel(mm, st, size);
		long end = System.nanoTime();
		System.out.println("MarkovModel running time: " + (end - start) + " nanoseconds\n");

		// testing EfficientMakovModel
		start = System.nanoTime();
		runModel(emm, st, size);
		end = System.nanoTime();
		System.out.println("EfficientMarkovModel running time: " + (end - start) + " nanoseconds");
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
		MarkovRunnerWithInterface tester = new MarkovRunnerWithInterface();

		//tester.runMarkov();

		tester.testHashMap();

		//tester.compareMethod();
	}
	
}
