import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        //ops.addArguments("disable-infobars");
        webDriver = new ChromeDriver(ops);

        //webDriver = new ChromeDriver();
    }


    @Test
    public void testLoginYouTube() {
        webDriver.get("https://youtube.com");
        webDriver.manage().window().maximize();

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((loginField))).click();
        webDriver.findElement(usernameField).sendKeys("autiteszti" + Keys.ENTER);
        // webDriver.findElement(usernameField).sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((passwordField))).click();
        webDriver.findElement(passwordField).sendKeys("Codecool123" + Keys.ENTER);
        //webDriver.findElement(passwordField).sendKeys(Keys.ENTER);


    }

    @Test
    public void testSearchOnYoutube() {
        boolean isCodecoolFoundOnYoutube = false;
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys("codecool");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search-icon-legacy"))).click();

        List<WebElement> allElements = webDriver.findElements(By.xpath("//*[@id=\"contents\"]/ytd-item-section-renderer[4]"));
        for (WebElement allElement : allElements) {
            if (allElement.getText().contains("codecool")) {
                isCodecoolFoundOnYoutube = true;
            }
        }
        assertTrue(isCodecoolFoundOnYoutube);
    }


    @RepeatedTest(3)
    public void testSearchOnYoutubeWithSearchHistory() {
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys("Rúzsa Magdi Nyár van");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search-icon-legacy"))).click();

        // webDriver.navigate().to("https://youtube.com");

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(Keys.CONTROL + "a");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(Keys.DELETE);
        // getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(Keys.F5);
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container\"]"))).click();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search"))).click();

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Eltávolítás']"))).click();

    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }


}



