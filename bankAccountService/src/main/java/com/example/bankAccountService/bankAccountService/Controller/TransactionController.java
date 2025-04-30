package com.example.bankAccountService.bankAccountService.Controller;

import com.example.bankAccountService.bankAccountService.Entity.TransactionRequest;
import com.example.bankAccountService.bankAccountService.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/prepare")
    public ResponseEntity<String> prepare(@RequestBody TransactionRequest request) {
        boolean ready = accountService.prepare(request);
        return ready ? ResponseEntity.ok("READY") : ResponseEntity.badRequest().body("ABORT");
    }

    @PostMapping("/commit")
    public ResponseEntity<String> commit(@RequestBody TransactionRequest request) {
        accountService.commit(request.getTransactionId());
        return ResponseEntity.ok("COMMITTED");
    }

    @PostMapping("/rollback")
    public ResponseEntity<String> rollback(@RequestBody TransactionRequest request) {
        accountService.rollback(request.getTransactionId());
        return ResponseEntity.ok("ROLLED BACK");
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.getBalance(userId));
    }
}

