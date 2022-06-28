package com.example.demo1.Products.Home;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Refrigerator extends HomeThings {

    public static ArrayList<Refrigerator> refrigerators = new ArrayList<>();
    public static Category refrigeratorCategory = new Category("Refrigerator");

    private int capacity;
    private String kind;
    private boolean withFreezer;

    public Refrigerator(String name, String brand, double price, String description,
                        String energyLabel, boolean guarantee,
                        int capacity, String kind, boolean withFreezer) {
        super(name, brand, price, description, energyLabel, guarantee);
        this.capacity = capacity;
        this.kind = kind;
        this.withFreezer = withFreezer;
    }

    public Refrigerator(String name, String brand, double price, String description,
                        String energyLabel, int guarantee,
                        int capacity, String kind, int withFreezer) {

        super(name, brand, price, description, energyLabel, guarantee == 1);
        this.capacity = capacity;
        this.kind = kind;
        this.withFreezer = withFreezer == 1;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String energyLabel, boolean guarantee,
                         int capacity, String kind, boolean withFreezer) {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setEnergyLabel(energyLabel);
        this.setGuarantee(guarantee);
        this.setCapacity(capacity);
        this.setKind(kind);
        this.withFreezer = withFreezer;
    }

    public void editInfo(Refrigerator newProduct) {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setEnergyLabel(newProduct.getEnergyLabel());
        this.setGuarantee(newProduct.isGuarantee());
        this.setCapacity(newProduct.getCapacity());
        this.setKind(newProduct.getKind());
        this.setWithFreezer(newProduct.isWithFreezer());
    }

    @Override
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("Refrigerator"));

        info.getChildren().add(new Label("Energy Label:  " + ((Refrigerator) t1).getEnergyLabel()));

        if (isGuarantee())
            info.getChildren().add(new Label("is Guarantee:  YES"));
        else
            info.getChildren().add(new Label("is Guarantee:  NO"));

        info.getChildren().add(new Label("Capacity:  " + ((Refrigerator) t1).capacity));

        info.getChildren().add(new Label("kind:  " + ((Refrigerator) t1).kind));

        if (isWithFreezer())
            info.getChildren().add(new Label("has Freezer:  YES"));
        else
            info.getChildren().add(new Label("has Freezer:  NO"));
    }

    public static void readFromDB(String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `refrigerators` on products.id=refrigerators.id WHERE products.category='refrigerator'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Refrigerator p = new Refrigerator(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("energyLabel"),
                        rs.getInt("guarantee"),
                        rs.getInt("capacity"),
                        rs.getString("kind"),
                        rs.getInt("withFreezer"));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertRefrigerator() {

        if (isGuarantee()) {
            if (isWithFreezer())
                return String.format("INSERT INTO `refrigerators` (`id`, `energyLabel`, `guarantee`, `capacity`, `kind`, `withFreezer`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 1, capacity, kind, 1);
            else
                return String.format("INSERT INTO `refrigerators` (`id`, `energyLabel`, `guarantee`, `capacity`, `kind`, `withFreezer`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 1, capacity, kind, 0);
        } else {
            if (isWithFreezer())
                return String.format("INSERT INTO `refrigerators` (`id`, `energyLabel`, `guarantee`, `capacity`, `kind`, `withFreezer`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 0, capacity, kind, 1);
            else
                return String.format("INSERT INTO `refrigerators` (`id`, `energyLabel`, `guarantee`, `capacity`, `kind`, `withFreezer`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 0, capacity, kind, 0);
        }
    }

    //    @Override
    public String toString2() {
        return "Refrigerator{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee=" + isGuarantee() +
                ", capacity=" + capacity +
                ", kind='" + kind + '\'' +
                ", withFreezer=" + withFreezer +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter) {
            case "energyLabel":
                for (Product product : homeCategory.getProducts())
                    if (((HomeThings) product).getEnergyLabel().equals(feature))
                        filtered.add(product);
                break;

            case "guarantee":
                for (Product product : homeCategory.getProducts())
                    if (((HomeThings) product).isGuarantee() == Boolean.parseBoolean(feature))
                        filtered.add(product);
                break;

            case "capacity":
                for (Refrigerator refrigerator : refrigerators)
                    if (refrigerator.capacity == Integer.parseInt(feature))
                        filtered.add(refrigerator);

                break;

            case "kind":
                for (Refrigerator refrigerator : refrigerators)
                    if (refrigerator.kind.equals(feature))
                        filtered.add(refrigerator);

                break;

            case "withFreezer":
                for (Refrigerator refrigerator : refrigerators)
                    if (refrigerator.withFreezer == Boolean.parseBoolean(feature))
                        filtered.add(refrigerator);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isWithFreezer() {
        return withFreezer;
    }

    public void setWithFreezer(boolean withFreezer) {
        this.withFreezer = withFreezer;
    }
}
