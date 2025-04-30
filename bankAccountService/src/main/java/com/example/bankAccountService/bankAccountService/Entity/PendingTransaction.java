package com.example.bankAccountService.bankAccountService.Entity;

public class PendingTransaction {
    private String userId;
    private double amount;

    public PendingTransaction(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}

