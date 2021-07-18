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
    String URL = "https://www.youtube.com";

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
        webDriver.get(URL);
        webDriver.manage().window().maximize();/**/
        Assertions.assertTrue(webDriver.getPageSource().contains("HU"),"Change language to hungarian for proper operation");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable((loginField))).click();
        webDriver.findElement(usernameField).sendKeys("autiteszti@gmail.com");
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
        searchInput.isEnabled();
        searchInput.sendKeys(Keys.CONTROL+"a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys("codecool");
        searchInput.sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button")));
        Assertions.assertEquals(URL+"/results?search_query=codecool", webDriver.getCurrentUrl(),"URL differ from declared above");
        WebElement subscribeButton = webDriver.findElement(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button"));

        if (subscribeButton.getText().equals("FELIRATKOZÁS")){
            System.out.println("Subscribe Button Status was fine: "+subscribeButton.getText());
        }else{
            System.out.println("Subscribe button status requests action to be modified to >>> FELIRATKOZÁS");
        }
        Assertions.assertTrue(subscribeButton.getText().contains("FELIRATKOZÁS"),"Subscribe action already deployed.");
        subscribeButton.click();
    }

    @Test
    @Order(3)
    public void testChannel_UNSubscribe(){
        testLoginYouTube();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search")));
        WebElement searchInput = webDriver.findElement(By.id("search"));
        searchInput.isEnabled();
        searchInput.sendKeys(Keys.CONTROL+"a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys("codecool");
        searchInput.sendKeys(Keys.ENTER);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button")));
        WebElement unsubscribeButton = webDriver.findElement(By.cssSelector("#subscribe-button > ytd-subscribe-button-renderer > tp-yt-paper-button"));
        Assertions.assertTrue(unsubscribeButton.getText().contains("FELIRATKOZVA"),"UNsubscribe action already deployed.");
        unsubscribeButton.click();

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"confirm-button\"]")));
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"confirm-button\"]")));
        WebElement bubbleText = webDriver.findElement(By.xpath("//*[@id=\"scrollable\"]/yt-formatted-string/span[3]"));
        Assertions.assertTrue(bubbleText.getText().contains("?"),"This bubble content is not(Leiratkozol errol: Codecool?) as expected");
        webDriver.findElement(By.xpath("//*[@id=\"confirm-button\"]")).click();
    }

    @AfterEach
    public void closeWebdriver(){
        webDriver.quit();
    }
}
