package Accounts.Customer;

import Accounts.Admin.AdminManager;
import Factors.BuyFactor;
import Factors.SaleFactor;
import Products.Product.Product;
import Products.Product.ProductManager;
import Products.Score;

import java.util.ArrayList;

public class CustomerManager {

    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    public static Customer customerModel;
    // Methods ---------------------------------------------------------------------
    public static boolean addToCart(int productID) {
        if (ProductManager.getProductByID(productID) != null) {
            customerModel.addToCart(ProductManager.getProductByID(productID));
            return true;
        }
        return false;
    }
    // -----------------------------------------------------------------------------
    public boolean removeFromCart(int productID) {
        for (Product p : customerModel.getCart())
            if (p.getId() == productID) {
                customerModel.getCart().remove(p);
                return true;
            }
        return false;
    }
    // -----------------------------------------------------------------------------
    // todo shipping page
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
        BuyFactor buyFactor = new BuyFactor(sum, (ArrayList<Product>) customerModel.getCart().clone());
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
        customerModel.setCredit(customerModel.getCredit() + credit);
    }
    // -----------------------------------------------------------------------------
    public boolean scoring(int productID, int score) { // todo fix score to do it 1 time

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
    public static boolean login(String username, String password) {

        for (Customer c: allCustomers)
            if (username.equals(c.getUsername()) && password.equals(c.getPassword()))
            {
                customerModel = c;
                return true;
            }

        return false;
    }
    // -----------------------------------------------------------------------------
    public void logout() {
        customerModel = null;
    }
    // -----------------------------------------------------------------------------
    public static boolean didBuyProduct(Product product) {

        // check if this user has bought the product -> search in History
        for (BuyFactor factor : customerModel.getHistory())
            for (Product p : factor.getProducts())
                if (p.getId() == product.getId())
                    return true;

        return false;
    }
}
