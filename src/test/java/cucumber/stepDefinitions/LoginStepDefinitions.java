package cucumber.stepDefinitions;

import cucumber.actions.NavigateTo;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.task.Login;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{} Navigate to automationpractice login site")
    public void navigateToSite(String actor) {
        theActorCalled(actor).attemptsTo(NavigateTo.navigateToHomePage());
    }

    @Given("Login with {} and {}")
    public void inputUserNameAndPassword(String userName, String passWord) {
        theActorInTheSpotlight().attemptsTo(Login.loginThePage(userName, passWord));
    }

}
