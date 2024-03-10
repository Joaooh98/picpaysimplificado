package com.picpaysimplificado.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.transaction.BankTransaction;
import com.picpaysimplificado.dto.TransactionDto;
import com.picpaysimplificado.repostories.IBankTransactionRepository;

@Service
public class BankTransactionService {

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private IBankTransactionRepository bankTransactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public BankTransaction createTransaction(TransactionDto transaction) throws Exception {

        var sender = this.accountUserService.findAccountUserById(transaction.senderId());
        var receiver = this.accountUserService.findAccountUserById(transaction.receiverId());

        accountUserService.validateTransaction(sender, transaction.value());

        boolean isAuthorizeTransaction = this.authorizeTransaction();
        if (!isAuthorizeTransaction) {
            throw new Exception("Unauthorized transaction");
        }

        var newTrasaction = new BankTransaction();

        newTrasaction.setAmount(transaction.value());
        newTrasaction.setSenderAccountUser(sender);
        newTrasaction.setReceiverAccountUser(receiver);
        newTrasaction.setCreatedAt(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.bankTransactionRepository.save(newTrasaction);
        this.accountUserService.saveAccontUser(sender);
        this.accountUserService.saveAccontUser(receiver);

        this.notificationService.sendNotification(sender, "Transaction completed successfully!");
        this.notificationService.sendNotification(receiver, "Transaction completed successfully!");

        return newTrasaction;
    }

    public boolean authorizeTransaction() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> authorizationResponse = restTemplate
                .getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".endsWith(message);
        }
        return false;
    }

}
