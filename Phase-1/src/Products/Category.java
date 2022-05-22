package Products;

import java.util.ArrayList;

public class Category {
    public static ArrayList<Category> allCategories = new ArrayList<>();

    private String name;
    private final ArrayList<String> features = new ArrayList<>();
    private final ArrayList<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
        allCategories.add(this);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
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

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}