package by.kazak.taf.business.page;

import by.kazak.taf.business.model.User;
import by.kazak.taf.business.page.common.element.InputElement;
import by.kazak.taf.business.page.dashboard.AllDashboardsPage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginPage extends BasePage {

    private static final String SIGNED_IN_SUCCESSFULLY = "Signed in successfully";

    public LoginPage() {
        super(Page.LOGIN);
    }

    private void setLogin(User user) {
        sendKeysToSpecificInput(user.getUserLogin(), InputElement.LOGIN);
    }

    private void setPassword(User user) {
        sendKeysToSpecificInput(user.getPassword(), InputElement.PASSWORD);
    }

    private void setUserCredentials(User user) {
        setLogin(user);
        setPassword(user);
    }

    public AllDashboardsPage login(User user) {
        setUserCredentials(user);
        clickSubmitBtn();
        validateLogInSuccessfulMessage(user);
        return new AllDashboardsPage();
    }

    private void validateLogInSuccessfulMessage(User user) {
        validateAppInfoMessages(SIGNED_IN_SUCCESSFULLY, String.format("User '%s' signed in successfully", user.getUserLogin()));
    }
}
