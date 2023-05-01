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
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/supplier"));
        Assertions.assertTrue(supplierDashboardPage.isDashboardLabelDisplayed());
    }

    @Test
    @DisplayName("Supplier login with empty login field")
    public void testSupplierCantLoginWithEmptyLoginField() {
        test = extent.createTest("Supplier login with empty login field", "____");
        supplierLoginPage.supplierLogin("", supplierPassword);
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getEmptyEmailFieldErrorMessage()));
        Assertions.assertTrue(supplierLoginPage.isEmptyEmailFieldErrorMessageDisplayed());
    }

    @Test
    @DisplayName("Supplier login with empty password field")
    public void testSupplierCantLoginWithEmptyPasswordField() {
        test = extent.createTest("Supplier login with empty login field", "____");
        supplierLoginPage.supplierLogin(supplierLogin, "");
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getEmptyPasswordFieldErrorMessage()));
        Assertions.assertTrue(supplierLoginPage.isEmptyPasswordFieldErrorMessageDisplayed());
    }

    @Test
    @DisplayName("Supplier login with invalid email format in email field")
    public void testSupplierCantLoginWithInvalidFormatEmailField() {
        test = extent.createTest("Supplier login with invalid email format in email field", "____");
        supplierLoginPage.supplierLogin("phptravels.com", getConfig().getProperty("password.invalid"));
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getInvalidEmailFormatErrorMessage()));
        Assertions.assertTrue(supplierLoginPage.isInvalidEmailFormatErrorMessageDisplayed());
    }

    @Test
    @DisplayName("Supplier login with invalid credentials")
    public void testSupplierCantLoginWithInvalidCredentionals() {
        test = extent.createTest("Supplier login with invalid credentials", "____");
        supplierLoginPage.supplierLogin(getConfig().getProperty("login.invalid"), getConfig().getProperty("password.invalid"));
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getInvalidLoginCredentialsErrorMessage()));
        Assertions.assertTrue(supplierLoginPage.isInvalidCredentialsErrorMessageDisplayed());
    }

}
