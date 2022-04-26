package Accounts.Admin;

import Accounts.Account;
import Accounts.ChangeRequest;
import Accounts.Seller.Seller;

import java.util.Scanner;

public class AdminPanel {
    Scanner sc = new Scanner(System.in);

    Admin adminLoggedIn;
    AdminManager manager = new AdminManager();

    public void adminMenu() {

        while (adminLoggedIn != null) {
            System.out.println("--- Admin Panel ---");
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Show All Users");
            System.out.println("3. Remove a User");
            System.out.println("4. 'Add Seller' Requests");
            System.out.println("5. 'Edit Seller Information' Requests");
            System.out.println("x. 'Add Product' Requests");
            System.out.println("x. 'Change Product Information' Requests");
            System.out.println("x. Comments");
            System.out.println("x. Categories");
            System.out.println("x. Add a Category");
            System.out.println("-1. Log out");

            int number = sc.nextInt();

            switch (number) {

                case -1:
                    manager.logout();
                    adminLoggedIn = null;
                    return;

                case 0:
                    System.out.println(adminLoggedIn.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number and password:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                    break;

                case 2:
                    for (Account acc : manager.getAllUsers())
                        System.out.println(acc.toString());
                    break;

                case 3:
                    System.out.println("Enter username:");
                    manager.removeUser(sc.next());
                    break;

                case 4:
                    for (Seller request : AdminManager.sellerAddRequests) {
                        System.out.println(request.toString());
                        System.out.println("Do you accept this request?\n1. YES\n2. NO");
                        int accept = sc.nextInt();
                        if (accept == 1) {
                            manager.acceptSeller(request);
                            System.out.println("Accepted");
                        } else if (accept == 2) {
                            AdminManager.sellerAddRequests.remove(request);
                            System.out.println("Unconfirmed");
                        }
                        System.out.println("-----------------------------------");
                    }
                    break;

                case 5:
                    for (ChangeRequest request : AdminManager.editInfoRequests) {
                        System.out.println("OLD ACCOUNT INFORMATION");
                        System.out.println(request.getOldInfo().toString());

                        System.out.println("|||||||||||||||||||||||||||||||||||");

                        System.out.println("NEW ACCOUNT INFORMATION");
                        System.out.println(request.getNewInfo().toString());

                        System.out.println("Do you accept this request?\n1. YES\n2. NO");
                        int accept = sc.nextInt();
                        if (accept == 1) {
                            manager.acceptChange(request);
                            System.out.println("Accepted");
                        } else if (accept == 2) {
                            AdminManager.sellerAddRequests.remove(request);
                            System.out.println("Unconfirmed");
                        }
                        System.out.println("-----------------------------------");
                    }
                    break;

                default:
                    System.out.println("Error");
            }
        }
    }
}