import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/1/28.
 */
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;

    public EfficientMarkovWord(int order){
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
            ArrayList<String> follows = map.get(key);
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

    public void buildMap(){
        map = new HashMap<>();
        for (int i=0;i<myText.length-myOrder+1;i++){
            WordGram wordGram = new WordGram(myText,i,myOrder);
            if (!map.keySet().contains(wordGram)){
                ArrayList<String> arrayList = new ArrayList<>();
                int idx = indexOf(myText,wordGram,0);
                while (idx!=-1&&idx+myOrder<myText.length){
                    arrayList.add(myText[idx+myOrder]);
                    idx = indexOf(myText,wordGram,idx+myOrder);
                }
                map.put(wordGram,arrayList);
            }
        }
    }

    public void printHashMapInfo(){
        int largestSize = 0;
        for (WordGram wordGram:map.keySet()){
            System.out.println(wordGram+" : "+ map.get(wordGram));
            if (map.get(wordGram).size()>largestSize){ largestSize = map.get(wordGram).size();}

        }
        System.out.println("The number of keys in the HashMap: "+map.keySet().size());
        System.out.println("The maximum number of elements following a key is: "+largestSize);
        System.out.println("The keys that have the maximum size value: ");
        for (WordGram wordGram:map.keySet()){
            if (map.get(wordGram).size()==largestSize){
                System.out.println("\""+wordGram+"\""+" : "+map.get(wordGram));
            }
        }
        System.out.println();
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
