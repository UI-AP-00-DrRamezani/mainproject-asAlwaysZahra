package UserInterface;

import Accounts.*;
import Enums.Status;
import Products.Product;
import Requests.SellerRequest;

import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);

    public static Login checkLogin = new Login();

    private Menu() {}

    public static void firstMenu() {
        while (true) {
            System.out.println("\n--- First Menu ---");
            System.out.println("Choose one:");
            System.out.println("1. Profile");
            System.out.println("2. Products");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    profileMenu();
                    break;
                case 2:
                    Category.showAllCategories();
                    System.out.print("Select the category: ");
                    String category = sc.next();
                    for (Category c: Category.allCategories)
                        if (c.getName().compareTo(category) == 0) {
                            productMenu(c);
                            break;
                        }
                    break;
                case 3:
                    System.out.println("Have a nice day, Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error\n");
            }
        }
    }

    static void profileMenu() {
        if (checkLogin.loggedIn) {
            checkLogin.getAccount().showInfo();
            checkLogin.getAccount().menu();
        } else {
            System.out.println("\n--- Profile Menu---");
            System.out.println("Choose one:");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Back");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    sc.nextLine();
                    String user = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    Login.login(user, pass);
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    firstMenu();
                    break;
                default:
                    System.out.println("Error");
                    profileMenu();
            }
        }
    }

    static void productMenu(Category category) {
        while (true) {
            category.printProducts();
            System.out.println("1. filter\t2. search\t3. see in details\t4. add to cart\t5. back");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    filter(category);
                    break;
                case 2:
                    search(category);
                    break;
                case 3:
                    System.out.print("Enter the ID of product: ");
                    int id = sc.nextInt();
                    boolean find = false;
                    for (Product p : Product.allProducts)

                        if (p.getId() == id) {
                            p.showProduct();
                            find = true;

                            System.out.println("1. add to cart\t2. comments\t3. add a comment\t4. back");
                            choice = sc.nextInt();
                            if (choice == 1) {

                                if (checkLogin.loggedIn) {
                                    ((Buyer) checkLogin.getAccount()).addToCart(p);
                                    break;
                                } else {
                                    System.out.println("\nPlease log in first");
                                    profileMenu();
                                }

                            } else if (choice == 2) {

                                printComments(p);

                            } else if (choice == 3) {

                                if (checkLogin.loggedIn)
                                    addComment(p, checkLogin.getAccount());
                                else {
                                    System.out.println("\nPlease log in first.");
                                    Menu.profileMenu();
                                }

                            }
                            break;
                        }
                    if (!find)
                        System.out.println("\nThis product ID doesnt exit.");
                    break;
                case 4:
                    if (checkLogin.loggedIn) {
                        System.out.print("Enter the ID of product: ");
                        id = sc.nextInt();
                        find = false;
                        for (Product p : Product.allProducts) {
                            if (p.getId() == id) {
                                ((Buyer) (checkLogin.getAccount())).addToCart(p);
                                find = true;
                                break;
                            }
                        }
                        if (!find)
                            System.out.println("\nThis product ID doesnt exit.");
                    } else {
                        System.out.println("\nPlease log in first");
                        profileMenu();
                    }
                case 5:
                    firstMenu();
                    break;
            }
        }
    }

    static void register() {
        System.out.println("\nAccount type:");
        System.out.println("1. Buyer");
        System.out.println("2. Seller");
        int type = sc.nextInt();

        System.out.println("Please enter the requested information");

        System.out.printf("First name: ");
        sc.nextLine();
        String fname = sc.nextLine();

        System.out.printf("Last name: ");
        String lname = sc.nextLine();

        System.out.printf("E-mail: ");
        String email = sc.nextLine();

        System.out.printf("Phone number: ");
        String phoneNumber = sc.nextLine();

        String user;
        do {
            System.out.printf("Username: ");
            user = sc.nextLine();
        } while (!availableUsername(user));

        System.out.printf("Password: ");
        String pass = sc.nextLine();

        double credit = 0;
        if(type == 1) {
            System.out.print("Initial credit: ");
            credit = sc.nextDouble();
        }
        String company = "";
        if(type == 2) {
            System.out.print("Company: ");
            company = sc.nextLine();
        }

        System.out.println(fname+"-"+lname+"-"+email+"-"+phoneNumber+"-"+user+"-"+pass+"-"+company+"-"+credit);

        if(type == 1) {
            new Buyer(user, fname, lname, email, phoneNumber, pass, credit);
            System.out.println("Your account is created successfully!");
        } else if(type == 2) {
            new SellerRequest(user, fname, lname, email, phoneNumber, pass, company);
            System.out.println("Your account is waiting for confirmation...");
        }
    }

    static boolean availableUsername(String username) {
        for (Account acc: Account.allAccounts)
            if (acc.getUsername().compareTo(username) == 0) {
                System.out.println(username + " is not available, please try another username.");
                return false;
            }
        return true;
    }

    static void search(Category category) {
        System.out.print("Enter product's name: ");
        String productName = sc.next();
        String format = "| %-3d | %-13s | %-8s | %-9s | %-8s | %-4s |%n";
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.format("| ID  | Product Name  | Brand    | Seller    | Price    | Rate |%n");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        for (Product p : category.getProducts()) {
            if(p.getName().compareTo(productName) == 0)
                System.out.format(format, p.getId(), p.getName(), p.getBrand(),
                        p.getSeller().getUsername(), "$"+p.getPrice(), p.getAvgRate()+"⭐");
        }
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.println("1. back");
        int choice = sc.nextInt();
        return;
    }

    static void filter(Category category) {
        System.out.println("select the feature you want to filter products by:");
        System.out.println("1. Price");
        System.out.println("2. Available goods");
        System.out.println("3. Brand");
        int filter = sc.nextInt();
        switch (filter) {
            case 1:
                System.out.println("Enter limits: ");
                System.out.print("low limit: ");
                double low = sc.nextDouble();
                System.out.print("high limit: ");
                double high = sc.nextDouble();
                System.out.println("");
                filterByPrice(low, high, category);
                break;
            case 2:
                filterByAvailability(category);
                break;
            case 3:
                System.out.print("Enter the brand: ");
                String brand = sc.next();
                filterByBrand(brand, category);
                break;
            default:
                System.out.println("Error in filter");
        }
    }

    static void filterByPrice(double low, double high, Category category) {
        String format = "| %-3d | %-13s | %-8s | %-9s | %-8s | %-4s |%n";
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.format("| ID  | Product Name  | Brand    | Seller    | Price    | Rate |%n");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        for (Product p : category.getProducts()) {
            if(p.getPrice() >= low && p.getPrice() <= high) {
                System.out.format(format, p.getId(), p.getName(), p.getBrand(),
                        p.getSeller().getUsername(), "$"+p.getPrice(), p.getAvgRate()+"⭐");
            }
        }
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.println("1. back");
        int choice = sc.nextInt();
        return;
    }

    static void filterByAvailability(Category category) {
        String format = "| %-3d | %-13s | %-8s | %-9s | %-8s | %-4s |%n";
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.format("| ID  | Product Name  | Brand    | Seller    | Price    | Rate |%n");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        for (Product p : category.getProducts())
            if(p.getNumber() > 0)
                System.out.format(format, p.getId(), p.getName(), p.getBrand(),
                        p.getSeller().getUsername(), "$"+p.getPrice(), p.getAvgRate()+"⭐");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.println("1. back");
        int choice = sc.nextInt();
        return;
    }

    static void filterByBrand(String brand, Category category) {
        String format = "| %-3d | %-13s | %-8s | %-9s | %-8s | %-4s |%n";
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.format("| ID  | Product Name  | Brand    | Seller    | Price    | Rate |%n");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        for (Product p : category.getProducts())
            if(brand == p.getBrand())
                System.out.format(format, p.getId(), p.getName(), p.getBrand(),
                        p.getSeller().getUsername(), "$"+p.getPrice(), p.getAvgRate()+"⭐");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.println("1. back");
        int choice = sc.nextInt();
        return;
    }

    static void addComment(Product product, Account account) {
        sc.nextLine();
        System.out.print("Comment text: ");
        String text = sc.nextLine();
        System.out.println("Did you buy this product? 1. yes\t2. no");
        int buy = sc.nextInt();
        if (buy == 1)
            new Comment(account, product, text, true);
        else if (buy == 2)
            new Comment(account, product, text, false);
        System.out.println("\nYour comment submitted.");
    }

    static void printComments(Product product) {
        System.out.println("Comments: ");
        for (Comment comment: product.getComments())
            if (comment.getStat() == Status.CONFIRMED) {
                comment.printComment();
                System.out.println("-----------------------------");
            }
    }
}