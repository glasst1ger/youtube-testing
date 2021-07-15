import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YoutubeTesting {

    private WebDriver webDriver;
    private By loginField = By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div[1]/div[1]/div/div/a");
    private By usernameField = By.id("identifierId");
    private By passwordField = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(webDriver, 10);
    }

    @BeforeAll
    static void setDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void setWebDriver() {
        webDriver = new ChromeDriver();
    }

    @Test
    @Order(1)
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
    @Order(2)
    public void testChannel_Subscribe(){
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search")));
        WebElement searchInput = webDriver.findElement(By.id("search"));
        searchInput.sendKeys("codecool");
        searchInput.sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button")));
        webDriver.findElement(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button")).click();
    }

    @Test
    @Order(3)
    public void testChannel_UNSubscribe(){
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search")));
        WebElement searchInput = webDriver.findElement(By.id("search"));
        searchInput.sendKeys("codecool");
        searchInput.sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button")));
        webDriver.findElement(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button")).click();

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"confirm-button\"]")));
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"confirm-button\"]")));
        webDriver.findElement(By.xpath("//*[@id=\"confirm-button\"]")).click();
    }

    @AfterEach
    public void closeWebdriver(){
        webDriver.close();
    }
}
