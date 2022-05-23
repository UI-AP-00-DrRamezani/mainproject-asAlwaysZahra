package Accounts.Customer;

import Factors.BuyFactor;
import Products.Product;

import java.util.Scanner;

public class CustomerPanel {
    Scanner sc = new Scanner(System.in);

    CustomerManager manager = new CustomerManager();

    public void customerMenu() {

        while (manager.customerModel != null) {
            System.out.println("--- Customer Panel ---");
            System.out.println("+Credit: " + manager.customerModel.getCredit());
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Increase Credit");
            System.out.println("3. Cart");
            System.out.println("4. Finalize Cart");
            System.out.println("5. Score a Product");
            System.out.println("6. History");
            System.out.println("-1. Log out");

            int number = sc.nextInt();

            switch (number) {

                case -1:
                    manager.logout();
                    return;

                case 0:
                    System.out.println(manager.customerModel.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number and password:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(),
                            sc.next(), sc.next(), sc.next());
                    break;

                case 2:
                    System.out.println("Enter the amount:");
                    manager.increaseCredit(sc.nextDouble());
                    break;

                case 3:
                    for (Product p : manager.customerModel.getCart())
                        System.out.println(p.toString());
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
                    System.out.println("\nList of products you have bought:\n");
                    for (BuyFactor factor : manager.customerModel.getHistory())
                        for (Product p : factor.getProducts())
                            System.out.println(p);

                    System.out.println("\nEnter the productID and a score between 1 and 5: ");
                    manager.scoring(sc.nextInt(), sc.nextInt());
                    break;

                case 6:
                    System.out.println("\nHistory\n");
                    for (BuyFactor factor : manager.customerModel.getHistory())
                    {
                        System.out.println(factor);
                        System.out.println("Products:");
                        for (Product p : factor.getProducts())
                            System.out.println(p);
                        System.out.println("-----------------------------------");
                    }
                    break;

                default:
                    System.out.println("Error");
            }
        }
    }
}
