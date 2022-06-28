package com.example.demo1.Products.Product;

import com.example.demo1.DataBase.ProductsTable;
import com.example.demo1.Products.Category;
import com.example.demo1.Products.Digital.Digital;
import com.example.demo1.Products.Digital.Laptop;
import com.example.demo1.Products.Digital.Mobile;
import com.example.demo1.Products.Food.Food;
import com.example.demo1.Products.Garment.Clothe;
import com.example.demo1.Products.Garment.Garment;
import com.example.demo1.Products.Garment.Shoes;
import com.example.demo1.Products.Home.HomeThings;
import com.example.demo1.Products.Home.Refrigerator;
import com.example.demo1.Products.Home.Stove;
import com.example.demo1.Products.Home.TV;
import com.example.demo1.Products.Score;

import java.util.ArrayList;

public class ProductManager {

    public static ArrayList<Product> allProducts = new ArrayList<>();
    public static Category allCategory = new Category("All");

    // Methods ---------------------------------------------------------------------
    public static void addProduct(Product product) {

        allProducts.add(product);
        allCategory.addProduct(product);

        // add to related category
        if (product instanceof Digital) {
            Digital.digitalCategory.addProduct(product);

            if (product instanceof Laptop) {
                Laptop.laptopCategory.addProduct(product);
                Laptop.laptopCategory.addProduct(product);
            }

            if (product instanceof Mobile) {
                Mobile.mobiles.add((Mobile) product);
                Mobile.mobileCategory.addProduct(product);
            }

        } else if (product instanceof Garment) {
            Garment.garmentCategory.addProduct(product);

            if (product instanceof Shoes) {
                Shoes.shoes.add((Shoes) product);
                Shoes.shoesCategory.addProduct(product);
            }

            if (product instanceof Clothe) {
                Clothe.clothes.add((Clothe) product);
                Clothe.clotheCategory.addProduct(product);
            }

        } else if (product instanceof HomeThings) {
            HomeThings.homeCategory.addProduct(product);

            if (product instanceof Refrigerator) {
                Refrigerator.refrigerators.add((Refrigerator) product);
                Refrigerator.refrigeratorCategory.addProduct(product);
            }

            if (product instanceof TV) {
                TV.tvs.add((TV) product);
                TV.tvCategory.addProduct(product);
            }

            if (product instanceof Stove) {
                Stove.stoves.add((Stove) product);
                Stove.stoveCategory.addProduct(product);
            }

        } else if (product instanceof Food) {
            Food.foodCategory.addProduct(product);
        }
    }

    // -----------------------------------------------------------------------------
    public static void removeProduct(Product product) {

        allProducts.remove(product);
        allCategory.removeProduct(product);

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

        product.setAvgRate((float) (sum / product.getScores().size()));

        // update in database
        ProductsTable.update(product);
    }

    // -----------------------------------------------------------------------------
    public static ArrayList<Product> filterByPrice(double min, double max, Category category) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product p : category.getProducts())
            if (p.getPrice() >= min && p.getPrice() <= max)
                filteredProducts.add(p);
        return filteredProducts;
    }

    // -----------------------------------------------------------------------------
    public static ArrayList<Product> filterByAvailability(Category category) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product p : category.getProducts())
            if (p.getNumber() > 0)
                filteredProducts.add(p);
        return filteredProducts;
    }

    // -----------------------------------------------------------------------------
    public static ArrayList<Product> filterByBrand(String brand, Category category) {
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

    // -----------------------------------------------------------------------------
    public static ArrayList<Product> removeFilter(Category category) {
        return category.getProducts();
    }
}
