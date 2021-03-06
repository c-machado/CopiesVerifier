package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {

    public WebDriver driver;

    public Browser(String _url){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        this.driver.get(_url);

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
                System.out.println("Case IMG " + getCopyFromType(dataType,element));
                return getCopyFromType(dataType,element);
            case "h2":
                System.out.println("Case H2 " + element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " "));
                return element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " ");
            case "h1":
                System.out.println("Case H1 " + element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " "));
                return element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " ");
            case "a":
                System.out.println("Case a " + getCopyFromType(dataType,element));
                return getCopyFromType(dataType,element);
            case "p":
                System.out.println("Case p " + Utils.htmlToText(element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " ")));
                return Utils.htmlToText(element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " "));
            case "div":
                System.out.println("Case div " + getCopyFromType(dataType,element));
                return getCopyFromType(dataType,element);
            case "span":
                System.out.println("Case span "+element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " "));
                return element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " ");
            case "h3":
                System.out.println("Case H3 " + element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " "));
                return element.getAttribute("innerHTML").trim().replaceAll("&nbsp;", " ");


            default:
                throw new RuntimeException("unknown locator " + tag + " : " + _selector);
        }
    }

    public String getCopyFromType(String _dataType, WebElement _element){
        switch (_dataType){
            case "text":
                System.out.println("Case TEXT " + _element.getAttribute("innerText"));
                return _element.getAttribute("innerText").trim();
            case "link":
                System.out.println("Case LINK " + _element.getAttribute("href"));
                return _element.getAttribute("href").trim();
            case "source":
                System.out.println("Case Source " + _element.getAttribute("data-src"));
                return _element.getAttribute("data-src");
            case "alt":
                System.out.println("Case altText " + _element.getAttribute("alt"));
                return  _element.getAttribute("alt");
            case "youtube":
                System.out.println("Case div "+_element.getAttribute("data-c-video-id"));
                return "https://youtube.com/watch?v=" + _element.getAttribute("data-c-video-id");
            default:
                throw new RuntimeException("unknown locator 1 " +_element);
        }
    }

    public List<List<Object>> compareCopies(String _selector, String _copyOnSheets) throws IOException, GeneralSecurityException {
        List copiesToReview = new ArrayList<>();
        List row = new  ArrayList<>();

        if(!getCopy(_selector).equals(_copyOnSheets)){
            row.add(Arrays.asList(_selector,_copyOnSheets));
            copiesToReview.add(row);
            System.out.println("en if " + copiesToReview);
            //sheets.appendValues(spreadSheetId, range,"ROW",row);
        }
        return row;
    }
}
