package com.example.bankAccountService.bankAccountService.Service;

import com.example.bankAccountService.bankAccountService.Entity.PendingTransaction;
import com.example.bankAccountService.bankAccountService.Entity.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {

    private Map<String, Double> accountBalances = new ConcurrentHashMap<>();
    private Map<String, PendingTransaction> pendingTransactions = new ConcurrentHashMap<>();

    public AccountService() {
        accountBalances.put("user1", 1000.0); // Initial balance
    }

    public boolean prepare(TransactionRequest request) {
        String userId = request.getUserId();
        double amount = request.getAmount();
        double balance = accountBalances.getOrDefault(userId, 0.0);

        if (balance >= amount) {
            pendingTransactions.put(request.getTransactionId(), new PendingTransaction(userId, amount));
            return true;
        } else {
            return false;
        }
    }

    public void commit(String transactionId) {
        PendingTransaction tx = pendingTransactions.remove(transactionId);
        if (tx != null) {
            accountBalances.computeIfPresent(tx.getUserId(), (k, v) -> v - tx.getAmount());
        }
    }

    public void rollback(String transactionId) {
        pendingTransactions.remove(transactionId); // Simply discard
    }

    public double getBalance(String userId) {
        return accountBalances.getOrDefault(userId, 0.0);
    }
}

