package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
    private static final String OPTION_PATTERN = "//div[@data-id='%d']//div[@class='catalog-navigation-list__aside-title']";
    private static final String CATEGORY_BUTTON_PATTERN = "//span[contains(text(),'%s')]";
    private static final By ELECTRONICS_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Электроника"));
    private static final By COMPUTERS_AND_NETWORKS_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Компьютеры и сети"));
    private static final By HOUSEHOLD_AND_APPLIANCES_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Бытовая техника"));
    private static final By CONSTRUCTION_AND_REPAIR_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Стройка и ремонт"));
    private static final By HOUSE_AND_GARDEN_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Дом и сад"));
    private static final By AUTO_AND_MOTO_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Авто и мото"));
    private static final By BEAUTY_AND_SPORT_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Красота и спорт"));
    private static final By FOR_CHILDREN_AND_MOTHERS_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Детям и мамам"));
    private static final By WORK_AND_OFFICE_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Работа и офис"));
    private static final By FOOD_BUTTON = By.xpath(String.format(CATEGORY_BUTTON_PATTERN, "Еда"));
    private static final By ALL_CATEGORIES = By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper']");
    private static final By ELECTRONICS_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 1));
    private static final By COMPUTERS_AND_NETWORKS_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 2));
    private static final By HOUSEHOLD_AND_APPLIANCES_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 3));
    private static final By CONSTRUCTION_AND_REPAIR_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 4));
    private static final By HOUSE_AND_GARDEN_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 5));
    private static final By AUTO_AND_MOTO_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 6));
    private static final By BEAUTY_AND_SPORT_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 7));
    private static final By FOR_CHILDREN_AND_MOTHERS_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 8));
    private static final By WORK_AND_OFFICE_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 9));
    private static final By FOOD_OPTIONS = By.xpath(String.format(OPTION_PATTERN, 16));
    private static final By OPTION_OFFERS = By.xpath("//span[@class='catalog-navigation-list__dropdown-data']");
    private static final By OPTION_OFFER_TITLE = By.xpath("span[@class='catalog-navigation-list__dropdown-title']");
    private static final By OPTION_OFFER_DESCRIPTION = By.xpath("span[@class='catalog-navigation-list__dropdown-description']");
    private static final By ELEMENT_FOR_SCROLL_CHECKBOXES_INTO_VIEW = By.xpath("//span[contains(text(),'Минимальная цена в предложениях магазинов')]");
    private static final By MANUFACTURERS_CHECKBOXES = By.xpath("//div[@class='schema-filter__fieldset'][1]//ul//input");
    private static final By PRODUCT_TITLES = By.xpath("//div[@class='schema-product__title']/a/span");

    public void openElectronicsCategory() {
        $(ELECTRONICS_BUTTON).click();
    }

    public void openComputersAndNetworksCategory() {
        $(COMPUTERS_AND_NETWORKS_BUTTON).click();
    }

    public void openHouseholdAppliancesCategory() {
        $(HOUSEHOLD_AND_APPLIANCES_BUTTON).click();
    }

    public void openConstructionAndRepairCategory() {
        $(CONSTRUCTION_AND_REPAIR_BUTTON).click();
    }

    public void openHouseAndGardenCategory() {
        $(HOUSE_AND_GARDEN_BUTTON).click();
    }

    public void openAutoAndMotoCategory() {
        $(AUTO_AND_MOTO_BUTTON).click();
    }

    public void openBeautyAndSportCategory() {
        $(BEAUTY_AND_SPORT_BUTTON).click();
    }

    public void openForChildrenAndMothersCategory() {
        $(FOR_CHILDREN_AND_MOTHERS_BUTTON).click();
    }

    public void openWorkAndOfficeCategory() {
        $(WORK_AND_OFFICE_BUTTON).click();
    }

    public void openFoodCategory() {
        $(FOOD_BUTTON).click();
    }

    public List<SelenideElement> getAllCatalogCategoriesButtons() {
        return $$(ALL_CATEGORIES);
    }

    public List<SelenideElement> getAllElectronicsOptions() {
        return $$(ELECTRONICS_OPTIONS);
    }

    public List<SelenideElement> getAllComputersAndNetworksOptions() {
        return $$(COMPUTERS_AND_NETWORKS_OPTIONS);
    }

    public List<SelenideElement> getAllHouseholdAppliancesOptions() {
        return $$(HOUSEHOLD_AND_APPLIANCES_OPTIONS);
    }

    public List<SelenideElement> getAllConstructionAndRepairOptions() {
        return $$(CONSTRUCTION_AND_REPAIR_OPTIONS);
    }

    public List<SelenideElement> getAllHouseAndGardenOptions() {
        return $$(HOUSE_AND_GARDEN_OPTIONS);
    }

    public List<SelenideElement> getAllAutoAndMotoOptions() {
        return $$(AUTO_AND_MOTO_OPTIONS);
    }

    public List<SelenideElement> getAllBeautyAndSportOptions() {
        return $$(BEAUTY_AND_SPORT_OPTIONS);
    }

    public List<SelenideElement> getAllForChildrenAndMothersOptions() {
        return $$(FOR_CHILDREN_AND_MOTHERS_OPTIONS);
    }

    public List<SelenideElement> getAllWorkAndOfficeOptions() {
        return $$(WORK_AND_OFFICE_OPTIONS);
    }

    public List<SelenideElement> getAllFoodOptions() {
        return $$(FOOD_OPTIONS);
    }

    public List<SelenideElement> getDisplayedOptionOffers() {
        return $$(OPTION_OFFERS).filter(Condition.visible);
    }

    public String getOptionOfferTitle(SelenideElement optionOffer) {
        return optionOffer.find(OPTION_OFFER_TITLE).getText();
    }

    public String getOptionOfferDescription(SelenideElement optionOffer) {
        return optionOffer.find(OPTION_OFFER_DESCRIPTION).getText();
    }

    public void selectOptionByTitle(String optionTitle) {
        getAllComputersAndNetworksOptions().stream()
                .filter(e -> e.getText().equals(optionTitle))
                .findFirst().get().click();
    }

    public void selectManufacturerByName(String manufacturerName) {
        scrollManufacturersCheckboxesIntoView();
        ElementsCollection checkboxes = $$(MANUFACTURERS_CHECKBOXES);
        for (SelenideElement checkbox : checkboxes) {
            if (checkbox.isSelected() && !Objects.equals(checkbox.getAttribute("value"),
                    manufacturerName.toLowerCase())) {
                checkbox.find(By.xpath("parent::span")).click();
                $(By.id("schema-products")).shouldHave(Condition.attribute("class",
                        "schema-products"));
            }
            else if (Objects.equals(checkbox.getAttribute("value"),
                    manufacturerName.toLowerCase()) && !checkbox.isSelected()) {
                checkbox.find(By.xpath("parent::span")).click();
                $(By.id("schema-products")).shouldHave(Condition.attribute("class",
                        "schema-products"));
            }
        }
    }

    public List<String> getAllProductTitles() {
        return $$(PRODUCT_TITLES).texts();
    }

    public void scrollManufacturersCheckboxesIntoView() {
        $(ELEMENT_FOR_SCROLL_CHECKBOXES_INTO_VIEW).scrollIntoView(false);
    }
}