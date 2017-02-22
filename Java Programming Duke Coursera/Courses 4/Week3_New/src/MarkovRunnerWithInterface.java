
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size,int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
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
		int seed= 10;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,seed);

    }

    public void compareMethods(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed= 42;

		double begin1 = System.nanoTime();

		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		runModel(emm, st, size,seed);

		double end1 = System.nanoTime();

		double tt1 =(end1-begin1)/1e9;


		double begin2 = System.nanoTime();
		MarkovModel mThree = new MarkovModel(2);
		runModel(mThree, st, size,seed);
		double end2 = System.nanoTime();
		double tt2 =(end2-begin2)/1e9;
		System.out.println(tt1+"\t"+tt2);
	}

	public void testHashMap(){
		EfficientMarkovModel iMarkovModel = new EfficientMarkovModel(5);
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
//		    	String st = "yes-this-is-a-thin-pretty-pink-thistle";
		iMarkovModel.setTraining(st);
		iMarkovModel.setRandom(531);
		iMarkovModel.printHashMapInfo();

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

}
