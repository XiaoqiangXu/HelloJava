import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SplittableRandom;

/**
 * Created by Xiaoqiang on 2017/1/17.
 */
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    public CharactersInPlay(){
        names = new ArrayList<>();
        counts = new ArrayList<>();
    }
    public void update(String person){
        if (names.contains(person)){
            int idx = names.indexOf(person);
            counts.set(idx,counts.get(idx)+1);
        }else{
            names.add(person);
            counts.add(1);
        }
    }
    public void findAllCharacters(){
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String words:fr.lines()){
            int idx = words.indexOf(".");
            if (idx>1) {
                String person = words.substring(0, idx);
                update(person);
            }
        }
    }
    public void charactersWithNumParts(int num1,int num2){


        for (int i=0;i<names.size();i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + "\t" + counts.get(i));
            }
        }

    }
    public static void main(String[] args){
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters();
//        for (int i=0;i<cip.names.size();i++) {
//            System.out.println(cip.names.get(i) + "\t" + cip.counts.get(i));
//        }
        cip.charactersWithNumParts(10,150);
    }
}
