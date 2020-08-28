package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import by.kazak.taf.business.model.User;

public class LoginPage extends BasePage {

    private static String loginInputXPath = "//input[@placeholder='Login']";
    private static String passwordInputXPath = "//input[@placeholder='Password']";
    private static String submitBtnXPath = "//button[@type='submit']";

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(LOGIN_URL);
        LOG.info(String.format("'%s' is opened", this.getClass().getSimpleName()));
        return this;
    }

    private void setLogin(User user) {
        sendKeys(user.getUserLogin(), loginInputXPath);
    }

    private void setPassword(User user) {
        sendKeys(user.getPassword(), passwordInputXPath);
    }

    private void setUserCredentials(User user) {
        setLogin(user);
        setPassword(user);
    }

    public MainPage login(User user) {
        setUserCredentials(user);
        click(submitBtnXPath);
        LOG.info(String.format("User '%s' logged in successfully", user.getUserLogin()));
        return new MainPage(driver);
    }
}
