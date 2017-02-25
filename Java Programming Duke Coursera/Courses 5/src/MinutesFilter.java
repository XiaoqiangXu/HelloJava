/**
 * Created by Xiaoqiang on 2017/2/24.
 */
public class MinutesFilter implements Filter{

    private int myMin;
    private int myMax;

    public MinutesFilter (int min,int max){
        myMax = max;
        myMin = min;
    }

    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id)>=myMin&&MovieDatabase.getMinutes(id)<=myMax;
    }
}
