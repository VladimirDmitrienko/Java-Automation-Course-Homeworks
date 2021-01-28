package runners;


import io.cucumber.testng.CucumberOptions;

import java.io.IOException;


@CucumberOptions(
        features = "src/test/resources/features/catalog.feature",
        glue = "stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true)
public class CatalogRunner extends BaseRunner {

    public CatalogRunner() throws IOException {
    }
}