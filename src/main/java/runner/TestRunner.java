package runner;

//import org.testng.annotations.Test;
import base.Homepage;

public class TestRunner {
    //private TestNGCucumberRunner testNGCucumberRunner;
    private Homepage home;

    /*@BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }*/

    //@Test
    public void feature() {
        //testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        home = new Homepage();
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
