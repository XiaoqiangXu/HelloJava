import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/1/28.
 */
public class MarkovWord implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order){
        myRandom = new Random();
        myOrder = order;
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram){
        ArrayList<String> follows = new ArrayList<String>();
        int start = indexOf(myText,kGram,0);
        while (start!=-1&&start+kGram.length()<myText.length) {
            follows.add(myText[start + kGram.length()]);
            start = indexOf(myText,kGram,start+kGram.length());
        }
        return follows;
    }

    public int indexOf(String[] words,WordGram target,int start){
        WordGram[] wordGrams = new WordGram[words.length-target.length()+1];
        for (int i=0;i<words.length-target.length()+1;i++){
            wordGrams[i] = new WordGram(words,i,target.length());
        }
        for (int i=start;i<words.length-target.length()+1;i++){
            if (wordGrams[i].equals(target)){
                return i;
            }
        }
        return -1;
    }

    public String toString(){
        return "MarkovWord of order "+myOrder;
    }
}

