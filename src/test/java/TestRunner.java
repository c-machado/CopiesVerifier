import org.testng.annotations.Test;
import base.SheetsQuickstart;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


public class TestRunner {
    private static SheetsQuickstart sheets;
    private static final String spreadsheetIdCopies = "1okfhWpOVqNP6zqm6hqNJVfUygqsrtgn-Zg4B-wlAwgo";
    private static final String spreadSheetIdErrors = "17Q49zfLzpkN483hxixKZPopUQUBVwkplAxssoWx63sA";
    private final String range = "Homepage!A3:C89";


    @Test
    public void feature() {
        try {
            sheets = new SheetsQuickstart();
            List<List<Object>> spreadSheetValues = sheets.authenticate(spreadsheetIdCopies,range);
            List<List<Object>> copiesToReview = sheets.validateCopies(spreadSheetValues);
            //sheets.authenticate(spreadSheetIdErrors,range);
            sheets.appendValues(spreadSheetIdErrors,range,"ROW", copiesToReview);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

}