# InstabugChallenge
Instabug task

### First, Please clone this repoistory which contains two folders:
#### YallaKora folder that contains testplan and issues folder for the Yallakora software application.

#### AutomationTasks folder that contains APITests folder for API tests, fblogin folder for e2e automation tests and WebDriver folder that contains the drivers which will be used for automation.

### For setting up the enviroment for Facebook login and resiteration E2E testing:

#### The implemented scripts are based on cucumber (behavior-driven development) framework using selenuim;I have implemented two features for each requested script the registeration and login for the facebook website using dynamic test data using the facebook test users api.

#### To run it please follow the following steps:

##### First you need Intellij to import the project, You need to import the "fblogin" folder on Intellij

##### Make sure to put the "fblogin" folder and "Webdriver" folder on the same directory root.

##### Go to the "TestRunner" class "/fblogin/src/test/java/TestRunner.java", make sure before running this class "TestRunner" go to "Edit Configuration" of this file and add "BROWSER_INSTANCE=chrome" OR "BROWSER_INSTANCE=firefox" based on your preference which driver you want to run.

##### This will run the two implemented scripts in order, The registeration script then with the same user for registeration it will run the login script.

### For setting up the enviroment for BestBuy Api testing:
#### The implemented scripts are done with postman.

#### To run them please follow the following steps:

##### First you need postman to import the provided two json files
##### Please import the API collection and import the enviroment variables file as well
##### Run the testcases provided for each API.
##### Check the attached file for explaining what I have covered in the API testing
