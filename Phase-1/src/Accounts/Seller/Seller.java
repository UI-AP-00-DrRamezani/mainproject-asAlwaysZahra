package Accounts.Seller;

import Accounts.Account;
import Factors.SaleFactor;
import Products.Product;

import java.util.ArrayList;

public class Seller extends Account {

    private String company;
    private final ArrayList<SaleFactor> history = new ArrayList<>();
    private final ArrayList<Product> saleProducts = new ArrayList<>();

    public Seller(String username, String firstName, String lastName,
                  String email, String phoneNumber, String password, String company)
    {
        super(username, firstName, lastName, email, phoneNumber, password);
        super.setAccountType("Seller");
        this.company = company;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "username='" + this.getUsername() + '\'' +
                ", name='" + this.getFirstName() + " " + this.getLastName() + '\'' +
                ", company='" + this.company + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                '}';
    }

    // Getters and Setters ================================================

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<Product> getSaleProducts() {
        return saleProducts;
    }

    public void addProduct(Product product) {
        saleProducts.add(product);
    }

    public ArrayList<SaleFactor> getHistory() {
        return history;
    }

    public void addToHistory(SaleFactor factor) {
        this.history.add(factor);
    }
}
