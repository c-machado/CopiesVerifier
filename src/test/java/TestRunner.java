
import base.Browser;
import org.testng.annotations.AfterTest;
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

    private final String rangeInput = "Smart Displays!A3:C53";
    private final String rangeOutput = "Smart Displays!A3:C53";


    private Browser browser;
    private final String url = "https://assistant.google.com/platforms/displays/";
    private final String base_URL = "https://assistant.google.com";

    public List<String> getSourceSheet(){
        List<String> sheets = new ArrayList<String>();
        sheets.add("Homepage!A3:C89");
        sheets.add("Phones!A3:C163");
        sheets.add("Speakers!A3:C202");
        sheets.add("Cars!A3:C56");
        sheets.add("TVs!A3:C64");
        sheets.add("Laptops&Tablets!A3:C78");
        sheets.add("Wearables!A3:C98");
        sheets.add("More Devices (Smart Clock)!A3:C53");
        sheets.add("Smart Displays!A3:C57");
        return sheets;
    }

    public String getURL(String _range){
        String url = _range.substring(0,_range.indexOf('!'));
        System.out.println("carola "+url);
        String complete_URL="";
        switch (url){
            case "Homepage":
                complete_URL = base_URL;
                break;
            case "Phones":
                complete_URL = base_URL + "/platforms/phones";
                break;
            case "Speakers":
                complete_URL = base_URL + "/platforms/speakers";
                break;
            case "Cars":
                complete_URL = base_URL + "/platforms/cars";
                break;
            case "TVs":
                complete_URL = base_URL + "/platforms/tv";
                break;
            case "Laptops&Tablets":
                complete_URL = base_URL + "/platforms/laptops";
                break;
            case "Wearables":
                complete_URL = base_URL + "/platforms/wearables";
                break;
            case "More Devices (Smart Clock)":
                complete_URL = base_URL + "/platforms/devices";
                break;
            case "Smart Displays":
                complete_URL = base_URL + "/platforms/displays";
                break;
            default:
                System.out.println("Something went wrong");
        }

        return  complete_URL;
    }

    @Test
    public void feature() {
        try {

            List<String> sheetsCopies = new ArrayList<String>();
            sheetsCopies = getSourceSheet();
            sourceSheet = new SpreadSheets(spreadsheetIdCopies);
            errorSheet = new SpreadSheets(spreadSheetIdErrors);
            for(String sheet: sheetsCopies) {

                String _url = getURL(sheet);
                browser = new Browser(_url);

//                sourceSheet.authenticate(spreadsheetIdCopies, rangeInput);
//                errorSheet.authenticate(spreadSheetIdErrors, rangeOutput);
                sourceSheet.authenticate(spreadsheetIdCopies, sheet);
                errorSheet.authenticate(spreadSheetIdErrors, sheet);
                boolean copiesMatched;
                List<List<Object>> copiesOrigin = sourceSheet.getValues(sheet);

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
                errorSheet.appendValues(sheet, rows);
            }

        } catch(IOException e){
            e.printStackTrace();
        } catch(GeneralSecurityException e){
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDownClass() throws Exception {
       browser.driver.quit();
    }
}