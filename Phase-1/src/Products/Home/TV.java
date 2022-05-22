package Products.Home;

import Products.Product;

import java.util.ArrayList;

public class TV extends HomeThings {

    public static ArrayList<TV> tvs = new ArrayList<>();

    private String pictureQuality;
    private int screenSize;

    public TV(String name, String brand, double price, String description,
              String energyLabel, boolean guarantee,
              String pictureQuality, int screenSize)
    {
        super(name, brand, price, description, energyLabel, guarantee);
        this.pictureQuality = pictureQuality;
        this.screenSize = screenSize;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String energyLabel, boolean guarantee,
                         String pictureQuality, int screenSize)
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setEnergyLabel(energyLabel);
        this.setGuarantee(guarantee);
        this.pictureQuality = pictureQuality;
        this.screenSize = screenSize;
    }

    @Override
    public String toString(){
        return "TV{" +
                "name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee='" + isGuarantee() + '\'' +
                ", pictureQuality='" + pictureQuality + '\'' +
                ", screenSize='" + screenSize + '\'' +
                '}';
    }

    @Override
    public ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        if (filter.equals("pictureQuality")) {

            for (TV tv : tvs)
                if (tv.pictureQuality.equals(feature))
                    filtered.add(tv);

        } else if (filter.equals("screenSize")) {

            for (TV tv : tvs)
                if (tv.screenSize == Integer.parseInt(feature))
                    filtered.add(tv);
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getPictureQuality() {
        return pictureQuality;
    }

    public void setPictureQuality(String pictureQuality) {
        this.pictureQuality = pictureQuality;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
