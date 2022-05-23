import Accounts.Admin.AdminManager;
import Products.Digital.Laptop;
import Products.ProductManager;
import UI.MainMenu;

public class Main {
    public static void main(String[] args) {



        AdminManager admin = new AdminManager();
        ProductManager.addProduct(new Laptop("lenovo1", "leno", 1200.3, "perfect",
                123, 4, "win", 3, 15, "intel", true));
        ProductManager.addProduct(new Laptop("lenovo2", "leno", 1200.3, "perfect",
                123, 4, "win", 3, 15, "intel", true));
        ProductManager.addProduct(new Laptop("lenovo3", "leno", 1200.3, "perfect",
                123, 4, "win", 3, 15, "intel", true));
        MainMenu.mainMenu();
    }
}
