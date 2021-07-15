import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class YoutubeTesting {

    private WebDriver webDriver;
    private By usernameField = By.id("");
    private By passwordField = By.id("");


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
        webDriver.get("https://wikipedia.org");
    }

}
