package operation;

import org.example.Account;
import org.example.Currency;
import org.example.Transaction;
import org.example.User;
import utility.ConsoleHelper;

public class MoneyOperation extends Operation {

    static final String SUCCESS_MESSAGE = "Операция прошла успешно";
    static final String INVITE_MESSAGE = "Введите сумму... макс. (100.000.000).";
    static final String FAILED_EXPENSE_OPERATION_MESSAGE = "Недостаточно средств.";
    static final String FAILED_RECEIPT_OPERATION_MESSAGE = "Превышен баланс счета.";

    private final boolean isExpense;

    public MoneyOperation(boolean isExpense) {
        this.isExpense = isExpense;
    }

    private Transaction createTransaction() {
        User user = getUserForTransaction();
        Currency currency;
        try {
            currency = getCurrencyForTransaction(user);
        }
        catch (RuntimeException e) {
            return getNullableTransaction();
        }
        printTransactionInviteMessage();
        double amount = readAmountFromConsole();
        Account account = user.getAccountByCurrency(currency);
        Transaction transaction = new Transaction(amount, isExpense);
        transaction.setAccount_id(account.getAccount_id());
        if (account.canDoOperation(transaction)) {
            account.doOperation(transaction);
            db_writer.updateAccountInDB(account);
        }
        else {
            printTransactionFailedMessage(transaction);
            return getNullableTransaction();
        }
        return transaction;
    }

    private User getUserForTransaction() {
        long user_id = new AccountOperation().readUserIdFromConsole();
        return db_reader.readUserFromDB(user_id);
    }

    private Currency getCurrencyForTransaction(User user) {
        showCurrencyMenu();
        Currency currency = new AccountOperation().readCurrencyFromConsole();
        if (user.getAccountByCurrency(currency).getAccount_id() == 0) {
            System.out.println("У данного пользователя нет счета в выбранной валюте.");
            throw new RuntimeException();
        }
        return currency;
    }

    void showCurrencyMenu() {
        System.out.println(AccountOperation.CURRENCY_MENU);
    }

    boolean validateAmount(String amount) {
        return amount.matches("[\\d]{1,10}(\\.[\\d]{1,3})?") && !amount.equals("0");
    }

    long readAmountFromConsole() {
        while (true) {
            String amount = ConsoleHelper.readLine();
            if (!validateAmount(amount)) {
                System.out.println("Введите валидное значение.");
                continue;
            }
            double dAmount = Double.parseDouble(amount);
            if (dAmount > 100_000_000) {
                System.out.println("Размер транзакции не может превышать 100.000.000");
                continue;
            }
            return (long) (dAmount * 1000);
        }
    }

    private Transaction getNullableTransaction() {
        return new Transaction();
    }

    private void printTransactionInviteMessage() {
        System.out.println(INVITE_MESSAGE);
    }

    private void printTransactionFailedMessage(Transaction transaction) {
        System.out.println(transaction.getAmount() < 0 ? FAILED_EXPENSE_OPERATION_MESSAGE : FAILED_RECEIPT_OPERATION_MESSAGE);
    }

    @Override
    public void execute() {
        Transaction transaction = createTransaction();
        if (transaction.getTransaction_id() != 0) {
            db_writer.writeTransactionToDB(transaction);
            showSuccessfulOperationMessage();
        }
    }

    @Override
    void showSuccessfulOperationMessage() {
        System.out.println(SUCCESS_MESSAGE);
    }
}