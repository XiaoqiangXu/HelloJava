import edu.duke.FileResource;

import java.util.ArrayList;

/**
 * Created by Xiaoqiang on 2017/1/23.
 */

public class LargestQuakes {
    public void findLargestQuakes(){

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
//		String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());

        System.out.println("largest such earthquake is at location "+indexOfLargest(list)+" and has magnitude "+
                list.get(indexOfLargest(list)).getMagnitude());

        ArrayList<QuakeEntry> arrayList = getLargest(list, 50);
        for (QuakeEntry qe:arrayList){
            System.out.println(qe);
        }


    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int idx = -1;
        double largestMag = 0;
        for (QuakeEntry qe:data){
            double currMag = qe.getMagnitude();
            if (currMag>largestMag){
                largestMag = currMag;
                idx = data.indexOf(qe);
            }

        }
        return idx;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (int i= 0;i<howMany;i++){
            int indexOfLargest = indexOfLargest(copy);
            answer.add(copy.get(indexOfLargest));
            copy.remove(indexOfLargest);
        }
        return answer;
    }

}
