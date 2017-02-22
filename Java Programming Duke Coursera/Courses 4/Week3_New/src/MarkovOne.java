import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/1/25.
 */
public class MarkovOne extends AbstractMarkovModel{

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int k=0; k < numChars-1; k++){
            ArrayList<Character> follow = getFollows(key);
            if (follow.size()==0){
                break;
            }
            int index2 = myRandom.nextInt(follow.size());
            char next = follow.get(index2);
            sb.append(next);
            key = Character.toString(next);
        }

        return sb.toString();
    }

    public String toString(){
        return "MarkovModel of order 1";
    }



}
