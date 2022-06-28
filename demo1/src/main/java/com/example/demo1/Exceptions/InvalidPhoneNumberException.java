package com.example.demo1.Exceptions;

import java.io.Serializable;

public class InvalidPhoneNumberException extends InvalidInputException implements Serializable {

    public InvalidPhoneNumberException() {
        super("Invalid Phone Number Format");
    }

    @Override
    public String toString() {
        return "Invalid Phone Number Exception";
    }
}
