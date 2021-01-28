package stepdefinitions;

import com.codeborne.selenide.Configuration;
import elements.SearchField;
import elements.TopMenu;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageobjects.CatalogPage;

import static com.codeborne.selenide.Selenide.open;


public class MainPageStepDefinitions {
    private static final Logger logger = Logger.getLogger(MainPageStepDefinitions.class);
    private final TopMenu topMenu = new TopMenu();
    private final SearchField searchField = new SearchField();
    private final CatalogPage catalogPage = new CatalogPage();

    @Given("Main page is opened")
    public void mainPageIsOpened() {
        open(Configuration.baseUrl);
        logger.info("Main page is opened");
    }

    @When("User hovers on auto sales button")
    public void userHoversOnAutoSalesButton() {
        logger.info("Hovering on auto sales button");
        topMenu.openAutoSalesDropdownList();
    }

    @When("User hovers on flat sales button")
    public void userHoversOnFlatSalesButton() {
        logger.info("Hovering on flat sales button");
        topMenu.openFlatSalesDropdownList();
    }

    @Then("Auto sales dropdown list is displayed")
    public void autoSalesDropdownListIsDisplayed() {
        logger.info("Making auto sales dropdown list displaying check");
        topMenu.checkDropdownListIsDisplayed();
    }

    @Then("Flat sales dropdown list is displayed")
    public void flatSalesDropdownListIsDisplayed() {
        logger.info("Making flat sales dropdown list displaying check");
        topMenu.checkDropdownListIsDisplayed();
    }

    @And("Flat sales dropdown list contains title {string}")
    public void flatSalesDropdownListContainsTitle(String title) {
        logger.info("Assert than flat sales dropdown contains " + title);
        Assert.assertTrue(topMenu.getDropdownTitles().contains(title));
    }

    @And("Auto sales dropdown list contains title {string}")
    public void autoSalesDropdownListContainsTitle(String title) {
        logger.info("Assert than auto sales dropdown contains " + title);
        Assert.assertTrue(topMenu.getDropdownTitles().contains(title));
    }

    @When("^User selects product category (.+)$")
    public void userSelectsProductCategory(String productCategory) {
        logger.info("Searching for " + productCategory);
        searchField.performSearch(productCategory);
    }
}