package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configs {

    public static String getUrl() throws IOException {
        return getProperties().getProperty("url");
    }

    public static int getNodesCount() throws IOException {
        return Integer.parseInt(getProperties().getProperty("nodesCount"));
    }

    public static String getBrowser() throws IOException {
        return getProperties().getProperty("browser");
    }

    public static boolean shouldUseSeleniumGrid() throws IOException {
        return Boolean.parseBoolean(getProperties().getProperty("useSeleniumGrid"));
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/configs.properties")) {
            properties.load(fis);
        }
        return properties;
    }
}