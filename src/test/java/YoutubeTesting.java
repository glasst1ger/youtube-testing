import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private By searchField = By.id("search");
    private By stopButton = By.xpath("//*[@id=\"movie_player\"]/div[29]/div[2]/div[1]/button");

    @BeforeAll
    static void setDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void setWebDriver() {
        webDriver = new ChromeDriver();
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
    public void testVideoPlayer() {
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((searchField))).click();

        webDriver.get("https://www.youtube.com/watch?v=Nd31XiSGJLw");

        for (int i = 0; i < 10; i++) {
            webDriver.findElement(stopButton).click();
            webDriver.findElement(stopButton).click();
        }


    }


}
