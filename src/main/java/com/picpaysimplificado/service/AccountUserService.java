package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.user.AccountUser;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dto.AccountUserDto;
import com.picpaysimplificado.repostories.IAccountUserRepository;

@Service
public class AccountUserService {

    @Autowired
    private IAccountUserRepository accountUserRepository;

    public void validateTransaction(AccountUser sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT || sender.getUserType() == null) {
            throw new Exception("Logist type user is not authorized to carry out transactions.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("User does not have a balance.");
        }
    }

    public AccountUser findAccountUserById(Long id) throws Exception {
        return this.accountUserRepository.findAccountUserById(id).orElseThrow(() -> new Exception("User not found."));
    }

    public void saveAccontUser(AccountUser accountUser) {
        this.accountUserRepository.save(accountUser);
    }

    public AccountUser createUser(AccountUserDto accountUserDto) {
        AccountUser newAccountUser = new AccountUser(accountUserDto);
        return this.accountUserRepository.save(newAccountUser);
    }

    public List<AccountUser> findAllAccontUsers() {
        return this.accountUserRepository.findAll();
    }
}
