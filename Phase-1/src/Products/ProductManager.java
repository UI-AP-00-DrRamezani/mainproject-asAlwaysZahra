package Products;

import java.util.ArrayList;

public class ProductManager {

    public static ArrayList<Product> allProducts = new ArrayList<>();
    public static Category all = new Category("All");

    // Methods ---------------------------------------------------------------------
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    // -----------------------------------------------------------------------------
    public static void removeProduct(Product product) {
        allProducts.remove(product);
    }
    // -----------------------------------------------------------------------------
    public static void calculateAvgRate(Product product) {
        double sum = 0;

        for (Score score : product.getScores())
            sum += score.getScore();

        product.setAvgRate(sum / (double) product.getScores().size());
    }
    // -----------------------------------------------------------------------------
    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }
    // -----------------------------------------------------------------------------
    public static ArrayList<Product> filterByPrice(double min, double max, Category category)
    {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product p : category.getProducts())
            if (p.getPrice() >= min && p.getPrice() <= max)
                filteredProducts.add(p);
        return filteredProducts;
    }
    // -----------------------------------------------------------------------------
    public static ArrayList<Product> filterByAvailability(Category category)
    {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product p : category.getProducts())
            if(p.getNumber() > 0)
                filteredProducts.add(p);
        return filteredProducts;
    }
    // -----------------------------------------------------------------------------
    public static ArrayList<Product> filterByBrand(String brand, Category category)
    {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product p : category.getProducts())
            if (brand.equals(p.getBrand()))
                filteredProducts.add(p);
        return filteredProducts;
    }
    // -----------------------------------------------------------------------------
    public static ArrayList<Product> search(String name, Category category) {
        ArrayList<Product> searchProducts = new ArrayList<>();
        for (Product p : category.getProducts())
            if (name.equals(p.getName()))
                searchProducts.add(p);
        return searchProducts;
    }
    // -----------------------------------------------------------------------------
}
