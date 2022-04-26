package Accounts.Admin;

import Accounts.Account;

import java.util.Scanner;

public class AdminPanel {
    Scanner sc = new Scanner(System.in);

    Admin adminLoggedIn;
    AdminManager manager = new AdminManager();

    public void adminMenu() {
        while (adminLoggedIn != null) {
            System.out.println("--- Admin Panel ---");
            System.out.println("0. Show Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Show All Users");
            System.out.println("3. Remove a User");
            System.out.println("x. 'Add Seller' Requests");
            System.out.println("x. 'Edit Seller Information' Requests");
            System.out.println("x. 'Add Product' Requests");
            System.out.println("x. 'Change Product Information' Requests");
            System.out.println("x. Comments");
            System.out.println("x. Categories");
            System.out.println("x. Add a Category");
            System.out.println("-1. Log out");
        }

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
                for (Account acc: manager.getAllUsers())
                    System.out.println(acc.toString());
                break;

            case 3:
                System.out.println("Enter username:");
                manager.removeUser(sc.next());
                break;

            default:
                System.out.println("Error");
        }
    }
}