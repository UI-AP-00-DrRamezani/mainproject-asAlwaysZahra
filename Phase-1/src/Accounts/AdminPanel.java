package Accounts;

import Enums.Status;
import UserInterface.Comment;
import Products.Digital.*;
import Products.Food.Food;
import Products.Garment.*;
import Products.Home.*;
import Products.Product;
import Requests.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminPanel {
    private static Scanner sc = new Scanner(System.in);

    public static final ArrayList<SellerRequest> sellersRequests = new ArrayList<>();
    public static final ArrayList<ChangeRequest> changeRequests = new ArrayList<>();
    public static final ArrayList<ProductRequest> productRequests = new ArrayList<>();
    public static final ArrayList<Comment> allComments = new ArrayList<>();
    public static final ArrayList<ChangeProductRequest> changeProductRequests = new ArrayList<>();

    static void confirmSeller (SellerRequest request) {
        System.out.println("Do you confirm this request?");
        System.out.println("1. yes\n2. no");
        int choice = sc.nextInt();

        if (choice == 1) {
            new Seller(request.getUsername(), request.getFirstName(), request.getLastName(),
                    request.getEmail(), request.getPhoneNumber(), request.getPassword(), request.getCompany());
            request.setStat(Status.CONFIRMED);
            System.out.println("Request was confirmed");
        } else {
            request.setStat(Status.UNCONFIRMED);
            System.out.println("Request was not confirmed");
        }
    }

    static void confirmChange(ChangeRequest request) {

        System.out.println("Do you confirm this request?");
        System.out.println("1. yes\n2. no");
        int choice = sc.nextInt();

        if (choice == 1) {

            Account old = request.getOldAccount();

            if (old instanceof Admin) {

                Admin neww = (Admin) request.getNewAccount();

                ((Admin) old).changeInfo(neww.getUsername(), neww.getFirstName(), neww.getLastName(),
                        neww.getEmail(), neww.getPhoneNumber(), neww.getPassword());

            } else if (old instanceof Seller) {

                Seller neww = (Seller) request.getNewAccount();

                ((Seller) old).changeInfo(neww.getUsername(), neww.getFirstName(), neww.getLastName(),
                        neww.getEmail(), neww.getPhoneNumber(), neww.getPassword(), neww.getCompany());

            } else if (old instanceof Buyer) {

                Buyer neww = (Buyer) request.getNewAccount();

                ((Buyer) old).changeInfo(neww.getUsername(), neww.getFirstName(), neww.getLastName(),
                        neww.getEmail(), neww.getPhoneNumber(), neww.getPassword());

            }

            request.setStat(Status.CONFIRMED);
            System.out.println("\nRequest was confirmed");

        } else if (choice == 2) {

            request.setStat(Status.UNCONFIRMED);
            System.out.println("\nRequest was not confirmed");
        }
    }

    static void confirmProduct(ProductRequest request) {
        System.out.println("Do you confirm this request?");
        System.out.println("1. yes\n2. no");
        int choice = sc.nextInt();

        if (choice == 1) {

            if (request.isAdd()) {
                //Product.allProducts.add()
                request.getSeller().getSaleProducts().add(request.getProduct());
                request.getProduct().setSeller(request.getSeller());

            } else if (request.isRemove()) {
                //Product.allProducts.remove()
                request.getSeller().getSaleProducts().remove(request.getProduct());
                request.getProduct().setSeller(null);

            } else {
                System.out.println("\nError in request type\n");
                return;
            }

            request.setStat(Status.CONFIRMED);
            System.out.println("\nRequest was confirmed");

        } else if (choice == 2) {
            request.setStat(Status.UNCONFIRMED);
            System.out.println("\nRequest was not confirmed");
        }
    }

    static void confirmChangeProduct(ChangeProductRequest request) {

        System.out.println("Do you confirm this request?");
        System.out.println("1. yes\n2. no");
        int choice = sc.nextInt();

        if (choice == 1) {

            Product old = request.getProductToChange();

            if (old instanceof Laptop) {

                Laptop neww = (Laptop) request.getNewProduct();

                ((Laptop) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getMemoryCapacity(), neww.getRAM(), neww.getOS(), neww.getWeight(), neww.getSize(),
                        neww.getProcessor(), neww.isGaming());

            } else if (old instanceof Mobile) {

                Mobile neww = (Mobile) request.getNewProduct();

                ((Mobile) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getMemoryCapacity(), neww.getRAM(), neww.getOS(), neww.getWeight(), neww.getSize(),
                        neww.getSIMCards(), neww.getCameraQuality());

            } else if (old instanceof Food) {

                Food neww = (Food) request.getNewProduct();

                ((Food) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription());

            } else if (old instanceof Clothe) {

                Clothe neww = (Clothe) request.getNewProduct();

                ((Clothe) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getCountry(), neww.getMaterial(), neww.getSize());

            } else if (old instanceof Shoes) {

                Shoes neww = (Shoes) request.getNewProduct();

                ((Shoes) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getCountry(), neww.getMaterial(), neww.getSize());

            } else if (old instanceof Refrigerator) {

                Refrigerator neww = (Refrigerator) request.getNewProduct();

                neww.changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getEnergyLabel(), neww.isGuarantee(), neww.getCapacity(), neww.getKind(),
                        neww.isWithFreezer());

            } else if (old instanceof Stove) {

                Stove neww = (Stove) request.getNewProduct();

                ((Stove) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getEnergyLabel(), neww.isGuarantee(), neww.getFlames(), neww.getMaterial(),
                        neww.isWithOven());

            } else if (old instanceof TV) {

                TV neww = (TV) request.getNewProduct();

                ((TV) old).changeInfo(neww.getName(), neww.getBrand(), neww.getPrice(), neww.getDescription(),
                        neww.getEnergyLabel(), neww.isGuarantee(), neww.getPictureQuality(), neww.getScreenSize());

            }

            request.setStat(Status.CONFIRMED);
            System.out.println("\nRequest was confirmed");

        } else if (choice == 2) {

            request.setStat(Status.UNCONFIRMED);
            System.out.println("\nRequest was not confirmed");
        }
    }

    static void checkSellerRequests() {
        System.out.println("--- REQUESTS ---");
        for (SellerRequest request: sellersRequests) {
            System.out.println("id: " + request.getId());
            System.out.println(request.getFirstName() + " " + request.getLastName());
            System.out.println("Company: " + request.getCompany());
            System.out.println(request.getPhoneNumber() + " - " + request.getEmail());
            System.out.println("username: " + request.getUsername() + " - password: " + request.getPassword());
            System.out.println(request.getStat());
            System.out.println("-----------------------------------");
        }

        System.out.print("Select the request id to check: ");
        int id = sc.nextInt();

        for (SellerRequest request: sellersRequests) {
            if (id == request.getId()) {
                if (request.getStat() == Status.WAITING)
                    confirmSeller(request);
                if (request.getStat() == Status.UNCONFIRMED)
                    System.out.println("UNCONFIRMED!");
                if (request.getStat() == Status.CONFIRMED)
                    System.out.println("CONFIRMED");
                System.out.println("-----------------------------------");
            }
        }
    }

    static void checkChangeRequests() {

        System.out.println("--- REQUESTS ---");

        for (ChangeRequest request: changeRequests) {
            System.out.println("CHANGE ACCOUNT INFORMATION");
            System.out.println("id: " + request.getId());
            request.printRequest();

            System.out.print("Select the request id to check: ");
            int id = sc.nextInt();

            for (ChangeRequest changeRequest: changeRequests) {

                if (id == changeRequest.getId()) {

                    if (changeRequest.getStat() == Status.WAITING)
                        confirmChange(changeRequest);

                    if (changeRequest.getStat() == Status.UNCONFIRMED)
                        System.out.println("UNCONFIRMED");

                    if (changeRequest.getStat() == Status.CONFIRMED)
                        System.out.println("CONFIRMED");

                    System.out.println("-----------------------------------");
                    break;
                }
            }
        }

        System.out.print("Select the request id to check: ");
        int id = sc.nextInt();

        for (ProductRequest request: productRequests) {

            if (id == request.getId()) {

                if (request.getStat() == Status.WAITING)
                    confirmProduct(request);
                if (request.getStat() == Status.UNCONFIRMED)
                    System.out.println("UNCONFIRMED");
                if (request.getStat() == Status.CONFIRMED)
                    System.out.println("CONFIRMED");
                System.out.println("-----------------------------------");
            }
        }
    }

    static void checkComments() {
        System.out.println("--- Comments ---");
        for (Comment comment: allComments) {
            // show comment if its status is waiting
            if (comment.getStat() == Status.WAITING) {
                System.out.println("User: " + comment.getUser().getUsername());
                System.out.println("Product: " + comment.getProduct().getId() + " - " + comment.getProduct().getName());
                System.out.println("User has bought the product: " + comment.getDidBuy());
                System.out.println("Comment: " + comment.getText());
                System.out.println("--------------------------------");
                // confirm
                System.out.println("\nDo you confirm this comment?");
                System.out.println("1. yes\n2. no");
                int choice = sc.nextInt();

                if (choice == 1) {
                    comment.getProduct().addComment(comment);
                    comment.setStat(Status.CONFIRMED);
                    System.out.println("Comment was confirmed");
                } else {
                    comment.setStat(Status.UNCONFIRMED);
                    System.out.println("Comment was not confirmed");
                }
            }
        }
    }

    static void checkProductRequests() {

        System.out.println("--- REQUESTS ---");

        for (ProductRequest request: productRequests) {
            System.out.println("id: " + request.getId());
            if(request.isAdd()) {
                System.out.println("ADD PRODUCT");
                request.printRequest();
            } else if (request.isRemove()) {
                System.out.println("REMOVE PRODUCT");
                request.printRequest();
            } else {
                System.out.println("Error");
                return;
            }
        }

        System.out.print("Select the request id to check: ");
        int id = sc.nextInt();

        for (ProductRequest request: productRequests) {

            if (id == request.getId()) {

                if (request.getStat() == Status.WAITING)
                    confirmProduct(request);
                if (request.getStat() == Status.UNCONFIRMED)
                    System.out.println("UNCONFIRMED");
                if (request.getStat() == Status.CONFIRMED)
                    System.out.println("CONFIRMED");
                System.out.println("-----------------------------------");
                break;
            }
        }
    }

    static void changeProductRequests() {

        System.out.println("--- REQUESTS ---");

        for (ChangeProductRequest request: changeProductRequests) {
            System.out.println("CHANGE PRODUCT");
            System.out.println("id: " + request.getId());
            request.printRequest();

            System.out.print("Select the request id to check: ");
            int id = sc.nextInt();

            for (ChangeProductRequest changeRequest: changeProductRequests) {

                if (id == changeRequest.getId()) {

                    if (changeRequest.getStat() == Status.WAITING)
                        confirmChangeProduct(changeRequest);
                    if (changeRequest.getStat() == Status.UNCONFIRMED)
                        System.out.println("UNCONFIRMED");
                    if (changeRequest.getStat() == Status.CONFIRMED)
                        System.out.println("CONFIRMED");
                    System.out.println("-----------------------------------");
                    break;
                }
            }
        }

        System.out.print("Select the request id to check: ");
        int id = sc.nextInt();

        for (ProductRequest request: productRequests) {

            if (id == request.getId()) {

                if (request.getStat() == Status.WAITING)
                    confirmProduct(request);
                if (request.getStat() == Status.UNCONFIRMED)
                    System.out.println("UNCONFIRMED");
                if (request.getStat() == Status.CONFIRMED)
                    System.out.println("CONFIRMED");
                System.out.println("-----------------------------------");
            }
        }
    }

    public static void showAllAccounts() {
        for (Account account: Account.allAccounts) {
            System.out.println(account.getRole() + " - " + account.getFirstName() + " " + account.getLastName());
        }
        System.out.println("---------------------------");
    }

    static void deleteAccount(String username) {
        boolean findUser = false;
        if(username.compareTo("admin") == 0) {
            System.out.println("Admin account can not be deleted!");
            return;
        } else {
            for (Account acc : Account.allAccounts) {
                if (acc.getUsername().compareTo(username) == 0) {
                    findUser = true;
                    Account.allAccounts.remove(acc);
                    System.gc();
                    System.out.println("Account deleted!");
                    return;
                }
            }
            if (!findUser)
                System.out.println("Username doesnt exist.");
        }
    }
}