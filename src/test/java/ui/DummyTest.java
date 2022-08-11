package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DummyTest {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().arch32().setup();
    }

    @Test
    @DisplayName("This is a dummy test")
    void dummyTest() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://st2016.inv.bg");
        System.out.println("Hello from Junit 5");
        Assertions.assertTrue(true);
    }
}
