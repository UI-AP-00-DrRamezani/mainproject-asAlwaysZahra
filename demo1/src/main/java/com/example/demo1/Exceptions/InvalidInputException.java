package com.example.demo1.Exceptions;

import java.io.Serializable;

public abstract class InvalidInputException extends RuntimeException implements Serializable {

    public InvalidInputException(String message) {
        super("Invalid Input: " + message);
    }

    public InvalidInputException() {
        super("Invalid Input");
    }

    @Override
    public String toString() {
        return "Invalid Input Exception";
    }
}
