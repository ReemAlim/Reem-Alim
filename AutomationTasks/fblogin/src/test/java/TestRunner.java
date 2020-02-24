import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import pages.LoginClass;


@RunWith(Cucumber.class)
@CucumberOptions (features = {"features/first_register.feature","features/second_login.feature"})

public class TestRunner {
    private static LoginClass loginClass = new LoginClass();

    @AfterClass
    public static void quit() {
        loginClass.quitDriver();
    }
}
