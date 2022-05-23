package Accounts.Seller;

import Accounts.Admin.AdminManager;
import Accounts.ChangeRequest;
import Products.ProductRequest;
import Products.Product;

import java.util.ArrayList;

public class SellerManager {

    public static ArrayList<Seller> allSellers = new ArrayList<>();

    private Seller sellerModel;
    // Methods ---------------------------------------------------------------------
    public boolean addSeller(String username, String firstName, String lastName,
                                    String email, String phoneNumber, String password, String company)
    {
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
    // -----------------------------------------------------------------------------
    public boolean editInfo(String username, String firstName, String lastName,
                           String email, String phoneNumber, String password, String company)
    {
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
    // -----------------------------------------------------------------------------
    public ArrayList<Seller> getAllSellers() {
        return allSellers;
    }
    // -----------------------------------------------------------------------------
    public void login(String username, String password) {
        for (Seller s: allSellers)
            if (username.equals(s.getUsername()) && password.equals(s.getPassword()))
                sellerModel = s;
    }
    // -----------------------------------------------------------------------------
    public void logout() {
        sellerModel = null;
    }
    // -----------------------------------------------------------------------------
    public void addProduct(Product product) {
        AdminManager.productRequests.add(new ProductRequest(product, null, sellerModel,
                true, false, false));
    }
    // -----------------------------------------------------------------------------
    public void removeProduct(Product product) {
        AdminManager.productRequests.add(new ProductRequest(product, null, sellerModel,
                false, true, false));
    }
    // -----------------------------------------------------------------------------
    public void editProduct(Product oldProduct, Product newProduct) {
        AdminManager.productRequests.add(new ProductRequest(newProduct, oldProduct, sellerModel,
                false, false, true));
    }
}
