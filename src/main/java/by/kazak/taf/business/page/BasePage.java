package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;

public class BasePage extends AbstractPage{

    protected BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
