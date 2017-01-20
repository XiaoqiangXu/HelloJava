import edu.duke.FileResource;

import java.util.Arrays;

/**
 * Created by Xiaoqiang on 2017/1/20.
 */
public class tester {
    public static void main(String[] args){

//        FileResource fr = new FileResource();
//        String str = fr.asString();

//        CaesarCipher cc = new CaesarCipher(2);
//
//        System.out.println(cc.encrypt(str));
//        System.out.println(cc.decrypt(cc.encrypt(str)));
//
//        CaesarCracker ccCrack = new CaesarCracker('a');
//        System.out.println(ccCrack.decrypt(str));
//        System.out.println("The key is: "+ccCrack.getKey(str));
//
//        int[] keys ={17,14,12,4};
//        VigenereCipher vigenereCipher = new VigenereCipher(keys);
//        System.out.println(vigenereCipher.encrypt(str));

        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.breakVigenere();
//        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 4, 5));
//        System.out.println(Arrays.toString(vigenereBreaker.tryKeyLength(str,5,'e')));

    }
}
