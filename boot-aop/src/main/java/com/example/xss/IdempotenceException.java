package com.example.xss;

public class IdempotenceException extends RuntimeException{

    public IdempotenceException(String message) {
        super(message);
    }
}
