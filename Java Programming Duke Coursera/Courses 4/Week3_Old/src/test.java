import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/1/25.
 */
public class test {

    public static void main(String[] args){
            MarkovRunner markovRunner = new MarkovRunner();
            markovRunner.runMarkovModel  ();

////
//        FileResource fr = new FileResource();
//        String st = fr.asString();
//        st = st.replace('\n', ' ');
//        MarkovOne markovOne=new MarkovOne();
////        markovOne.setTraining("this is a test yes this is a test.");
//        markovOne.setTraining(st);
//        ArrayList<Character> arrayList = markovOne.getFollows("he");
//        System.out.println(arrayList);
//        System.out.println(arrayList.size());




        }
}
