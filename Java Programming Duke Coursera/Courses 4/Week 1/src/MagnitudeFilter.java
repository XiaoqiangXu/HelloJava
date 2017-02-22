/**
 * Created by Xiaoqiang on 2017/1/24.
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    public MagnitudeFilter(double min,double max){
        minMag =min;
        maxMag = max;
    }
    public boolean satisfies(QuakeEntry qe){
        if (qe.getMagnitude()>=minMag&&qe.getMagnitude()<=maxMag){
            return true;
        }
        return false;
    }
    public String getName(){
        return "Magnitude";
    }
}
