package Accounts.Seller;

import Factors.SaleFactor;
import Products.Digital.*;
import Products.Food.Food;
import Products.Garment.*;
import Products.Home.*;
import Products.Product.Product;
import UI.MainMenu;

import java.util.Scanner;

public class SellerPanel {
    static Scanner sc = new Scanner(System.in);

    static SellerManager manager = new SellerManager();

    public static void sellerMenu() {

        while (SellerManager.sellerModel != null)
        {
            System.out.println("\nWelcome Dear " + SellerManager.sellerModel.getFirstName() + " " + SellerManager.sellerModel.getLastName());
            System.out.println("--- Seller Panel ---");
            System.out.println("0. Account Information");
            System.out.println("1. Edit Information");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Edit Product Information");
            System.out.println("5. Your Products");
            System.out.println("6. History");
            System.out.println("7. Back");
            System.out.println("8. Log out");

            int number = sc.nextInt();

            switch (number)
            {
                case 0:
                    System.out.println(SellerManager.sellerModel.toString());
                    break;

                case 1:
                    System.out.println("Enter new username, first name, last name,\n" +
                            "email, phone number, password and company:");
                    manager.editInfo(sc.next(), sc.next(), sc.next(),
                            sc.next(), sc.next(), sc.next(), sc.next());
                    System.out.println("""
                            \nYour request for adding a product was submitted,
                            please wait for admin confirmation.""");
                    break;

                case 2:
                    addProduct();
                    break;

                case 3:
                    System.out.println("Enter productID to remove:");

                    if (manager.removeProduct(sc.nextInt()))
                        System.out.println("""
                            \nYour request for adding a product was submitted,
                            please wait for admin confirmation.""");
                    else
                        System.out.println("You dont have this product.");

                    break;

                case 4:
                    System.out.println("Enter productID to edit," +
                            "Then enter NEW information for your product.");

                    if (editProduct(sc.nextInt()))
                        System.out.println("""
                            \nYour request for editing a product was submitted,
                            please wait for admin confirmation.""");
                    else
                        System.out.println("You dont have this product.");

                    break;

                case 5:
                    for (Product p : SellerManager.sellerModel.getSaleProducts())
                        System.out.println(p);
                    break;

                case 6:
                    System.out.println("\nHistory\n");
                    for (SaleFactor factor : SellerManager.sellerModel.getHistory()) {
                        System.out.println(factor);
                        System.out.println("-----------------------------------");
                    }
                    break;

                case 7:
                    MainMenu.mainMenu();
                    break;

                case 8:
                    manager.logout();
                    MainMenu.mainMenu();
                    return;

                default:
                    System.out.println("Error");
            }
        }
    }

    public static void addProduct() {

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

        System.out.println("\nIn which category your product will be?");
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
        switch (choice)
        {
            case 1:
                System.out.print("Memory capacity: ");
                int memory = sc.nextInt();

                System.out.print("RAM: ");
                int RAM = sc.nextInt();

                System.out.print("Operating system: ");
                String os = sc.next();

                System.out.print("Weight: ");
                double weight = sc.nextDouble();

                System.out.print("Size: ");
                int size = sc.nextInt();

                System.out.print("Processor model: ");
                String processor = sc.next();

                System.out.println("Gaming:\n1. yes\n2. no");
                int isGaming = sc.nextInt();

                if(isGaming == 1)
                    manager.addProduct(new Laptop(name, brand, price, description,
                            memory, RAM, os, weight, size, processor, true));
                if(isGaming == 2)
                    manager.addProduct(new Laptop(name, brand, price, description,
                            memory, RAM, os, weight, size, processor, false));
                break;

            case 2:
                System.out.print("Memory capacity: ");
                memory = sc.nextInt();

                System.out.print("RAM: ");
                RAM = sc.nextInt();

                System.out.print("Operating system: ");
                os = sc.next();

                System.out.print("Weight: ");
                weight = sc.nextDouble();

                System.out.print("Size: ");
                size = sc.nextInt();

                System.out.print("Number of SIM cards: ");
                int SIMCards = sc.nextInt();

                System.out.print("Camera quality: ");
                int cameraQuality = sc.nextInt();

                manager.addProduct(new Mobile(name, brand, price, description,
                        memory, RAM, os, weight, size, SIMCards, cameraQuality));
                break;

            case 3:
                System.out.println("Kind:\n1. boot\n2. sport\n3. slipper\n4. work\n5. sandal");
                Shoes.ShoeKind shoeKind = Shoes.ShoeKind.valueOf(sc.next().toUpperCase());

                System.out.print("Country: ");
                String country = sc.next();

                System.out.print("Material: ");
                String material = sc.nextLine();

                System.out.print("Size: ");
                int shoeSize = sc.nextInt();

                manager.addProduct(new Shoes(name, brand, price, description,
                        country, material, shoeSize, shoeKind));
                break;

            case 4:
                System.out.println("Kind:\n1. T-shirt\n2. pants\n3. skirt\n4. jeans\n5. coat\n6. jacket\n7. scarf");
                Clothe.ClotheKind clotheKind = Clothe.ClotheKind.valueOf(sc.next().toUpperCase());

                System.out.print("Country: ");
                country = sc.next();

                System.out.print("Material: ");
                material = sc.next();

                System.out.print("Size: ");
                String clotheSize = sc.next();

                manager.addProduct(new Clothe(name, brand, price, description,
                        country, material, clotheSize, clotheKind));
                break;

            case 5:
                System.out.print("Energy label: ");
                String energy = sc.next();

                System.out.println("Have guarantee:\n1. yes\n2. no");
                int guarantee = sc.nextInt();
                boolean isGuarantee = guarantee == 1;

                System.out.print("Number of flames: ");
                int flames = sc.nextInt();

                System.out.print("Material: ");
                material = sc.next();

                System.out.println("Have oven:\n1. yes\n2. no");
                int oven = sc.nextInt();
                boolean withOven = oven == 1;

                manager.addProduct(new Stove(name, brand, price, description,
                        energy, isGuarantee, flames, material, withOven));
                break;

            case 6:
                System.out.print("Energy label: ");
                energy = sc.next();

                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = guarantee == 1;

                System.out.print("Picture quality: ");
                String quality = sc.next();

                System.out.print("Screen size in inch: ");
                int screenSize = sc.nextInt();

                manager.addProduct(new TV(name, brand, price, description,
                        energy, isGuarantee, quality, screenSize));
                break;

            case 7:
                System.out.print("Energy label: ");
                energy = sc.next();

                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = guarantee == 1;

                System.out.print("Capacity: ");
                int capacity = sc.nextInt();

                System.out.print("Kind: ");
                String kind = sc.next();

                System.out.println("Have freezer:\n1. yes\n2. no");
                int freezer = sc.nextInt();
                boolean withFreezer = freezer == 1;

                manager.addProduct(new Refrigerator(name, brand, price, description,
                                energy, isGuarantee, capacity, kind, withFreezer));
                break;

            case 8:
                System.out.print("Production date: ");
                String pro = sc.next();

                System.out.print("Expiration date: ");
                String exp = sc.next();

                manager.addProduct(new Food(name, brand, price, description, pro, exp));

                break;

            default:
                System.out.println("This category doesn't exist.");
                return;
        }

        System.out.println("""
                Your request for adding a product was submitted,
                please wait for admin confirmation.""");
    }

    public static boolean editProduct(int oldProduct) {

        boolean found = false;

        System.out.println("Please enter the NEW information for the product");

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

        System.out.println("\nIn which category your product is?");
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
        switch (choice)
        {
            case 1:
                System.out.print("Memory capacity: ");
                int memory = sc.nextInt();

                System.out.print("RAM: ");
                int RAM = sc.nextInt();

                System.out.print("Operating system: ");
                String os = sc.next();

                System.out.print("Weight: ");
                double weight = sc.nextDouble();

                System.out.print("Size: ");
                int size = sc.nextInt();

                System.out.print("Processor model: ");
                String processor = sc.next();

                System.out.println("Gaming:\n1. yes\n2. no");
                int isGaming = sc.nextInt();

                if(isGaming == 1)
                    found = manager.editProduct(oldProduct, new Laptop(name, brand, price, description,
                            memory, RAM, os, weight, size, processor, true));
                if(isGaming == 2)
                    found = manager.editProduct(oldProduct, new Laptop(name, brand, price, description,
                            memory, RAM, os, weight, size, processor, false));
                break;

            case 2:
                System.out.print("Memory capacity: ");
                memory = sc.nextInt();

                System.out.print("RAM: ");
                RAM = sc.nextInt();

                System.out.print("Operating system: ");
                os = sc.next();

                System.out.print("Weight: ");
                weight = sc.nextDouble();

                System.out.print("Size: ");
                size = sc.nextInt();

                System.out.print("Number of SIM cards: ");
                int SIMCards = sc.nextInt();

                System.out.print("Camera quality: ");
                int cameraQuality = sc.nextInt();

                found = manager.editProduct(oldProduct, new Mobile(name, brand, price, description,
                        memory, RAM, os, weight, size, SIMCards, cameraQuality));
                break;

            case 3:
                System.out.println("Kind:\n1. boot\n2. sport\n3. slipper\n4. work\n5. sandal");
                Shoes.ShoeKind shoeKind = Shoes.ShoeKind.valueOf(sc.next().toUpperCase());

                System.out.print("Country: ");
                String country = sc.next();

                System.out.print("Material: ");
                String material = sc.nextLine();

                System.out.print("Size: ");
                int shoeSize = sc.nextInt();

                found = manager.editProduct(oldProduct, new Shoes(name, brand, price, description,
                        country, material, shoeSize, shoeKind));
                break;

            case 4:
                System.out.println("Kind:\n1. T-shirt\n2. pants\n3. skirt\n4. jeans\n5. coat\n6. jacket\n7. scarf");
                Clothe.ClotheKind clotheKind = Clothe.ClotheKind.valueOf(sc.next().toUpperCase());

                System.out.print("Country: ");
                country = sc.next();

                System.out.print("Material: ");
                material = sc.next();

                System.out.print("Size: ");
                String clotheSize = sc.next();

                found = manager.editProduct(oldProduct, new Clothe(name, brand, price, description,
                        country, material, clotheSize, clotheKind));
                break;

            case 5:
                System.out.print("Energy label: ");
                String energy = sc.next();

                System.out.println("Have guarantee:\n1. yes\n2. no");
                int guarantee = sc.nextInt();
                boolean isGuarantee = guarantee == 1;

                System.out.print("Number of flames: ");
                int flames = sc.nextInt();

                System.out.print("Material: ");
                material = sc.next();

                System.out.println("Have oven:\n1. yes\n2. no");
                int oven = sc.nextInt();
                boolean withOven = oven == 1;

                found = manager.editProduct(oldProduct, new Stove(name, brand, price, description,
                        energy, isGuarantee, flames, material, withOven));
                break;

            case 6:
                System.out.print("Energy label: ");
                energy = sc.next();

                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = guarantee == 1;

                System.out.print("Picture quality: ");
                String quality = sc.next();

                System.out.print("Screen size in inch: ");
                int screenSize = sc.nextInt();

                found = manager.editProduct(oldProduct, new TV(name, brand, price, description,
                        energy, isGuarantee, quality, screenSize));
                break;

            case 7:
                System.out.print("Energy label: ");
                energy = sc.next();

                System.out.println("Have guarantee:\n1. yes\n2. no");
                guarantee = sc.nextInt();
                isGuarantee = guarantee == 1;

                System.out.print("Capacity: ");
                int capacity = sc.nextInt();

                System.out.print("Kind: ");
                String kind = sc.next();

                System.out.println("Have freezer:\n1. yes\n2. no");
                int freezer = sc.nextInt();
                boolean withFreezer = freezer == 1;

                found = manager.editProduct(oldProduct, new Refrigerator(name, brand, price, description,
                        energy, isGuarantee, capacity, kind, withFreezer));
                break;

            case 8:
                System.out.print("Production date: ");
                String pro = sc.next();

                System.out.print("Expiration date: ");
                String exp = sc.next();

                found = manager.editProduct(oldProduct, new Food(name, brand, price, description, pro, exp));

                break;

            default:
                System.out.println("This category doesn't exist.");
        }

        return found;
    }
}
