import java.util.Random;

/**
 * Created by Xiaoqiang on 2017/2/25.
 */
public class test {
    public static void main(String[] args){
        RecommendationRunner recommendationRunner = new RecommendationRunner();
        System.out.println(recommendationRunner.getItemsToRate());
        recommendationRunner.printRecommendationsFor("11");
    }
}
