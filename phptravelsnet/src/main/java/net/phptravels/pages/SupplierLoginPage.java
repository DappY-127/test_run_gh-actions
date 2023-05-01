package net.phptravels.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class SupplierLoginPage {
    protected WebDriver driver;

    @FindBy(css = ".pure-material-textfield-outlined [name='email']")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[text()='Invalid Login Credentials']")
    private WebElement invalidLoginCredentialsErrorMessage;

    @FindBy(xpath = "//div/p[text()='The Password field is required.']")
    private WebElement emptyPasswordFieldErrorMessage;

    @FindBy(xpath = "//div/p[text()='The Email field is required.']")
    private WebElement emptyEmailFieldErrorMessage;

    @FindBy(xpath = "//div/p[text()='The Email field must contain a valid email address.']")
    private WebElement invalidEmailFormatErrorMessage;

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getInvalidLoginCredentialsErrorMessage() {
        return invalidLoginCredentialsErrorMessage;
    }

    public WebElement getEmptyPasswordFieldErrorMessage() {
        return emptyPasswordFieldErrorMessage;
    }

    public WebElement getEmptyEmailFieldErrorMessage() {
        return emptyEmailFieldErrorMessage;
    }

    public WebElement getInvalidEmailFormatErrorMessage() {
        return invalidEmailFormatErrorMessage;
    }

    public SupplierLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public boolean isEmptyEmailFieldErrorMessageDisplayed() {
        try {
            return emptyEmailFieldErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isEmptyPasswordFieldErrorMessageDisplayed() {
        try {
            return emptyPasswordFieldErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInvalidCredentialsErrorMessageDisplayed() {
        try {
            return invalidLoginCredentialsErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInvalidEmailFormatErrorMessageDisplayed() {
        try {
            return invalidEmailFormatErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return loginButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginButtonClickable() {
        try {
            return loginButton.isDisplayed() && loginButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void supplierLogin(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }
}
