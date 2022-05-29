package Accounts.Admin;

import Accounts.Account;
import Accounts.ChangeRequest;
import Accounts.Seller.Seller;
import Products.Category;
import Products.Comment;
import Products.ProductRequest;
import UI.MainMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminPanel {
    static Scanner sc = new Scanner(System.in);

    static AdminManager manager = new AdminManager();

    public static void adminMenu() {

        while (AdminManager.adminModel != null)
        {
            System.out.println("\nWelcome Dear " + AdminManager.adminModel.getFirstName() + " " + AdminManager.adminModel.getLastName());
            System.out.println("--- Admin Panel ---");
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Show All Users");
            System.out.println("3. Remove a User");
            System.out.println("4. 'Add Seller' Requests");
            System.out.println("5. 'Edit User Information' Requests");
            System.out.println("6. 'Add Product' Requests");
            System.out.println("7. 'Change Product Information' Requests");
            System.out.println("8. 'Remove Product' Requests");
            System.out.println("9. Comments");
            System.out.println("10. Categories");
            System.out.println("11. Add a Category");
            System.out.println("12. Back");
            System.out.println("13. Log out");

            int number = sc.nextInt();

            switch (number)
            {
                case 0:
                    System.out.println(AdminManager.adminModel.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number and password:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                    System.out.println("Information edited successfully");
                    break;

                case 2:
                    for (Account acc : manager.getAllUsers())
                        System.out.println(acc.toString());
                    break;

                case 3:
                    System.out.println("Enter username:");
                    if (manager.removeUser(sc.next()))
                        System.out.println("User removed successfully");
                    else
                        System.out.println("User doesn't exit");
                    break;

                case 4:
                    for (Seller request : AdminManager.sellerAddRequests)
                    {
                        System.out.println(request.toString());
                        System.out.println("\nDo you accept this request?\n1. YES\n2. NO");
                        int accept = sc.nextInt();

                        if (accept == 1) {
                            manager.acceptSeller(request);
                            System.out.println("Accepted");

                        } else if (accept == 2) {
                            System.out.println("Unconfirmed");
                        }
                        System.out.println("-----------------------------------");
                    }
                    AdminManager.sellerAddRequests.clear();
                    break;

                case 5:
                    for (ChangeRequest request : AdminManager.editInfoRequests)
                    {
                        System.out.println("-OLD ACCOUNT INFORMATION");
                        System.out.println(request.getOldInfo().toString());

                        System.out.println("||||||||||||||||||||||||");

                        System.out.println("-NEW ACCOUNT INFORMATION");
                        System.out.println(request.getNewInfo().toString());

                        System.out.println("\nDo you accept this request?\n1. YES\n2. NO");
                        int accept = sc.nextInt();

                        if (accept == 1) {
                            manager.acceptChange(request);
                            System.out.println("Accepted");

                        } else if (accept == 2) {
                            System.out.println("Unconfirmed");
                        }
                        System.out.println("-----------------------------------");
                    }
                    AdminManager.editInfoRequests.clear();
                    break;

                case 6:
                    // for error change while iterate
                    ArrayList<ProductRequest> requests = (ArrayList<ProductRequest>) AdminManager.productRequests.clone();
                    for (ProductRequest request : requests)
                    {
                        if (request.isAdd())
                        {
                            System.out.println(request);
                            System.out.println("\nDo you accept this request?\n1. YES\n2. NO");
                            int accept = sc.nextInt();

                            if (accept == 1) {
                                manager.acceptAddProduct(request);
                                System.out.println("Accepted");

                            } else if (accept == 2) {
                                System.out.println("Unconfirmed");
                            }
                            System.out.println("-----------------------------------");
                        }
                    }
                    AdminManager.productRequests.removeIf(ProductRequest::isAdd);
                    break;

                case 7:
                    requests = (ArrayList<ProductRequest>) AdminManager.productRequests.clone();
                    for (ProductRequest request : requests)
                    {
                        if (request.isChange()) {
                            System.out.println(request);
                            System.out.println("\nDo you accept this request?\n1. YES\n2. NO");
                            int accept = sc.nextInt();

                            if (accept == 1) {
                                manager.acceptEditProduct(request);
                                System.out.println("Accepted");

                            } else if (accept == 2) {
                                System.out.println("Unconfirmed");
                            }
                            System.out.println("-----------------------------------");
                        }
                    }
                    AdminManager.productRequests.removeIf(ProductRequest::isChange);
                    break;

                case 8:
                    requests = (ArrayList<ProductRequest>) AdminManager.productRequests.clone();
                    for (ProductRequest request : requests)
                    {
                        if (request.isRemove())
                        {
                            System.out.println(request);
                            System.out.println("\nDo you accept this request?\n1. YES\n2. NO");
                            int accept = sc.nextInt();

                            if (accept == 1) {
                                manager.acceptRemoveProduct(request);
                                System.out.println("Accepted");

                            } else if (accept == 2) {
                                System.out.println("Unconfirmed");
                            }
                            System.out.println("-----------------------------------");
                        }
                    }
                    AdminManager.productRequests.removeIf(ProductRequest::isRemove);
                    break;

                case 9:
                    for (Comment comment : AdminManager.comments)
                    {
                        System.out.println(comment.toString());

                        if (comment.getStat() == Comment.Status.WAITING) {
                            System.out.println("\nDo you accept this comment?\n1. YES\n2. NO");
                            int accept = sc.nextInt();

                            if (accept == 1) {
                                manager.acceptComment(comment);
                                System.out.println("Accepted");

                            } else if (accept == 2) {
                                manager.rejectComment(comment);
                                System.out.println("Unconfirmed");
                            }
                        }
                        System.out.println("-----------------------------------");
                    }
                    break;

                case 10:
                    for (Category category : Category.allCategories)
                        System.out.println(category);
                    break;

                case 11:
                    System.out.println("Enter category's name");
                    new Category(sc.next());
                    break;

                case 12:
                    MainMenu.mainMenu();
                    break;

                case 13:
                    manager.logout();
                    MainMenu.mainMenu();
                    return;

                default:
                    System.out.println("Error");
            }
        }
    }
}