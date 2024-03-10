package com.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.domain.transaction.BankTransaction;
import com.picpaysimplificado.dto.TransactionDto;
import com.picpaysimplificado.service.BankTransactionService;

@RestController()
@RequestMapping("/transaction")
public class BankTransactionController {

    @Autowired
    private BankTransactionService transactionService;

    @PostMapping
    public ResponseEntity<BankTransaction> create(@RequestBody TransactionDto transactionDto) throws Exception {
        BankTransaction newBankTransaction = this.transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(newBankTransaction, HttpStatus.OK);
    }
}
