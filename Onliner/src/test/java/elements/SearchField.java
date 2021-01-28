package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchField {
    private static final By SEARCH_INPUT = By.xpath("//input[@class='fast-search__input']");
    private static final By SEARCH_RESULTS_FRAME = By.xpath("//iframe[@class='modal-iframe']");
    private static final By SEARCH_RESULTS_LIST = By.xpath("//ul[@class='search__results']");
    private static final By SEARCH_RESULTS = By.xpath("//div[@class='result__wrapper']");

    public boolean performSearch(String query) {
        SelenideElement input = getInput();
        input.click();
        input.sendKeys(query);
        SelenideElement iframe = $(SEARCH_RESULTS_FRAME);
        iframe.shouldBe(Condition.visible);
        switchTo().frame(iframe);
        $(SEARCH_RESULTS_LIST).shouldBe(Condition.visible);
        List<SelenideElement> searchResults = $$(SEARCH_RESULTS);
        for (SelenideElement element : searchResults) {
            if (element.getText().contains(query)) {
                element.click();
                return true;
            }
        }
        return false;
    }

    private SelenideElement getInput() {
        return $(SEARCH_INPUT);
    }
}