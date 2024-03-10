package com.picpaysimplificado.repostories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.domain.transaction.BankTransaction;

public interface IBankTransactionRepository extends JpaRepository<BankTransaction, Long>{

}
