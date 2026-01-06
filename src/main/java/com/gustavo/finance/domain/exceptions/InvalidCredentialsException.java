package com.gustavo.finance.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException() {
        super("Email ou senha inválidos");
    }
    
}
