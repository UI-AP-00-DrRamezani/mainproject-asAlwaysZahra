package Products.Garment;

import Products.Product;

import java.util.ArrayList;

public class Shoes extends Garment {

    public static ArrayList<Shoes> shoes = new ArrayList<>();

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

    public enum ShoeKind {
        BOOT, SPORT, SLIPPER, WORK, SANDAL
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", material='" + getMaterial() + '\'' +
                ", size='" + size + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }

    @Override
    public ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        if (filter.equals("size")) {

            for (Shoes shoes : shoes)
                if (shoes.size == Integer.parseInt(feature))
                    filtered.add(shoes);

        } else if (filter.equals("kind")) {

            for (Shoes shoes : shoes)
                if (shoes.kind.equals(ShoeKind.valueOf(feature)))
                    filtered.add(shoes);
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