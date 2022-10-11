package com.example.moneytransfer.exception;

public class AccountDataException extends RuntimeException{
    public AccountDataException() {super();}

    public AccountDataException(String message) {
        super(message);
    }
}
