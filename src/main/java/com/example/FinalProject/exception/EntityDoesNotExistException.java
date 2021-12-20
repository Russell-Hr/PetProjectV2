package com.example.FinalProject.exception;

public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException() {
    }

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}
