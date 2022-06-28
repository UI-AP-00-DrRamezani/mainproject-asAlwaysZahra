package com.example.demo1.Products.Garment;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Clothe extends Garment {

    public static ArrayList<Clothe> clothes = new ArrayList<>();
    public static Category clotheCategory = new Category("Clothe");

    private String size;
    private ClotheKind kind;

    public Clothe(String name, String brand, double price, String description,
                  String country, String material, String size, ClotheKind kind) {
        super(name, brand, price, description, country, material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String country, String material, String size, ClotheKind kind) {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setCountry(country);
        this.setMaterial(material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(Clothe newProduct) {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setCountry(newProduct.getCountry());
        this.setMaterial(newProduct.getMaterial());
        this.setSize(newProduct.size);
        this.setKind(newProduct.kind);
    }

    public enum ClotheKind {
        T_SHIRT, PANTS, SKIRT, JEANS, COAT, JACKET, SCARF
    }

    @Override
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("Clothe"));

        info.getChildren().add(new Label("Country:  " + ((Clothe) t1).getCountry()));

        info.getChildren().add(new Label("Material:  " + ((Clothe) t1).getMaterial()));

        info.getChildren().add(new Label("Size:  " + ((Clothe) t1).getSize()));

        info.getChildren().add(new Label("Clothe Kind:  " + ((Clothe) t1).kind));
    }

    public static void readFromDB(String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `clothes` on products.id=clothes.id WHERE products.category='clothe'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Clothe p = new Clothe(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("country"),
                        rs.getString("material"),
                        rs.getString("size"),
                        ClotheKind.valueOf(rs.getString("kind")));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertClothe() {
        return String.format("INSERT INTO `clothes` (`id`, `country`, `material`, `size`, `kind`) " +
                        "VALUES ('%d', '%s', '%s', '%s', '%s')",
                getId(), getCountry(), getMaterial(), size, kind);
    }

    public String toString2() {
        return "Clothe{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", material='" + getMaterial() + '\'' +
                ", size='" + size + '\'' +
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
                for (Clothe clothe : clothes)
                    if (clothe.size.equals(feature))
                        filtered.add(clothe);
                break;

            case "kind":
                for (Clothe clothe : clothes)
                    if (clothe.kind.equals(ClotheKind.valueOf(feature)))
                        filtered.add(clothe);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ClotheKind getKind() {
        return kind;
    }

    public void setKind(ClotheKind kind) {
        this.kind = kind;
    }
}