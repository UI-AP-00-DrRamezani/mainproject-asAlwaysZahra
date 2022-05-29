package Products.Digital;

import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Mobile extends Digital {

    public static ArrayList<Mobile> mobiles = new ArrayList<>();
    public static Category mobileCategory = new Category("Mobile");

    private int SIMCards;
    private int cameraQuality;

    public Mobile(String name, String brand, double price, String description,
                  int memoryCapacity, int RAM, String OS, double weight, int size,
                  int SIMCards, int cameraQuality)
    {
        super(name, brand, price, description, memoryCapacity, RAM, OS, weight, size);
        this.SIMCards = SIMCards;
        this.cameraQuality = cameraQuality;
    }

    public void editInfo (String name, String brand, double price, String description,
                         int memoryCapacity, int RAM, String OS, double weight, int size,
                         int SIMCards, int cameraQuality)
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setMemoryCapacity(memoryCapacity);
        this.setRAM(RAM);
        this.setOS(OS);
        this.setWeight(weight);
        this.setSize(size);
        this.SIMCards = SIMCards;
        this.cameraQuality = cameraQuality;
    }

    public void editInfo(Mobile newProduct)
    {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setMemoryCapacity(newProduct.getMemoryCapacity());
        this.setRAM(newProduct.getRAM());
        this.setOS(newProduct.getOS());
        this.setWeight(newProduct.getWeight());
        this.setSize(newProduct.getSize());
        this.SIMCards = newProduct.SIMCards;
        this.cameraQuality = newProduct.cameraQuality;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", memoryCapacity='" + getMemoryCapacity() + '\'' +
                ", RAM='" + getRAM() + '\'' +
                ", OS='" + getOS() + '\'' +
                ", weight='" + getWeight() + '\'' +
                ", size='" + getSize() + '\'' +
                ", SIMCards='" + SIMCards + '\'' +
                ", cameraQuality='" + cameraQuality + '\'' +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter)
        {
            case "memoryCapacity":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getMemoryCapacity() == Integer.parseInt(feature))
                        filtered.add(product);
                break;

            case "RAM":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getRAM() == Integer.parseInt(feature))
                        filtered.add(product);
                break;

            case "OS":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getOS().equals(feature))
                        filtered.add(product);
                break;

            case "weight":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getWeight() == Double.parseDouble(feature))
                        filtered.add(product);
                break;

            case "size":
                for (Product product : digitalCategory.getProducts())
                    if (((Digital) product).getSize() == Integer.parseInt(feature))
                        filtered.add(product);
                break;

            case "SIMCards":

                for (Mobile mobile : mobiles)
                    if (mobile.SIMCards == Integer.parseInt(feature))
                        filtered.add(mobile);

                break;
            case "cameraQuality":

                for (Mobile mobile : mobiles)
                    if (mobile.cameraQuality == Integer.parseInt(feature))
                        filtered.add(mobile);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public int getSIMCards() {
        return SIMCards;
    }

    public void setSIMCards(int SIMCards) {
        this.SIMCards = SIMCards;
    }

    public int getCameraQuality() {
        return cameraQuality;
    }

    public void setCameraQuality(int cameraQuality) {
        this.cameraQuality = cameraQuality;
    }
}