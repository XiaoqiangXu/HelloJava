import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Xiaoqiang on 2017/1/26.
 */
public class EfficientMarkovModel extends AbstractMarkovModel{

    private HashMap<String,ArrayList<String>> map;
    private int number;

    public EfficientMarkovModel(int N) {
        number = N;
        map = new HashMap<>();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-number);
        String key = myText.substring(index, index+number);
        sb.append(key);

        for(int k=0; k < numChars-number; k++){
            ArrayList<String> follow = map.get(key);
            if (follow.size()==0){
                break;
            }
            int index2 = myRandom.nextInt(follow.size());
            String next = follow.get(index2);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }

    public HashMap<String,ArrayList<String>> buildMap(){
        for (int i=0;i<myText.length()-number+1;i++){
            String curr =  myText.substring(i,i+number);
            if (!map.containsKey(curr)){
                int idx = myText.indexOf(curr);
                ArrayList<String> al = new ArrayList<>();
                while(idx !=-1&&idx+curr.length()+1<=myText.length()){
                    al.add(myText.substring(idx+curr.length(),idx+curr.length()+1));
                    idx = myText.indexOf(curr,idx+curr.length());
                }
                map.put(curr,al);
            }
        }
        return map;
    }

    public void printHashMapInfo(){
        buildMap();
        String maxKey=null;
        int max = 0;
        for (String key:map.keySet()){
            System.out.println(key+" : "+map.get(key));
            if (map.get(key).size()>max){
                max = map.get(key).size();
                maxKey = key;
            }
        }
        System.out.println("The number of keys in the HashMap "+map.keySet().size());
        System.out.println("The size of the largest value in the HashMap "+max);
        System.out.println("The keys that have the maximum size value "+maxKey);
    }

    public String toString(){
        return "EfficientMarkovModel of order "+number;
    }
}
