import Accounts.Admin.AdminManager;
import Accounts.Seller.Seller;
import Products.Digital.Laptop;
import Products.Product;
import Products.ProductManager;
import Products.ProductRequest;
import UI.MainMenu;

public class Main {
    public static void main(String[] args) {

        AdminManager admin = new AdminManager();

        Product l1 = new Laptop("lenovo1", "leno", 1200.1, "perfect",
                123, 4, "win", 3, 15, "intel", true);
        Product l2 = new Laptop("lenovo2", "leno", 1200.2, "perfect",
                123, 4, "win", 3, 15, "intel", true);
        Product l3 = new Laptop("lenovo3", "leno", 1200.3, "perfect",
                123, 4, "win", 3, 15, "intel", true);

        ProductManager.addProduct(l1);
        ProductManager.addProduct(l2);
        ProductManager.addProduct(l3);

        Seller s1 = new Seller("seller", "fi", "la", "em", "091", "pass", "comp");

        admin.acceptSeller(s1);
        admin.acceptAddProduct(new ProductRequest(l1, null, s1, true, false, false));
        admin.acceptAddProduct(new ProductRequest(l2, null, s1, true, false, false));
        admin.acceptAddProduct(new ProductRequest(l3, null, s1, true, false, false));


        MainMenu.mainMenu();
    }
}
