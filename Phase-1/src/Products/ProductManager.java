package Products;

import Products.Digital.Digital;
import Products.Digital.Laptop;
import Products.Digital.Mobile;
import Products.Food.Food;
import Products.Garment.Clothe;
import Products.Garment.Garment;
import Products.Garment.Shoes;
import Products.Home.HomeThings;
import Products.Home.Refrigerator;
import Products.Home.Stove;
import Products.Home.TV;

import java.util.ArrayList;

public class ProductManager {

    public static ArrayList<Product> allProducts = new ArrayList<>();
    public static Category all = new Category("All");

    // Methods ---------------------------------------------------------------------
    public static void addProduct(Product product) {

        allProducts.add(product);
        all.addProduct(product);

        // add to related category
        if (product instanceof Digital) {
            Digital.digitalCategory.addProduct(product);

            if (product instanceof Laptop)
                Laptop.laptops.add((Laptop) product);

            if (product instanceof Mobile)
                Mobile.mobiles.add((Mobile) product);

        } else if (product instanceof Garment) {
            Garment.garmentCategory.addProduct(product);

            if (product instanceof Shoes)
                Shoes.shoes.add((Shoes) product);

            if (product instanceof Clothe)
                Clothe.clothes.add((Clothe) product);

        } else if (product instanceof HomeThings) {
            HomeThings.homeCategory.addProduct(product);

            if (product instanceof Refrigerator)
                Refrigerator.refrigerators.add((Refrigerator) product);

            if (product instanceof TV)
                TV.tvs.add((TV) product);

            if (product instanceof Stove)
                Stove.stoves.add((Stove) product);

        } else if (product instanceof Food) {
            Food.foodCategory.addProduct(product);
        }
    }
    // -----------------------------------------------------------------------------
    public static void removeProduct(Product product) {

        allProducts.remove(product);
        all.removeProduct(product);

        // remove from related category
        if (product instanceof Digital) {
            Digital.digitalCategory.removeProduct(product);

            if (product instanceof Laptop)
                Laptop.laptops.remove((Laptop) product);

            if (product instanceof Mobile)
                Mobile.mobiles.remove((Mobile) product);

        } else if (product instanceof Garment) {
            Garment.garmentCategory.removeProduct(product);

            if (product instanceof Shoes)
                Shoes.shoes.remove((Shoes) product);

            if (product instanceof Clothe)
                Clothe.clothes.remove((Clothe) product);

        } else if (product instanceof HomeThings) {
            HomeThings.homeCategory.removeProduct(product);

            if (product instanceof Refrigerator)
                Refrigerator.refrigerators.remove((Refrigerator) product);

            if (product instanceof TV)
                TV.tvs.remove((TV) product);

            if (product instanceof Stove)
                Stove.stoves.remove((Stove) product);

        } else if (product instanceof Food) {
            Food.foodCategory.removeProduct(product);
        }
    }
    // -----------------------------------------------------------------------------
    public static void editProduct(Product oldProduct, Product newProduct) {

        if (oldProduct instanceof Digital) {

            if (oldProduct instanceof Laptop)
                ((Laptop) oldProduct).editInfo((Laptop) newProduct);

            if (oldProduct instanceof Mobile)
                ((Mobile) oldProduct).editInfo((Mobile) newProduct);

        } else if (oldProduct instanceof Garment) {

            if (oldProduct instanceof Shoes)
                ((Shoes) oldProduct).editInfo((Shoes) newProduct);

            if (oldProduct instanceof Clothe)
                ((Clothe) oldProduct).editInfo((Clothe) newProduct);

        } else if (oldProduct instanceof HomeThings) {

            if (oldProduct instanceof Refrigerator)
                ((Refrigerator) oldProduct).editInfo((Refrigerator) newProduct);

            if (oldProduct instanceof TV)
                ((TV) oldProduct).editInfo((TV) newProduct);

            if (oldProduct instanceof Stove)
                ((Stove) oldProduct).editInfo((Stove) newProduct);

        } else if (oldProduct instanceof Food) {
            ((Food) oldProduct).editInfo((Food) newProduct);
        }
    }
    // -----------------------------------------------------------------------------
    public static void calculateAvgRate(Product product) {
        double sum = 0;

        for (Score score : product.getScores())
            sum += score.getScore();

        product.setAvgRate(sum / (double) product.getScores().size());
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
            if (p.getName().contains(name) || name.contains(p.getName()))
                searchProducts.add(p);
        return searchProducts;
    }
    // -----------------------------------------------------------------------------
    public static Product getProductByID(int id) {
        for (Product p : allProducts)
            if (id == p.getId())
                return p;
        return null;
    }
}
