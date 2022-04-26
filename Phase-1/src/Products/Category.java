package Products;

import java.util.ArrayList;

public class Category {
    public static ArrayList<Category> allCategories = new ArrayList<>();

    private String name;
    private ArrayList<String> features = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
        allCategories.add(this);
    }

    // Getters and Setters ================================================

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFeature(String feature) {
        this.features.add(feature);
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}