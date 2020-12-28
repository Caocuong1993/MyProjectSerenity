package cucumber.userInterface;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPageUI {
    public static Target EMAIL_FIELD    =  Target.the("email field")
            .locatedBy("//*[@id='email']");
    public static Target SIGN_IN    =  Target.the("Sign in")
            .locatedBy("//a[contains(text(),'Sign in')]");

    public static Target
            PASSWORD_FIELD = Target.the("password field")
            .locatedBy("//*[@id='passwd']");

    public static Target BTN_LOGIN = Target.the("btn login")
            .locatedBy("//*[@id='SubmitLogin']");

    public static Target ERROR_MESSAGE_ELEMENT   = Target.the("error message element")
            .locatedBy("//form[@method='post'][not(@name)]//div[@class='form-errors clearfix']");
}
