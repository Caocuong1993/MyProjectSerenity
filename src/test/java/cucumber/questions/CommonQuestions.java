package cucumber.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class CommonQuestions {
    public static Question<String> textValue(Target tagert) {
        return actor -> Text.of(tagert).viewedBy(actor).asString();
    }

    public static Question<String> attributeValue(Target tagert) {
        return actor -> Value.of(tagert).viewedBy(actor).asString();
    }

    public static Question<Boolean> isDisplay(Target target) {
        return new Question<Boolean>() {
            @Override
            public Boolean answeredBy(Actor actor) {
                return getDriver().findElement(By.xpath(target.getCssOrXPathSelector())).isDisplayed();
            }
        };

    }

    public static Question<String> textQuestion(Target target) {
        return new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                return getDriver().findElement(By.xpath(target.getCssOrXPathSelector())).getText();
            }
        };
    }

    public static Question<String> attributeQuestion(Target target, String attribute) {
        return new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                return getDriver().findElement(By.xpath(target.getCssOrXPathSelector())).getAttribute(attribute);
            }
        };
    }

    public static Question<List<String>> listAttributeQuestion(Target target, String attribute) {
        return new Question<List<String>>() {
            @Override
            public List<String> answeredBy(Actor actor) {
                List<String> stringList = new ArrayList<>();
                List<WebElement> list = getDriver().findElements(By.xpath(target.getCssOrXPathSelector()));
                for (WebElement e : list
                ) {
                    stringList.add(e.getAttribute(attribute));
                }
                stringList.add("1");
                System.out.println("aaaaaaaaaaaaa" + stringList.toString());
                return stringList;
            }
        };
    }
}
