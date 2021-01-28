package db;

import org.example.Account;
import org.example.Currency;
import org.example.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Reader {

    private static final String CONNECTION_STRING = "jdbc:sqlite:/home/vladimir/Desktop/test.db";

    public User readUserFromDB(long user_id) {
        User user = new User("", "", "");
        user.setUser_id(0);

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
                try (Statement statement = connection.createStatement()) {
                    String query =
                            "SELECT name, address " +
                                    "FROM users " +
                                    "WHERE user_id = " + user_id;
                    ResultSet resultSet = statement.executeQuery(query);
                    resultSet.next();
                    if (!resultSet.isClosed()) {
                        String name = resultSet.getString("name");
                        String firstName = name.split(" ")[0];
                        String lastName = name.split(" ")[1];
                        String address = resultSet.getString("address");
                        user = new User(firstName, lastName, address);
                        user.setUser_id(user_id);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (user.getUser_id() == 0) {
            return user;
        }

        user.setAccounts(readAccountsFromDB(user.getUser_id()));
        return user;
    }

    private List<Account> readAccountsFromDB(long user_id) {
        List<Account> accounts = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
                try (Statement statement = connection.createStatement()) {
                    String query =
                            "SELECT account_id, user_id, balance, currency " +
                            "FROM accounts " +
                            "WHERE user_id = " + user_id;
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        long account_id = resultSet.getLong("account_id");
                        long user_id_fromDB = resultSet.getLong("user_id");
                        long balance = resultSet.getLong("balance");
                        String currency = resultSet.getString("currency");
                        Account account = new Account(user_id_fromDB, balance, Currency.valueOf(currency));
                        account.setAccount_id(account_id);
                        accounts.add(account);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}