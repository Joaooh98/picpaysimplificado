package com.picpaysimplificado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.domain.user.AccountUser;
import com.picpaysimplificado.dto.AccountUserDto;
import com.picpaysimplificado.service.AccountUserService;

@RestController()
@RequestMapping("/user")
public class AccontUsesController {

    @Autowired
    private AccountUserService accountUserService;
    
    @PostMapping
    public ResponseEntity<AccountUser> create(@RequestBody AccountUserDto accountUser){
        var user = accountUserService.createUser(accountUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountUser>> findAll(){
        List<AccountUser> allAccontUsers = accountUserService.findAllAccontUsers();
        
        return new ResponseEntity<>(allAccontUsers, HttpStatus.OK);
    }
}
