package UI;

import Accounts.Admin.AdminManager;
import Accounts.Admin.AdminPanel;
import Accounts.Customer.CustomerManager;
import Accounts.Customer.CustomerPanel;
import Accounts.Seller.SellerManager;
import Accounts.Seller.SellerPanel;

import java.util.Scanner;

public class ProfileMenu {
    static Scanner sc = new Scanner(System.in);

    static CustomerManager customerManager = new CustomerManager();
    static SellerManager sellerManager = new SellerManager();

    public static void panelMenu()
    {
        // check if any user didn't log in
        if (AdminManager.adminModel == null &&
                CustomerManager.customerModel == null &&
                SellerManager.sellerModel == null)
        {
            System.out.println("1. Sign up");
            System.out.println("2. Log in");

            int n = sc.nextInt();

            if (n == 1)
                signUp();
            else if (n == 2)
                login();

        } else {
            if (AdminManager.adminModel != null)
                AdminPanel.adminMenu();
            else if (CustomerManager.customerModel != null)
                CustomerPanel.customerMenu();
            else if (SellerManager.sellerModel != null)
                SellerPanel.sellerMenu();
        }
    }

    static void login()
    {
        System.out.println("--- Log in ---");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Seller");
        int n = sc.nextInt();
        System.out.println("Enter username and password");
        if (n == 1) {
            if (AdminManager.login(sc.next(), sc.next()))
                AdminPanel.adminMenu();
            else
                System.out.println("Wrong Information");

        } else if (n == 2) {
            if (CustomerManager.login(sc.next(), sc.next()))
                CustomerPanel.customerMenu();
            else
                System.out.println("Wrong Information");

        } else if (n == 3) {
            if (SellerManager.login(sc.next(), sc.next()))
                SellerPanel.sellerMenu();
            else
                System.out.println("Wrong Information");
        }
    }

    static void signUp()
    {
        System.out.println("--- Sign up ---");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println("Enter a username, your first name, last name,\n" +
                    "email, phone number, password and initial credit:");

            if (customerManager.addCustomer(sc.next(), sc.next(), sc.next(), sc.next(),
                    sc.next(), sc.next(), sc.nextDouble()))
                System.out.println("Your account created successfully");
            else
                System.out.println("This username is not available");

        } else if (n == 2) {

            System.out.println("Enter a username, your first name, last name,\n" +
                    "email, phone number, password and company:");

            if (sellerManager.addSeller(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(),
                    sc.next(), sc.next()))
                System.out.println("Your request was submitted");
            else
                System.out.println("This username is not available");
        }
    }
}
