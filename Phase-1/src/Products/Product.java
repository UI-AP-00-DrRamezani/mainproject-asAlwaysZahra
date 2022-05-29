package Products;

import java.util.ArrayList;

import Accounts.Seller;
import UserInterface.Category;
import UserInterface.Comment;

public abstract class Product {
    public static ArrayList<Product> allProducts = new ArrayList<>();
    public static Category all = new Category("all");
    private static int ID_COUNTER = 100;

    private int id;
    private String name;
    private String brand;
    private double price;
    private Seller seller;
    private int number; // number of this product
    private String description;
    private ArrayList<Double> scores = new ArrayList<>();
    private double avgRate;
    private final ArrayList<Comment> comments = new ArrayList<>();

    public Product(String name,
                   String brand,
                   double price,
                   String description)
    {
        this.id = ID_COUNTER++;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;

        allProducts.add(this);
        all.addProduct(this);
    }

    public void calculateAverageRate() {
        double sum = 0;
        for (double score : this.scores)
            sum += score;
        this.avgRate = sum / (double) this.scores.size();
    }

    public static void showAllProducts() {
        for (Product p: allProducts) {
            p.showProduct();
            System.out.println("=================================");
        }
    }

    public abstract void specialFeatures();

    public abstract void categoryFilter();

    public void showProduct() {
        System.out.println("Product: " + this.id + " - " + this.name);
        System.out.println("Average Rate: " + this.avgRate + "⭐");
        System.out.println("Brand: " + this.brand);
        System.out.println("Price: $" + this.price);
        System.out.println("Number of product available: " + this.number);
        System.out.println("Seller: " + this.seller.getUsername());
        System.out.println("Description ---------------\n" + this.description);
        System.out.println("Special features ----------");
        specialFeatures();
        System.out.println("\n===========================\n");
    }

    // Setters and Getters

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

    public ArrayList<Double> getScores() {
        return scores;
    }

    public void addScore(double score) {
        this.scores.add(score);
    }
}