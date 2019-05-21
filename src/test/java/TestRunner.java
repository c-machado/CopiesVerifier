import org.testng.annotations.Test;
import base.SheetsQuickstart;
import java.io.IOException;
import java.security.GeneralSecurityException;


public class TestRunner {
//    private static Homepage home;
    private static SheetsQuickstart sheets;


    @Test
    public void feature() {
        //testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
//        home = new Homepage();
//        home.setUp();
        System.out.println("test execution");
        try {
            sheets.authenticate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

}