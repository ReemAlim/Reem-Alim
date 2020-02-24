package pages;

import com.google.inject.internal.ErrorsException;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import utilities.CommonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


class BaseClass {
    private enum Browsers {CHROME, FIREFOX}

    static String userTestDataPath = "src/test/java/test_data/user_data.json";
    private static String uiLocatorsPath = "src/main/java/utilities/UILocators.json";
    private static String urlsPath = "src/test/java/test_data/URLs.json";
    static  JSONObject urls = CommonUtils.jsonReader(urlsPath);
    static JSONObject uiLocators = CommonUtils.jsonReader(uiLocatorsPath);
    public static WebDriver driver = createWebDriver();
    private static  String apiURL = (String) urls.get("testUserApi");
    private static  String userNameApi = (String) urls.get("userNameApi");

    BaseClass() {
        createRandomTestUser();
        appendUserNameAndBirthDateToJsonObject();
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeClass
    private static WebDriver createWebDriver() throws RuntimeException {
        String browserInstance = System.getenv("BROWSER_INSTANCE");
        Browsers browser = Browsers.valueOf(System.getProperty("browser", browserInstance).toUpperCase());
        if(driver != null) {
            return driver;
        }
        switch (browser) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", (String) urls.get("firefoxDriverPath"));
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("permissions.default.desktop-notification", 1);
                DesiredCapabilities capabilities=DesiredCapabilities.firefox();
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                return new FirefoxDriver(capabilities);
            case CHROME:
                System.setProperty("webdriver.chrome.driver", (String) urls.get("chromeDriverPath"));
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en-US");
                options.setExperimentalOption("prefs", prefs);
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Unsupported webdriver: " + browser);
        }
    }

    private static void createRandomTestUser() {
        try {
            JsonNode userData = Unirest.post(apiURL)
                    .asJson()
                    .getBody();
            CommonUtils.writeIntoJSONFile(userTestDataPath, userData);

        } catch (UnirestException e) {
            System.err.println(e);
        }
    }

    private static String getTestUserNameUsingID() {
        try {
            JSONObject userTestData = CommonUtils.jsonReader(userTestDataPath);

            JSONObject userDataObject = (JSONObject) userTestData.get("User Data");
            String userID = (String)  userDataObject.get("id");

            String resultedURL = userNameApi.replace("%s", userID);

            JsonNode userName = Unirest.get(resultedURL)
                    .asJson()
                    .getBody();
            return userName.getObject().get("name").toString();

        } catch (UnirestException e) {
            System.err.println(e);
            return null;
        }
    }

    private static void appendUserNameAndBirthDateToJsonObject() {
        JSONObject userTestData = CommonUtils.jsonReader(userTestDataPath);
        String userFullName = getTestUserNameUsingID();
        JSONObject userDataObject = (JSONObject) userTestData.get("User Data");
        String[] nameArray = userFullName.split("\\s+");
        String firstNameKey = "firstName";
        String firstNameValue = nameArray[0];
        String surNameKey = "surName";
        String surNameValue = nameArray[2];
        JSONObject nameObject = new JSONObject();

        nameObject.put(firstNameKey, firstNameValue);
        nameObject.put(surNameKey, surNameValue);

        userDataObject.put("userName", nameObject);
        userDataObject.put("birthDay", "18");
        userDataObject.put("birthMonth", "2");
        userDataObject.put("birthYear", "1985");

        try {
            FileWriter fileWriter = new FileWriter(userTestDataPath);
            fileWriter.write(userDataObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }



}