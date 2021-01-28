package docker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ComposeFile {
    private final Path composeFile;

    public ComposeFile(Path composeFile) {
        this.composeFile = composeFile;
    }

    private static final String SERVICES =
            "services:\n" +
            "    selenium-hub:\n" +
            "        image: selenium/hub:latest\n" +
            "        container_name: selenium-hub\n" +
            "        ports:\n" +
            "        - \"4444:4444\"\n    ";
    private static final String CHROME_NODE =
            "chrome:\n" +
                    "        image: selenium/node-chrome:latest\n" +
                    "        volumes:\n" +
                    "        - /dev/shm:/dev/shm\n" +
                    "        depends_on:\n" +
                    "        - selenium-hub\n" +
                    "        environment:\n" +
                    "        - HUB_HOST=selenium-hub\n" +
                    "        - HUB_PORT=4444";
    private static final String FIREFOX_NODE =
            "firefox: \n" +
                    "        image: selenium/node-firefox:latest\n" +
                    "        volumes: \n" +
                    "        - /dev/shm:/dev/shm\n" +
                    "        depends_on:\n" +
                    "        - selenium-hub\n" +
                    "        environment:\n" +
                    "        - HUB_HOST=selenium-hub\n" +
                    "        - HUB_PORT=4444";

    public void createChromeComposeFile() throws IOException {
        Files.createFile(composeFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(composeFile.toFile()))) {
            writer.write(SERVICES);
            writer.write(CHROME_NODE);
        }
    }

    public void createFirefoxComposeFile() throws IOException {
        Files.createFile(composeFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(composeFile.toFile()))) {
            writer.write(SERVICES);
            writer.write(FIREFOX_NODE);
        }
    }

    public void deleteComposeFile() throws IOException {
        Files.delete(composeFile);
    }

    public Path getComposeFile() {
        return composeFile;
    }
}