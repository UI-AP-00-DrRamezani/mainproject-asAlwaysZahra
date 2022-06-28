package com.example.demo1.Accounts;

import com.example.demo1.Accounts.Admin.Admin;
import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Accounts.Seller.Seller;

public abstract class Account {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String accountType; // set in child classes

    public Account(String username, String firstName, String lastName,
                   String email, String phoneNumber, String password)
    {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String insertUser() {
        if (this instanceof Admin)
            return String.format("INSERT INTO `users` (`type`, `username`, `firstName`, `lastName`, `password`) VALUES ('%s', '%s', '%s', '%s', '%s')",
                "Admin", username, firstName, lastName, password);
        if (this instanceof Seller)
            return String.format("INSERT INTO `users` (`type`, `username`, `firstName`, `lastName`, `password`) VALUES ('%s', '%s', '%s', '%s', '%s')",
                    "Seller", username, firstName, lastName, password);
        if (this instanceof Customer)
            return String.format("INSERT INTO `users` (`type`, `username`, `firstName`, `lastName`, `password`) VALUES ('%s', '%s', '%s', '%s', '%s')",
                    "Customer", username, firstName, lastName, password);
        return null;
    }

    public String updateUser() {
        return String.format("UPDATE `users` SET username='%s', firstName='%s', lastName='%s', password='%s' WHERE users.username=",
                username, firstName, lastName, password);
    }

    public String deleteUser() {
        return String.format("DELETE FROM `users` WHERE users.username='%s'", username);
    }

    @Override
    public abstract String toString();

    // Getters and Setters ================================================

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
