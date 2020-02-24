package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.RegistrationClass;

public class Registration {
    private RegistrationClass registrationClass = new RegistrationClass();

    @Given("^Navigate to Facebook website$")
    public void navigateToFacebookWebsite() {
        registrationClass.navigateToFacebookWebsite();
    }

    @And("^Fill the sign up form$")
    public void fillTheSignUpForm() {
        registrationClass.fillTheSignUpForm();
    }

    @Then("^Click sign up button$")
    public void clickSignUpButton() {
        registrationClass.clickSignUpButton();
    }

    @Then("^Assert that the user homepage is opened$")
    public void assertThatTheUserHomepageIsOpened() {
        registrationClass.assertThatTheUserHomepageIsOpened();
    }

    @Then("^Logout from facebook$")
    public void logoutFromFacebook() {
        registrationClass.clickOnNavigationPanelSettings();
        registrationClass.clickOnLogoutButton();
    }

}
