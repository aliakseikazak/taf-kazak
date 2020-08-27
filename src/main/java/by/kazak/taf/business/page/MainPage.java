package by.kazak.taf.business.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

public class MainPage extends AbstractPage {

    private final By loggedInUserXPath = By.xpath("//div[contains(@class, 'username')]");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        log.info(String.format("'%s' is opened", this.getClass().getSimpleName()));
        return this;
    }

    public MainPage validateLoggedInUserName(String userName) {
        WebElement linkLoggedInUser = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(loggedInUserXPath));
        assertThat(linkLoggedInUser.getText(), is(equalToIgnoringCase(userName)));
        return this;
    }
}
