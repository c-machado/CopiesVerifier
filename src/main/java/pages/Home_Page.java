package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Home_Page {

    private static WebDriver driver;
    private String videoTitle;
    private String videoDescription;
    private String videoId;
    private String devicesTitle;
    private String devicesDescription;
    private String devicesLearnMoreLink;
    private String devicesPhonesTitle;
    private String whatIsNewTitle;
    private String whatIsNewDescription;
    private String whatIsNewCTA;
    private String logoTitle;
    private String logoDescription;
    private String securityTitle;
    private String securityDescription;
    private String securityCtaLearn;
    private String securityLink;
    private String footerOption;
    private String whatIsNewLinkCTA;


    @FindBy(css = "[data-c-slide-1].carousel__item:not(.clone) .home-carousel__text-image[src]")
    public static WebElement heroCarouselImgSlide1;

    @FindBy(css = "[data-c-slide-1].carousel__item:not(.clone) .home-carousel__copy")
    public static WebElement heroCarouselDescpSlide1;

    @FindBy(css = "[data-c-slide-1].carousel__item:not(.clone) a[href]")
    public static WebElement heroCarouselLearnCtaSlide1;

    @FindBy(css = "[data-c-slide-1].carousel__item:not(.clone) a[href]")
    public static WebElement heroCarouselLearnLinkSlide1;

    @FindBy(css = "[data-tracking-name=video-section] h2")
    public WebElement videoTitleElement;

    @FindBy(css = "[data-tracking-name=video-section] p")
    public WebElement videoDescriptionElement;

    @FindBy(css = "[data-c-video-id]")
    public WebElement videoYoutubeId;

    @FindBy(css = "[data-tracking-name=devices-section] h2")
    public WebElement devicesTitleElement;

    @FindBy(css = "[data-tracking-name=devices-section] p")
    public WebElement devicesDescriptionElement;

    @FindBy(css = "[data-tracking-name=devices-section] p a[href]")
    public WebElement devicesLearnLinkElement;

    @FindBy(css = ".icon-nav__card:nth-child(1) span")
    public WebElement phonesTitleElement;

    @FindBy(css = ".icon-nav__content")
    public List<WebElement> devicesElements;

    @FindBy(css = "[data-tracking-name=whats-new-section] h3")
    public WebElement whatIsNewTitleElement;

    @FindBy(css = "[data-tracking-name=whats-new-section] p")
    public WebElement whatIsNewDescriptionElement;

    @FindBy(css = "[data-tracking-name='whats-new-section'] a.featured-card__cta")
    public WebElement whatIsNewCTAElement;

    @FindBy(css = "[data-tracking-name='partners-section'] h2")
    public WebElement logoTitleElement;

    @FindBy(css = "[data-tracking-name='partners-section'] p")
    public WebElement logoDescriptionElement;

    @FindBy(css= ".logo-grid__logo img")
    public List<WebElement> logoGridElement;

    @FindBy(css = "[data-tracking-name='privacy-section'] h2")
    public WebElement securityTitleElement;

    @FindBy(css = "[data-tracking-name='privacy-section'] p")
    public WebElement securityDescriptionElement;

    @FindBy(css = "[data-tracking-name='privacy-section'] a.h-c-button--flat")
    public WebElement securityLearnCtaElement;

    @FindBy(css = ".footer__disclaimers p")
    public WebElement footerDisclaimerElement;


    public Home_Page(WebDriver _driver) {
        this.driver = _driver;
        PageFactory.initElements(_driver, this);
    }

    public static void getTagName(Object id){
        System.out.println("ID " +id);
        System.out.println("CARO " + driver.findElement(By.cssSelector(id.toString())).getTagName());
    }

    public static void getHeroCarouselInfo(){
        WebDriverWait waituntilCarouselIsLoaded = new WebDriverWait(driver,30);
        waituntilCarouselIsLoaded.until(presenceOfElementLocated(By.cssSelector("[data-c-slide-1]")));

        System.out.println("1. hero_carousel_img_slide_1" + heroCarouselImgSlide1.getAttribute("src") + "\n");

        String description = heroCarouselDescpSlide1.getAttribute("innerText");
        System.out.println("2. hero_carousel_descp_slide_1" + description + "\n");

        System.out.println("3. hero_carousel_learn_cta_slide_1" + heroCarouselLearnCtaSlide1.getAttribute("innerText") + "\n");

        System.out.println("4. hero_carousel_learn_link__slide_1" + heroCarouselLearnLinkSlide1.getAttribute("href") + "\n");
        heroCarouselLearnLinkSlide1.click();
    }

    public void getVideoInfo(){
        videoTitle = videoTitleElement.getText();
        videoDescription = videoDescriptionElement.getText();
        videoId = videoYoutubeId.getAttribute("data-c-video-id");
        System.out.println("TITLE: " + videoTitle + " DESCRIPTION: " + videoDescription + " Youtube Id: "+ videoId + "\n");
    }

    public void getDevicesInfo(){
        devicesTitle = devicesTitleElement.getAttribute("innerText");
        devicesDescription = devicesDescriptionElement.getAttribute("innerText");
        devicesLearnMoreLink = devicesLearnLinkElement.getAttribute("href");
        devicesPhonesTitle = phonesTitleElement.getAttribute("innerText");
        System.out.println("devicesTitle " + devicesTitle + "\n" + " DESCRIPTION: " + devicesDescription + "\n" + " LINK LEARN MORE: " + devicesLearnMoreLink
                + " PHONES TITLE: "+ "\n" + devicesPhonesTitle + " DEVICES ELEMENTS SIZE: "+ "\n" + devicesElements.size() + "\n");
    }

    public void getWhatIsNew(){
        whatIsNewTitle = whatIsNewTitleElement.getAttribute("innerText");
        whatIsNewDescription = whatIsNewDescriptionElement.getAttribute("innerText");
        whatIsNewCTA = whatIsNewCTAElement.getAttribute("innerText");
        whatIsNewLinkCTA = whatIsNewCTAElement.getAttribute("href");
        System.out.println("WHAT IS NEW TITLE: "+ whatIsNewTitle + " DESCRIPTION: " + whatIsNewDescription + " CTA: " + whatIsNewCTA + "\n");
    }

    public void getLogoInfo(){
        logoTitle = logoTitleElement.getAttribute("innerText");
        logoDescription = logoDescriptionElement.getAttribute("innerText");
        System.out.println("TITLE LOGO GRID: " + logoTitle + " DESCRIPTION: " + logoDescription + " SIZE: "+ logoGridElement.size() + "\n");
    }

    public void getSecurityInfo(){
        securityTitle = securityTitleElement.getAttribute("innerText");
        securityDescription = securityDescriptionElement.getAttribute("innerText");
        securityCtaLearn = securityLearnCtaElement.getAttribute("innerText");
        securityLink = securityLearnCtaElement.getAttribute("href");
        System.out.println("SECURITY TITLE: "+ securityTitle + " DESCRIPTION: " + securityDescription + " CTA: "
                + securityCtaLearn + "LINK: " + securityLink + "\n");
    }

    public void getFooterInfo(){
        footerOption = footerDisclaimerElement.getAttribute("innerText");
        System.out.println("FOOTER: "+ footerOption);
    }


}
