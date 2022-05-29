package UserInterface;

import Products.Product;

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

    void printProducts() {
        String format = "| %-3d | %-13s | %-8s | %-9s | %-8s | %-4s |%n";
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        System.out.format("| ID  | Product Name  | Brand    | Seller    | Price    | Rate |%n");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
        for (Product p: this.products)
            System.out.format(format, p.getId(), p.getName(), p.getBrand(),
                    p.getSeller().getUsername(), "$"+p.getPrice(), p.getAvgRate()+"⭐");
        System.out.format("+-----+---------------+----------+-----------+----------+------+%n");
    }

    public static void showAllCategories() {
        for (Category category: allCategories)
            System.out.println("✨ " + category.getName());
    }

    // Setters and Getters

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