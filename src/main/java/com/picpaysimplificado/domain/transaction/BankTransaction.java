package com.picpaysimplificado.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpaysimplificado.domain.user.AccountUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "bankTransaction")
@Table(name= "bankTransaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class BankTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private AccountUser senderAccountUser;
    
    @ManyToOne
    @JoinColumn(name="receiver_id")
    private AccountUser receiverAccountUser;
    
    private LocalDateTime createdAt;
}
