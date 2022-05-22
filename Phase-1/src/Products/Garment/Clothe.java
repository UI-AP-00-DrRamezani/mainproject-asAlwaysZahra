package Products.Garment;

import Products.Product;

import java.util.ArrayList;

public class Clothe extends Garment {

    public static ArrayList<Clothe> clothes = new ArrayList<>();

    private String size; // enum????????
    private ClotheKind kind;

    public Clothe(String name, String brand, double price, String description,
                  String country, String material, String size, ClotheKind kind)
    {
        super(name, brand, price, description, country, material);
        this.size = size;
        this.kind = kind;
    }

    public void editInfo(String name, String brand, double price, String description,
                           String country, String material, String size, ClotheKind kind)
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

    public enum ClotheKind {
        T_SHIRT, PANTS, SKIRT, JEANS, COAT, JACKET, SCARF
    }

    @Override
    public String toString() {
        return "Clothe{" +
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

            for (Clothe clothe : clothes)
                if (clothe.size.equals(feature))
                    filtered.add(clothe);

        } else if (filter.equals("kind")) {

            for (Clothe clothe : clothes)
                if (clothe.kind.equals(ClotheKind.valueOf(feature)))
                    filtered.add(clothe);
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ClotheKind getKind() {
        return kind;
    }

    public void setKind(ClotheKind kind) {
        this.kind = kind;
    }
}