import java.util.concurrent.atomic.LongAccumulator;

public class Tester {
    public void MethodsTester(){
        EarthQuakeClient eqc = new EarthQuakeClient();
        //eqc.createCSV();
        //eqc.bigQuakes();
        //eqc.closeToMe();
        //eqc.quakesOfDepth();
        //eqc.quakesByPhrase();

        //ClosestQuakes cq = new ClosestQuakes();
        //cq.findClosestQuakes();

        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }


    public static void main(String[] args) {
        Tester test = new Tester();
        test.MethodsTester();

    }
}
