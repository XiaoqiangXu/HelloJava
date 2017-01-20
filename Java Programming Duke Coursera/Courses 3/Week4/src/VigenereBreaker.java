import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "abcdefghijklnmopqrstuvwxyz";
        int[] count = new int[26];
        for (String temp:dictionary){
            for (char c:temp.toLowerCase().toCharArray()){
                int idx = alphabet.indexOf(c);
                if (idx !=-1) {
                    count[idx]++;
                }
            }
        }
        int maxIndex = -1;
        int maxcount = 0;
        for (int i=0;i<count.length;i++){
            if (count[i]>maxcount){
                maxcount = count[i];
                maxIndex = i;
            }
        }
        return alphabet.charAt(maxIndex);
    }

    public String breakForAllLangs(String encypted, HashMap<String,HashSet<String>> languages){
        String bestLang = null;
        String output = null;
        int maxcount = 0;
        for (String lang:languages.keySet()){
            String decrypted =breakForLanguage(encypted,languages.get(lang),mostCommonCharIn(languages.get(lang)));
            int currcount = countWords(decrypted,languages.get(lang));
            if(currcount>maxcount){
                maxcount = currcount;
                output = decrypted;
                bestLang = lang;
            }
        }
        System.out.println("The language identified for the message "+bestLang);
        return output;
    }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder str  = new StringBuilder();
        for (int i=whichSlice;i<message.length();i+=totalSlices){
            char currstr  = message.charAt(i);
            str.append(currstr);
        }
        return str.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i=0;i<klength;i++){
            String slice = sliceString(encrypted,i,klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            key[i] = cracker.getKey(slice);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hashSet= new HashSet<>();
        for (String lines:fr.lines()){
            hashSet.add(lines.toLowerCase());
        }
        return hashSet;
    }

    public int countWords(String message,HashSet<String> dictionary){
        int count = 0;
        for (String word:message.toLowerCase().split("\\W+")){
            if (dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted,HashSet<String> dictionary,char mostCommonCharIn){
        String decryption = null;
        int maxRealWords = 0;
        int[] resultKey = null;
        for (int keylength=1;keylength<=100;keylength++){
            int[]key = tryKeyLength(encrypted,keylength,mostCommonCharIn );
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int realWords = countWords(decrypted,dictionary);
            if(realWords>maxRealWords){
                maxRealWords = realWords;
                decryption = decrypted;
                resultKey = key;
            }
        }
//        System.out.println("Keys length is "+resultKey.length+". The keys are: "+Arrays.toString(resultKey));
//        System.out.println("Valid words : "+maxRealWords);
        return decryption;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String str = fr.asString();

        String[] Dics = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese","Spanish"};
        HashMap<String,HashSet<String >> languages= new HashMap<>();
        for (String lang:Dics){
            FileResource dic = new FileResource("dictionaries\\"+lang);
            languages.put(lang,readDictionary(dic));
        }

        String decypted = breakForAllLangs(str,languages);
        System.out.println(decypted);


    }
    
}
