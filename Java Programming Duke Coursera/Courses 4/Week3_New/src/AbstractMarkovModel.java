
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<Character> getFollows(String key){
        ArrayList<Character> follow = new ArrayList<>();
        int idx = myText.indexOf(key);
        while (idx!=-1&&idx+key.length()<myText.length()){
            follow.add(myText.charAt(idx+key.length()));
            idx = myText.indexOf(key,idx+key.length());
        }
        return follow;
    }

}
