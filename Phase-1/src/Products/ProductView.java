package Products;

import Accounts.Account;
import Accounts.Admin.AdminManager;
import Accounts.Customer.Customer;
import Accounts.Customer.CustomerManager;
import Accounts.Seller.SellerManager;
import UI.*;

import java.util.ArrayList;
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

        System.out.println("\n1. Filter\t2. Search\t3. See in Details\t4. Add to Cart\t5. Back");

        while (true)
        {
            switch (sc.nextInt())
            {
                //all of these methods are in this class

                case 1 -> filter(c);

                case 2 -> search(c);

                case 3 -> seeInDetail();

                case 4 -> addToCart();

                case 5 -> MainMenu.mainMenu();
            }
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
        System.out.println("1. Price\n2. Available goods\n3. Brand");

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

            default:
                System.out.println("Error");
        }
    }
    // -----------------------------------------------------------------------------
    static void search (Category category) {

        System.out.print("Enter product's name: ");
        String productName = sc.next();

        printProductsTable(ProductManager.search(productName, category));

        System.out.println("1. back");
        int choice = sc.nextInt();
    }
    // -----------------------------------------------------------------------------
    static void printProductsTable(ArrayList<Product> list) {

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
        System.out.println("\nDid you buy this product?\t1. yes\t2. no");
        int buy = sc.nextInt();

        if (buy == 1)
            new Comment(account, product, text, true);
        else if (buy == 2)
            new Comment(account, product, text, false);

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
                ProfileMenu.panelMenu();
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
                ProfileMenu.panelMenu();
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
                else
                    CustomerManager.addToCart(id); //todo same id -> product
            }
            else
                System.out.println("\nThis product ID doesnt exit.");

        } else {
            System.out.println("\nPlease log in first");
            ProfileMenu.panelMenu();
        }
    }
}
