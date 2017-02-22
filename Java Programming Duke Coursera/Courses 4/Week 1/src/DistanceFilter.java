/**
 * Created by Xiaoqiang on 2017/1/24.
 */
public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDis;
    public DistanceFilter(Location location, double max){
        loc = location;
        maxDis = max;
    }
    public boolean satisfies(QuakeEntry qe){
        if (qe.getLocation().distanceTo(loc)<maxDis){
            return true;
        }
        return false;
    }
    public String getName(){
        return "Distance";
    }
}
