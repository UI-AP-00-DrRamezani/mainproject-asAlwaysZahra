package Products.Garment;

import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Shoes extends Garment {

    public static ArrayList<Shoes> shoes = new ArrayList<>();
    public static Category shoesCategory = new Category("Shoes");

    private int size;
    private ShoeKind kind;

    public Shoes(String name, String brand, double price, String description,
                 String country, String material, int size, ShoeKind kind)
    {
        super(name, brand, price, description, country, material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(String name, String brand, double price, String description,
                           String country, String material, int size, ShoeKind kind)
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setCountry(country);
        this.setMaterial(material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(Shoes newProduct)
    {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setCountry(newProduct.getCountry());
        this.setMaterial(newProduct.getMaterial());
        this.setSize(newProduct.size);
        this.setKind(newProduct.kind);
    }

    public enum ShoeKind {
        BOOT, SPORT, SLIPPER, WORK, SANDAL
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", material='" + getMaterial() + '\'' +
                ", size='" + size + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter) {

            case "country":
                for (Product product : garmentCategory.getProducts())
                    if (((Garment) product).getCountry().equals(feature))
                        filtered.add(product);
                break;

            case "material":
                for (Product product : garmentCategory.getProducts())
                    if (((Garment) product).getMaterial().equals(feature))
                        filtered.add(product);
                break;

            case "size":
                for (Shoes shoes : shoes)
                    if (shoes.size == Integer.parseInt(feature))
                        filtered.add(shoes);
                break;

            case "kind":
                for (Shoes shoes : shoes)
                    if (shoes.kind.equals(ShoeKind.valueOf(feature)))
                        filtered.add(shoes);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ShoeKind getKind() {
        return kind;
    }

    public void setKind(ShoeKind kind) {
        this.kind = kind;
    }
}