package com.example.demo1.Exceptions;

import java.io.Serializable;

public class MoneyInventoryException extends InvalidPurchaseException implements Serializable {

    public MoneyInventoryException() {
        super("Credit is not enough to pay,\n" +
                "please charge your account first");
    }

    @Override
    public String toString() {
        return "Money Inventory Exception";
    }
}
