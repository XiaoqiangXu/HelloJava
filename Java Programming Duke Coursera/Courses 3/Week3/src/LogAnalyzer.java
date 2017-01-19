
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
}
