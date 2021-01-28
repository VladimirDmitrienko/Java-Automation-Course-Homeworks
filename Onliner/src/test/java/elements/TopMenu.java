package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import pageobjects.CatalogPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TopMenu {
    private static final String BUTTON_PATTERN = "//nav//span[contains(text(), '%s')]";
    private static final By CATALOG_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Каталог"));
    private static final By NEWS_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Новости"));
    private static final By AUTO_SALES_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Автобарахолка"));
    private static final By FLAT_SALES_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Дома и квартиры"));
    private static final By SERVICES_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Услуги"));
    private static final By SALES_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Барахолка"));
    private static final By FORUM_BUTTON = By.xpath(String.format(BUTTON_PATTERN, "Форум"));
    private static final By DROPDOWN_LIST = By.xpath("//div[@class='b-main-navigation__dropdown b-main-navigation__dropdown_visible']");
    private static final By DROPDOWN_LIST_TITLES = By.xpath("//div[@class='b-main-navigation__dropdown-title']");

    public CatalogPage openCatalog() {
        $(CATALOG_BUTTON).click();
        return page(CatalogPage.class);
    }

    public void openNews() {
        $(NEWS_BUTTON).click();
    }

    public void openAutoSales() {
        $(AUTO_SALES_BUTTON).click();
    }

    public void openFlatSales() {
        $(FLAT_SALES_BUTTON).click();
    }

    public void openServices() {
        $(SERVICES_BUTTON).click();
    }

    public void openSales() {
        $(SALES_BUTTON).click();
    }

    public void openForum() {
        $(FORUM_BUTTON).click();
    }

    public void openNewsDropdownList() {
        $(NEWS_BUTTON).hover();
    }

    public void openAutoSalesDropdownList() {
        $(AUTO_SALES_BUTTON).hover();
    }

    public void openFlatSalesDropdownList() {
        $(FLAT_SALES_BUTTON).hover();
    }

    public void checkDropdownListIsDisplayed() {
        $(DROPDOWN_LIST).shouldBe(Condition.visible);
    }

    public List<String> getDropdownTitles() {
        return $$(DROPDOWN_LIST_TITLES).filter(Condition.visible).texts();
    }
}