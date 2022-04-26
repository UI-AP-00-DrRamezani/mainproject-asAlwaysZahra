package Accounts.Seller;

import java.util.Scanner;

public class SellerPanel {
    Scanner sc = new Scanner(System.in);

    Seller sellerLoggedIn;
    SellerManager manager = new SellerManager();

    public void adminMenu() {

        while (sellerLoggedIn != null) {
            System.out.println("--- Seller Panel ---");
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("x. Add Product");
            System.out.println("x. Remove Product");
            System.out.println("x. Change Product Information");
            System.out.println("x. Your Products");
            System.out.println("x. History");
            System.out.println("-1. Log out");

            int number = sc.nextInt();

            switch (number) {

                case -1:
                    manager.logout();
                    sellerLoggedIn = null;
                    return;

                case 0:
                    System.out.println(sellerLoggedIn.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number, password and company:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(),
                            sc.next(), sc.next(), sc.next(), sc.next());
                    break;

                case 2:

                    break;

                case 3:

                    break;

                default:
                    System.out.println("Error");
            }
        }
    }
}
