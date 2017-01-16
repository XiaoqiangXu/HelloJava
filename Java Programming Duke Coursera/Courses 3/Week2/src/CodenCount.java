import edu.duke.FileResource;

import java.util.HashMap;

/**
 * Created by Xiaoqiang on 2017/1/15.
 */
public class CodenCount
{
    private HashMap<String,Integer> map;
    public CodenCount(){
        map = new HashMap<>();
    }
    public void buildCodonMap(int start,String dna){
        map.clear();
        for (int i=start;i+3<=dna.length();i=i+3){
            String temp = dna.substring(i,i+3);
            if (map.containsKey(temp)){
                map.put(temp, map.get(temp)+ 1);

            }else{
                map.put(temp,1);
            }
        }
    }
    public String getMostCommonCodon(){
        String mostCommonCoden =null;
        int maxcount = 0;
        for (String temp:map.keySet()){
            if (map.get(temp)>maxcount){
                maxcount = map.get(temp);
                mostCommonCoden = temp;
            }
        }
        return mostCommonCoden;
    }
    public void printCodonCounts(int start,int end){
        for (String temp:map.keySet()){
            int value = map.get(temp);
            if (value>=start&&value<=end){
                System.out.println(temp+'\t'+value);
            }
        }
    }
    public void test(){
        //String dna = "CGTTCAAGTTCAA";
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.trim();
        dna = dna.toUpperCase();
        int start = 1;
        int end =5;
        buildCodonMap(0,dna);
        System.out.println("Reading frame starting with 0 results in "+map.size()+" unique codons");
        System.out.println("and most common code is "+getMostCommonCodon()+" with count "+map.get(getMostCommonCodon()));
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        printCodonCounts(start,end);

        buildCodonMap(1,dna);
        System.out.println("Reading frame starting with 1 results in "+map.size()+" unique codons");
        System.out.println("and most common code is "+getMostCommonCodon()+" with count "+map.get(getMostCommonCodon()));
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        printCodonCounts(start,end);

        buildCodonMap(2,dna);
        System.out.println("Reading frame starting with 2 results in "+map.size()+" unique codons");
        System.out.println("and most common code is "+getMostCommonCodon()+" with count "+map.get(getMostCommonCodon()));
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        printCodonCounts(start,end);
    }
    public static void main(String[] args){
        CodenCount cc = new CodenCount();
        cc.test();
    }


}
