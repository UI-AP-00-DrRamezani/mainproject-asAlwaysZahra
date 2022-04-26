package Accounts.Customer;

import java.util.Scanner;

public class CustomerPanel {
    Scanner sc = new Scanner(System.in);

    Customer customerLoggedIn;
    CustomerManager manager = new CustomerManager();

    public void customerMenu() {

        while (customerLoggedIn != null) {
            System.out.println("--- Customer Panel ---");
            System.out.println("+Credit: " + customerLoggedIn.getCredit());
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Increase Credit");
            System.out.println("x. Cart");
            System.out.println("x. Finalize Cart");
            System.out.println("x. Score Products");
            System.out.println("x. History");
            System.out.println("-1. Log out");

            int number = sc.nextInt();

            switch (number) {

                case -1:
                    manager.logout();
                    customerLoggedIn = null;
                    return;

                case 0:
                    System.out.println(customerLoggedIn.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number and password:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(),
                            sc.next(), sc.next(), sc.next());
                    break;

                case 2:
                    System.out.println("Enter the value:");
                    manager.increaseCredit(sc.nextDouble());
                    break;

                case 3:

                    break;

                default:
                    System.out.println("Error");
            }
        }
    }
}
