
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr =new FileResource("Data\\"+filename);
         for (String line:fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }

     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le:records){
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return  uniqueIPs.size();
     }

     public void printAllHigherThanNum(int num){
         for(LogEntry le:records){
             int statusCode = le.getStatusCode();
             if (statusCode>num){
                 System.out.println(le);
             }
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> IPsOnDay = new ArrayList<>();
         for (LogEntry le:records){
             String str = le.getAccessTime().toString();
             int idx = str.indexOf(someday);
             if (idx!=-1){
                 if (!IPsOnDay.contains(le.getIpAddress())) {
                     IPsOnDay.add(le.getIpAddress());
                 }
             }

         }
         return IPsOnDay;
     }

     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPsInRange = new ArrayList<>();
         for (LogEntry le:records){
             if (le.getStatusCode()>=low&&le.getStatusCode()<=high){
                 if (!uniqueIPsInRange.contains(le.getIpAddress())){
                     uniqueIPsInRange.add(le.getIpAddress());
                 }

             }
         }
         return uniqueIPsInRange.size();
     }

    public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<>();
         for (LogEntry le:records){
             if (!counts.containsKey(le.getIpAddress())){
                 counts.put(le.getIpAddress(),1);
             }else{
                 counts.put(le.getIpAddress(),counts.get(le.getIpAddress())+1);
             }
         }
         return counts;
     }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int mostNVBIP = 0;
         for (int temp:counts.values()){
             if (temp>mostNVBIP){
                 mostNVBIP = temp;
             }
         }
         return mostNVBIP;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        int mostNVBIP = mostNumberVisitsByIP(counts);
        ArrayList<String> al = new ArrayList<>();
        for (String key: counts.keySet()){
            if (counts.get(key)==mostNVBIP){
                al.add(key);
            }
        }
        return al;

    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> IPFD = new HashMap<>();
        for (LogEntry le:records){
            String str = le.getAccessTime().toString();
            str = str.substring(4,10);
            if (!IPFD.containsKey(str)){
                ArrayList<String> al = new ArrayList<>();
                al.add(le.getIpAddress());
                IPFD.put(str,al);
            }else{
                ArrayList<String> al = IPFD.get(str);
                al.add(le.getIpAddress());
                IPFD.put(str,al);
            }
        }
        return IPFD;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> IPFD){
        String output = null;
        int maxcount = 0;
        for (String str:IPFD.keySet()){
            if (IPFD.get(str).size()>maxcount){
                maxcount = IPFD.get(str).size();
                output = str;
            }

        }
        return output;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> IPFD, String someday){
        ArrayList<String> al = IPFD.get(someday);
        ArrayList<String> output = new ArrayList<>();
        HashMap<String,Integer> IPwithMostVOD= new HashMap<>();
        for (String str:al){
            if (!IPwithMostVOD.containsKey(str)){
                IPwithMostVOD.put(str,1);

            }else{
                IPwithMostVOD.put(str,IPwithMostVOD.get(str)+1);
            }
        }
        int mostNVBIP = mostNumberVisitsByIP(IPwithMostVOD);
        for (String str:IPwithMostVOD.keySet()){
            if (IPwithMostVOD.get(str)==mostNVBIP){
                output.add(str);
            }
        }
        return output;
    }



}
