package Products.Product;

import Accounts.Seller.Seller;
import Products.Comment;
import Products.Score;

import java.util.ArrayList;

public abstract class Product {

    private static int ID_COUNTER = 100;
    private int id;
    private String name;
    private String brand;
    private double price;
    private Seller seller;
    private int number;
    private String description;
    private final ArrayList<Score> scores;
    private double avgRate;
    private final ArrayList<Comment> comments;

    public Product(String name, String brand, double price, String description)
    {
        this.id = ID_COUNTER++;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        scores = new ArrayList<>();
        comments = new ArrayList<>();
    }

    @Override
    public abstract String toString();

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
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
