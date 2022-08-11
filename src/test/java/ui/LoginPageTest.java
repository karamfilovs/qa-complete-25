package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPageTest extends BaseTest {
    private static final String BAD_PASSWORD = "124545454";
    private static final String BAD_CREDS_MESSAGE = "Грешно потребителско име или парола. Моля, опитайте отново.  ";
    private static final String BLANK_CREDS_MESSAGE = "Моля, попълнете вашия email  ";
    private static final String LOGOUT_SUCCESS_MSG = "Вие излязохте от акаунта си. ";


    @Test
    @Tag("login")
    @DisplayName("Can login with valid credentials")
    void canLoginWithValidCredentials() {
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

    @Test
    @Tag("login")
    @DisplayName("Can login with valid credentials and logout")
    void canLoginWithValidCredentialsAndLogout() {
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
        //Logout from the system
        System.out.println("Logging out from the system");
        userPanel.click();
        WebElement logoutLink = driver.findElement(By.cssSelector("a.selenium-button-logout"));
        logoutLink.click();
        //Check logout success
        WebElement logoutSuccessMessage = driver.findElement(By.id("okmsg"));
        Assertions.assertEquals(LOGOUT_SUCCESS_MSG, logoutSuccessMessage.getText());
        WebElement companyName = driver.findElement(By.id("wellcome"));
        Assertions.assertEquals("QA Ground", companyName.getText());
    }

    @Test
    @Tag("login")
    @DisplayName("Cant login with invalid credentials")
    void cantLoginWithInvalidCredentials() {
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
        passwordField.sendKeys(BAD_PASSWORD);
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.cssSelector("input.selenium-submit-button"));
        loginButton.click();
        //Check bad login message
        WebElement badLoginError = driver.findElement(By.xpath("//div[@class='selenium-error-block']"));
        Assertions.assertEquals(BAD_CREDS_MESSAGE, badLoginError.getText());
    }

    @Test
    @Tag("login")
    @DisplayName("Cant login with blank credentials")
    void cantLoginWithBlankCredentials() {
        //Check that the navigation to Login was successful
        WebElement loginPageHeading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", loginPageHeading.getText());
        //Click Login button
        System.out.println("Clicking Login button");
        WebElement loginButton = driver.findElement(By.cssSelector("input.selenium-submit-button"));
        loginButton.click();
        //Check bad login message
        WebElement badLoginError = driver.findElement(By.xpath("//div[@class='selenium-error-block']"));
        Assertions.assertEquals(BLANK_CREDS_MESSAGE, badLoginError.getText());
    }
}
