package runners;

import com.codeborne.selenide.Configuration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import configuration.Configs;
import docker.Docker;

import java.io.IOException;

public class BaseRunner extends AbstractTestNGCucumberTests {
    private final boolean shouldUseSeleniumGrid = Configs.shouldUseSeleniumGrid();
    private Docker docker;

    public BaseRunner() throws IOException {
    }

    @BeforeSuite(alwaysRun = true)
    public void baseSetUp() throws IOException {
        Configuration.baseUrl = Configs.getUrl();
        Configuration.startMaximized = true;
        Configuration.pageLoadTimeout = 15000;
        Configuration.timeout = 20000;
        Configuration.browser = Configs.getBrowser();
        if (shouldUseSeleniumGrid) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            startSeleniumGrid();
        }
    }

    private void startSeleniumGrid() throws IOException {
        docker = new Docker();
        docker.startSeleniumGrid();
    }

    @AfterSuite(alwaysRun = true)
    private void stopSeleniumGrid() throws IOException, InterruptedException {
        if (shouldUseSeleniumGrid) {
            docker.stopSeleniumGrid();
        }
    }
}