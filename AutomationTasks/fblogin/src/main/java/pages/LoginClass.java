package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import utilities.CommonUtils;

public class LoginClass extends BaseClass {
    private static final JSONObject userTestData = CommonUtils.jsonReader(userTestDataPath);
    private static final JSONObject loginLocators = (JSONObject)  uiLocators.get("loginLocators");

    public void fillInTheUsernameAndPassword(){
        driver.findElement(By.xpath((String) loginLocators.get("emailLocator"))).sendKeys((String) userTestData.get("email"));
        driver.findElement(By.xpath((String) loginLocators.get("passwordLocator"))).sendKeys((String) userTestData.get("password"));
    }

    public void clickOnTheLoginButton(){
        driver.findElement(By.xpath((String) loginLocators.get("loginButton"))).click();
    }

    public static void quitDriver(){
        driver.quit();
    }

}
