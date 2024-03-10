package com.picpaysimplificado.dto;

import java.math.BigDecimal;

import com.picpaysimplificado.domain.user.UserType;

public record AccountUserDto(
        String firstName,
        String lastName,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType) {

}
