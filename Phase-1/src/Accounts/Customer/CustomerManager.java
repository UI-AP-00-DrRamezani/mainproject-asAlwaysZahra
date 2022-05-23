package Accounts.Customer;

import Accounts.Admin.AdminManager;
import Factors.BuyFactor;
import Factors.SaleFactor;
import Products.Product;
import Products.ProductManager;
import Products.Score;

import java.util.ArrayList;

public class CustomerManager {

    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    private Customer customerModel;
    // Methods ---------------------------------------------------------------------
    public boolean buy() {
        double sum = 0;

        // calculate overall payment
        for (Product p: customerModel.getCart())
            sum += p.getPrice();

        // if credit is not enough
        if (sum > customerModel.getCredit())
            return false;
        else
            customerModel.setCredit(customerModel.getCredit() - sum);

        // add to history for customer
        BuyFactor buyFactor = new BuyFactor(sum, customerModel.getCart());
        customerModel.addToHistory(buyFactor);

        // add sellers of products to buyFactor
        for (Product p: customerModel.getCart())
            buyFactor.addSeller(p.getSeller());

        // add to history for seller / decrease number of product
        for (Product p: customerModel.getCart()) {
            p.getSeller().addToHistory(new SaleFactor(p.getPrice(), p, customerModel));
            p.setNumber(p.getNumber()-1);
        }

        // remove from cart
        customerModel.getCart().clear();

        return true;
    }
    // -----------------------------------------------------------------------------
    public boolean addCustomer(String username, String firstName, String lastName,
                             String email, String phoneNumber, String password, double credit)
    {
        // check username
        if (AdminManager.availableUsername(username)) {

            Customer newCustomer = new Customer(username, firstName, lastName,
                    email, phoneNumber, password, credit);

            allCustomers.add(newCustomer);
            AdminManager.allUsers.add(newCustomer);
            return true;

        } else {
            // if username is unavailable
            return false;
        }
    }
    // -----------------------------------------------------------------------------
    public boolean editInfo(String username, String firstName, String lastName,
                            String email, String phoneNumber, String password)
    {
        // check username
        if (AdminManager.availableUsername(username)) {
            customerModel.setUsername(username);
            customerModel.setFirstName(firstName);
            customerModel.setLastName(lastName);
            customerModel.setEmail(email);
            customerModel.setPhoneNumber(phoneNumber);
            customerModel.setPassword(password);
            return true;
        } else {
            // if username is not available
            return false;
        }
    }
    // -----------------------------------------------------------------------------
    public void increaseCredit(double credit) {
        customerModel.setCredit(credit);
    }
    // -----------------------------------------------------------------------------
    public boolean scoring(int productID, int score) {

        // try to find the product in history
        for (BuyFactor factor : customerModel.getHistory())
            for (Product p: factor.getProducts())
                if (p.getId() == productID) {
                    p.addScore(new Score(customerModel, score, p));
                    // update avgRate
                    ProductManager.calculateAvgRate(p);
                    return true;
                }

        // if product can not be found in history
        return false;
    }
    // -----------------------------------------------------------------------------
    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }
    // -----------------------------------------------------------------------------
    public void login(String username, String password) {
        for (Customer c: allCustomers)
            if (username.equals(c.getUsername()) && password.equals(c.getPassword()))
                customerModel = c;
    }
    // -----------------------------------------------------------------------------
    public void logout() {
        customerModel = null;
    }
    // -----------------------------------------------------------------------------
}
