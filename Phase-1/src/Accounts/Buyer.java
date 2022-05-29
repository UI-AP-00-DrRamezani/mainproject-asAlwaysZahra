package Accounts;

import Factors.BuyFactor;
import Factors.SaleFactor;
import Products.Product;
import Requests.ChangeRequest;
import UserInterface.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Buyer extends Account {
    private static Scanner sc = new Scanner(System.in);
    public static final ArrayList<Buyer> allCustomers = new ArrayList<>();

    private double credit;
    private final ArrayList<Product> cart = new ArrayList<>();
    private final ArrayList<BuyFactor> history = new ArrayList<>();

    public Buyer(String username,
                 String firstName,
                 String lastName,
                 String email,
                 String phoneNumber,
                 String password,
                 double credit) {
        super(username, firstName, lastName, email, phoneNumber, password);
        super.setRole("Buyer");
        this.credit = credit;
        allCustomers.add(this);
    }

    public void addToCart(Product product) {
        if (product.getNumber() > 0) {
            this.cart.add(product);
            System.out.println("\nAdded to cart successfully"); // better off dont print ?
        } else
            System.out.println("\nThis product is not available. Please try again later.");
    }

    void showCart() {
        double sum = 0;
        System.out.println("\nProducts: ");
        for (Product product : this.cart) {
            System.out.println(product.getId() + " - " + product.getName() + " - " + "$"+product.getPrice());
            sum += product.getPrice();
        }
        System.out.println("\nSum = $" + sum);
    }

    void buy() {
        this.showCart();
        System.out.println("\nPlease wait ...");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("---------------------------");
        double sum = 0;
        for (Product product : this.cart)
            sum += product.getPrice();

        if (this.getCredit() >= sum) {
            // add sale factors to the sellers' histories
            for (Product product : this.cart)
                product.getSeller().getHistory().add(new SaleFactor(product.getPrice(), product, this.getUsername()));

            // add buy factor to the buyer's history
            BuyFactor factor = new BuyFactor(sum, this.cart);
            for (Product p : this.cart) {
                factor.addSellerName(p.getSeller().getUsername());
                p.setNumber(p.getNumber()-1);
            }
            this.history.add(factor);

            // empty cart
            cart.clear();

            this.setCredit(this.getCredit() - sum);
            System.out.println("Payment is done successfully!");
            System.out.println("Your products will be sent soon.");
        } else
            System.out.println("Your credit is not enough,\nplease charge your account first");
    }

    void scoring() {
        for (BuyFactor factor : this.history) {
            for (Product product : factor.getProducts()) {
                System.out.println("for product: " + product.getId() + " - " + product.getName());
                System.out.println("Seller: " + product.getSeller().getUsername());
                System.out.print("inter your score between 1 to 5: ");
                double score = sc.nextDouble();
                product.addScore(score);
                product.calculateAverageRate();
                System.out.println("------------------------------");
            }
        }
    }

    void showHistory() {
        for (BuyFactor factor: this.history)
            factor.printFactor();
    }

    public void changeInfo(String username, String firstName, String lastName,
                           String email, String phoneNumber, String password) {
        this.setUsername(username);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setPassword(password);
    }

    @Override
    public void showInfo() {
        System.out.println("--- Account Information ---");
        System.out.println("Customer Account");
        System.out.println(this.getFirstName() + " " + this.getLastName());
        System.out.println("Username: " + this.getUsername());
        System.out.println("Phone number: " + this.getPhoneNumber());
        System.out.println("E-mail: " + this.getEmail());
        System.out.println("Credit: $" + this.credit);
        System.out.println("---------------------------");
    }

    @Override
    public void menu() {
        while (Menu.checkLogin.loggedIn) {
            System.out.println("\n0. Show account information");
            System.out.println("1. Change information");
            System.out.println("2. Score products");
            System.out.println("3. Show cart");
            System.out.println("4. Buy");
            System.out.println("5. Show history");
            System.out.println("6. Back");
            System.out.println("7. Log out");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    showInfo();
                    break;
                case 1:
                    System.out.println("Enter new user name, first name, last name, email, phone number and password: ");
                    System.out.print("new user name: ");
                    String username = sc.next();
                    System.out.print("new first name: ");
                    String firstName = sc.next();
                    System.out.print("new last name: ");
                    String lastName = sc.next();
                    System.out.print("new email: ");
                    String email = sc.next();
                    System.out.print("new phone number: ");
                    String phoneNumber = sc.next();
                    System.out.print("new password: ");
                    String password = sc.next();

                    new ChangeRequest(this,
                            new Buyer(username, firstName, lastName, email, phoneNumber, password, 0));

                    System.out.println("Your request for changing information submitted,\n" +
                            "please wait for admin confirmation");

                    break;
                case 2:
                    scoring();
                    break;
                case 3:
                    showCart();
                    break;
                case 4:
                    buy();
                    break;
                case 5:
                    showHistory();
                    break;
                case 6:
                    Menu.firstMenu();
                    break;
                case 7:
                    Menu.checkLogin.setAccount(null);
                    Menu.checkLogin.loggedIn = false;
                    System.out.println("\nLog out successfully\n");
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    // Setters and Getters

    public ArrayList<Product> getCart() {
        return this.cart;
    }

    public void addToHistory(BuyFactor factor) {
        this.history.add(factor);
    }

    public ArrayList<BuyFactor> getHistory() {
        return getHistory();
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}