package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void creationSucceedsWithValidUsernameAndPassword(String username, String password) {
        createUser(username, password, true);
    }

    @Then("new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("invalid username {string} and valid password {string} and matching password confirmation are entered")
    public void creationFailsWithInvalidUsernameAndValidPassword(String username, String password) {
        createUser(username, password, true);
    }

    @When("valid username {string} and invalid password {string} and matching password confirmation are entered")
    public void creationFailsWithValidUsernameAndInvalidPassword(String username, String password) {
        createUser(username, password, true);
    }

    @When("valid username {string} and valid password {string} and not-matching confirmation are entered")
    public void creationFailsWithValidUsernameAndPasswordAndNotMatchingConfirmation(String username, String password) {
        createUser(username, password, false);
    }

    @Then("user is not created and error {string} is reported")
    public void newUserIsNotCreated(String error) {
        pageHasContent(error);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUser(String username, String password, boolean match) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        if (match) {
            element.sendKeys(password);
        } else {
            element.sendKeys(password + "n");
        }
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
