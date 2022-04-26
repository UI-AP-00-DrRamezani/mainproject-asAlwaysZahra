package Accounts.Admin;

import Accounts.Account;

import java.util.ArrayList;

public class AdminManager {

    static ArrayList<Account> allUsers = new ArrayList<>();
    //static ArrayList<Seller> sellerAddRequests = new ArrayList<>();
    //static ArrayList<Seller> SellerChangeRequests = new ArrayList<>();
    //static ArrayList<Product> productAddRequests = new ArrayList<>();
    //static ArrayList<Product> productChangeRequests = new ArrayList<>();

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
                // remove from sellers and customers list !!!!!!!!!!!!!!!!!!
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
    public Admin getAdminModel() {
        return adminModel;
    }
    // -----------------------------------------------------------------------------
}