package cucumber.actions;

import cucumber.userInterface.HomePage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
public static Performable navigateToHomePage(){
    return Task.where("{0} Navigate to website",Open.browserOn().the(HomePage.class));
}
}
