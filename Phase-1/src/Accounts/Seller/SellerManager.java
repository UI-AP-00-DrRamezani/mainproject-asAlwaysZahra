package Accounts.Seller;

import Accounts.Admin.AdminManager;
import Accounts.ChangeRequest;
import Products.Product.ProductManager;
import Products.ProductRequest;
import Products.Product.Product;

import java.util.ArrayList;

public class SellerManager {

    public static ArrayList<Seller> allSellers = new ArrayList<>();

    public static Seller sellerModel;
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
    public static boolean login(String username, String password) {

        for (Seller s: allSellers)
            if (username.equals(s.getUsername()) && password.equals(s.getPassword()))
            {
                sellerModel = s;
                return true;
            }

        return false;
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
    public boolean removeProduct(int productID) {

        // find and get product
        Product product = ProductManager.getProductByID(productID);

        if (product != null)
        {
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
