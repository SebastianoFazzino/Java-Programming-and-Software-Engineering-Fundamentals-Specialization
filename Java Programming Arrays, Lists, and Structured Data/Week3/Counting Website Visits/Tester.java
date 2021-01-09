
import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        int IPs = la.countUniqueIPs();
        System.out.println("There are " + IPs + " unique IPs in this log record");
        la.printAllHigherThanNum(400);
        String day = "Sep 27";
        System.out.println("Unique IPs on day " + day + ": " + la.uniqueIPVisitsOnDay(day).size());
        System.out.println("Unique IPs list:");
        System.out.println(la.uniqueIPVisitsOnDay(day));
        System.out.println("Number of unique addresses in the record: " + la.countUniqueIPsInRange(200, 299));
        System.out.println("Number of visits per IP:");
        HashMap<String,Integer> counts = la.countVisitPerIP();
        System.out.println(counts);
        System.out.println("Most number of visits by an IP");
        System.out.println(la.mostNumberVisitsByIP(counts));
        System.out.println("IPs with most visits:");
        System.out.println(la.iPsMostVisits(counts));
        HashMap<String,ArrayList<String>> counts2 = la.iPsForDays();
        System.out.println("IPs per day:");
        System.out.println(counts2);
        System.out.println("Day with most IPs visits:");
        System.out.println(la.dayWithMostIPVisits(counts2));
        System.out.println("IPs with most visits on " + day);
        System.out.println(la.iPsWithMostVisitsOnDay(counts2, day));
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        test.testLogAnalyzer();
    }
}


