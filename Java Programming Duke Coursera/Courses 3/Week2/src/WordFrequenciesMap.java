import edu.duke.*;
import  java.util.*;

/**
 * Created by Xiaoqiang on 2017/1/15.
 */
public class WordFrequenciesMap {
    public void countWordsMap(){
        FileResource resource = new FileResource();
        int total = 0;
        HashMap<String,Integer> map=new HashMap<>();
        for (String w:resource.words()){
            total++;
            w = w.toLowerCase();
            if(!map.containsKey(w)){
                map.put(w,1);
            }else {
                map.put(w,map.get(w)+1);
            }
        }
        for (String w: map.keySet()){
            int occurrences = map.get(w);
            if (occurrences>500){
                System.out.println(occurrences+"  "+w);
            }
        }
        System.out.println("total words = "+total);
        System.out.println("total unique words = "+map.size());

    }
    public static void main (String[] arg){
        WordFrequenciesMap hml = new WordFrequenciesMap();
        hml.countWordsMap();
    }
}

