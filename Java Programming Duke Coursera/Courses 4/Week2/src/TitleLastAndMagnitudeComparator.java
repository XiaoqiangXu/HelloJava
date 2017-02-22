import java.util.Comparator;
import java.util.DoubleSummaryStatistics;

/**
 * Created by Xiaoqiang on 2017/1/25.
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1,QuakeEntry q2){
        String str1 = q1.getInfo();
        String str2 = q2.getInfo();
        int idx1 = str1.indexOf(",");
        int idx2 = str2.indexOf(",");
        while (idx1!=-1){
            str1 = str1.substring(idx1+2);
            idx1 = str1.indexOf(",");
        }
        while (idx2!=-1){
            str2 = str2.substring(idx2+2);
            idx2 = str2.indexOf(",");
        }
        int difftitle = str1.compareTo(str2);
        if (difftitle==0){
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return difftitle;
    }
}
