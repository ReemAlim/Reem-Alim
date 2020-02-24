package step_definitions;

import cucumber.api.java.en.Then;
import pages.LoginClass;


public class Login {
    private LoginClass loginClass = new LoginClass();

    @Then("^Fill in the username and password fields$")
    public void fillInTheUsernameAndPasswordFields() {
        loginClass.fillInTheUsernameAndPassword();
    }

    @Then("^Click login button$")
    public void clickLoginButton() {
        loginClass.clickOnTheLoginButton();
    }

}
