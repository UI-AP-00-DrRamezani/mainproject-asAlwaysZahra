package Accounts.Customer;

import Factors.BuyFactor;
import Products.Product.Product;
import UI.MainMenu;

import java.util.Scanner;

public class CustomerPanel {
    static Scanner sc = new Scanner(System.in);

    static CustomerManager manager = new CustomerManager();

    public static void customerMenu()
    {
        while (CustomerManager.customerModel != null)
        {
            System.out.println("\nWelcome Dear " + CustomerManager.customerModel.getFirstName() + " " + CustomerManager.customerModel.getLastName());
            System.out.println("--- Customer Panel ---");
            System.out.println("+Credit: " + CustomerManager.customerModel.getCredit());
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Increase Credit");
            System.out.println("3. Cart");
            System.out.println("4. Finalize Cart");
            System.out.println("5. Remove a Product From The Cart");
            System.out.println("6. Score a Product");
            System.out.println("7. History");
            System.out.println("8. Back");
            System.out.println("9. Log out");

            int number = sc.nextInt();

            switch (number) {
                case 0:
                    System.out.println(CustomerManager.customerModel.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number and password:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(),
                            sc.next(), sc.next(), sc.next());
                    System.out.println("""
                            \nYour request for adding a product was submitted,
                            please wait for admin confirmation.""");
                    break;

                case 2:
                    System.out.println("Enter the amount:");
                    manager.increaseCredit(sc.nextDouble());
                    break;

                case 3:
                    if (CustomerManager.customerModel.getCart().isEmpty()) {
                        System.out.println("Cart is Empty");

                    } else {
                        for (Product p : CustomerManager.customerModel.getCart())
                            System.out.println(p.toString());
                    }

                    break;

                case 4:
                    if (manager.buy())
                        System.out.println("Payment is done successfully!\n" +
                                "Your products will be sent soon.");
                    else
                        System.out.println("Your credit is not enough to pay,\n" +
                                "please charge your account first");
                    break;

                case 5:
                    System.out.println("Enter productID:");
                    if (manager.removeFromCart(sc.nextInt()))
                        System.out.println("Removed");
                    else
                        System.out.println("You do not have this product in the cart");
                    break;

                case 6:
                    System.out.println("\nList of products you have bought:\n");
                    for (BuyFactor factor : CustomerManager.customerModel.getHistory())
                        for (Product p : factor.getProducts())
                            System.out.println(p);

                    System.out.println("\nEnter the productID and a score between 1 and 5: ");

                    if (manager.scoring(sc.nextInt(), sc.nextInt()))
                        System.out.println("OK");
                    else
                        System.out.println("You did not buy this product");
                    break;

                case 7:
                    System.out.println("\nHistory\n");
                    for (BuyFactor factor : CustomerManager.customerModel.getHistory())
                    {
                        System.out.println(factor);
                        System.out.println("Products:");
                        for (Product p : factor.getProducts())
                            System.out.println(p);
                        System.out.println("-----------------------------------");
                    }
                    break;

                case 8:
                    MainMenu.mainMenu();
                    break;

                case 9:
                    manager.logout();
                    MainMenu.mainMenu();
                    return;

                default:
                    System.out.println("Error");
            }
        }
    }
}
