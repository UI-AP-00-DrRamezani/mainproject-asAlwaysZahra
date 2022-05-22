package Products.Digital;

import Products.Product;

import java.util.ArrayList;

public class Mobile extends Digital {

    public static ArrayList<Mobile> mobiles = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Mobile{" +
                "name='" + getName() + '\'' +
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

    @Override
    public ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        if (filter.equals("SIMCards")) {

            for (Mobile mobile : mobiles)
                if (mobile.SIMCards == Integer.parseInt(feature))
                    filtered.add(mobile);

        } else if (filter.equals("cameraQuality")) {

            for (Mobile mobile : mobiles)
                if (mobile.cameraQuality == Integer.parseInt(feature))
                    filtered.add(mobile);
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