package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.CommonUtils;

public class RegistrationClass extends BaseClass {
    private JSONObject userTestData = CommonUtils.jsonReader(userTestDataPath);
    private JSONObject userNameRoot = (JSONObject) userTestData.get("userName");

    public void navigateToFacebookWebsite() {
        driver.navigate().to((String) urls.get("url"));
    }

    public void fillTheSignUpForm(){
        driver.findElement(By.xpath((String) uiLocators.get("firstName"))).sendKeys((String)userNameRoot.get("firstName"));
        driver.findElement(By.xpath((String) uiLocators.get("surName"))).sendKeys((String)userNameRoot.get("surName"));
        driver.findElement(By.xpath((String) uiLocators.get("mobileOrEmail"))).sendKeys((String)userTestData.get("email"));
        driver.findElement(By.xpath((String) uiLocators.get("emailConfirmation"))).sendKeys((String)userTestData.get("email"));
        driver.findElement(By.xpath((String) uiLocators.get("password"))).sendKeys((String)userTestData.get("password"));
        setBirthDate((String)userTestData.get("birthDay"), (String)userTestData.get("birthMonth"), (String)userTestData.get("birthYear"));
        driver.findElement(By.xpath((String) uiLocators.get("gender"))).click();
    }

    public void clickSignUpButton(){
        driver.findElement(By.xpath((String) uiLocators.get("signUpButton"))).click();
    }

    public void clickOnNavigationPanelSettings () {
        driver.findElement(By.xpath((String) uiLocators.get("userNavigationPanel"))).click();
    }

    public void assertThatTheUserHomepageIsOpened(){
        String nameXpath = "//span[contains(text(), '"+ userNameRoot.get("firstName") +"')]";
        Assert.assertTrue(driver.findElement(By.xpath(nameXpath)).isDisplayed());

    }

    private void setBirthDate(String day, String month, String year) {
        Select birthdayDropdown = new Select(driver.findElement(By.xpath((String) uiLocators.get("birthDay"))));
        birthdayDropdown.selectByValue(day);
        Select birthMonthDropdown = new Select(driver.findElement(By.xpath((String) uiLocators.get("birthMonth"))));
        birthMonthDropdown.selectByValue(month);
        Select birthYearDropdown = new Select(driver.findElement(By.xpath((String) uiLocators.get("birthYear"))));
        birthYearDropdown.selectByValue(year);
    }

    public void clickOnLogoutButton(){
        driver.findElement(By.xpath((String) uiLocators.get("logOutButton"))).click();
    }


}
