package Accounts;

import Enums.*;
import Factors.SaleFactor;
import Products.*;
import Products.Digital.*;
import Products.Food.Food;
import Products.Garment.*;
import Products.Home.*;
import UserInterface.*;
import Requests.ChangeProductRequest;
import Requests.ChangeRequest;
import Requests.ProductRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class Seller extends Account {
    private Scanner sc = new Scanner(System.in);
    public static final ArrayList<Seller> allSellers = new ArrayList<>();

    private String company;
    private final ArrayList<SaleFactor> history = new ArrayList<>();
    private final ArrayList<Product> saleProducts = new ArrayList<>();

    Seller(String username,
           String firstName,
           String lastName,
           String email,
           String phoneNumber,
           String password,
           String company)
    {
        super(username, firstName, lastName, email, phoneNumber, password);
        super.setRole("Seller");
        this.company = company;

        allSellers.add(this);
    }

    void addProduct() {
//        this.saleProducts.add(product);
        System.out.println("Please enter the requested information for the new product");

        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Brand: ");
        String brand = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        sc.nextLine();
        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.println(name+"-"+brand+"-"+price+"-"+description);

        System.out.println("In which category your product will be?");
        System.out.println("-Digital products");
        System.out.println("\t1. Laptop");
        System.out.println("\t2. Mobile");
        System.out.println("-Garment");
        System.out.println("\t3. Shoes");
        System.out.println("\t4. Clothe");
        System.out.println("-Home things");
        System.out.println("\t5. Stove");
        System.out.println("\t6. TV");
        System.out.println("\t7. Refrigerator");
        System.out.println("8. Food");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Memory capacity: ");
                int memory = sc.nextInt();
                System.out.print("RAM: ");
                int RAM = sc.nextInt();
                sc.nextLine();
                System.out.print("Operating system: ");
                String os = sc.nextLine();
                System.out.print("Weight: ");
                double weight = sc.nextDouble();
                System.out.print("Size: ");
                int size = sc.nextInt();
                sc.nextLine();
                System.out.print("Processor model: ");
                String processor = sc.nextLine();
                System.out.println("Gaming:\n1. yes\n2. no");
                int isGaming = sc.nextInt();
                if(isGaming == 1)
                    new ProductRequest(new Laptop(name, brand, price, description, memory, RAM, os, weight, size, processor, true),
                            this, true, false);
                if(isGaming == 2)
                    new ProductRequest(new Laptop(name, brand, price, description, memory, RAM, os, weight, size, processor, false),
                            this, true, false);
                break;

            case 2:
                System.out.print("Memory capacity: ");
                memory = sc.nextInt();
                System.out.print("RAM: ");
                RAM = sc.nextInt();
                sc.nextLine();
                System.out.print("Operating system: ");
                os = sc.nextLine();
                System.out.print("Weight: ");
                weight = sc.nextDouble();
                System.out.print("Size: ");
                size = sc.nextInt();
                System.out.print("Number of SIM cards: ");
                int SIMcards = sc.nextInt();
                System.out.print("Camera quality: ");
                int cameraQuality = sc.nextInt();
                new ProductRequest(new Mobile(name, brand, price, description, memory, RAM, os, weight, size, SIMcards, cameraQuality),
                        this, true, false);
                break;

            case 3:
                System.out.println("Kind:\n1. boot\n2. sport\n3. slipper\n4. work\n5. sandal");
                ShoeKind shoekind = ShoeKind.valueOf(sc.next().toUpperCase());
                sc.nextLine();
                System.out.print("Country: ");
                String country = sc.nextLine();
                System.out.print("Material: ");
                String material = sc.nextLine();
                System.out.print("Size: ");
                int shoeSize = sc.nextInt();

                new ProductRequest(new Shoes(name, brand, price, description, country, material, shoeSize, shoekind),
                        this, true, false);
                break;

            case 4:
                System.out.println("Kind:\n1. T-shirt\n2. pants\n3. skirt\n4. jeans\n5. coat\n6. jacket\n7. scarf");
                ClotheKind clotheKind = ClotheKind.valueOf(sc.next().toUpperCase());
                sc.nextLine();
                System.out.print("Country: ");
                country = sc.nextLine();
                System.out.print("Material: ");
                material = sc.nextLine();
                System.out.print("Size: ");
                String clotheSize = sc.nextLine();

                new ProductRequest(new Clothe(name, brand, price, description, country, material, clotheSize, clotheKind),
                        this, true, false);
                break;

            case 5:
                System.out.print("Energy label: ");
                String energy = sc.next();
                System.out.println("Have guarantee:\n1. yes\n2. no");
                int guarantee = sc.nextInt();
                boolean isGuarantee = false;
                if(guarantee == 1)
                    isGuarantee = true;
                if(guarantee == 2)
                    isGuarantee = false;
                System.out.print("Number of flames: ");
                int flames = sc.nextInt();
                System.out.print("Material: ");
                material = sc.next();
                System.out.println("Have oven:\n1. yes\n2. no");
                int oven = sc.nextInt();
                boolean withOven = false;
                if (oven == 1)
                    withOven = true;
                if (oven == 2)
                    withOven = false;

                new ProductRequest(new Stove(name, brand, price, description, energy, isGuarantee, flames, material, withOven),
                        this, true, false);
                break;

            case 6:
                System.out.print("Energy label: ");
                energy = sc.next();
                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = false;
                if(guarantee == 1)
                    isGuarantee = true;
                if(guarantee == 2)
                    isGuarantee = false;
                System.out.print("Picture quality: ");
                String quality = sc.next();
                System.out.print("Screen size in inch: ");
                int screenSize = sc.nextInt();

                new ProductRequest(new TV(name, brand, price, description, energy, isGuarantee, quality, screenSize),
                        this, true, false);

                break;

            case 7:
                System.out.print("Energy label: ");
                energy = sc.next();
                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = false;
                if(guarantee == 1)
                    isGuarantee = true;
                if(guarantee == 2)
                    isGuarantee = false;
                System.out.print("Capacity: ");
                int capacity = sc.nextInt();
                System.out.print("Kind: ");
                String kind = sc.next();
                System.out.println("Have freezer:\n1. yes\n2. no");
                int freezer = sc.nextInt();
                boolean withFreezer = false;
                if(freezer == 1)
                    withFreezer = true;
                if(freezer == 2)
                    withFreezer = false;

                new ProductRequest(new Refrigerator(name, brand, price, description, energy, isGuarantee, capacity, kind, withFreezer),
                        this, true, false);
                break;

            case 8:
                System.out.print("Production date: ");
                String pro = sc.next();
                System.out.print("Expiration date: ");
                String exp = sc.next();
                new ProductRequest(new Food(name, brand, price, description, pro, exp),
                        this, true, false);
                break;

            default:
                System.out.println("This category doesnt exist.");
                return;
        }
        System.out.println("\nYour request for adding a product was submitted," +
                "\nplease wait for admin confirmation.");

    }

    void changeProduct() {

        System.out.print("product ID: ");
        int id = sc.nextInt();
        boolean find = false;
        Product productToChanage = null;

        for (Product p: Product.allProducts)
            if (id == p.getId()) {
                productToChanage = p;
                find = true;
                break;
            }

        if (!find) {
            System.out.println("This product doesnt exist");
            return;
        }

        System.out.println("Please enter new information for the this product");

        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Brand: ");
        String brand = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        sc.nextLine();
        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.println("In which category your product is?");
        System.out.println("-Digital products");
        System.out.println("\t1. Laptop");
        System.out.println("\t2. Mobile");
        System.out.println("-Garment");
        System.out.println("\t3. Shoes");
        System.out.println("\t4. Clothe");
        System.out.println("-Home things");
        System.out.println("\t5. Stove");
        System.out.println("\t6. TV");
        System.out.println("\t7. Refrigerator");
        System.out.println("8. Food");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Memory capacity: ");
                int memory = sc.nextInt();
                System.out.print("RAM: ");
                int RAM = sc.nextInt();
                sc.nextLine();
                System.out.print("Operating system: ");
                String os = sc.nextLine();
                System.out.print("Weight: ");
                double weight = sc.nextDouble();
                System.out.print("Size: ");
                int size = sc.nextInt();
                sc.nextLine();
                System.out.print("Processor model: ");
                String processor = sc.nextLine();
                System.out.println("Gaming:\n1. yes\n2. no");
                int isGaming = sc.nextInt();

                if(isGaming == 1)
                    new ChangeProductRequest(productToChanage,
                            new Laptop(name, brand, price, description, memory, RAM, os, weight, size, processor, true));

                if(isGaming == 2)
                    new ChangeProductRequest(productToChanage,
                            new Laptop(name, brand, price, description, memory, RAM, os, weight, size, processor, false));

                break;

            case 2:
                System.out.print("Memory capacity: ");
                memory = sc.nextInt();
                System.out.print("RAM: ");
                RAM = sc.nextInt();
                sc.nextLine();
                System.out.print("Operating system: ");
                os = sc.nextLine();
                System.out.print("Weight: ");
                weight = sc.nextDouble();
                System.out.print("Size: ");
                size = sc.nextInt();
                System.out.print("Number of SIM cards: ");
                int SIMcards = sc.nextInt();
                System.out.print("Camera quality: ");
                int cameraQuality = sc.nextInt();

                new ChangeProductRequest(productToChanage,
                        new Mobile(name, brand, price, description, memory, RAM, os, weight, size, SIMcards, cameraQuality));

                break;

            case 3:
                System.out.println("Kind:\n1. boot\n2. sport\n3. slipper\n4. work\n5. sandal");
                ShoeKind shoekind = ShoeKind.valueOf(sc.next().toUpperCase());
                sc.nextLine();
                System.out.print("Country: ");
                String country = sc.nextLine();
                System.out.print("Material: ");
                String material = sc.nextLine();
                System.out.print("Size: ");
                int shoeSize = sc.nextInt();

                new ChangeProductRequest(productToChanage,
                        new Shoes(name, brand, price, description, country, material, shoeSize, shoekind));

                break;

            case 4:
                System.out.println("Kind:\n1. T-shirt\n2. pants\n3. skirt\n4. jeans\n5. coat\n6. jacket\n7. scarf");
                ClotheKind clotheKind = ClotheKind.valueOf(sc.next().toUpperCase());
                sc.nextLine();
                System.out.print("Country: ");
                country = sc.nextLine();
                System.out.print("Material: ");
                material = sc.nextLine();
                System.out.print("Size: ");
                String clotheSize = sc.nextLine();

                new ChangeProductRequest(productToChanage,
                        new Clothe(name, brand, price, description, country, material, clotheSize, clotheKind));

                break;

            case 5:
                System.out.print("Energy label: ");
                String energy = sc.next();
                System.out.println("Have guarantee:\n1. yes\n2. no");
                int guarantee = sc.nextInt();
                boolean isGuarantee = false;
                if(guarantee == 1)
                    isGuarantee = true;
                if(guarantee == 2)
                    isGuarantee = false;
                System.out.print("Number of flames: ");
                int flames = sc.nextInt();
                System.out.print("Material: ");
                material = sc.next();
                System.out.println("Have oven:\n1. yes\n2. no");
                int oven = sc.nextInt();
                boolean withOven = false;
                if (oven == 1)
                    withOven = true;
                if (oven == 2)
                    withOven = false;

                new ChangeProductRequest(productToChanage,
                        new Stove(name, brand, price, description, energy, isGuarantee, flames, material, withOven));

                break;

            case 6:
                System.out.print("Energy label: ");
                energy = sc.next();
                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = false;
                if(guarantee == 1)
                    isGuarantee = true;
                if(guarantee == 2)
                    isGuarantee = false;
                System.out.print("Picture quality: ");
                String quality = sc.next();
                System.out.print("Screen size in inch: ");
                int screenSize = sc.nextInt();

                new ChangeProductRequest(productToChanage,
                        new TV(name, brand, price, description, energy, isGuarantee, quality, screenSize));

                break;

            case 7:
                System.out.print("Energy label: ");
                energy = sc.next();
                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = false;
                if(guarantee == 1)
                    isGuarantee = true;
                if(guarantee == 2)
                    isGuarantee = false;
                System.out.print("Capacity: ");
                int capacity = sc.nextInt();
                System.out.print("Kind: ");
                String kind = sc.next();
                System.out.println("Have freezer:\n1. yes\n2. no");
                int freezer = sc.nextInt();
                boolean withFreezer = false;
                if(freezer == 1)
                    withFreezer = true;
                if(freezer == 2)
                    withFreezer = false;

                new ChangeProductRequest(productToChanage,
                        new Refrigerator(name, brand, price, description, energy, isGuarantee, capacity, kind, withFreezer));

                break;

            case 8:
                System.out.print("Production date: ");
                String pro = sc.next();
                System.out.print("Expiration date: ");
                String exp = sc.next();

                new ChangeProductRequest(productToChanage,
                        new Food(name, brand, price, description, pro, exp));

                break;

            default:
                System.out.println("This category doesnt exist.");
                return;
        }
        System.out.println("\nYour request for changing a product was submitted," +
                "\nplease wait for admin confirmation.");

    }

    void removeProduct() {
//        this.saleProducts.remove(product);
        System.out.print("Enter the id of product you want to remove: ");
        int id = sc.nextInt();
        for (Product p: Product.allProducts)
            if(id == p.getId()) {
                new ProductRequest(p, p.getSeller(), false, true);
                System.out.println("Your request for removing this product submitted,\n" +
                        "please wait for admin confirmation");
                return;
            }
        System.out.println("\nThis product id doesnt exist.\n");
    }

    void showProducts() {
        for (Product p: this.saleProducts) {
            p.showProduct();
            System.out.println("\n==============================================\n");
        }
    }

    void increaseProduct() {
        System.out.print("Enter product id: ");
        int id = sc.nextInt();
        for (Product p: this.saleProducts)
            if (id == p.getId()) {
                System.out.print("How many of this products you want to add: ");
                int number = sc.nextInt();
                p.setNumber(number);
                System.out.println("\nNumber of product now: " + p.getNumber());
                return;
            }
        System.out.println("\nYou dont have this product\n");
    }

    void showHistory() {
        for (SaleFactor factor: this.history)
            factor.printFactor();
    }

    public void changeInfo(String username, String firstName, String lastName,
                           String email, String phoneNumber, String password, String company)
    {
        this.setUsername(username);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setPassword(password);
        this.company = company;
    }

    @Override
    public void showInfo() {
        System.out.println("--- Account Information ---");
        System.out.println("Seller Account");
        System.out.println(this.getFirstName() + " " + this.getLastName());
        System.out.println("Username: " + this.getUsername());
        System.out.println("Phone number: " + this.getPhoneNumber());
        System.out.println("E-mail: " + this.getEmail());
        System.out.println("Company: " + this.company);
        System.out.println("---------------------------");
    }

    @Override
    public void menu() {
        while (Menu.checkLogin.loggedIn) {
            System.out.println("\n0. Show account information");
            System.out.println("1. Add a product");
            System.out.println("2. Remove a product");
            System.out.println("3. Change product information");
            System.out.println("4. Change information");
            System.out.println("5. Show your products");
            System.out.println("6. Increase number of products");
            System.out.println("7. Show history");
            System.out.println("8. Back");
            System.out.println("9. Log out");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    showInfo();
                    break;
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    changeProduct();
                    break;
                case 4:
                    System.out.println("Enter new user name, first name, last name, email, phone number, password and company: ");
                    System.out.print("new user name: ");
                    String username = sc.next();
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
                    System.out.print("new company: ");
                    String company = sc.next();

                    new ChangeRequest(this,
                            new Seller(username, firstName, lastName, email, phoneNumber, password, company));

                    System.out.println("Your request for changing information submitted,\n" +
                            "please wait for admin confirmation");

                    break;
                case 5:
                    showProducts();
                    break;
                case 6:
                    increaseProduct();
                    break;
                case 7:
                    showHistory();
                    break;
                case 8:
                    Menu.firstMenu();
                    break;
                case 9:
                    Menu.checkLogin.loggedIn = false;
                    Menu.checkLogin.setAccount(null);
                    System.out.println("\nLog out successfully\n");
                    break;
                default:
                    System.out.println("\nError\n");
            }
        }
    }

    // Setters and Getters

    public ArrayList<Product> getSaleProducts() {
        return saleProducts;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<SaleFactor> getHistory() {
        return history;
    }

    public void addToHistory(SaleFactor factor) {
        this.history.add(factor);
    }
}