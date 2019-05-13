package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Home_Page;

import java.util.concurrent.TimeUnit;

public class Homepage {

    private WebDriver driver;
    private Home_Page home_page;

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://assistant.google.com/");
        home_page = new Home_Page(driver);
        home_page.getHeroCarouselInfo();
        home_page.getVideoInfo();
        home_page.getDevicesInfo();
        home_page.getWhatIsNew();
        home_page.getLogoInfo();
        home_page.getSecurityInfo();
        home_page.getFooterInfo();
    }



}
