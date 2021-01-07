
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
        la.readFile("short-test_log");
        la.printAll();
        int IPs = la.countUniqueIPs();
        System.out.println("There are " + IPs + " unique IPs in this log record");
        la.printAllHigherThanNum(200);
        String day = "Sep 30";
        System.out.println("Unique IPs on day " + day + ": " + la.uniqueIPVisitsOnDay(day).size());
        System.out.println("Unique IPs list:");
        System.out.println(la.uniqueIPVisitsOnDay(day));
        System.out.println("Number of unique addresses in the record: " + la.countUniqueIPsInRange(300, 399));
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        test.testLogAnalyzer();
    }
}


