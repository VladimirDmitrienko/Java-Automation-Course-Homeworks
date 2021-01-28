package runners;

import io.cucumber.testng.CucumberOptions;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/resources/features/mainpage.feature",
        glue = "stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true)
public class MainPageRunner extends BaseRunner {

    public MainPageRunner() throws IOException {
    }
}
