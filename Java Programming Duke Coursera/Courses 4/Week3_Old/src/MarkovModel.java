import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/1/25.
 */
public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int number;
    public MarkovModel(int N) {
        myRandom = new Random();
        number = N;
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-number);
        String key = myText.substring(index, index+number);
        sb.append(key);

        for(int k=0; k < numChars-number; k++){
            ArrayList<Character> follow = getFollows(key);
            if (follow.size()==0){
                break;
            }
            int index2 = myRandom.nextInt(follow.size());
            char next = follow.get(index2);
            sb.append(next);
            key = key.substring(1)+Character.toString(next);
        }
        return sb.toString();
    }
    public ArrayList<Character> getFollows(String key){
        ArrayList<Character> follow = new ArrayList<>();
        int idx = myText.indexOf(key);
        while (idx!=-1&&idx+key.length()<myText.length()){
            follow.add(myText.charAt(idx+key.length()));
            idx = myText.indexOf(key,idx+key.length());
        }
        return follow;
    }
}
