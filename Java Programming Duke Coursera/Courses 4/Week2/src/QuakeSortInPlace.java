
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int i = 0;
        while(i<in.size()&&!checkInSortedOrder(in)){
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            i++;
        }
        System.out.println("should sort after "+i+" passes");

    }

    public int getLargestDepth(ArrayList<QuakeEntry> quake,int from){
        int largestIndex = from;
        for (int i=from+1;i<quake.size();i++){
            if (quake.get(i).getDepth()>quake.get(largestIndex).getDepth()){
                largestIndex = i;
            }
        }
        return largestIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0;i<50;i++){
            int idx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qLargest = in.get(idx);
            in.set(i,qLargest);
            in.set(idx,qi);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,int numSorted){
        for (int i=0;i+1<quakeData.size()-numSorted;i++){
            QuakeEntry curr = quakeData.get(i);
            QuakeEntry next = quakeData.get(i+1);
            if (curr.getMagnitude()>next.getMagnitude()){
                quakeData.set(i,next);
                quakeData.set(i+1,curr);
            }
        }
//        System.out.println("Printing Quakes after pass "+numSorted);
//        for (QuakeEntry qe:quakeData){
//            System.out.println(qe);
//        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData){
        for (int i=0;i<quakeData.size()-1;i++){
            if (quakeData.get(i).getMagnitude()>quakeData.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData){
        int i=0;
        while (i<quakeData.size()-1&&!checkInSortedOrder(quakeData)){
            onePassBubbleSort(quakeData,i);
            i++;
        }
        System.out.println("should sort after "+i+" passes");
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData){
        for (int i=0;i<quakeData.size()-1;i++){
            onePassBubbleSort(quakeData,i);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        sortByMagnitudeWithBubbleSortWithCheck (list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
