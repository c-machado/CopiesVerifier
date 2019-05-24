package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

public class AssistantCopies {

    private static SheetsQuickstart sheets;
    private static WebDriver driver;


    public AssistantCopies(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://assistant.google.com/");
        //home = new Home_Page(driver);
    }

    public void main(String args[]){
        sheets = new SheetsQuickstart();

        try{
            sheets.authenticate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static String getTagName(Object selector){
        System.out.println("ID " + driver.findElement(By.cssSelector(selector.toString())).getTagName());
        return driver.findElement(By.cssSelector(selector.toString())).getTagName();
    }
    

    public static String getCopy(String tag, Object element){
        switch (tag){
            case "id":
                System.out.println("Case ID " + driver.findElement(By.id(element.toString())));
                return driver.findElement(By.id(element.toString())).getText();
            case "img":
                System.out.println("Case IMG " + driver.findElement(By.cssSelector(element.toString())).getAttribute("src"));
                return driver.findElement(By.cssSelector(element.toString())).getAttribute("img");
            default:
                throw new RuntimeException("unknown locator " + tag + " : " + element);
        }

    }
}
