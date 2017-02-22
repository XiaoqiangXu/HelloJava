import java.util.Comparator;

/**
 * Created by Xiaoqiang on 2017/1/25.
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1,QuakeEntry q2){
          int difftitle = q1.getInfo().compareTo(q2.getInfo());
          if (difftitle==0){
              return Double.compare(q1.getDepth(),q2.getDepth());
          }
          return difftitle;
    }
}
