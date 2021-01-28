package application;

import operation.*;
import utility.ConsoleHelper;

public class App {

    private static final String MAIN_MENU =
            "Введите номер операции:\n\t" +
            "1. Создать нового пользователя.\n\t" +
            "2. Добавить счет существующему пользователю.\n\t" +
            "3. Пополнить счет.\n\t" +
            "4. Снять деньги со счета.\n\t" +
            "5. Завершить работу";

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        showMainMenu();
        String operationNumber = readOperationNumberFromConsole();
        if (!operationNumber.equals("5")) {
            Executable executable = defineOperation(operationNumber);
            executable.execute();
        }
    }

    private void showMainMenu() {
        System.out.println(MAIN_MENU);
    }

    private Executable defineOperation(String operationNumber) {
        switch (operationNumber) {
            case "1" :
                return new UserOperation();
            case "2" :
                return new AccountOperation();
            case "3" :
                return new MoneyOperation(false);
            case "4" :
                return new MoneyOperation(true);
            default:
                throw new RuntimeException();
        }
    }

    private String readOperationNumberFromConsole() {
        String operationNumber;
        while (true) {
            operationNumber = ConsoleHelper.readLine().trim();
            if (operationNumber.equals("1") || operationNumber.equals("2") ||
                    operationNumber.equals("3") || operationNumber.equals("4") ||
                    operationNumber.equals("5")) {
                return operationNumber;
            }
            System.out.println("Введите значение от 1 до 5.");
        }
    }
}