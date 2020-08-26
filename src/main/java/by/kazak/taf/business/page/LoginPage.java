package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.kazak.taf.business.model.User;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(LOGIN_URL);
        log.info(String.format("'%s' is opened", this.getClass().getSimpleName()));
        return this;
    }

    private void setLogin(User user) {
        loginInput.sendKeys(user.getUserLogin());
    }

    private void setPassword(User user) {
        passwordInput.sendKeys(user.getPassword());
    }

    private void setUserCredentials(User user) {
        setLogin(user);
        setPassword(user);
    }

    public MainPage login(User user) {
        setUserCredentials(user);
        submitBtn.click();
        log.info(String.format("User '%s' logged in successfully", user.getUserLogin()));
        return new MainPage(driver);
    }
}
