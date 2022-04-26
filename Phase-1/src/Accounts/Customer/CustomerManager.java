package Accounts.Customer;

import Accounts.Admin.AdminManager;

import java.util.ArrayList;

public class CustomerManager {

    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    private Customer customerModel;
    // Methods ---------------------------------------------------------------------
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
    public Customer getCustomerModel() {
        return customerModel;
    }
    // -----------------------------------------------------------------------------
    public void setCustomerModel(Customer model) {
        customerModel = model;
    }
}
