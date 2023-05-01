package net.phptravels.tests;

import net.phptravels.pages.SupplierDashboardPage;
import net.phptravels.pages.SupplierLoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DisplayName("Supplier user login tests")
public class SupplierLoginTest extends BaseTest {

    private SupplierLoginPage supplierLoginPage;
    private String supplierLogin;
    private String supplierPassword;

    @BeforeEach
    public void navigateToSupplierLoginPage() {
        String url = getConfig().getProperty("url.supplierlogin");
        supplierLogin = getConfig().getProperty("login.supplier");
        supplierPassword = getConfig().getProperty("password.supplier");
        driver.get(url);
        supplierLoginPage = new SupplierLoginPage(driver);
    }
    @Test
    @DisplayName("Supplier login with valid credentials")
    public void testSupplierCanLoginWithValidCredentials() {
        test = extent.createTest("Supplier login with valid credentials", "____");
        supplierLoginPage.supplierLogin(supplierLogin, supplierPassword);
        SupplierDashboardPage supplierDashboardPage = new SupplierDashboardPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(supplierDashboardPage.getSupplierAccountDropdown()));
        getWait().until(ExpectedConditions.visibilityOf(supplierDashboardPage.getDashboardLabel()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(getDriver().getCurrentUrl().contains("/supplier"),
                        "URL doesn't contain '/supplier'"),
                () -> Assertions.assertTrue(supplierDashboardPage.isDashboardLabelDisplayed(),
                        "Dashboard label is not displayed")
        );
    }

    @Test
    @DisplayName("Supplier login with empty login field")
    public void testSupplierCantLoginWithEmptyLoginField() {
        test = extent.createTest("Supplier login with empty login field", "____");
        supplierLoginPage.supplierLogin("", supplierPassword);
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getEmptyEmailFieldErrorMessage()));
        String expectedErrorMessage = "The Email field is required.";
        String actualErrorMessage = supplierLoginPage.getEmptyEmailFieldErrorMessage().getText();
        Assertions.assertAll(
                () -> Assertions.assertTrue(supplierLoginPage.isEmptyEmailFieldErrorMessageDisplayed(),
                        "Empty email field error label is not displayed"),
                () -> Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                        "Error message is not as expected.")
        );
    }

    @Test
    @DisplayName("Supplier login with empty password field")
    public void testSupplierCantLoginWithEmptyPasswordField() {
        test = extent.createTest("Supplier login with empty login field", "____");
        supplierLoginPage.supplierLogin(supplierLogin, "");
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getEmptyPasswordFieldErrorMessage()));
        String expectedErrorMessage = "The Password field is required.";
        String actualErrorMessage = supplierLoginPage.getEmptyPasswordFieldErrorMessage().getText();
        Assertions.assertAll(
                () -> Assertions.assertTrue(supplierLoginPage.isEmptyPasswordFieldErrorMessageDisplayed(),
                        "Empty password field error label is not displayed"),
                () -> Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                        "Error message is not as expected.")
        );
    }

    @Test
    @DisplayName("Supplier login with invalid email format in email field")
    public void testSupplierCantLoginWithInvalidFormatEmailField() {
        test = extent.createTest("Supplier login with invalid email format in email field", "____");
        supplierLoginPage.supplierLogin("phptravels.com", getConfig().getProperty("password.invalid"));
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getInvalidEmailFormatErrorMessage()));
        String expectedErrorMessage = "The Email field must contain a valid email address.";
        String actualErrorMessage = supplierLoginPage.getInvalidEmailFormatErrorMessage().getText();
        Assertions.assertAll(
                () -> Assertions.assertTrue(supplierLoginPage.isInvalidEmailFormatErrorMessageDisplayed(),
                        "Invalid email format field error label is not displayed"),
                () -> Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                        "Error message is not as expected.")
        );
    }

    @Test
    @DisplayName("Supplier login with invalid credentials")
    public void testSupplierCantLoginWithInvalidCredentionals() {
        test = extent.createTest("Supplier login with invalid credentials", "____");
        supplierLoginPage.supplierLogin(getConfig().getProperty("login.invalid"), getConfig().getProperty("password.invalid"));
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getInvalidLoginCredentialsErrorMessage()));
        String expectedErrorMessage = "Invalid Login Credentials";
        String actualErrorMessage = supplierLoginPage.getInvalidLoginCredentialsErrorMessage().getText();
        Assertions.assertAll(
                () -> Assertions.assertTrue(supplierLoginPage.isInvalidCredentialsErrorMessageDisplayed(),
                        "Invalid credentials error label is not displayed"),
                () -> Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                        "Error message is not as expected.")
        );
    }

}
