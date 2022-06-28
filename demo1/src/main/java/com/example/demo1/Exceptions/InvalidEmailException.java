package com.example.demo1.Exceptions;

import java.io.Serializable;

public class InvalidEmailException extends InvalidInputException implements Serializable {

    public InvalidEmailException() {
        super("Invalid  Email Format");
    }

    @Override
    public String toString() {
        return "Invalid Email Exception";
    }
}
