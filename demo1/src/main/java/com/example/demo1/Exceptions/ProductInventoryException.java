package com.example.demo1.Exceptions;

import java.io.Serializable;

public class ProductInventoryException extends InvalidPurchaseException implements Serializable {

    public ProductInventoryException() {
        super("Lack of Product Inventory");
    }

    @Override
    public String toString() {
        return "Product Inventory Exception";
    }
}
