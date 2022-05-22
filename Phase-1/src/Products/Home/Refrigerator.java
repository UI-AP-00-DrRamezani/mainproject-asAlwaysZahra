package Products.Home;

import Products.Product;

import java.util.ArrayList;

public class Refrigerator extends HomeThings {

    public static ArrayList<Refrigerator> refrigerators = new ArrayList<>();

    private int capacity;
    private String kind;
    private boolean withFreezer;

    public Refrigerator(String name, String brand, double price, String description,
                        String energyLabel, boolean guarantee,
                        int capacity, String kind, boolean withFreezer)
    {
        super(name, brand, price, description, energyLabel, guarantee);
        this.capacity = capacity;
        this.kind = kind;
        this.withFreezer = withFreezer;
    }

    public void editInfo(String name, String brand, double price, String description,
                           String energyLabel, boolean guarantee,
                           int capacity, String kind, boolean withFreezer)
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

    @Override
    public String toString() {
        return "Refrigerator{" +
                "name='" + getName() + '\'' +
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

    @Override
    public ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter)
        {
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
