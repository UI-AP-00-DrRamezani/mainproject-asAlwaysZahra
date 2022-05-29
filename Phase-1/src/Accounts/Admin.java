package Accounts;

import UserInterface.Category;
import UserInterface.Menu;

import java.util.Scanner;

public class Admin extends Account {
    Scanner sc = new Scanner(System.in);

    private static Admin admin;

    private Admin(String firstName, String lastName, String email, String phoneNumber) {

        super("admin", firstName, lastName, email, phoneNumber, "admin");
        super.setRole("Admin");
    }

    public static Admin getAdmin(String firstName, String lastName, String email, String phoneNumber) {
        if (admin == null)
            admin = new Admin(firstName, lastName, email, phoneNumber);
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public void changeInfo(String username, String firstName, String lastName,
                           String email, String phoneNumber, String password)
    {
        Admin.getAdmin().setUsername(username);
        Admin.getAdmin().setFirstName(firstName);
        Admin.getAdmin().setLastName(lastName);
        Admin.getAdmin().setEmail(email);
        Admin.getAdmin().setPhoneNumber(phoneNumber);
        Admin.getAdmin().setPassword(password);
    }

    @Override
    public String toString() {
        return "Admin {" +
                "username: " + this.getUsername() + " - " +
                "name: " + this.getFirstName() + " " + this.getLastName() + " - " +
                "email: " + this.getEmail() + " - " +
                "phone number: " + this.getPhoneNumber() + " - " +
                "password: " + this.getPassword() +
                "}" ;
    }

    @Override
    public void showInfo() {
        System.out.println("--- Account Information ---");
        System.out.println("Admin Account");
        System.out.println(Admin.getAdmin().getFirstName() + " " + Admin.getAdmin().getLastName());
        System.out.println(Admin.getAdmin().getPhoneNumber());
        System.out.println(Admin.getAdmin().getEmail());
        System.out.println("---------------------------");
    }

    @Override
    public void menu() {
        while (Menu.checkLogin.loggedIn) {
            System.out.println("\n0. Show account information");
            System.out.println("1. Check sellers requests");
            System.out.println("2. Check change information requests");
            System.out.println("3. Check 'add/remove' product requests");
            System.out.println("4. Check 'change' product information requests");
            System.out.println("5. Check comments");
            System.out.println("6. Delete an account");
            System.out.println("7. Change information");
            System.out.println("8. Show all accounts");
            System.out.println("9. Show all categories");
            System.out.println("10. back");
            System.out.println("11. Log out");

            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    Admin.getAdmin().showInfo();
                    System.out.println("---------------------------");
                    break;
                case 1:
                    AdminPanel.checkSellerRequests();
                    break;
                case 2:
                    AdminPanel.checkChangeRequests();
                    break;
                case 3:
                    AdminPanel.checkProductRequests();
                    break;
                case 4:
                    AdminPanel.changeProductRequests();
                    break;
                case 5:
                    AdminPanel.checkComments();
                    break;
                case 6:
                    sc.nextLine();
                    System.out.print("Enter the username of account which you want to delete: ");
                    String username = sc.nextLine();
                    AdminPanel.deleteAccount(username);
                    break;
                case 7:
                    System.out.println("Enter new user name, first name, last name, email, phone number and password: ");
                    System.out.print("new user name: ");
                    username = sc.next();
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
                    break;
                case 8:
                    AdminPanel.showAllAccounts();
                    break;
                case 9:
                    Category.showAllCategories();
                    break;
                case 10:
                    Menu.firstMenu();
                    break;
                case 11:
                    Menu.checkLogin.loggedIn = false;
                    Menu.checkLogin.setAccount(null);
                    System.out.println("\nLog out successfully\n");
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }
}