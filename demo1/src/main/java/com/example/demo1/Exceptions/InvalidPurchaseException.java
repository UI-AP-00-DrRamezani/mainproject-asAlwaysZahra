package com.example.demo1.Exceptions;

import java.io.Serializable;

public abstract class InvalidPurchaseException extends RuntimeException implements Serializable {

    public InvalidPurchaseException(String message) {
        super("Invalid Purchase: " + message);
    }

    public InvalidPurchaseException() {
        super("Invalid Purchase");
    }

    @Override
    public String toString() {
        return "Invalid Purchase Exception";
    }

}
