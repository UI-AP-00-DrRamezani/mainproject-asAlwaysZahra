package Products.Home;

<<<<<<< HEAD
import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Refrigerator extends HomeThings {

    public static ArrayList<Refrigerator> refrigerators = new ArrayList<>();
    public static Category refrigeratorCategory = new Category("Refrigerator");

=======
public class Refrigerator extends HomeThings {
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    private int capacity;
    private String kind;
    private boolean withFreezer;

<<<<<<< HEAD
    public Refrigerator(String name, String brand, double price, String description,
                        String energyLabel, boolean guarantee,
                        int capacity, String kind, boolean withFreezer)
    {
=======
    public Refrigerator(String name,
                        String brand,
                        double price,
                        String description,
                        String energyLabel,
                        boolean guarantee,
                        int capacity,
                        String kind,
                        boolean withFreezer) {
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
        super(name, brand, price, description, energyLabel, guarantee);
        this.capacity = capacity;
        this.kind = kind;
        this.withFreezer = withFreezer;
    }

<<<<<<< HEAD
    public void editInfo(String name, String brand, double price, String description,
                           String energyLabel, boolean guarantee,
                           int capacity, String kind, boolean withFreezer)
=======
    public void changeInfo(String name,
                    String brand,
                    double price,
                    String description,
                    String energyLabel,
                    boolean guarantee,
                    int capacity,
                    String kind,
                    boolean withFreezer)
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setEnergyLabel(energyLabel);
        this.setGuarantee(guarantee);
        this.setCapacity(capacity);
        this.setKind(kind);
        this.withFreezer = withFreezer;
    }

<<<<<<< HEAD
    public void editInfo(Refrigerator newProduct)
    {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setEnergyLabel(newProduct.getEnergyLabel());
        this.setGuarantee(newProduct.isGuarantee());
        this.setCapacity(newProduct.getCapacity());
        this.setKind(newProduct.getKind());
        this.setWithFreezer(newProduct.isWithFreezer());
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee='" + isGuarantee() + '\'' +
                ", capacity='" + capacity + '\'' +
                ", kind='" + kind + '\'' +
                ", withFreezer='" + withFreezer + '\'' +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter)
        {
            case "energyLabel":
                for (Product product : homeCategory.getProducts())
                    if (((HomeThings) product).getEnergyLabel().equals(feature))
                        filtered.add(product);
                break;

            case "guarantee":
                for (Product product : homeCategory.getProducts())
                    if (((HomeThings) product).isGuarantee() == Boolean.parseBoolean(feature))
                        filtered.add(product);
                break;

            case "capacity":
                for (Refrigerator refrigerator : refrigerators)
                    if (refrigerator.capacity == Integer.parseInt(feature))
                        filtered.add(refrigerator);

                break;

            case "kind":
                for (Refrigerator refrigerator : refrigerators)
                    if (refrigerator.kind.equals(feature))
                        filtered.add(refrigerator);

                break;

            case "withFreezer":
                for (Refrigerator refrigerator : refrigerators)
                    if (refrigerator.withFreezer == Boolean.parseBoolean(feature))
                        filtered.add(refrigerator);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================
=======
    @Override
    public void specialFeatures() {
        System.out.println("Energy label: " + super.getEnergyLabel());
        if (super.isGuarantee())
            System.out.println("Guarantee: YES");
        else
            System.out.println("Guarantee: NO");
        System.out.println("Capacity: " + this.capacity);
        System.out.println("Kind: " + this.kind);
        if (withFreezer)
            System.out.println("Have freezer: YES");
        else
            System.out.println("Have freezer: NO");
    }

    @Override
    public void categoryFilter() {

    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isWithFreezer() {
        return withFreezer;
    }

    public void setWithFreezer(boolean withFreezer) {
        this.withFreezer = withFreezer;
    }
}
