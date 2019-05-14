package runner;

import base.Homepage;

public class TestRunner {

//    @BeforeClass(alwaysRun = true)
//    public void setUpClass() throws Exception {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }

    public static void main(String[] args) {
        System.out.println("testing");
        Homepage home = new Homepage();
        home.setUp();
    }

    /*@DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }*/
}
