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
                al.add(f.getName());
                map.put(temp,al);
            }else{
                ArrayList<String> al = map.get(temp);
                if (!al.contains(f.getName())) {
                    al.add(f.getName());
                    map.put(temp, al);
                }
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

    public  ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> al = new ArrayList<>();
        for (String temp:map.keySet()){
            ArrayList<String> als= map.get(temp);
            if (als.size()==number){
                al.add(temp);
            }
        }
        return al;

    }
    public void printFilesIn(String word){
        ArrayList<String> sl = map.get(word);
        for (String temp:sl){
            System.out.println(temp);
        }
    }

    public static void main(String[] arg){
        WordsinFiles wif = new WordsinFiles();
        wif.buildWordFileMap();
        System.out.println("The greatest number of files a word appears in is "+wif.maxNumber()+", and there are "+
                wif.wordsInNumFiles(wif.maxNumber()).size()+" such words: "+ wif.wordsInNumFiles(wif.maxNumber()));
        //wif.printFilesIn("cats");
        for (String temp:wif.map.keySet()){
            System.out.println(temp+" appears "+
                    wif.map.get(temp).size()+" times in: ");
            wif.printFilesIn(temp);

        }

    }
}
