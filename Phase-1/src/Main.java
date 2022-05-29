<<<<<<< HEAD
import Accounts.Admin.AdminManager;
import Accounts.Customer.CustomerManager;
import Accounts.Seller.Seller;
import Products.Digital.Laptop;
import Products.Product.Product;
import Products.ProductRequest;
import UI.MainMenu;
=======
import Accounts.Admin;
import UserInterface.Menu;
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

public class Main {
    public static void main(String[] args) {

<<<<<<< HEAD
        AdminManager admin = new AdminManager();

        Product l1 = new Laptop("lenovo1", "leno", 1200.1, "perfect1",
                123, 4, "win", 3, 15, "intel", true);
        Product l2 = new Laptop("lenovo2", "leno", 1200.2, "perfect2",
                124, 4, "win", 2, 14, "intel", true);
        Product l3 = new Laptop("lenovo3", "leno", 1200.3, "perfect3",
                125, 4, "win", 1, 13, "intel", true);


        CustomerManager customerManager = new CustomerManager();
        customerManager.addCustomer("azin", "Azin", "Azari", "azin@gmail", "0912378877", "azinn", 12000);
        Seller s1 = new Seller("ziba", "Ziba", "Vahidi", "zibaa@yahoo", "09193048093", "zibaa", "comp1");

        admin.acceptSeller(s1);
        admin.acceptAddProduct(new ProductRequest(l1, null, s1, true, false, false));
        admin.acceptAddProduct(new ProductRequest(l2, null, s1, true, false, false));
        admin.acceptAddProduct(new ProductRequest(l3, null, s1, true, false, false));

        MainMenu.mainMenu();
    }
}
=======
        Admin.getAdmin("Zahra", "Masumi",
                "myEmail@email.com", "09123456789");

        Menu.firstMenu();

    }
}
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
