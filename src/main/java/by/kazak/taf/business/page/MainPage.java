package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

public class MainPage extends BasePage {

    private static String loggedInUserXPath = "//div[contains(@class, 'username')]";

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        LOG.info(String.format("'%s' is opened", this.getClass().getSimpleName()));
        return this;
    }

    public MainPage validateLoggedInUserName(String userName) {
        assertThat(getText(loggedInUserXPath), is(equalToIgnoringCase(userName)));
        return this;
    }
}
