package Products.Home;

import Products.Product;

import java.util.ArrayList;

public class Stove extends HomeThings {

    public static ArrayList<Stove> stoves = new ArrayList<>();

    private int flames;
    private String material;
    private boolean withOven;

    public Stove(String name, String brand, double price, String description,
                 String energyLabel, boolean guarantee,
                 int flames, String material, boolean withOven)
    {
        super(name, brand, price, description, energyLabel, guarantee);
        this.flames = flames;
        this.material = material;
        this.withOven = withOven;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String energyLabel, boolean guarantee,
                         int flames, String material, boolean withOven)
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setEnergyLabel(energyLabel);
        this.setGuarantee(guarantee);
        this.flames = flames;
        this.material = material;
        this.withOven = withOven;
    }


    public void editInfo(Stove newProduct)
    {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setEnergyLabel(newProduct.getEnergyLabel());
        this.setGuarantee(newProduct.isGuarantee());
        this.setFlames(newProduct.getFlames());
        this.setMaterial(newProduct.getMaterial());
        this.setWithOven(newProduct.isWithOven());
    }

    @Override
    public String toString() {
        return "Stove{" +
                "name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee='" + isGuarantee() + '\'' +
                ", flames='" + flames + '\'' +
                ", material='" + material + '\'' +
                ", withOven='" + withOven + '\'' +
                '}';
    }

    @Override
    public ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter)
        {
            case "flames":
                for (Stove stove : stoves)
                    if (stove.flames == Integer.parseInt(feature))
                        filtered.add(stove);

                break;

            case "material":
                for (Stove stove : stoves)
                    if (stove.material.equals(feature))
                        filtered.add(stove);
                break;

            case "withOven":
                for (Stove stove : stoves)
                    if (stove.withOven == Boolean.parseBoolean(feature))
                        filtered.add(stove);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public int getFlames() {
        return flames;
    }

    public void setFlames(int flames) {
        this.flames = flames;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isWithOven() {
        return withOven;
    }

    public void setWithOven(boolean withOven) {
        this.withOven = withOven;
    }
}
