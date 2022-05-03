package Accounts.Admin;

import Accounts.Account;
import Accounts.ChangeRequest;
import Accounts.Customer.Customer;
import Accounts.Customer.CustomerManager;
import Accounts.ProductRequest;
import Accounts.Seller.Seller;
import Accounts.Seller.SellerManager;
import Products.Comment;
import Products.ProductManager;

import java.util.ArrayList;

public class AdminManager {

    public static ArrayList<Account> allUsers = new ArrayList<>();
    public static ArrayList<Seller> sellerAddRequests = new ArrayList<>();
    public static ArrayList<ChangeRequest> editInfoRequests = new ArrayList<>();
    public static ArrayList<Comment> comments = new ArrayList<>();
    public static ArrayList<ProductRequest> productRequests = new ArrayList<>();

    private Admin adminModel;
    // Methods ---------------------------------------------------------------------
    public void editInfo(String username, String firstName, String lastName,
                         String email, String phoneNumber, String password) {
        adminModel.setUsername(username);
        adminModel.setFirstName(firstName);
        adminModel.setLastName(lastName);
        adminModel.setEmail(email);
        adminModel.setPhoneNumber(phoneNumber);
        adminModel.setPassword(password);
    }
    // -----------------------------------------------------------------------------
    public boolean removeUser(String username) {
        boolean found = false;
        for (Account acc: allUsers)
            if (username.equals(acc.getUsername())) {
                found = true;
                allUsers.remove(acc);
                // remove from sellers and customers list
                if (acc instanceof Seller)
                    SellerManager.allSellers.remove(acc);
                if (acc instanceof Customer)
                    CustomerManager.allCustomers.remove(acc);
            }
        return found;
    }
    // -----------------------------------------------------------------------------
    public ArrayList<Account> getAllUsers() {
        return allUsers;
    }
    // -----------------------------------------------------------------------------
    public void login(String username, String password) {
        if (username.equals(Admin.getAdmin().getUsername()) &&
            password.equals(Admin.getAdmin().getPassword()))
            adminModel = Admin.getAdmin();
    }
    // -----------------------------------------------------------------------------
    public void logout() {
        adminModel = null;
    }
    // -----------------------------------------------------------------------------
    public static boolean availableUsername(String username) {
        for (Account acc: allUsers)
            if (username.equals(acc.getUsername()))
                return false;
        return true;
    }
    // -----------------------------------------------------------------------------
    public void acceptSeller(Seller seller) {
        allUsers.add(seller);
        SellerManager.allSellers.add(seller);
    }
    // -----------------------------------------------------------------------------
    public void acceptChange(ChangeRequest request) {

        request.getOldInfo().setUsername(request.getNewInfo().getUsername());
        request.getOldInfo().setFirstName(request.getNewInfo().getFirstName());
        request.getOldInfo().setLastName(request.getNewInfo().getLastName());
        request.getOldInfo().setEmail(request.getNewInfo().getEmail());
        request.getOldInfo().setPhoneNumber(request.getNewInfo().getPhoneNumber());
        request.getOldInfo().setPassword(request.getNewInfo().getPassword());

        if (request.getNewInfo() instanceof Seller) {
            ((Seller) request.getOldInfo()).setCompany(((Seller) request.getNewInfo()).getCompany());
        }
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
        productRequests.remove(request);
    }
    // -----------------------------------------------------------------------------
    public void acceptRemoveProduct(ProductRequest request) {
        ProductManager.removeProduct(request.getProduct());
        request.getSeller().getSaleProducts().remove(request.getProduct());
        productRequests.remove(request);
    }
    // -----------------------------------------------------------------------------
    public void acceptChangeProduct(ProductRequest request) {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        productRequests.remove(request);
    }
}