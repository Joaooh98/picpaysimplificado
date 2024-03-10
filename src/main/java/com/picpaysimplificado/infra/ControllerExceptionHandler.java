package com.picpaysimplificado.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaysimplificado.dto.ExceptionDto;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ControllerExceptionHandler {
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exeception){
        ExceptionDto exceptionDto = new ExceptionDto("usuario ja cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDto);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotfund(DataIntegrityViolationException exeception){
        
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeralException(Exception exeception){
        ExceptionDto exceptionDto = new ExceptionDto(exeception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDto);
    }
}
