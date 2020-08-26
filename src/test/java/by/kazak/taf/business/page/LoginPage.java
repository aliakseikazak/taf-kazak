package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

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
        return this;
    }

    public MainPage login(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitBtn.click();
        return new MainPage(driver);
    }
}
