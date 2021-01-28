package docker;

import configuration.Configs;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Docker {
    private final ComposeFile dockerComposeFile = new ComposeFile(Paths.get(DOCKER_FILES_FOLDER + "/docker-compose.yml"));
    private final Path dockerStartLogsFile = Paths.get(DOCKER_FILES_FOLDER + "/docker-start-logs.txt");
    private final Path dockerScaleUpLogsFile = Paths.get(DOCKER_FILES_FOLDER + "/docker-scale-up-logs.txt");
    private final Path dockerBatFile = Paths.get(DOCKER_FILES_FOLDER + "/docker.bat");
    private String browser;
    private int nodesCount;
    static final Path DOCKER_FILES_FOLDER = Paths.get("src/test/resources/dockerfiles");
    private static final String DOCKER_UP_PATTERN = "docker-compose -f %s up";
    private static final String DOCKER_DOWN_PATTERN = "docker-compose -f %s down";
    private static final String DOCKER_SCALE_UP_PATTERN = "docker-compose up --scale %s=%s";
    private static final long TIME_TO_START_HUB_WITH_ONE_NODE = 20_000L;
    private static final long TIME_TO_SCALE_UP_NODES = 20_000L;
    private static final String CMD_COMMAND = "cmd /c start ";
    private static final String EXPECTED_LOG_MESSAGE = "The node is registered to the hub and ready to use";
    private static final String HUB_EXCEPTION_MESSAGE = String.format("Selenium Grid hub is not started within %s seconds.", TIME_TO_START_HUB_WITH_ONE_NODE / 1000);
    private static final String NODES_EXCEPTION_MESSAGE = String.format("Nodes are not scaled up within %s seconds.", TIME_TO_SCALE_UP_NODES / 1000);

    public void startSeleniumGrid() throws IOException {
        initializeDockerObject();
        createComposeFile();
        startSeleniumHubWithOneNode();
        scaleUpNodes();
    }

    private void initializeDockerObject() throws IOException {
        initBrowser();
        initNodesCount();
        createStartLogsFile();
        createDockerBatFile();
    }

    private void startSeleniumHubWithOneNode() throws IOException {
        rewriteDockerBatFile(getDockerUpCommand());
        Runtime.getRuntime().exec(CMD_COMMAND + dockerBatFile.getFileName(), null, DOCKER_FILES_FOLDER.toFile());
        long startTime = System.currentTimeMillis();
        long endTime = startTime + TIME_TO_START_HUB_WITH_ONE_NODE;
        try (BufferedReader reader = new BufferedReader(new FileReader(dockerStartLogsFile.toFile()))) {
            while (true) {
                String line = reader.readLine();
                if (line != null && line.contains(EXPECTED_LOG_MESSAGE)) {
                    break;
                }
                if (System.currentTimeMillis() > endTime) {
                    throw new RuntimeException(HUB_EXCEPTION_MESSAGE);
                }
            }
        }
    }

    private void scaleUpNodes() throws IOException {
        initNodesCount();
        if (nodesCount > 1) {
            createScaleUpLogsFile();
            rewriteDockerBatFile(getScaleUpCommand());
            Runtime.getRuntime().exec(CMD_COMMAND + dockerBatFile.getFileName(), null, DOCKER_FILES_FOLDER.toFile());
            long startTime = System.currentTimeMillis();
            long endTime = startTime + TIME_TO_SCALE_UP_NODES;
            try (BufferedReader reader = new BufferedReader(new FileReader(dockerStartLogsFile.toFile()))) {
                while (nodesCount != 1) {
                    String line = reader.readLine();
                    if (line != null && line.contains(EXPECTED_LOG_MESSAGE)) {
                        nodesCount--;
                    }
                    if (System.currentTimeMillis() > endTime) {
                        throw new RuntimeException(NODES_EXCEPTION_MESSAGE);
                    }
                }
            }
        }
    }

    public void stopSeleniumGrid() throws IOException, InterruptedException {
        rewriteDockerBatFile(getDockerDownCommand());
        Runtime.getRuntime().exec(CMD_COMMAND + dockerBatFile.getFileName(), null, DOCKER_FILES_FOLDER.toFile());
        Thread.sleep(10000);
        Files.delete(dockerStartLogsFile);
        if (Files.exists(dockerScaleUpLogsFile)) {
            Files.delete(dockerScaleUpLogsFile);
        }
        Files.delete(dockerBatFile);
        deleteComposeFile();
    }

    private String getDockerUpCommand() {
        return String.format(DOCKER_UP_PATTERN, dockerComposeFile.getComposeFile().getFileName());
    }

    private String getScaleUpCommand() {
        return String.format(DOCKER_SCALE_UP_PATTERN, browser, nodesCount);
    }

    private String getDockerDownCommand() {
        return String.format(DOCKER_DOWN_PATTERN, dockerComposeFile.getComposeFile().getFileName());
    }

    private void initBrowser() throws IOException {
        browser = Configs.getBrowser();
        if (!browser.equals("chrome") && !browser.equals("firefox")) {
            throw new IllegalArgumentException(String.format("There is no docker image for [ %s ]", browser));
        }
    }

    private void initNodesCount() throws IOException {
        nodesCount = Configs.getNodesCount();
        if (nodesCount < 1 || nodesCount > 10) {
            throw new IllegalArgumentException(String.format("Invalid nodes count: [ %s ]", nodesCount));
        }
    }

    private void createStartLogsFile() throws IOException {
        Files.createFile(dockerStartLogsFile);
    }

    private void createScaleUpLogsFile() throws IOException {
        Files.createFile(dockerScaleUpLogsFile);
    }

    private void rewriteDockerBatFile(String command) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dockerBatFile.toFile()))) {
            if (command.equals(getScaleUpCommand())) {
                writer.write(command + " >> " + dockerScaleUpLogsFile.getFileName());
            }
            else if (command.equals(getDockerDownCommand())) {
                writer.write(command);
            }
            else {
                writer.write(command + " >> " + dockerStartLogsFile.getFileName());
            }
        }
    }

    private void createDockerBatFile() throws IOException {
        Files.createFile(dockerBatFile);
    }

    private void createComposeFile() throws IOException {
        if (browser.equals("chrome")) {
            dockerComposeFile.createChromeComposeFile();
        }
        else if (browser.equals("firefox")) {
            dockerComposeFile.createFirefoxComposeFile();
        }
    }

    private void deleteComposeFile() throws IOException {
        dockerComposeFile.deleteComposeFile();
    }
}