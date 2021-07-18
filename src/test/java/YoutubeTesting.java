import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class YoutubeTesting {

    private WebDriver webDriver;
    private By loginField = By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div[1]/div[1]/div/div/a");
    private By usernameField = By.id("identifierId");
    private By passwordField = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");


    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(webDriver, 10);
    }

    private By searchField = By.id("search");
    private By stopButton = By.id("movie_player");
    private By wideScreenButton = new By.ByCssSelector(".ytp-size-button.ytp-button");
    private By fullScreenButton = new By.ByCssSelector(".ytp-fullscreen-button.ytp-button");
    private By likeButton = By.id("top-level-buttons-computed");
    private By settingButton = new By.ByCssSelector(".ytp-button.ytp-settings-button");

    @BeforeAll
    static void setDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void setWebDriver() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        webDriver = new ChromeDriver(ops);
    }


    @Test
    public void testLoginYouTube() {
        webDriver.get("https://youtube.com");
        webDriver.manage().window().maximize();

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((loginField))).click();
        webDriver.findElement(usernameField).sendKeys("autiteszti");
        webDriver.findElement(usernameField).sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((passwordField))).click();
        webDriver.findElement(passwordField).sendKeys("Codecool123");
        webDriver.findElement(passwordField).sendKeys(Keys.ENTER);
    }
    @Test
    public void testStopPlayButton() {
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((searchField))).click();
        webDriver.get("https://www.youtube.com/watch?v=Nd31XiSGJLw");

        for (int i = 0; i < 9; i++) {
           // getWebDriverWait().until(ExpectedConditions.elementToBeClickable((stopButton))).click();
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            webDriver.findElement(stopButton).click();
        }

    }
    @Test
    public void testSizeButton() {
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((searchField))).click();
        webDriver.get("https://www.youtube.com/watch?v=Nd31XiSGJLw");

        for (int i = 0; i < 3; i++) {
            webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            webDriver.findElement(wideScreenButton).click();
        }
        webDriver.findElement(fullScreenButton).click();
    }
    @Test
    public void testLikeButton() {
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((searchField))).click();
        webDriver.get("https://www.youtube.com/watch?v=Nd31XiSGJLw");

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((likeButton))).click();

        webDriver.findElement(likeButton).click();

    }
    @Test
    public void testVideoSettingButton() {
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((searchField))).click();
        webDriver.get("https://www.youtube.com/watch?v=Nd31XiSGJLw");

        webDriver.findElement(settingButton).click();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Kommentárok']"))).click();

        webDriver.findElement(settingButton).click();
        webDriver.findElement(settingButton).click();

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Lejátszási sebesség']"))).click();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='2']"))).click();

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Minőség']"))).click();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='144p']"))).click();
    }
}
