package by.kazak.taf.business.page;

import by.kazak.taf.business.model.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginPage extends BasePage {

    private static String loginInputXPath = "//input[@placeholder='Login']";
    private static String passwordInputXPath = "//input[@placeholder='Password']";
    private static String submitBtnXPath = "//button[@type='submit']";

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
        log.info(String.format("User '%s' logged in successfully", user.getUserLogin()));
        return new MainPage();
    }
}
