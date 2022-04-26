package Accounts.Customer;

import Accounts.Account;

import java.util.ArrayList;

public class Customer extends Account {

    private double credit;
//    private final ArrayList<Product> cart = new ArrayList<>();
//    private final ArrayList<BuyFactor> history = new ArrayList<>();

    public Customer(String username, String firstName, String lastName,
                 String email, String phoneNumber, String password, double credit)
    {
        super(username, firstName, lastName, email, phoneNumber, password);
        super.setAccountType("Customer");
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "username: " + this.getUsername() + '\'' +
                ", name: " + this.getFirstName() + " " + this.getLastName() + '\'' +
                ", credit: " + this.credit + '\'' +
                ", email: " + this.getEmail() + '\'' +
                ", phone number: " + this.getPhoneNumber() + '\'' +
                ", password: " + this.getPassword() + '\'' +
                " }" ;
    }

    // Getters and Setters ================================================

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
