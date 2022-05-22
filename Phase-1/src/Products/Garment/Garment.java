package Products.Garment;

import Products.Category;
import Products.Product;

public abstract class Garment extends Product {

    public static Category garment = new Category("garment");

    private String country;
    private String material;

    public Garment(String name, String brand, double price, String description,
                   String country, String material)
    {
        super(name, brand, price, description);
        this.country = country;
        this.material = material;
    }

    // Getters and Setters ================================================

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