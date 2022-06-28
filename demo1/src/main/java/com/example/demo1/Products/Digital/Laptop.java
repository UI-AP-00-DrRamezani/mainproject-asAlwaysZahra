package com.example.demo1.Products.Digital;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Laptop extends Digital {

    public static ArrayList<Laptop> laptops = new ArrayList<>();
    public static Category laptopCategory = new Category("Laptop");

    private String processor;
    private boolean isGaming;

    public Laptop(String name, String brand, double price, String description,
                  int memoryCapacity, int RAM, String OS, double weight, int size,
                  String processor, boolean isGaming) {
        super(name, brand, price, description, memoryCapacity, RAM, OS, weight, size);
        this.processor = processor;
        this.isGaming = isGaming;
    }

    public Laptop(String name, String brand, double price, String description,
                  int memoryCapacity, int RAM, String OS, double weight, int size,
                  String processor, int isGaming) {
        super(name, brand, price, description, memoryCapacity, RAM, OS, weight, size);
        this.processor = processor;
        this.isGaming = isGaming == 1;
    }

    public void editInfo(String name, String brand, double price, String description,
                         int memoryCapacity, int RAM, String OS, double weight, int size,
                         String processor, boolean isGaming) {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setMemoryCapacity(memoryCapacity);
        this.setRAM(RAM);
        this.setOS(OS);
        this.setWeight(weight);
        this.setSize(size);
        this.processor = processor;
        this.isGaming = isGaming;
    }

    public void editInfo(Laptop newProduct) {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setMemoryCapacity(newProduct.getMemoryCapacity());
        this.setRAM(newProduct.getRAM());
        this.setOS(newProduct.getOS());
        this.setWeight(newProduct.getWeight());
        this.setSize(newProduct.getSize());
        this.processor = newProduct.processor;
        this.isGaming = newProduct.isGaming;
    }

    @Override
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("Laptop"));

        info.getChildren().add(new Label("Memory Capacity:  " + ((Laptop) t1).getMemoryCapacity()));

        info.getChildren().add(new Label("Ram:  " + ((Laptop) t1).getRAM()));

        info.getChildren().add(new Label("Operating System:  " + ((Laptop) t1).getOS()));

        info.getChildren().add(new Label("Weight:  " + ((Laptop) t1).getWeight()));

        info.getChildren().add(new Label("Size:  " + ((Laptop) t1).getSize()));

        info.getChildren().add(new Label("Processor:  " + ((Laptop) t1).getProcessor()));

        if (isGaming)
            info.getChildren().add(new Label("Gaming:  YES"));
        else
            info.getChildren().add(new Label("Gaming:  NO"));
    }

    public static void readFromDB (String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `laptops` on products.id=laptops.id WHERE products.category='laptop'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Laptop p = new Laptop(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("memoryCapacity"),
                        rs.getInt("RAM"),
                        rs.getString("OS"),
                        rs.getDouble("weight"),
                        rs.getInt("size"),
                        rs.getString("processor"),
                        rs.getInt("isGaming"));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertLaptop() {

        if (isGaming)
            return String.format("INSERT INTO `laptops` (`id`, `memoryCapacity`, `RAM`, `OS`, `weight`, `size`, `processor`, `isGaming`) " +
                            "VALUES ('%d', '%d', '%d', '%s', '%f', '%d', '%s', '%d')",
                    getId(), getMemoryCapacity(), getRAM(), getOS(), getWeight(), getSize(), processor, 1);
        else
            return String.format("INSERT INTO `laptops` (`id`, `memoryCapacity`, `RAM`, `OS`, `weight`, `size`, `processor`, `isGaming`) " +
                            "VALUES ('%d', '%d', '%d', '%s', '%f', '%d', '%s', '%d')",
                    getId(), getMemoryCapacity(), getRAM(), getOS(), getWeight(), getSize(), processor, 0);
    }

    //    @Override
    public String toString2() {
        return "Laptop{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", memoryCapacity=" + getMemoryCapacity() +
                ", RAM=" + getRAM() +
                ", OS='" + getOS() + '\'' +
                ", weight=" + getWeight() +
                ", size=" + getSize() +
                ", processor='" + processor + '\'' +
                ", isGaming=" + isGaming +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter) {
            case "memoryCapacity":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getMemoryCapacity() == Integer.parseInt(feature))
                        filtered.add(product);
                break;

            case "RAM":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getRAM() == Integer.parseInt(feature))
                        filtered.add(product);
                break;

            case "OS":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getOS().equals(feature))
                        filtered.add(product);
                break;

            case "weight":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getWeight() == Double.parseDouble(feature))
                        filtered.add(product);
                break;

            case "size":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getSize() == Integer.parseInt(feature))
                        filtered.add(product);
                break;

            case "processor":
                for (Laptop laptop : laptops)
                    if (laptop.getProcessor().contains(feature))
                        filtered.add(laptop);
                break;

            case "isGaming":
                for (Laptop laptop : laptops)
                    if (laptop.isGaming == Boolean.parseBoolean(feature))
                        filtered.add(laptop);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public boolean isGaming() {
        return isGaming;
    }

    public void setGaming(boolean gaming) {
        isGaming = gaming;
    }
}