package Products.Garment;

<<<<<<< HEAD
import Products.Category;
import Products.Product.Product;

public abstract class Garment extends Product {

    public static Category garmentCategory = new Category("Garment");
=======
import Products.Product;
import UserInterface.Category;

import java.util.ArrayList;

public abstract class Garment extends Product {
    public static ArrayList<Garment> allGarment = new ArrayList<>();
    public static Category garment = new Category("garment");
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    private String country;
    private String material;

<<<<<<< HEAD
    public Garment(String name, String brand, double price, String description,
                   String country, String material)
    {
        super(name, brand, price, description);
        this.country = country;
        this.material = material;
    }

    // Getters and Setters ================================================
=======
    public Garment(String name,
            String brand,
            double price,
            String description,
            String country,
            String material) {

        super(name, brand, price, description);
        this.country = country;
        this.material = material;

        allGarment.add(this);
        garment.addProduct(this);
    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}