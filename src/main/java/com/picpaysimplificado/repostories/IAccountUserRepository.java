package com.picpaysimplificado.repostories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.domain.user.AccountUser;

public interface IAccountUserRepository extends JpaRepository<AccountUser, Long>{
    
    Optional<AccountUser> findAccountUserByDocument(String document);

    Optional<AccountUser> findAccountUserById(Long id);
}
