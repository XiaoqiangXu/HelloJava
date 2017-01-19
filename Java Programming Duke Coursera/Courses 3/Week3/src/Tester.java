
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
//        la.readFile("weblog1_log");
        la.readFile("weblog2_log");

        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);

        System.out.println("Maximum number of visits to this website by a single IP address "+la.mostNumberVisitsByIP(counts));
        System.out.println("Maximum visited IP addresses "+la.iPsMostVisits(counts));

        HashMap<String,ArrayList<String>> IPFD= la.iPsForDays();
        System.out.println(IPFD);

        System.out.println(la.dayWithMostIPVisits(IPFD));

        System.out.println(la.iPsWithMostVisitsOnDay(IPFD,"Sep 29"));



//        la.printAll();
        System.out.println("There are "+la.countUniqueIPs()+" IPs.");
//
//        la.printAllHigherThanNum(400);
//
        ArrayList<String> IPsOnday = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(IPsOnday.size()+" IPs:"+IPsOnday);
//
//
        System.out.println(la.countUniqueIPsInRange(200,299));


    }
}
