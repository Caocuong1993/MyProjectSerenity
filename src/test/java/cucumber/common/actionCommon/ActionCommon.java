package cucumber.common.actionCommon;

import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ActionCommon {
    public void inputText(Target targetLocator, String value){
        theActorInTheSpotlight().attemptsTo(Enter.theValue(value).into(targetLocator));
    }

}
