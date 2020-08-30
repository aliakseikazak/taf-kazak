package by.kazak.taf.business.page;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.kazak.taf.core.config.ConfigData;
import lombok.extern.log4j.Log4j2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Log4j2
public class BasePage extends AbstractPage {

    protected static final String BASE_FORMAT = "%s%s";
    protected static final String SUBMIT_BTN_XPATH = "//button[@type='submit']";
    protected static final String DROPDOWN_ITEM_XPATH = "//div[contains(@class, 'single-option')]";
    protected static final String DROPDOWN_VALUE_XPATH = "//span[contains(@class, 'value')]";

    protected static String pageInitXPathFormat = "//*[contains(@class, '%s')]";

    protected BasePage(Page page) {
        waitUntilElementVisible(String.format(pageInitXPathFormat, page.getPageInitXPath()));
        log.info(page.toString());
    }

    protected void clickSubmitBtn() {
        click(SUBMIT_BTN_XPATH);
    }

    protected void click(String elementXPath) {
        click(ConfigData.WAIT_TIMEOUT_SECONDS, elementXPath);
    }

    protected void click(long timeout, String elementXPath) {
        waitForPageLoaded(timeout);
        Integer[] i = {0};
        new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until((ExpectedCondition<Boolean>) driver -> {
                    try {
                        WebElement element = findElement(elementXPath);
                        if (element.isEnabled()) {
                            assert driver != null;
                            String previousPageSource = driver.getPageSource();
                            click(findElement(elementXPath));
                            i[0]++;
                            return (!previousPageSource.equals(driver.getPageSource()) || i[0] > 0);
                        }
                    } catch (NoSuchElementException e) {
                        if (i[0] > 0) return true;
                    }
                    return false;
                });
        log.info("Click on element with xPath: '{}'", createXPath(elementXPath));
    }

    private void click(WebElement element) {
        element.click();
    }

    protected void sendKeys(String value, String elementXPath) {
        sendKeys(ConfigData.WAIT_TIMEOUT_SECONDS, value, elementXPath);
    }

    protected void sendKeys(long timeout, String value, String elementXPath) {
        if (null == value) return;
        waitForPageLoaded(timeout);
        new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until((ExpectedCondition<Boolean>) driver -> {
                    WebElement element = findElement(elementXPath);
                    selectTextByPressCtrlA(elementXPath);
                    pressBackspace(elementXPath);
                    sendKeys(element, value);
                    String actualValue = element.getAttribute("value");
                    return (StringUtils.startsWith(value, actualValue) ||
                            StringUtils.startsWith(value, element.getText()) ||
                            StringUtils.startsWith(value, element.getAttribute("title")) ||
                            StringUtils.endsWith(value, actualValue));
                });
        log.info("Set value '{}' in element with xPath: '{}'", value, createXPath(elementXPath));
    }

    private void sendKeys(WebElement element, String value) {
        element.sendKeys(value);
    }

    protected void validateAppInfoMessages(String message) {
        String infoMessageXPath = "//div[@id='notification-root']//p";
        waitUntilElementVisible(infoMessageXPath);
        assertThat(String.format("Check that '%s' app info message appears correctly", message),
                getText(infoMessageXPath), is(equalToIgnoringCase(message)));
        click(infoMessageXPath);
        waitUntilElementInvisible(infoMessageXPath);
    }

    protected String getText(String elementXPath) {
        return getText(ConfigData.WAIT_TIMEOUT_SECONDS, elementXPath);
    }

    protected String getText(long timeout, String elementXPath) {
        waitForPageLoaded(timeout);
        return new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until((ExpectedCondition<String>) driver -> {
                    String text = getText(findElement(elementXPath));
                    log.info("Received '{}' text from element with xPath: '{}'", text, createXPath(elementXPath));
                    return text;
                });
    }

    private String getText(WebElement element) {
        return element.getText();
    }

    protected void selectValueFromDropdown(String value, String elementXPath) {
        selectValueFromDropdown(ConfigData.WAIT_TIMEOUT_SECONDS, value, elementXPath);
    }

    protected void selectValueFromDropdown(long timeout, String value, String elementXPath) {
        if (null == value) return;
        waitForPageLoaded(timeout);
        Integer[] i = {0};
        click(elementXPath);
        new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until((ExpectedCondition<Boolean>) driver -> {
                    try {
                        List<WebElement> dropdownList = findElements(String.format(BASE_FORMAT, elementXPath, DROPDOWN_ITEM_XPATH));
                        WebElement item = dropdownList.stream().filter(option -> getText(option).contains(value)).findFirst().orElse(null);
                        assert item != null;
                        click(item);
                        i[0]++;
                        String selectedValue = getText(String.format(BASE_FORMAT, elementXPath, DROPDOWN_VALUE_XPATH));
                        return value.equals(selectedValue);
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        if (i[0] > 0) return true;
                    }
                    return false;
                });
        log.info("Select '{}' value from dropdown xpath: {}", value, createXPath(elementXPath));
    }

    protected void selectTextByPressCtrlA(String elementXPath) {
        WebElement element = waitUntilElementVisible(elementXPath);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        log.debug("Select all in element in element with xPath: '{}'", createXPath(elementXPath));
    }

    protected void pressBackspace(String elementXPath) {
        waitUntilElementToBeClickable(elementXPath).sendKeys(Keys.BACK_SPACE);
        log.debug("Press 'BACKSPACE' button in element with xPath: '{}'", createXPath(elementXPath));
    }

    protected List<WebElement> findElements(String elementXPath) {
        return driver.findElements(By.xpath(elementXPath));
    }

    protected WebElement findElement(String elementXPath) {
        return driver.findElement(By.xpath(elementXPath));
    }

    protected String createXPath(String elementXPath) {
        String xPath = String.format("(%s)[not(ancestor::div[contains(@style,'display: none')])]", elementXPath);
        xPath = xPath.replaceAll("\\$(.*?)\\$", String.format("translate(%s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')", "$1"));
        log.debug("Created xPath: {}", xPath);
        return xPath;
    }

    protected WebElement waitUntilElementVisible(String elementXPath) {
        return waitUntilElementVisible(ConfigData.WAIT_TIMEOUT_SECONDS, elementXPath);
    }

    protected WebElement waitUntilElementVisible(long timeout, String elementXPath) {
        String xPath = createXPath(elementXPath);
        log.debug("Wait until element visible with xPath: '{}', max '{}' seconds", xPath, timeout);
        return new WebDriverWait(driver, timeout, ConfigData.POLLING_INTERVAL)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    protected boolean waitUntilElementInvisible(String elementXPath) {
        return waitUntilElementInvisible(ConfigData.WAIT_TIMEOUT_SECONDS, elementXPath);
    }

    protected boolean waitUntilElementInvisible(long timeout, String elementXPath) {
        String xPath = createXPath(elementXPath);
        log.debug("Wait until element be invisible with xPath: '{}', max '{}' seconds", xPath, timeout);
        WebDriverWait wait = new WebDriverWait(driver, timeout, ConfigData.POLLING_INTERVAL);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    }

    protected WebElement waitUntilElementToBeClickable(String elementXPath) {
        String xPath = createXPath(elementXPath);
        log.debug("Wait until element to be clickable, xPath: '{}'", elementXPath);
        WebDriverWait wait = new WebDriverWait(driver, ConfigData.WAIT_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    private void waitForPageLoaded(long timeout) {
        log.debug("Wait for page loaded... max '{}' seconds", timeout);
        new WebDriverWait(driver, timeout).until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) Objects.requireNonNull(driver))
                .executeScript("return document.readyState").equals("complete"));
    }
}
