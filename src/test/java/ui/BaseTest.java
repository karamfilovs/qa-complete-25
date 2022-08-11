package ui;

import api.LoginAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    private static final String BASE_URI = "https://st2016.inv.bg";
    protected static final String EMAIL = "karamfilovs@gmail.com";
    protected static final String PASSWORD = "123456";
    protected static final String DOMAIN = "st2016";
    protected static String TOKEN = "";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().arch32().setup();
        TOKEN = LoginAPI.getBearerToken(EMAIL, PASSWORD, DOMAIN);
    }

    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to(BASE_URI);
        System.out.println("Starting test:" + testInfo.getDisplayName());
    }

    @AfterEach
    void afterEachTest() {
        driver.quit();
    }

    protected void login() {
        //Check that the navigation to Login was successful
        WebElement loginPageHeading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", loginPageHeading.getText());
        //Enter email
        System.out.println("Entering email:" + EMAIL);
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.clear();
        emailField.sendKeys(EMAIL);
        //Enter password
        System.out.println("Entering password:" + PASSWORD);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys(PASSWORD);
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.cssSelector("input.selenium-submit-button"));
        loginButton.click();
        //Check that the user is logged in successfully
        WebElement userPanel = driver.findElement(By.cssSelector("div.userpanel-header"));
        Assertions.assertEquals(EMAIL, userPanel.getText());
    }
}
