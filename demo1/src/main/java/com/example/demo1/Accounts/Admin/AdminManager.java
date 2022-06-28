package com.example.demo1.Accounts.Admin;

import com.example.demo1.Accounts.Account;
import com.example.demo1.Accounts.ChangeRequest;
import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Accounts.Seller.Seller;
import com.example.demo1.Accounts.Seller.SellerManager;
import com.example.demo1.DataBase.ProductsTable;
import com.example.demo1.DataBase.UsersTable;
import com.example.demo1.Exceptions.InvalidEmailException;
import com.example.demo1.Exceptions.InvalidPhoneNumberException;
import com.example.demo1.Products.Comment;
import com.example.demo1.Products.Product.ProductManager;
import com.example.demo1.Products.ProductRequest;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AdminManager {

    public static ArrayList<Account> allUsers = new ArrayList<>();
    public static ArrayList<Seller> sellerAddRequests = new ArrayList<>();
    public static ArrayList<ChangeRequest> editInfoRequests = new ArrayList<>();
    public static ArrayList<Comment> comments = new ArrayList<>();
    public static ArrayList<ProductRequest> productRequests = new ArrayList<>();

    public static Admin adminModel;

    // Methods ---------------------------------------------------------------------
    public static boolean editInfo(String username, String firstName, String lastName,
                                   String email, String phoneNumber, String password)
            throws InvalidEmailException, InvalidPhoneNumberException {
        String emailReg = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern1 = Pattern.compile(emailReg);

        String numReg = "(\\+989|9|09)(12|19|35|36|37|38|39|32)\\d{7}";
        Pattern pattern2 = Pattern.compile(numReg);

        if (!pattern1.matcher(email).matches())
            throw new InvalidEmailException();

        else if (!pattern2.matcher(phoneNumber).matches())
            throw new InvalidPhoneNumberException();

        if (!availableUsername(username))
            return false;

        else {
            String oldUser = adminModel.getUsername();
            adminModel.setUsername(username);
            adminModel.setFirstName(firstName);
            adminModel.setLastName(lastName);
            adminModel.setEmail(email);
            adminModel.setPhoneNumber(phoneNumber);
            adminModel.setPassword(password);

            // update in database
            UsersTable.update(adminModel, oldUser);
        }

        return true;
    }

    // -----------------------------------------------------------------------------
    public boolean removeUser(String username) {

        // finding user
        for (Account acc : allUsers)
            if (username.equals(acc.getUsername())) {
                allUsers.remove(acc);

                // remove from sellers and customers list
                if (acc instanceof Seller)
                    SellerManager.allSellers.remove(acc);
                if (acc instanceof Customer)
                    CustomerManager.allCustomers.remove(acc);

                // remove from Data Base
                UsersTable.delete(acc);

                return true;
            }

        // username could not be found
        return false;
    }

    // -----------------------------------------------------------------------------
    public static boolean login(String username, String password) {
        if (username.equals(Admin.getAdmin().getUsername()) &&
                password.equals(Admin.getAdmin().getPassword())) {
            adminModel = Admin.getAdmin();
            return true;
        } else
            return false;
    }

    // -----------------------------------------------------------------------------
    public static void logout() {
        adminModel = null;
    }

    // -----------------------------------------------------------------------------
    public static boolean availableUsername(String username) {
        for (Account acc : allUsers)
            if (username.equals(acc.getUsername()))
                return false;
        return true;
    }

    // -----------------------------------------------------------------------------
    public void acceptSeller(Seller seller) {
        allUsers.add(seller);
        SellerManager.allSellers.add(seller);
        // insert into database
        UsersTable.insert(seller);
    }

    // -----------------------------------------------------------------------------
    public void acceptChange(ChangeRequest request) {

        String oldUsername = request.getOldInfo().getUsername();
        request.getOldInfo().setUsername(request.getNewInfo().getUsername());
        request.getOldInfo().setFirstName(request.getNewInfo().getFirstName());
        request.getOldInfo().setLastName(request.getNewInfo().getLastName());
        request.getOldInfo().setEmail(request.getNewInfo().getEmail());
        request.getOldInfo().setPhoneNumber(request.getNewInfo().getPhoneNumber());
        request.getOldInfo().setPassword(request.getNewInfo().getPassword());

        if (request.getNewInfo() instanceof Seller) {
            ((Seller) request.getOldInfo()).setCompany(((Seller) request.getNewInfo()).getCompany());
        }

        // update data in Data Base
        UsersTable.update(request.getNewInfo(), oldUsername);
    }

    // -----------------------------------------------------------------------------
    public void acceptComment(Comment comment) {
        comment.getProduct().addComment(comment);
        comment.setStat(Comment.Status.ACCEPTED);
    }

    // -----------------------------------------------------------------------------
    public void rejectComment(Comment comment) {
        comment.setStat(Comment.Status.UNCONFIRMED);
    }

    // -----------------------------------------------------------------------------
    public void acceptAddProduct(ProductRequest request) {
        ProductManager.addProduct(request.getProduct());
        request.getSeller().addProduct(request.getProduct());
        request.getProduct().setSeller(request.getSeller());
        // insert to database
        ProductsTable.insert(request.getProduct());
        productRequests.remove(request);
    }

    // -----------------------------------------------------------------------------
    public void acceptEditProduct(ProductRequest request) {
        ProductManager.editProduct(request.getOldProduct(), request.getProduct());
        // update in database
        ProductsTable.update(request.getProduct());
        productRequests.remove(request);
    }

    // -----------------------------------------------------------------------------
    public void acceptRemoveProduct(ProductRequest request) {
        ProductManager.removeProduct(request.getProduct());
        request.getSeller().getSaleProducts().remove(request.getProduct());
        // delete from database
        ProductsTable.delete(request.getProduct());
        productRequests.remove(request);
    }
}