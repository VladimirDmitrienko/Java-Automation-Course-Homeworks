package utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleHelper {

    public static String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
