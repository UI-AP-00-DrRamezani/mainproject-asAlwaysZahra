package com.example.demo1.Accounts.Customer;

import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.DataBase.UsersTable;
import com.example.demo1.Exceptions.InvalidEmailException;
import com.example.demo1.Exceptions.InvalidPhoneNumberException;
import com.example.demo1.Exceptions.MoneyInventoryException;
import com.example.demo1.Exceptions.ProductInventoryException;
import com.example.demo1.Factors.BuyFactor;
import com.example.demo1.Factors.SaleFactor;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import com.example.demo1.Products.Score;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CustomerManager {

    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    public static Customer customerModel;

    // Methods ---------------------------------------------------------------------
    // todo add username exception
    public static boolean addCustomer(String username, String firstName, String lastName,
                                      String email, String phoneNumber, String password, double credit)
            throws InvalidEmailException, InvalidPhoneNumberException {

        String emailReg = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern1 = Pattern.compile(emailReg);

        String numReg = "(\\+989|9|09)(10|11|12|13|14|16|18|19|35|36|37|38|39|32)\\d{7}";
        Pattern pattern2 = Pattern.compile(numReg);

        if (!pattern1.matcher(email).matches())
            throw new InvalidEmailException();

        else if (!pattern2.matcher(phoneNumber).matches())
            throw new InvalidPhoneNumberException();

        else {
            // check username
            if (AdminManager.availableUsername(username)) {

                Customer newCustomer = new Customer(username, firstName, lastName,
                        email, phoneNumber, password, credit);

                allCustomers.add(newCustomer);
                AdminManager.allUsers.add(newCustomer);
                // add to Data Base
                UsersTable.insert(newCustomer);
                return true;

            } else {
                // if username is unavailable
                return false;
            }
        }
    }

    // -----------------------------------------------------------------------------
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
    public static void buy() throws MoneyInventoryException, ProductInventoryException {
        double sum = 0;

        // calculate overall payment
        for (Product p : customerModel.getCart())
            sum += p.getPrice();

        // if credit is not enough
        if (sum > customerModel.getCredit())
            throw new MoneyInventoryException();

        else {

            customerModel.setCredit(customerModel.getCredit() - sum);

            // add to history for customer
            BuyFactor buyFactor = new BuyFactor(sum, (ArrayList<Product>) customerModel.getCart().clone());
            customerModel.addToHistory(buyFactor);

            // add sellers of products to buyFactor
            for (Product p : customerModel.getCart())
                buyFactor.addSeller(p.getSeller());

            // add to history for seller / decrease number of product
            for (Product p : customerModel.getCart()) {
                p.getSeller().addToHistory(new SaleFactor(p.getPrice(), p, customerModel));
                if (p.getNumber() <= 0)
                    throw new ProductInventoryException();
                else
                    p.setNumber(p.getNumber() - 1);
            }

            // remove from cart
            customerModel.getCart().clear();
        }

    }

    // -----------------------------------------------------------------------------
    public static boolean editInfo(String username, String firstName, String lastName,
                                   String email, String phoneNumber, String password)
            throws InvalidEmailException, InvalidPhoneNumberException {
        String emailReg = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern1 = Pattern.compile(emailReg);

        String numReg = "(\\+989|9|09)(10|11|12|13|14|16|18|19|35|36|37|38|39|32)\\d{7}";
        Pattern pattern2 = Pattern.compile(numReg);

        if (!pattern1.matcher(email).matches())
            throw new InvalidEmailException();

        else if (!pattern2.matcher(phoneNumber).matches())
            throw new InvalidPhoneNumberException();

        else {
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
    }

    // -----------------------------------------------------------------------------
    public void increaseCredit(double credit) {
        customerModel.setCredit(customerModel.getCredit() + credit);
    }

    // -----------------------------------------------------------------------------
    public boolean scoring(int productID, int score) {
        // todo fix score to do it 1 time
        // try to find the product in history
        for (BuyFactor factor : customerModel.getHistory())
            for (Product p : factor.getProducts())
                if (p.getId() == productID) {
                    p.addScore(new Score(customerModel, score, p));
                    ProductManager.calculateAvgRate(p);
                    return true;
                }

        // if product can not be found in history
        return false;
    }

    // -----------------------------------------------------------------------------
    public static void login(Customer c) {
        customerModel = c;
    }

    // -----------------------------------------------------------------------------
    public static void logout() {
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
