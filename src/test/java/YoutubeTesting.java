import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YoutubeTesting {

    private WebDriver webDriver;
    private By loginField = By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div[1]/div[1]/div/div/a");
    private By usernameField = By.id("identifierId");
    private By passwordField = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(webDriver, 5);
    }


    @BeforeAll
    static void setDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void setWebDriver() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        ops.addArguments("--lang=hu");
        //ops.setExperimentalOption("excludeSwitches", Collections.singletonList("disable-popup-blocking"));
        ops.addExtensions(new File("extension_1_36_2_0.crx"));
        webDriver = new ChromeDriver(ops);
    }


    @Test
    public void testLoginYouTube() {
        webDriver.get("https://youtube.com");
        webDriver.manage().window().maximize();

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((loginField))).click();
        webDriver.findElement(usernameField).sendKeys("autiteszti" + Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((passwordField))).click();
        webDriver.findElement(passwordField).sendKeys("Codecool123" + Keys.ENTER);

    }

    @RepeatedTest(3)
    public void testSearchOnYoutube() {
        boolean isRuzsaMagdiBestSingerEverOnYoutube = false;
        String ruzsaMagdolna = "Rúzsa Magdolna";

        testLoginYouTube();
        findVideo(ruzsaMagdolna);


        List<WebElement> allElements = webDriver.findElements(By.id("contents"));
        for (WebElement allElement : allElements) {
            if (allElement.getText().contains(ruzsaMagdolna)) {
                isRuzsaMagdiBestSingerEverOnYoutube = true;
            }
        }
        assertTrue(isRuzsaMagdiBestSingerEverOnYoutube);
    }

    @Test
    public void testFindVideoFromSearchHistory() {
        testLoginYouTube();

        String[] favouriteMagdolnaRuzsaSongs = {"Rúzsa Magdi Április", "Rúzsa Magdi Jel", "Rúzsa Magdi Nyár van"};
        for (String s : favouriteMagdolnaRuzsaSongs) {
            findVideo(s);
            clearSearchBar();
        }

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container\"]"))).click();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).click();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sbse0\"]/div[1]"))).click();

    }



    @RepeatedTest(3)
    public void testYouTubeRemoveFromSearchHistory() {
        testLoginYouTube();


        String[] favouriteMagdolnaRuzsaSongs = {"Rúzsa Magdi Április", "Rúzsa Magdi Jel", "Rúzsa Magdi Nyár van"};
        for (String s : favouriteMagdolnaRuzsaSongs) {
            findVideo(s);
            clearSearchBar();
        }

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container\"]"))).click();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).click();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Eltávolítás']"))).click();
    }

    private void clearSearchBar() {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(Keys.CONTROL + "a");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(Keys.DELETE);
    }

    private void findVideo(String videoName) {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(videoName);
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search-icon-legacy"))).click();
    }

    @Test
    public void clickLikeOnYoutube() {
        testLoginYouTube();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys("Zámbó Jimmy - Még nem veszíthetek [ALPÁRI REMIX]");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search-icon-legacy"))).click();


        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a/yt-img-shadow/img"))).click();
        //getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[6]/div[2]/ytd-video-primary-info-renderer/div/div/div[3]/div/ytd-menu-renderer/div/ytd-toggle-button-renderer[1]"))).click();


        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[6]/div[2]/ytd-video-primary-info-renderer/div/div/div[3]/div/ytd-menu-renderer/div/ytd-toggle-button-renderer[2]/a"))).click();


    }


    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }


}



