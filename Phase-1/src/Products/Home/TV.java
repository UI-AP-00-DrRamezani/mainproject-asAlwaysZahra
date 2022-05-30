package Products.Home;

import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class TV extends HomeThings {

    public static ArrayList<TV> tvs = new ArrayList<>();
    public static Category tvCategory = new Category("TV");

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


    public void editInfo(TV newProduct)
    {
        this.setName(newProduct.getName());
        this.setBrand(newProduct.getBrand());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setEnergyLabel(newProduct.getEnergyLabel());
        this.setGuarantee(newProduct.isGuarantee());
        this.setPictureQuality(newProduct.getPictureQuality());
        this.setScreenSize(newProduct.getScreenSize());
    }

    @Override
    public String toString(){
        return "TV{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee='" + isGuarantee() + '\'' +
                ", pictureQuality='" + pictureQuality + '\'' +
                ", screenSize='" + screenSize + '\'' +
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

            case "pictureQuality":

                for (TV tv : tvs)
                    if (tv.pictureQuality.equals(feature))
                        filtered.add(tv);

                break;
            case "screenSize":

                for (TV tv : tvs)
                    if (tv.screenSize == Integer.parseInt(feature))
                        filtered.add(tv);
                break;
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
