package stepdefinitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import elements.TopMenu;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageobjects.CatalogPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;

public class CatalogStepDefinitions {
    private static final Logger logger = Logger.getLogger(CatalogStepDefinitions.class);
    private final TopMenu topMenu = new TopMenu();
    private final CatalogPage catalogPage = new CatalogPage();

    @Given("Catalog page is opened")
    public void catalogPageIsOpened() {
        open(Configuration.baseUrl);
        topMenu.openCatalog();
        logger.info("Catalog page is opened");
    }

    @When("Computers and networks category is selected")
    public void computersAndNetworksCategoryIsSelected() {
        catalogPage.openComputersAndNetworksCategory();
        logger.info("Computers and networks category is selected");
    }

    @Then("Catalog categories are displayed:")
    public void catalogCategoriesAreDisplayed(DataTable categories) {
        logger.info("Making catalog categories displaying check");
        List<String> expectedCatalogCategories = categories.asList();
        List<String> actualCatalogCategories = catalogPage.getAllCatalogCategoriesButtons()
                .stream().map(SelenideElement::getText)
                .collect(Collectors.toList());
        Assert.assertEquals(actualCatalogCategories.size(), expectedCatalogCategories.size());
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < expectedCatalogCategories.size(); i++) {
            softAssert.assertEquals(expectedCatalogCategories.get(i), actualCatalogCategories.get(i));
        }
        softAssert.assertAll();
    }

    @Then("Computers and networks options are displayed")
    public void computersAndNetworksOptionsAreDisplayed(DataTable options) {
        logger.info("Making computers and networks options displaying check");
        List<String> expectedOptions = options.asList();
        List<String> actualOptions = catalogPage
                .getAllComputersAndNetworksOptions().stream()
                .map(SelenideElement::getText).collect(Collectors.toList());
        SoftAssert softAssert = new SoftAssert();
        expectedOptions.forEach(e ->
                softAssert.assertTrue(actualOptions.contains(e), e));
        softAssert.assertAll();
    }

    @And("Accessories option is selected")
    public void accessoriesOptionIsSelected() {
        catalogPage.selectOptionByTitle("Комплектующие");
        logger.info("Accessories option is selected");
    }

    @Then("Accessories offers have price and description")
    public void accessoriesOffersHavePriceAndDescription() {
        logger.info("Making accessories offers price and description displaying check");
        SoftAssert softAssert = new SoftAssert();
        List<SelenideElement> optionOffers = catalogPage.getDisplayedOptionOffers();
        String regEx = "\\d+(,\\d+)?\\sтовар[а|ов]*\\sот\\s\\d+(,\\d+)?\\sр\\.";
        for (SelenideElement optionOffer : optionOffers) {
            String optionTitle = catalogPage.getOptionOfferTitle(optionOffer);
            String optionDescription = catalogPage.getOptionOfferDescription(optionOffer);
            softAssert.assertTrue(!optionTitle.isEmpty() && optionDescription.matches(regEx),
                    optionTitle + " " + optionDescription);
        }
        softAssert.assertAll();
    }

    @And("^User selects one manufacturer (.*)$")
    public void userSelectsOneManufacturer(String manufacturer) {
        catalogPage.selectManufacturerByName(manufacturer);
        logger.info(manufacturer + " is selected");
    }

    @Then("^Only products from selected manufacturer (.*) are displayed$")
    public void onlyProductsFromSelectedManufacturerAreDisplayed(String manufacturer) {
        logger.info("Making products from only one manufacturer displaying check");
        SoftAssert softAssert = new SoftAssert();
        catalogPage.getAllProductTitles().forEach(t -> softAssert.assertTrue(t.contains(manufacturer), t + " " + manufacturer));
        softAssert.assertAll();
    }
}