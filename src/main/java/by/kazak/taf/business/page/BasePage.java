package by.kazak.taf.business.page;

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

@Log4j2
public class BasePage extends AbstractPage {

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
                            findElement(elementXPath).click();
                            i[0]++;
                            return (!previousPageSource.equals(driver.getPageSource()) || i[0] > 0);
                        }
                    } catch (NoSuchElementException e) {
                        if (i[0] > 0) return true;
                    }
                    return false;
                });
        log.info(String.format("Click on element with xPath: '%s'", createXPath(elementXPath)));
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
                    element.sendKeys(value);
                    String actualValue = element.getAttribute("value");
                    return (StringUtils.startsWith(value, actualValue) ||
                            StringUtils.startsWith(value, element.getText()) ||
                            StringUtils.startsWith(value, element.getAttribute("title")) ||
                            StringUtils.endsWith(value, actualValue));
                });
        log.info(String.format("Set value '%s' in element with xPath: '%s'", value, createXPath(elementXPath)));
    }

    protected String getText(String elementXPath) {
        return getText(ConfigData.WAIT_TIMEOUT_SECONDS, elementXPath);
    }

    protected String getText(long timeout, String elementXPath) {
        waitForPageLoaded(timeout);
        return new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until((ExpectedCondition<String>) driver -> {
                    String text = findElement(elementXPath).getText();
                    log.info(String.format("Get text from element with xPath: '%s'", createXPath(elementXPath)));
                    return text;
                });
    }

    protected void selectTextByPressCtrlA(String elementXPath) {
        WebElement element = waitUntilElementVisible(elementXPath);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        log.debug(String.format("Select all in element in element with xPath: '%s'", createXPath(elementXPath)));
    }

    protected void pressBackspace(String elementXPath) {
        waitUntilElementToBeClickable(elementXPath).sendKeys(Keys.BACK_SPACE);
        log.debug(String.format("Press 'BACKSPACE' button in element with xPath: '%s'", createXPath(elementXPath)));
    }

    protected WebElement findElement(String elementXPath) {
        return driver.findElement(By.xpath(elementXPath));
    }

    protected String createXPath(String elementXPath) {
        String xPath = String.format("(%s)[not(ancestor::div[contains(@style,'display: none')])]", elementXPath);
        xPath = xPath.replaceAll("\\$(.*?)\\$", String.format("translate(%s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')", "$1"));
        log.debug(String.format("Created xPath: %s", xPath));
        return xPath;
    }

    protected WebElement waitUntilElementVisible(String elementXPath) {
        return waitUntilElementVisible(ConfigData.WAIT_TIMEOUT_SECONDS, elementXPath);
    }

    protected WebElement waitUntilElementVisible(long timeout, String elementXPath) {
        String xPath = createXPath(elementXPath);
        log.debug(String.format("Wait until element visible with xPath: '%s', max '%d' seconds", xPath, timeout));
        return new WebDriverWait(driver, timeout,ConfigData. POLLING_INTERVAL)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    protected WebElement waitUntilElementToBeClickable(String elementXPath) {
        String xPath = createXPath(elementXPath);
        log.debug(String.format("Wait until element to be clickable, xPath: '%s'", elementXPath));
        WebDriverWait wait = new WebDriverWait(driver, ConfigData.WAIT_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    private void waitForPageLoaded(long timeout) {
        log.debug(String.format("Wait for page loaded... max '%d' seconds", timeout));
        new WebDriverWait(driver, timeout).until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) Objects.requireNonNull(driver))
                .executeScript("return document.readyState").equals("complete"));
    }
}
