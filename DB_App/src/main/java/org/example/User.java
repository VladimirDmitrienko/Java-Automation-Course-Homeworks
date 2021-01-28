package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long user_id;
    private final String firstName;
    private final String lastName;
    private final String address;

    private List<Account> accounts = new ArrayList<>();

    public User(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        user_id = Long.parseLong(RandomStringUtils.randomNumeric(10));
    }

    public Account getAccountByCurrency(Currency currency) {
        for (Account account : accounts) {
            if (account.getCurrency() == currency) {
                return account;
            }
        }
        Account account = new Account(0, 0, null);
        account.setAccount_id(0);
        return account;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}