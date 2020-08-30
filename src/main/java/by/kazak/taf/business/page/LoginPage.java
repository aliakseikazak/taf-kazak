package by.kazak.taf.business.page;

import by.kazak.taf.business.model.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginPage extends BasePage {

    private static final String SIGNED_IN_SUCCESSFULLY = "Signed in successfully";

    private static String loginInputXPath = "//input[@placeholder='Login']";
    private static String passwordInputXPath = "//input[@placeholder='Password']";

    public LoginPage() {
        super(Page.LOGIN);
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

    public DashboardPage login(User user) {
        setUserCredentials(user);
        clickSubmitBtn();
        validateLogInSuccessfulMessage(user);
        return new DashboardPage();
    }

    private void validateLogInSuccessfulMessage(User user) {
        validateAppInfoMessages(SIGNED_IN_SUCCESSFULLY);
        log.info("User '{}' logged in successfully", user.getUserLogin());
    }
}
