
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
//        String st = "this is just a test yes this is a simple test";
        IMarkovModel markovWord = new MarkovWord(5);
        markovWord.setRandom(844);
        runModel(markovWord, st, 120);
    }

    public void testHashMap(){
//        String st = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        markovWord.setRandom(64);
        markovWord.setTraining(st);
        markovWord.buildMap();
//        runModel(markovWord, st, 50);
        markovWord.printHashMapInfo();
    }

    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
//        String st = "this is a test yes this is really a test yes a test this is wow";
        double start = System.nanoTime();
        MarkovWord markovWord = new MarkovWord(2);
        runModel(markovWord,st,1000,42);
        double middle1 = System.nanoTime();
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        efficientMarkovWord.setTraining(st);
        efficientMarkovWord.buildMap();
        double middle2 = System.nanoTime();
        runModel(efficientMarkovWord,st,100000,42);
        double end = System.nanoTime();
        System.out.println("Time consuming of MarkovWord :"+Double.toString((middle1-start)/1e9));
        System.out.println("Time consuming of buildMap :"+Double.toString((middle2-middle1)/1e9));
        System.out.println("Time consuming of EfficientMarkovWord :"+Double.toString((end-middle2)/1e9));
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
