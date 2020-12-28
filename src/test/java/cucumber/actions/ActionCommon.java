package cucumber.actions;

import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ActionCommon {
    public void inputText(Target targetLocator, String value) {
        theActorInTheSpotlight().attemptsTo(Enter.theValue(value).into(targetLocator));
    }

    public WebElement getElement(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }


    public void typeText(Target targetLocators, String value) {
        waitVisible(targetLocators);
        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(value).into(targetLocators));
    }


    public void clickElement(Target targetLocators) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(targetLocators, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(targetLocators));
    }

    public void waitVisible(Target targetLocators) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(targetLocators, isVisible()).forNoMoreThan(30).seconds());
    }

    public void waitInvisible(Target targetLocators) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(targetLocators, WebElementStateMatchers.isNotVisible()));
    }

    public void waitElement(Target targetLocators) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(targetLocators, isVisible()).forNoMoreThan(10).seconds());
    }


    public WebElement existElement(String xpath) {
        try {
            return getDriver().findElement(By.xpath(xpath));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement existElement(Target targetLocators) {
        try {
            return getDriver().findElement(By.xpath(targetLocators.getCssOrXPathSelector()));
        } catch (Exception e) {
            return null;
        }
    }

    public void clearText(String xpath) {
        try {
            ActionCommon.printLog("Clear text in Field Input");
            getDriver().findElement(By.xpath(xpath)).clear();
        } catch (Exception e) {
            ActionCommon.printLog(" ======== Canot clear text in field or Not find element =======");
        }
    }

    public void clickTab(Target targetLocators) {
        getDriver().findElement(By.xpath(targetLocators.getCssOrXPathSelector())).sendKeys(Keys.TAB);
    }


    public String getAttributeElement(String xpath, String attribute) {
        ActionCommon.printLog("Get Attribute of Element");
        return getDriver().findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public String getText(String xpath) {
        return getDriver().findElement(By.xpath(xpath)).getText().trim();
    }

    public List<String> getTexts(String xpath) {
        List<String> listValue = new ArrayList<>();
        List<WebElement> webElementList = getDriver().findElements(By.xpath(xpath));
        for (WebElement webElement : webElementList) {
            listValue.add(webElement.getText());
        }
        return listValue;
    }

    public void switchToChildWindowsWithContainsTitle(String title) {
        Set<String> allWindows = getDriver().getWindowHandles();
        for (String runWindow : allWindows) {
            getDriver().switchTo().window(runWindow);
            getDriver().manage().window().maximize();
            String currentWin = getDriver().getTitle();
            if (currentWin.contains(title)) {
                break;
            }
        }
    }


    public void zoomOut() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_MINUS);
        } catch (AWTException e) {
            ActionCommon.printLog("AWTException: " + e);
        }
    }

    public void zoomIn() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_EQUALS);
        } catch (AWTException e) {
            ActionCommon.printLog("AWTException: " + e);
        }
    }


    public void scrollBarByJs(Target targetLocators) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Find element by link text and store in variable "Element"
        WebElement element = getElement(targetLocators.getCssOrXPathSelector());

        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollBarByJs(String xpath) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Find element by link text and store in variable "Element"
        WebElement element = getElement(xpath);

        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickElementByActions(Target targetLocators) {
        WebElement element = getElement(targetLocators.getCssOrXPathSelector());
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click().perform();
    }

    public String getCurrentDateLocalWithFormat(String formatDate) {
        SimpleDateFormat format = new SimpleDateFormat(formatDate); //dd-MMM-yy
        return format.format(new Date());
    }

    public void copyFile(String pathSource, String pathDest) {
        File source = new File(pathSource);
        File dest = new File(pathDest);

        try {
            Files.copy(source.toPath(), dest.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentDateCalendar(String format) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }


    public void waitVisible(Target targetLocators, int amount) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(targetLocators, isVisible()).forNoMoreThan(amount).seconds());

    }


    public static void printLog(String message) {
        // Create a Logger
        Logger logger = Logger.getLogger(ActionCommon.class.getName());

        // Log messages using log(Level level, String msg)
        logger.log(Level.INFO, message);
    }

    public void selectValueOption(Target xpathExpression, String value) {
        Select select = new Select(getElement(xpathExpression.getCssOrXPathSelector()));
        select.selectByVisibleText(value);
    }

    public void selectIndexOption(Target xpathExpression, int index) {
        Select select = new Select(getElement(xpathExpression.getCssOrXPathSelector()));
        select.selectByIndex(index);
    }

    public int getDaysBetween(String firstDay, String secondDay) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse(firstDay);
            endDate = formatter.parse(secondDay);
        } catch (ParseException e) {
            ActionCommon.printLog("ParseException: " + e);
        }
        assert startDate != null;
        return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
    }

    public void pressKeys(Target targetLocator, CharSequence keys) {
        getElement(targetLocator.getCssOrXPathSelector()).sendKeys(keys);
    }

    public String getIpAddress() {
        InetAddress myIP = null;
        try {
            myIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            ActionCommon.printLog("UnknownHostException: " + e);
        }

        assert myIP != null;
        ActionCommon.printLog(myIP.getHostAddress());
        return myIP.getHostAddress();
    }
}
