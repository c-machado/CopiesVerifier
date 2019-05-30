package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class CopyVerifier {

    private static SheetsQuickstart sheets;
    private static WebDriver driver;


    public CopyVerifier(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://assistant.google.com/");
        //home = new Home_Page(driver);
    }

    public String getCopy(String _selector){
        String dataType = "";
        String [] selectorKeys = _selector.split(",");
        String selector = selectorKeys[0];
        if(selectorKeys.length>1) {
            dataType = selectorKeys[1];
        }
        WebElement element = driver.findElement(By.cssSelector(selector));
        String tag = element.getTagName();
        System.out.println("tag " +tag);

        switch (tag){
            case "img":
                System.out.println("Case IMG " + element.getAttribute("data-src"));
                return element.getAttribute("data-src");
            case "h2":
                System.out.println("Case H2 " + element.getAttribute("innerHTML").trim());
                return element.getAttribute("innerHTML").trim();
            case "a":
                System.out.println("Case a " + getCopyFromType(dataType,element));
                return getCopyFromType(dataType,element);

            default:
                throw new RuntimeException("unknown locator " + tag + " : " + _selector);
        }
    }

    public String getCopyFromType(String _dataType, WebElement _element){
        switch (_dataType){
            case "text":
                System.out.println("Case TEXT " + _element.getAttribute("innerText"));
                return _element.getAttribute("innerText");
            case "link":
                System.out.println("Case LINK " + _element.getAttribute("href"));
                return _element.getAttribute("href");
            default:
                throw new RuntimeException("unknown locator 1 " +_element);
        }
    }

    public void compareCopies(String _selector, String _copyOnSheets){
        Assert.assertEquals(getCopy(_selector), _copyOnSheets, "Copies match");
    }
}
