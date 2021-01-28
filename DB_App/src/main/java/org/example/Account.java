package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Account {
    private long account_id;
    private final long user_id;
    private long balance;
    private final Currency currency;

    public Account(long user_id, long balance, Currency currency) {
        account_id = Long.parseLong(RandomStringUtils.randomNumeric(10));
        this.user_id = user_id;
        this.balance = balance;
        this.currency = currency;
    }

    public boolean canDoOperation(Transaction transaction) {
        return balance + transaction.getAmount() <= 2000000000000.0 && balance + transaction.getAmount() >= 0;
    }

    public void doOperation(Transaction transaction) {
        balance += transaction.getAmount();
    }

    public long getAccount_id() {
        return account_id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public long getBalance() {
        return balance;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
}