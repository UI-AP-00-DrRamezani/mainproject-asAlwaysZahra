package com.example.demo1.Accounts.Customer;

import com.example.demo1.Accounts.Account;
import com.example.demo1.Factors.BuyFactor;
import com.example.demo1.Products.Product.Product;

import java.util.ArrayList;

public class Customer extends Account {

    private double credit;
    private final ArrayList<Product> cart = new ArrayList<>();
    private final ArrayList<BuyFactor> history = new ArrayList<>();

    public Customer(String username, String firstName, String lastName,
                 String email, String phoneNumber, String password, double credit)
    {
        super(username, firstName, lastName, email, phoneNumber, password);
        super.setAccountType("Customer");
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + getUsername() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", credit='" + this.credit + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

    // Getters and Setters ================================================

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        this.cart.add(product);
    }

    public ArrayList<BuyFactor> getHistory() {
        return history;
    }

    public void addToHistory(BuyFactor factor) {
        this.history.add(factor);
    }
}
