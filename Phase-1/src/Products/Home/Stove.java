package Products.Home;

<<<<<<< HEAD
import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Stove extends HomeThings {

    public static ArrayList<Stove> stoves = new ArrayList<>();
    public static Category stoveCategory = new Category("Stove");

=======
public class Stove extends HomeThings {
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    private int flames;
    private String material;
    private boolean withOven;

<<<<<<< HEAD
    public Stove(String name, String brand, double price, String description,
                 String energyLabel, boolean guarantee,
                 int flames, String material, boolean withOven)
    {
=======
    public Stove(String name,
                 String brand,
                 double price,
                 String description,
                 String energyLabel,
                 boolean guarantee,
                 int flames,
                 String material,
                 boolean withOven) {
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
        super(name, brand, price, description, energyLabel, guarantee);
        this.flames = flames;
        this.material = material;
        this.withOven = withOven;
    }

<<<<<<< HEAD
    public void editInfo(String name, String brand, double price, String description,
                         String energyLabel, boolean guarantee,
                         int flames, String material, boolean withOven)
=======
    public void changeInfo(String name,
                    String brand,
                    double price,
                    String description,
                    String energyLabel,
                    boolean guarantee,
                    int flames,
                    String material,
                    boolean withOven)
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
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

<<<<<<< HEAD

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
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
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
=======
    @Override
    public void specialFeatures() {
        System.out.println("Energy label: " + super.getEnergyLabel());
        if (super.isGuarantee())
            System.out.println("Guarantee: YES");
        else
            System.out.println("Guarantee: NO");
        System.out.println("Number of flames: " + this.flames);
        System.out.println("Material: " + this.material);
        if (withOven)
            System.out.println("Have oven: YES");
        else
            System.out.println("Have oven: NO");
    }

    @Override
    public void categoryFilter() {

    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

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
