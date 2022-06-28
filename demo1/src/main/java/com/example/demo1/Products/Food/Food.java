package com.example.demo1.Products.Food;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Digital.Laptop;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Food extends Product {

    public static Category foodCategory = new Category("Food");

    private final String proDate;
    private final String expDate;

    public Food(String name, String brand, double price, String description,
                String proDate, String expDate)
    {
        super(name, brand, price, description);
        this.proDate = proDate;
        this.expDate = expDate;
    }

    public void editInfo(Food newFood)
    {
        this.setName(newFood.getName());
        this.setBrand(newFood.getBrand());
        this.setPrice(newFood.getPrice());
        this.setDescription(newFood.getDescription());
        // dates can not be edited
    }

    public void editInfo(String name, String brand, double price, String description)
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        // dates can not be edited
    }

    @Override
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("Food"));

        info.getChildren().add(new Label("Production Date:  " + ((Food) t1).proDate));

        info.getChildren().add(new Label("Expiration Date:  " + ((Food) t1).expDate));
    }

    public static void readFromDB (String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `foods` on products.id=foods.id WHERE products.category='food'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Food p = new Food(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("proDate"),
                        rs.getString("expDate"));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertFood() {
        return String.format("INSERT INTO `foods` (`id`, `proDate`, `expDate`) " +
                        "VALUES ('%d', '%s', '%s')", getId(), proDate, expDate);
    }

    public String toString2() {
        return "Food{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", proDate='" + proDate + '\'' +
                ", expDate='" + expDate + '\'' +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        if (filter.equals("proDate")) {

            for (Product food : foodCategory.getProducts())
                if (((Food) food).proDate.equals(feature))
                    filtered.add(food);

        } else if (filter.equals("expDate")) {

            for (Product food : foodCategory.getProducts())
                if (((Food) food).expDate.equals(feature))
                    filtered.add(food);
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getProDate() {
        return proDate;
    }

    public String getExpDate() {
        return expDate;
    }
}
