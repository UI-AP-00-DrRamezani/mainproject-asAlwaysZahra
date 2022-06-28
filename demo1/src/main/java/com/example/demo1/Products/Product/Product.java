package com.example.demo1.Products.Product;

import com.example.demo1.Accounts.Seller.Seller;
import com.example.demo1.Products.Comment;
import com.example.demo1.Products.Digital.Laptop;
import com.example.demo1.Products.Digital.Mobile;
import com.example.demo1.Products.Food.Food;
import com.example.demo1.Products.Garment.Clothe;
import com.example.demo1.Products.Garment.Shoes;
import com.example.demo1.Products.Home.Refrigerator;
import com.example.demo1.Products.Home.Stove;
import com.example.demo1.Products.Home.TV;
import com.example.demo1.Products.Score;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Product implements Comparable<Product> {

    private static int ID_COUNTER = 1;
    private final int id;
    private String name;
    private String brand;
    private double price;
    private Seller seller;
    private int number;
    private String description;
    private final ArrayList<Score> scores;
    private float avgRate;
    private final ArrayList<Comment> comments;

    public Product(String name, String brand, double price, String description) {
//        this.id = getMaxID()+1;
        this.id = ID_COUNTER++;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        scores = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public abstract void addToGUI(VBox info, Product t1);

    @Override
    public String toString() {
        return getId() + ". " + getName() + " - $" + getPrice();
    }

    public String insertProduct() {

        if (this instanceof Laptop)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Laptop");

        if (this instanceof Mobile)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Mobile");

        if (this instanceof Food)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Food");

        if (this instanceof Clothe)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Clothe");

        if (this instanceof Shoes)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Shoes");

        if (this instanceof TV)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "TV");

        if (this instanceof Stove)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Stove");

        if (this instanceof Refrigerator)
            return String.format("INSERT INTO `products` (`id`, `name`, `brand`, `price`, `sellerUsername`, `number`, `description`, `avgRate`, `category`) " +
                            "VALUES ('%d', '%s', '%s', '%f', '%s', '%d', '%s', '%f', '%s')",
                    id, name, brand, price, seller.getUsername(), number, description, avgRate, "Refrigerator");

        return null;
    }

    // todo add this for avg rate
    public String updateProduct() { // todo add for all categories
        return String.format("UPDATE `products` SET name='%s', brand='%s', price=%f, " +
                        "number=%d, description='%s', avgRate=%f WHERE products.id=%d",
                name, brand, price, number, description, avgRate, id);
    }

    public String deleteProduct() {
        return String.format("DELETE FROM `products` WHERE products.id='%d'", id);
    }

    private int getMaxID() {
        String url = "jdbc:mysql://localhost/shopDataBase";
        String user = "zahra";
        String pass = "zahra";
        int maxID = 0;

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "SELECT MAX(id) FROM `products`;";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            if (rs.next())
                maxID = rs.getInt(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());//todo
        }

        return maxID;
    }

    @Override
    public int compareTo(Product p2) {
        // todo
//        if (this instanceof Digital) {
//            if (this instanceof Laptop  && !(p2 instanceof Laptop))
//                return 1;
//            else if (this instanceof Mobile && !(p2 instanceof Mobile))
//                return 1;
//        } else if (p2 instanceof Digital) {
//
//        } else {
//
//        }

        if (this.name.charAt(0) > p2.name.charAt(0))
            return 1;
        else if (this.name.charAt(0) < p2.name.charAt(0))
            return -1;
        else {
            if (this.avgRate > p2.avgRate)
                return 1;
            else if (this.avgRate < p2.avgRate)
                return -1;
            else {
                if (this.price < p2.price)
                    return 1;
                else if (this.price > p2.price)
                    return -1;
                else {
                    return Integer.compare(this.number, p2.number);
                }
            }
        }
    }

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(float avgRate) {
        this.avgRate = avgRate;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void addScore(Score score) {
        this.scores.add(score);
    }
}
