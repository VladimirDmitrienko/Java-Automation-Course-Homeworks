package operation;

import org.example.Account;
import org.example.Currency;
import org.example.User;
import utility.ConsoleHelper;

public class AccountOperation extends Operation {

    static final String CURRENCY_MENU =
            "Введите номер валюты:\n\t" +
                    "1. USD\n\t" +
                    "2. EURO\n\t" +
                    "3. RUBLE";
    private static final String SUCCESS_MESSAGE = "Счет успешно создан.";

    @Override
    public void execute() {
        Account account = createAccount();
        if (account.getAccount_id() != 0) {
            db_writer.writeAccountToDB(account);
            showSuccessfulOperationMessage();
        }
    }

    private Account createAccount() {
        long user_id = readUserIdFromConsole();
        User user = db_reader.readUserFromDB(user_id);
        showCurrencyMenu();
        Currency currency = readCurrencyFromConsole();
        if (user.getAccountByCurrency(currency).getAccount_id() != 0) {
            System.out.println("Счет в такой валюте уже зарегистрирован.");
            return getNullableAccount();
        }
        return new Account(user_id, 0, currency);
    }

    long readUserIdFromConsole() {
        System.out.println("Введите ID пользователя.");
        long user_id;
        while (true) {
            String id = ConsoleHelper.readLine();
            if (id.isEmpty() || id.length() > 10) {
                System.out.println("Введите валидный ID пользователя.");
                continue;
            }
            user_id = Long.parseLong(id);
            User user = db_reader.readUserFromDB(user_id);
            if (user.getUser_id() == 0) {
                System.out.println("Пользователь с таким ID не существует.");
                continue;
            }
            break;
        }
        return user_id;
    }

    private void showCurrencyMenu() {
        System.out.println(CURRENCY_MENU);
    }

    Currency readCurrencyFromConsole() {
        String currencyNumber;
        while (true) {
            currencyNumber = ConsoleHelper.readLine();
            switch (currencyNumber) {
                case "1" :
                    return Currency.USD;
                case "2" :
                    return Currency.EURO;
                case "3" :
                    return Currency.RUBLE;
                default:
                    System.out.println("Введите номер одной из валют.");
            }
        }
    }

    private Account getNullableAccount() {
        Account account = new Account(0, 0, null);
        account.setAccount_id(0);
        return account;
    }

    @Override
    void showSuccessfulOperationMessage() {
        System.out.println(SUCCESS_MESSAGE);
    }
}