package com.example.demo1.Accounts.Seller;

import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.Accounts.ChangeRequest;
import com.example.demo1.Exceptions.InvalidEmailException;
import com.example.demo1.Exceptions.InvalidPhoneNumberException;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import com.example.demo1.Products.ProductRequest;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SellerManager {

    public static ArrayList<Seller> allSellers = new ArrayList<>();

    public static Seller sellerModel;

    // Methods ---------------------------------------------------------------------
    public static boolean addSeller(String username, String firstName, String lastName,
                                    String email, String phoneNumber, String password, String company)
            throws InvalidEmailException, InvalidPhoneNumberException {
        String emailReg = "^[A-Za-z0-9+_.-]+@(.+)$"; // email regex
        Pattern pattern1 = Pattern.compile(emailReg);

        String numReg = "(\\+989|9|09)(10|11|12|13|14|16|18|19|35|36|37|38|39|32)\\d{7}"; // phone number regex
        Pattern pattern2 = Pattern.compile(numReg);

        if (!pattern1.matcher(email).matches())
            throw new InvalidEmailException();

        else if (!pattern2.matcher(phoneNumber).matches())
            throw new InvalidPhoneNumberException();

        else {
            // check username
            if (AdminManager.availableUsername(username)) {
                AdminManager.sellerAddRequests.add(new Seller(username, firstName, lastName,
                        email, phoneNumber, password, company));
                return true;

            } else {
                // if username is unavailable
                return false;
            }
        }
    }

    // -----------------------------------------------------------------------------
    public static boolean editInfo(String username, String firstName, String lastName,
                                   String email, String phoneNumber, String password, String company)
            throws InvalidEmailException, InvalidPhoneNumberException {
        String emailReg = "^[A-Za-z0-9+_.-]+@(.+)$"; // email regex
        Pattern pattern1 = Pattern.compile(emailReg);

        String numReg = "(\\+989|9|09)(10|11|12|13|14|16|18|19|35|36|37|38|39|32)\\d{7}"; // phone number regex
        Pattern pattern2 = Pattern.compile(numReg);

        if (!pattern1.matcher(email).matches())
            throw new InvalidEmailException();

        else if (!pattern2.matcher(phoneNumber).matches())
            throw new InvalidPhoneNumberException();

        else {
            // check username
            if (AdminManager.availableUsername(username)) {
                new ChangeRequest(sellerModel,
                        new Seller(username, firstName, lastName,
                                email, phoneNumber, password, company));
                return true;
            } else {
                // if username is not available
                return false;
            }
        }
    }

    // -----------------------------------------------------------------------------
    public static boolean login(String username, String password) {

        for (Seller s : allSellers)
            if (username.equals(s.getUsername()) && password.equals(s.getPassword())) {
                sellerModel = s;
                return true;
            }

        return false;
    }

    // -----------------------------------------------------------------------------
    public static void logout() {
        sellerModel = null;
    }

    // -----------------------------------------------------------------------------
    public void addProduct(Product product) {
        AdminManager.productRequests.add(new ProductRequest(product, null, sellerModel,
                true, false, false));
    }

    // -----------------------------------------------------------------------------
    public boolean removeProduct(int productID) {

        // find and get product
        Product product = ProductManager.getProductByID(productID);

        if (product != null) {
            AdminManager.productRequests.add(new ProductRequest(product, null, sellerModel,
                    false, true, false));
            return true;
        }

        // if product could not be found
        return false;
    }

    // -----------------------------------------------------------------------------
    public boolean editProduct(int oldProductID, Product newProduct) {

        // find and get product
        Product product = ProductManager.getProductByID(oldProductID);

        if (product != null) {
            AdminManager.productRequests.add(new ProductRequest(newProduct, product, sellerModel,
                    false, false, true));
            return true;
        }

        // else if product could not be found
        return false;
    }
}
