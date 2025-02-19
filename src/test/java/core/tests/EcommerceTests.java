package core.tests;

import core.globals.Globals;
import core.pages.*;
import core.utilities.BaseInformation;
import core.utilities.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import core.listeners.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class EcommerceTests {

    RegisterPage registerPage = new RegisterPage();
    SignInPage signInPage = new SignInPage();
    CheckPageFiltersPage checkPageFiltersPage = new CheckPageFiltersPage();
    WishListPage wishListPage = new WishListPage();
    ShoppingCartTestPage shoppingCartTestPage = new ShoppingCartTestPage();
    EmptyShoppingCartPage emptyShoppingCartPage = new EmptyShoppingCartPage();

    @AfterTest
    public void quit() {
        BaseInformation.quit();
    }

// Test 1: Register a new account

    @Test(priority = 1)
    public void testRegister() {
        // Open registration page and fill the form
        registerPage.openRegistrationPage(Globals.baseUrl);
        registerPage.clickRegisterButton();
        registerPage.setFirstName("TestFirstName");
        registerPage.setLastName("TestLastName");
        registerPage.setEmail("test" + System.currentTimeMillis());
        registerPage.setPassword("SecurePass123");
        registerPage.setConfirmPassword("SecurePass123");
        registerPage.clickRegisterButtonForm();

        // Verify registration and sign out
        Assert.assertTrue(registerPage.checkRegister(), "Registration check failed!");
        Assert.assertTrue(registerPage.isSuccessMessageDisplayed(), "Success message not displayed!");
        registerPage.signOut();
        WaitUtils.waitFor(3000);
    }

// Test 2: Sign In with newly created account

    @Test(priority = 2, dependsOnMethods = "testRegister")
    public void testSignIn() {
        // Navigate to Sign In page
        signInPage.openSignInPage(Globals.baseUrl);
        signInPage.clickSignInLink();

        // Input credentials and Sign In
        signInPage.setEmail(Globals.email);
        signInPage.setPassword(Globals.password);
        signInPage.clickSignInButton();

        // Verify Welcome Text
        String expectedWelcomeText = "Welcome, TestFirstName TestLastName!";
        String actualWelcomeText = signInPage.getWelcomeMessageText();
        signInPage.checkEqualityOfMessages(actualWelcomeText, expectedWelcomeText);

        // Sign out after verification
        signInPage.clickSignOut();
        WaitUtils.waitFor(3000);
    }

   // Test 3: Check Page Filters

    @Test(priority = 3, dependsOnMethods = "testSignIn")
    public void checkPageFilters() {
        // Navigate and sign in
        signInPage.openSignInPage(Globals.baseUrl);
        signInPage.clickSignInLink();
        signInPage.setEmail(Globals.email);
        signInPage.setPassword(Globals.password);
        signInPage.clickSignInButton();

        // Navigate to Jackets Page and apply filters
        checkPageFiltersPage.navigateToJacketsPage();
        checkPageFiltersPage.selectRedColorFilter();
        checkPageFiltersPage.verifyAllProductsHaveRedColorSelected();
        checkPageFiltersPage.selectWantedPriceFilter();
        checkPageFiltersPage.verifyThatOnlyTwoProductsAreDisplayed(2);
        checkPageFiltersPage.verifyAllProductPricesInRange();

        WaitUtils.waitFor(3000);
    }


    // Test 4: Add Items to Wish List

    @Test(priority = 4, dependsOnMethods = "checkPageFilters")
    public void wishListTest() {
        wishListPage.removePriceFilter();
        wishListPage.checkThatCountIsIncreased();
        wishListPage.clickOnProductItemAndWait(0);
        wishListPage.clickOnAddToWishListButtonAndWait();
        wishListPage.isProductSuccessfulWishListMessageDisplayed(0);

        checkPageFiltersPage.navigateToJacketsPage();
        checkPageFiltersPage.selectRedColorFilter();
        wishListPage.clickOnProductItemAndWait(1);
        wishListPage.clickRedOptionToJackets();
        wishListPage.clickOnAddToWishListButtonAndWait();
        wishListPage.isProductSuccessfulWishListMessageDisplayed(1);

        wishListPage.verifyTwoItemsAreDisplayed(2);
        WaitUtils.waitFor(3000);
    }


    //Test 5: Add Items to Shopping Cart and Verify Total

    @Test(priority = 5, dependsOnMethods = "checkPageFilters")
    public void shoppingCartTest() {
        checkPageFiltersPage.navigateToJacketsPage();
        checkPageFiltersPage.selectRedColorFilter();
        checkPageFiltersPage.selectWantedPriceFilter();
        shoppingCartTestPage.clickOnProduct1Size();
        shoppingCartTestPage.clickOnProduct2Size();
        shoppingCartTestPage.hoverOverFirstCardAndClickAddToCart();
        shoppingCartTestPage.verifyFirstProductIsAddedToCart();
        shoppingCartTestPage.hoverOverSecondCardAndClickAddToCart();
        shoppingCartTestPage.verifySecondProductIsAddedToCart();
        shoppingCartTestPage.clickOnShoppingCartLink();
        shoppingCartTestPage.verifyThatWeHaveNavigatedToShoppingCartPage();
        shoppingCartTestPage.verifyTotalShoppingSum();

        WaitUtils.waitFor(3000);
    }

  // Test 6: Empty Shopping Cart

    @Test(priority = 6, dependsOnMethods = "shoppingCartTest")
    public void emptyShoppingCartTest() {
        emptyShoppingCartPage.removeTheSecondShoppingItem();
        emptyShoppingCartPage.verifyThatTheNumberOfTheItemsDecreases();
        emptyShoppingCartPage.removeTheFirstShoppingItem();
        emptyShoppingCartPage.verifyThatTheNumberOfTheItemsDecreases();
        emptyShoppingCartPage.verifyThatTheEmptyShoppingCardIsDisplayed();
        emptyShoppingCartPage.closeBrowser();
    }
}
