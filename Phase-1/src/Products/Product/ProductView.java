package Products.Product;

import Accounts.Account;
import Accounts.Admin.AdminManager;
import Accounts.Customer.Customer;
import Accounts.Customer.CustomerManager;
import Accounts.Seller.SellerManager;
import Products.Category;
import Products.Comment;
import Products.Digital.Laptop;
import Products.Digital.Mobile;
import Products.Food.Food;
import Products.Garment.Clothe;
import Products.Garment.Shoes;
import Products.Home.Refrigerator;
import Products.Home.Stove;
import Products.Home.TV;
import UI.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProductView {
    static Scanner sc = new Scanner(System.in);
    // Methods ---------------------------------------------------------------------
    public static void productMenu() {
        while (true)
        {
            System.out.println("--- Products ---");
            System.out.println("Select a Category:");

            for (Category category : Category.allCategories)
                System.out.println("- " + category.getName());

            System.out.println("1. Back");

            String choice = sc.next();

            if ("1".equals(choice)) {
                MainMenu.mainMenu();
                break;
            }

            // print category products
            for (Category c : Category.allCategories)
                if (c.getName().equalsIgnoreCase(choice))
                {
                    // print products in a table
                    printProductsTable(c.getProducts());
                    // things we can do in product page
                    showProductsOptions(c);
                    break;
                }
        }
    }
    // -----------------------------------------------------------------------------
    static void showProductsOptions(Category c) {

        System.out.println("\n1. Filter\t2. Remove Filter\t3. Search\t4. See in Details\t5. Add to Cart\t6. Back");

        switch (sc.nextInt())
        {
            //all of these methods are in this class

            case 1 -> filter(c);

            case 2 -> printProductsTable(ProductManager.removeFilter(c));

            case 3 -> search(c);

            case 4 -> seeInDetail();

            case 5 -> addToCart();

            case 6 -> productMenu();
        }
    }
    // -----------------------------------------------------------------------------
    static Account checkLogin() {

        if (AdminManager.adminModel != null)
            return AdminManager.adminModel;

        else if (SellerManager.sellerModel != null)
            return SellerManager.sellerModel;

        else if (CustomerManager.customerModel != null)
            return CustomerManager.customerModel;

        else
            return null;
    }
    // -----------------------------------------------------------------------------
    static void filter(Category category) {

        System.out.println("Select the feature you want to filter products by:");
        System.out.println("1. Price\n2. Available goods\n3. Brand\n4. Category Filter");

        switch (sc.nextInt())
        {
            case 1:
                System.out.println("Enter limits:");
                printProductsTable(ProductManager.filterByPrice(sc.nextDouble(), sc.nextDouble(), category));
                break;

            case 2:
                printProductsTable(ProductManager.filterByAvailability(category));
                break;

            case 3:
                System.out.println("Brand:");
                printProductsTable(ProductManager.filterByBrand(sc.next(), category));
                break;

            case 4:
                categoryFilter(category);
                break;

            default:
                System.out.println("Error");
        }

        // show what we can do with products
        showProductsOptions(category);
    }
    // -----------------------------------------------------------------------------
    static void search(Category category) {

        System.out.print("Enter product's name: ");
        String productName = sc.next();

        printProductsTable(ProductManager.search(productName, category));

        showProductsOptions(category);
    }
    // -----------------------------------------------------------------------------
    public static void printProductsTable(ArrayList<Product> list) {

        String format = "| %-3d | %-13s | %-8s | %-9s | %-8s | %-4s |%n";
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.format("| ID  | Product Name  | Brand    | Seller    | Price    | Rate |%n");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");

        for (Product p : list)
            System.out.format(format, p.getId(), p.getName(), p.getBrand(),
                    p.getSeller().getUsername(), "$"+p.getPrice(), p.getAvgRate()+"⭐");

        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
    }
    // -----------------------------------------------------------------------------
    static void addComment(Product product, Account account) {

        sc.nextLine();
        System.out.println("\nComment text:");
        String text = sc.nextLine();

        if (checkLogin() instanceof Customer) {

            // check if this user has bought the product -> search in History
            if (CustomerManager.didBuyProduct(product))
                new Comment(account, product, text, true);

            // if the product couldn't be found in user's History
            else
                new Comment(account, product, text, false);

        } else {
            // if user is not Customer, So he/she didn't buy the product
            new Comment(account, product, text, false);
        }

        System.out.println("\nYour comment submitted.");
    }
    // -----------------------------------------------------------------------------
    static void seeInDetail() {

        System.out.print("Enter the ID of product: ");
        int id = sc.nextInt();
        Product product = ProductManager.getProductByID(id);

        if (product == null) {
            System.out.println("\nThis product ID doesn't exit.");
            productMenu();
            return;
        }

        System.out.println(product);
        System.out.println("1. Add To Cart\t2. Comments\t3. Add a Comment\t4. Back");

        int number = sc.nextInt();

        if (number == 1) {
            // check login
            if (checkLogin() instanceof Customer) {

                if (CustomerManager.addToCart(id))
                    System.out.println("Added to cart successfully");

            } else {

                System.out.println("\nPlease log in with a customer account first");
                ProfileMenu.profileMenu();
            }

        } else if (number == 2) {

            for (Comment comment : product.getComments())
                System.out.println(comment.toString());

        } else if (number == 3) {

            if (checkLogin() != null)
                // method in this class
                addComment(product, checkLogin());

            else {
                System.out.println("\nPlease log in first.");
                ProfileMenu.profileMenu();
            }
        }
    }
    // -----------------------------------------------------------------------------
    static void addToCart() {

        // check if user has logged in
        if (checkLogin() != null)
        {
            System.out.print("Enter the ID of product: ");
            int id = sc.nextInt();
            Product product = ProductManager.getProductByID(id);

            // check if product exists
            if (product != null)
            {
                // check if the user logged in isn't a customer
                if (!(checkLogin() instanceof Customer))
                    System.out.println("You can not buy product, please log in with a customer account");
                else {
                    CustomerManager.addToCart(id);
                    System.out.println("added");
                }
            }
            else
                System.out.println("\nThis product ID doesnt exit.");

        } else {
            System.out.println("\nPlease log in first");
            ProfileMenu.profileMenu();
        }
    }
    // -----------------------------------------------------------------------------
    static void categoryFilter(Category category) {

        if (category.getName().equals("Laptop")) {

            System.out.println("4. memoryCapacity\n5. RAM\n6. OS\n7. weight\n8. size\n9. processor\n10. gaming");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter memoryCapacity:");
                    printProductsTable(Laptop.categoryFilter("memoryCapacity", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter RAM:");
                    printProductsTable(Laptop.categoryFilter("RAM", sc.next()));
                }
                case 6 -> {
                    System.out.println("Enter OS:");
                    printProductsTable(Laptop.categoryFilter("OS", sc.next()));
                }
                case 7 -> {
                    System.out.println("Enter weight:");
                    printProductsTable(Laptop.categoryFilter("weight", sc.next()));
                }
                case 8 -> {
                    System.out.println("Enter size:");
                    printProductsTable(Laptop.categoryFilter("size", sc.next()));
                }
                case 9 -> {
                    System.out.println("Enter processor:");
                    printProductsTable(Laptop.categoryFilter("processor", sc.next()));
                }
                case 10 -> {
                    System.out.println("be gaming? Enter true or false:");
                    printProductsTable(Laptop.categoryFilter("isGaming", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("Mobile")) {

            System.out.println("4. memoryCapacity\n5. RAM\n6. OS\n7. weight\n8. size\n9. SIMCards\n10. cameraQuality");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter memoryCapacity:");
                    printProductsTable(Mobile.categoryFilter("memoryCapacity", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter RAM:");
                    printProductsTable(Mobile.categoryFilter("RAM", sc.next()));
                }
                case 6 -> {
                    System.out.println("Enter OS:");
                    printProductsTable(Mobile.categoryFilter("OS", sc.next()));
                }
                case 7 -> {
                    System.out.println("Enter weight:");
                    printProductsTable(Mobile.categoryFilter("weight", sc.next()));
                }
                case 8 -> {
                    System.out.println("Enter size:");
                    printProductsTable(Mobile.categoryFilter("size", sc.next()));
                }
                case 9 -> {
                    System.out.println("Enter SIMCards:");
                    printProductsTable(Mobile.categoryFilter("SIMCards", sc.next()));
                }
                case 10 -> {
                    System.out.println("Enter cameraQuality:");
                    printProductsTable(Mobile.categoryFilter("cameraQuality", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("Food")) {

            System.out.println("4. proDate\n5. expDate");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter proDate:");
                    printProductsTable(Food.categoryFilter("proDate", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter expDate:");
                    printProductsTable(Food.categoryFilter("expDate", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("Clothe")) {

            System.out.println("4. country\n5. material");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter country:");
                    printProductsTable(Clothe.categoryFilter("country", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter material:");
                    printProductsTable(Clothe.categoryFilter("material", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("Shoes")) {

            System.out.println("4. country\n5. material");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter country:");
                    printProductsTable(Shoes.categoryFilter("country", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter material:");
                    printProductsTable(Shoes.categoryFilter("material", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("Refrigerator")) {

            System.out.println("4. energyLabel\n5. guarantee\n6. capacity\n7. kind\n8. freezer");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter energyLabel:");
                    printProductsTable(Refrigerator.categoryFilter("country", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter guarantee:");
                    printProductsTable(Refrigerator.categoryFilter("material", sc.next()));
                }
                case 6 -> {
                    System.out.println("Enter capacity:");
                    printProductsTable(Refrigerator.categoryFilter("capacity", sc.next()));
                }
                case 7 -> {
                    System.out.println("Enter kind:");
                    printProductsTable(Refrigerator.categoryFilter("kind", sc.next()));
                }
                case 8 -> {
                    System.out.println("be with Freezer? Enter true or false:");
                    printProductsTable(Refrigerator.categoryFilter("withFreezer", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("Stove")) {

            System.out.println("4. energyLabel\n5. guarantee\n6. flames\n7. material\n8. oven");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter energyLabel:");
                    printProductsTable(Stove.categoryFilter("energyLabel", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter guarantee:");
                    printProductsTable(Stove.categoryFilter("guarantee", sc.next()));
                }
                case 6 -> {
                    System.out.println("Enter flames:");
                    printProductsTable(Stove.categoryFilter("flames", sc.next()));
                }
                case 7 -> {
                    System.out.println("Enter material:");
                    printProductsTable(Stove.categoryFilter("material", sc.next()));
                }
                case 8 -> {
                    System.out.println("be with Oven? Enter true or false:");
                    printProductsTable(Stove.categoryFilter("withOven", sc.next()));
                }
                default -> System.out.println("Error");
            }

        } else if (category.getName().equals("TV")) {

            System.out.println("4. energyLabel\n5. guarantee\n6. pictureQuality\n7. screenSize");

            switch (sc.nextInt())
            {
                case 4 -> {
                    System.out.println("Enter energyLabel:");
                    printProductsTable(TV.categoryFilter("energyLabel", sc.next()));
                }
                case 5 -> {
                    System.out.println("Enter guarantee:");
                    printProductsTable(TV.categoryFilter("guarantee", sc.next()));
                }
                case 6 -> {
                    System.out.println("Enter pictureQuality:");
                    printProductsTable(TV.categoryFilter("pictureQuality", sc.next()));
                }
                case 7 -> {
                    System.out.println("Enter screenSize:");
                    printProductsTable(TV.categoryFilter("screenSize", sc.next()));
                }
                default -> System.out.println("Error");
            }

        }
    }
}
