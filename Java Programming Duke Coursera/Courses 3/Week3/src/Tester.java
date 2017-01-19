
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public static void main(String[] args) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
//        la.printAll();
        System.out.println("There are "+la.countUniqueIPs()+" IPs.");

        la.printAllHigherThanNum(400);

        ArrayList<String> IPsOnday = la.uniqueIPVisitsOnDay("Mar 17");
        System.out.println(IPsOnday.size()+" IPs:"+IPsOnday);


        System.out.println(la.countUniqueIPsInRange(300,399));


    }
}
