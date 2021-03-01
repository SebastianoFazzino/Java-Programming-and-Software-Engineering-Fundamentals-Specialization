public class Tester {
    public void earthQuakeClientTester(){
        EarthQuakeClient2 eqc2 = new EarthQuakeClient2();

        //eqc2.quakesWithFilter();
        eqc2.testMatchAllFilter();
        //eqc2.testMatchAllFilter2();
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        test.earthQuakeClientTester();
    }
}

