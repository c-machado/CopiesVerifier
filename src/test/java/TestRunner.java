
import base.Browser;
import org.testng.annotations.Test;
import base.SpreadSheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


public class TestRunner {
    private SpreadSheets sourceSheet;
    private SpreadSheets errorSheet;
    private final String spreadsheetIdCopies = "1okfhWpOVqNP6zqm6hqNJVfUygqsrtgn-Zg4B-wlAwgo";
    private final String spreadSheetIdErrors = "17Q49zfLzpkN483hxixKZPopUQUBVwkplAxssoWx63sA";

    //private final String spreadSheetIdErrors = "1sXL9zoHI-xdbQj--TGzcaGPu_zL01kZuFKcySQyLgsM";
    private final String rangeInput = "Homepage!A3:C89";
    private final String rangeOutput = "Homepage!A4:C4";


    private Browser browser;
    private final String url = "https://assistant.google.com/";

    @Test
    public void feature() throws IOException, GeneralSecurityException {
        try {

            sourceSheet = new SpreadSheets(spreadsheetIdCopies);
            errorSheet = new SpreadSheets(spreadSheetIdErrors);
            browser = new Browser(url);
            sourceSheet.authenticate(spreadsheetIdCopies, rangeInput);

            errorSheet.authenticate(spreadSheetIdErrors, rangeOutput);
            boolean copiesMatched;
            List<List<Object>> copiesOrigin = sourceSheet.getValues(rangeInput);
            String selector = "", copyOnSheet = "", copyOnSite = "";
            List rows = new ArrayList();
            for (int rowNumber = 0; rowNumber < copiesOrigin.size(); rowNumber++) {
                copiesMatched = false;
                List<Object> row = copiesOrigin.get(rowNumber);

                if (row.size() > 0) {
                    selector = row.get(0).toString();
                    if (row.size() > 2) {
                        copyOnSheet = row.get(2).toString().trim();
                    }
                }

                if (!selector.isEmpty()) {
                    copyOnSite = this.browser.getCopy(selector);
                    copiesMatched = copyOnSheet.equals(copyOnSite);
                    if (copiesMatched == false) {
                        //save in the error file

                        List column = new ArrayList();
                        column.add(selector);
                        column.add(copyOnSite);
                        column.add(copyOnSheet);
                        rows.add(column);


                    }
                }


                System.out.println("----------");
                System.out.println("copies \n" +
                        "\trowNumber > " + rowNumber + "\n" +
                        "\tSelector > " + selector + "\n" +
                        "\tCopy > " + copyOnSheet + "\n" +
                        "\tCopyOnSite > " + copyOnSite + "\n" +
                        "\tCopiesMatched > " + copiesMatched);
                System.out.println("----------");
           }

                errorSheet.appendValues(rangeOutput, rows);


                //System.out.println("copies " + copiesOrigin.get(0).get(2));

            /*List<List<Object>> spreadSheetValues = sheets.authenticate(spreadsheetIdCopies,range);
            List<List<Object>> rows = sheets.validateCopies(spreadSheetValues);
            //sheets.authenticate(spreadSheetIdErrors,range);
            sheets.appendValues(spreadSheetIdErrors,range,"ROW", rows);*/

        } catch(IOException e){
            e.printStackTrace();
        } catch(GeneralSecurityException e){
            e.printStackTrace();
        }

    }

}