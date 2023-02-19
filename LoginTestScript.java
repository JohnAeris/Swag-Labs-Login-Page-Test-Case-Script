import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;



public class LoginTestScript {
    public static void main(String[] args) throws InterruptedException{
        System.setProperty("webdriver.edge.driver", "D:\\Projects\\Test Automation\\WebDriver\\Microsoft Edge\\edgedriver_win64\\msedgedriver.exe"); //Webdriver path
        
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.saucedemo.com"); //SauceDemo.com

        //Maximize Method
        driver.manage().window().maximize();

        //Current URL
        String actualWebUrl = driver.getCurrentUrl();
        String expectedWebUrl = "https://www.saucedemo.com/";
        if (actualWebUrl.equalsIgnoreCase(expectedWebUrl)) {
            System.out.println("TC_ID_001 ========================================== Successful");
        } else {
            System.out.println("TC_ID_001 ========================================== Failed");
        }

        //Web Title
        String actualWebTitle = driver.getTitle();
        String expectedWebTitle = "Swag Labs";
        if (actualWebTitle.equalsIgnoreCase(expectedWebTitle)) {
            System.out.println("TC_ID_002 ========================================== Successful");
        } else {
            System.out.println("TC_ID_002 ========================================== Failed");
        }

        //Variable Initialization
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        long delay = 3000;
        
        String correctUsername = "standard_user";
        String correctPassword = "secret_sauce";
        String incorrectUsername = "user";
        String incorrectPassword = "secret";
        String blankInput = "";

        //Login Test Case 1: Username / Password X
        usernameTextField(usernameField, correctUsername);
        passwordTextField(passwordField, incorrectPassword);
        click(loginBtn);
        Thread.sleep(delay);
        WebElement actualErrorMessage = driver.findElement(By.cssSelector("div.error-message-container"));
        testCase(actualErrorMessage, "TC_ID_003", "Epic sadface: Username and password do not match any user in this service");
        clearTextField(usernameField);
        clearTextField(passwordField);

        //Login Test Case 2: Username X Password /
        usernameTextField(usernameField, incorrectUsername);
        passwordTextField(passwordField, correctPassword);
        click(loginBtn);
        Thread.sleep(delay);
        testCase(actualErrorMessage, "TC_ID_004", "Epic sadface: Username and password do not match any user in this service");
        clearTextField(usernameField);
        clearTextField(passwordField);

        //Login Test Case 3: Username X Password X
        usernameTextField(usernameField, incorrectUsername);
        passwordTextField(passwordField, incorrectPassword);
        click(loginBtn);
        Thread.sleep(delay);
        testCase(actualErrorMessage, "TC_ID_005", "Epic sadface: Username and password do not match any user in this service");
        clearTextField(usernameField);
        clearTextField(passwordField);

        //Login Test Case 4: Username / Password -
        usernameTextField(usernameField, correctUsername);
        passwordTextField(passwordField, blankInput);
        click(loginBtn);
        Thread.sleep(delay);
        testCase(actualErrorMessage, "TC_ID_006", "Epic sadface: Password is required");
        clearTextField(usernameField);
        clearTextField(passwordField);

        //Login Test Case 5: Username - Password /
        usernameTextField(usernameField, blankInput);
        passwordTextField(passwordField, correctPassword);
        click(loginBtn);
        Thread.sleep(delay);
        testCase(actualErrorMessage, "TC_ID_007", "Epic sadface: Username is required");
        clearTextField(usernameField);
        clearTextField(passwordField);

        //Login Test Case 6: Username - Password -
        usernameTextField(usernameField, blankInput);
        passwordTextField(passwordField, blankInput);
        click(loginBtn);
        Thread.sleep(delay);
        testCase(actualErrorMessage, "TC_ID_008", "Epic sadface: Username is required");
        clearTextField(usernameField);
        clearTextField(passwordField);

        //Login Test Case 7: Username / Password /
        usernameTextField(usernameField, correctUsername);
        passwordTextField(passwordField, correctPassword);
        click(loginBtn);
        Thread.sleep(delay);
        if (driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/inventory.html")){
            System.out.println("TC_ID_009 ========================================== Successful");
        } else {
            System.out.println("TC_ID_009 ========================================== Failed");
        }

        //Quit Method
        driver.quit();
    }

    //Test Case Status Method
    public static String testCase(WebElement actualErrorMessage, String testCaseNumber, String expectedErrorMessage) {
        String error = actualErrorMessage.getText();
        if (error.equalsIgnoreCase(expectedErrorMessage)) {
           System.out.println(testCaseNumber + " ========================================== Successful");
        } else {
            System.out.println(testCaseNumber + " ========================================== Failed");
        }
        return error;
    }

    //Input username method
    public static WebElement usernameTextField(WebElement field, String inputUsername) {
        field.sendKeys(inputUsername);
        return field;
    }

    //Input password method
    public static WebElement passwordTextField(WebElement field, String inputPassword) {
        field.sendKeys(inputPassword);
        return field;
    }

    //Click button method
    public static WebElement click(WebElement btn) {
        btn.click();
        return btn;
    }

    //Clear textfield method
    public static WebElement clearTextField(WebElement field) {
        field.sendKeys(Keys.CONTROL + "a");
        field.sendKeys(Keys.DELETE);
        return field;

    }
}