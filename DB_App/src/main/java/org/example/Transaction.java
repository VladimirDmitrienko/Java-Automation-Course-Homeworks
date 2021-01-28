package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Transaction {
    private long transaction_id;
    private long account_id;
    private double amount;

    public Transaction() {}

    public Transaction(double amount, boolean isExpense) {
        transaction_id = Long.parseLong(RandomStringUtils.randomNumeric(10));
        this.amount = isExpense ? -amount : amount;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public double getAmount() {
        return amount;
    }
}