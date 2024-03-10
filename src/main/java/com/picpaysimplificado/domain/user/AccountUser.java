package com.picpaysimplificado.domain.user;

import java.math.BigDecimal;

import com.picpaysimplificado.dto.AccountUserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="AccountUser")
@Table(name="AccountUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class AccountUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;
    
    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public AccountUser(AccountUserDto accountUserDto) {
        this.firstName = accountUserDto.firstName();
        this.lastName = accountUserDto.lastName();
        this.document = accountUserDto.document();
        this.email = accountUserDto.email();
        this.password = accountUserDto.password();
        this.balance = accountUserDto.balance();
        this.userType = accountUserDto.userType();
    }
    
}

