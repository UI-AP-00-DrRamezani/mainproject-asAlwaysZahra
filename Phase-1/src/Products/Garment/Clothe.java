package Products.Garment;

import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Clothe extends Garment {

    public static ArrayList<Clothe> clothes = new ArrayList<>();
    public static Category clotheCategory = new Category("Clothe");

    private String size; // todo enum????????
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

    public void editInfo(Clothe newProduct)
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

    public enum ClotheKind {
        T_SHIRT, PANTS, SKIRT, JEANS, COAT, JACKET, SCARF
    }

    @Override
    public String toString() {
        return "Clothe{" +
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
                for (Clothe clothe : clothes)
                    if (clothe.size.equals(feature))
                        filtered.add(clothe);
                break;

            case "kind":
                for (Clothe clothe : clothes)
                    if (clothe.kind.equals(ClotheKind.valueOf(feature)))
                        filtered.add(clothe);
                break;
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