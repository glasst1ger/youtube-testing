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


    @BeforeAll
    static void setDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void setWebDriver() {
        webDriver = new ChromeDriver();
    }


    @Test
    public void testLoginWiki() {
        webDriver.get("https://youtube.com");
        webDriver.manage().window().maximize();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((loginField))).click();
        webDriver.findElement(usernameField).sendKeys("autiteszti");
        WebElement textbox = webDriver.findElement(usernameField);
        textbox.sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((passwordField))).click();
        webDriver.findElement(passwordField).sendKeys("Codecool123");
        WebElement textbox2 = webDriver.findElement(passwordField);
        textbox2.sendKeys(Keys.ENTER);



    }

    // //*[@id="identifierId"]

}
