import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Xiaoqiang on 2017/1/16.
 */
public class WordsinFiles {
    private HashMap<String,ArrayList<String>> map;
    public WordsinFiles(){
        map = new HashMap<>();
    }
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String temp:fr.words()){
            if (!map.containsKey(temp)){
                ArrayList<String> al = new ArrayList<>();
                al.add(temp);
                map.put(temp,al);
            }else{
                ArrayList<String> al = map.get(temp);
                al.add(temp);
                map.put(temp,al);
            }
        }
    }
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int maxcount = 0;
        //String mostCommonword = null;
        for (String word:map.keySet()){
            ArrayList<String> temp = map.get(word);
            int tempcount = temp.size();
            if (tempcount>maxcount){
                maxcount = tempcount;
               // mostCommonword = word;
            }
        }
        return maxcount;
    }
    public static void main(String[] arg){
        WordsinFiles wif = new WordsinFiles();
        wif.buildWordFileMap();
        System.out.println(wif.maxNumber());
    }
}
