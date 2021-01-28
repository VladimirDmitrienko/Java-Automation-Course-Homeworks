package db;

import org.example.Account;
import org.example.Transaction;
import org.example.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Writer {

    private static final String CONNECTION_STRING = "jdbc:sqlite:/home/vladimir/Desktop/test.db";

    public void writeUserToDB(User user) {
        String id = String.valueOf(user.getUser_id());
        String name = user.getFirstName() + " " + user.getLastName();
        String address = user.getAddress();

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
                try (Statement statement = connection.createStatement()) {
                    String query = "INSERT INTO users " +
                            String.format("VALUES(%s, '%s', '%s');", id, name, address);
                    statement.executeUpdate(query);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccountInDB(Account account) {
        String account_id = String.valueOf(account.getAccount_id());
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {

                try (Statement statement = connection.createStatement()) {
                    String query =
                            "UPDATE accounts " +
                            "SET balance = " + Math.abs(account.getBalance()) +
                            " WHERE account_id = " + account_id;
                    statement.executeUpdate(query);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeAccountToDB(Account account) {
        String account_id = String.valueOf(account.getAccount_id());
        String user_id = String.valueOf(account.getUser_id());
        String currency = account.getCurrency().toString();
        String balance = String.valueOf(account.getBalance());

        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {

                try (Statement statement = connection.createStatement()) {
                    String query = "INSERT INTO accounts " +
                            String.format("VALUES(%s, %s, %s, '%s');", account_id, user_id, balance, currency);
                    statement.executeUpdate(query);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeTransactionToDB(Transaction transaction) {
        String transaction_id = String.valueOf(transaction.getTransaction_id());
        String account_id = String.valueOf(transaction.getAccount_id());
        String amount = String.valueOf(transaction.getAmount()).replaceAll(",", ".");

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
                try (Statement statement = connection.createStatement()) {
                    String query = "INSERT INTO transactions " +
                            String.format("VALUES(%s, %s, %s);", transaction_id, account_id, amount);
                    statement.executeUpdate(query);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}