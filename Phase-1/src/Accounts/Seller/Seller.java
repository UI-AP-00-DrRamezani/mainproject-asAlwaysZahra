package Accounts.Seller;

import Accounts.Account;

public class Seller extends Account {

    private String company;
    //private final ArrayList<SaleFactor> history = new ArrayList<>();
    //private final ArrayList<Product> saleProducts = new ArrayList<>();

    public Seller(String username, String firstName, String lastName,
                  String email, String phoneNumber, String password, String company)
    {
        super(username, firstName, lastName, email, phoneNumber, password);
        super.setAccountType("Seller");
        this.company = company;
    }

    @Override
    public String toString() {
        return "Seller {" +
                "username: " + this.getUsername() + " - " +
                ", name: " + this.getFirstName() + " " + this.getLastName() + " - " +
                ", company: " + this.company + " - " +
                ", email: " + this.getEmail() + " - " +
                ", phone number: " + this.getPhoneNumber() + " - " +
                ", password: " + this.getPassword() +
                " }" ;
    }

    // Getters and Setters ================================================

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
