package com.example.demo1.Products.Garment;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Shoes extends Garment {

    public static ArrayList<Shoes> shoes = new ArrayList<>();
    public static Category shoesCategory = new Category("Shoes");

    private int size;
    private ShoeKind kind;

    public Shoes(String name, String brand, double price, String description,
                 String country, String material, int size, ShoeKind kind) {
        super(name, brand, price, description, country, material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String country, String material, int size, ShoeKind kind) {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setCountry(country);
        this.setMaterial(material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(Shoes newProduct) {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setCountry(newProduct.getCountry());
        this.setMaterial(newProduct.getMaterial());
        this.setSize(newProduct.size);
        this.setKind(newProduct.kind);
    }

    public enum ShoeKind {
        BOOT, SPORT, SLIPPER, WORK, SANDAL
    }

    @Override
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("Shoes"));

        info.getChildren().add(new Label("Country:  " + ((Shoes) t1).getCountry()));

        info.getChildren().add(new Label("Material:  " + ((Shoes) t1).getMaterial()));

        info.getChildren().add(new Label("Size:  " + ((Shoes) t1).getSize()));

        info.getChildren().add(new Label("Shoes Kind:  " + ((Shoes) t1).kind));
    }

    public static void readFromDB(String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `shoes` on products.id=shoes.id WHERE products.category='shoes'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Shoes p = new Shoes(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("country"),
                        rs.getString("material"),
                        rs.getInt("size"),
                        ShoeKind.valueOf(rs.getString("kind")));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertShoes() {
        return String.format("INSERT INTO `shoes` (`id`, `country`, `material`, `size`, `kind`) " +
                        "VALUES ('%d', '%s', '%s', '%d', '%s')",
                getId(), getCountry(), getMaterial(), size, kind);
    }

    //    @Override
    public String toString2() {
        return "Shoes{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", material='" + getMaterial() + '\'' +
                ", size=" + size +
                ", kind='" + kind + '\'' +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter) {

            case "country":
                for (Product product : garmentCategory.getProducts())
                    if (((Garment) product).getCountry().equals(feature))
                        filtered.add(product);
                break;

            case "material":
                for (Product product : garmentCategory.getProducts())
                    if (((Garment) product).getMaterial().equals(feature))
                        filtered.add(product);
                break;

            case "size":
                for (Shoes shoes : shoes)
                    if (shoes.size == Integer.parseInt(feature))
                        filtered.add(shoes);
                break;

            case "kind":
                for (Shoes shoes : shoes)
                    if (shoes.kind.equals(ShoeKind.valueOf(feature)))
                        filtered.add(shoes);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ShoeKind getKind() {
        return kind;
    }

    public void setKind(ShoeKind kind) {
        this.kind = kind;
    }
}