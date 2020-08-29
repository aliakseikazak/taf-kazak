package by.kazak.taf.business.page;

import lombok.extern.log4j.Log4j2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MainPage extends BasePage {

    private static String loggedInUserXPath = "//div[contains(@class, 'username')]";

    public MainPage validateLoggedInUserName(String userName) {
        assertThat(getText(loggedInUserXPath), is(equalToIgnoringCase(userName)));
        return this;
    }
}
