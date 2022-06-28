package com.example.demo1.Products.Home;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Stove extends HomeThings {

    public static ArrayList<Stove> stoves = new ArrayList<>();
    public static Category stoveCategory = new Category("Stove");

    private int flames;
    private String material;
    private boolean withOven;

    public Stove(String name, String brand, double price, String description,
                 String energyLabel, boolean guarantee,
                 int flames, String material, boolean withOven) {
        super(name, brand, price, description, energyLabel, guarantee);
        this.flames = flames;
        this.material = material;
        this.withOven = withOven;
    }

    public Stove(String name, String brand, double price, String description,
                 String energyLabel, int guarantee,
                 int flames, String material, int withOven) {
        super(name, brand, price, description, energyLabel, guarantee == 1);
        this.flames = flames;
        this.material = material;
        this.withOven = withOven == 1;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String energyLabel, boolean guarantee,
                         int flames, String material, boolean withOven) {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setEnergyLabel(energyLabel);
        this.setGuarantee(guarantee);
        this.flames = flames;
        this.material = material;
        this.withOven = withOven;
    }


    public void editInfo(Stove newProduct) {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setEnergyLabel(newProduct.getEnergyLabel());
        this.setGuarantee(newProduct.isGuarantee());
        this.setFlames(newProduct.getFlames());
        this.setMaterial(newProduct.getMaterial());
        this.setWithOven(newProduct.isWithOven());
    }

    @Override
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("Stove"));

        info.getChildren().add(new Label("Energy Label:  " + ((Stove) t1).getEnergyLabel()));

        if (isGuarantee())
            info.getChildren().add(new Label("is Guarantee:  YES"));
        else
            info.getChildren().add(new Label("is Guarantee:  NO"));

        info.getChildren().add(new Label("Flames:  " + ((Stove) t1).flames));

        info.getChildren().add(new Label("Material:  " + ((Stove) t1).material));

        if (isWithOven())
            info.getChildren().add(new Label("has Oven:  YES"));
        else
            info.getChildren().add(new Label("has Oven:  NO"));
    }

    public static void readFromDB(String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `stoves` on products.id=stoves.id WHERE products.category='Stove'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Stove p = new Stove(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("energyLabel"),
                        rs.getInt("guarantee"),
                        rs.getInt("flames"),
                        rs.getString("material"),
                        rs.getInt("withOven"));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertStove() {

        if (isGuarantee()) {
            if (isWithOven())
                return String.format("INSERT INTO `stoves` (`id`, `energyLabel`, `guarantee`, `flames`, `material`, `withOven`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 1, flames, material, 1);
            else
                return String.format("INSERT INTO `stoves` (`id`, `energyLabel`, `guarantee`, `flames`, `material`, `withOven`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 1, flames, material, 0);
        } else {
            if (isWithOven())
                return String.format("INSERT INTO `stoves` (`id`, `energyLabel`, `guarantee`, `flames`, `material`, `withOven`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 0, flames, material, 1);
            else
                return String.format("INSERT INTO `stoves` (`id`, `energyLabel`, `guarantee`, `flames`, `material`, `withOven`) " +
                                "VALUES ('%d', '%s', '%d', '%d', '%s', '%d')",
                        getId(), getEnergyLabel(), 0, flames, material, 0);
        }
    }

//    @Override
    public String toString2() {
        return "Stove{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee=" + isGuarantee() +
                ", flames=" + flames +
                ", material='" + material + '\'' +
                ", withOven=" + withOven +
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

            case "flames":
                for (Stove stove : stoves)
                    if (stove.flames == Integer.parseInt(feature))
                        filtered.add(stove);

                break;

            case "material":
                for (Stove stove : stoves)
                    if (stove.material.equals(feature))
                        filtered.add(stove);
                break;

            case "withOven":
                for (Stove stove : stoves)
                    if (stove.withOven == Boolean.parseBoolean(feature))
                        filtered.add(stove);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public int getFlames() {
        return flames;
    }

    public void setFlames(int flames) {
        this.flames = flames;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isWithOven() {
        return withOven;
    }

    public void setWithOven(boolean withOven) {
        this.withOven = withOven;
    }
}
