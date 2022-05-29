package Products.Food;

import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Food extends Product {

    public static Category foodCategory = new Category("Food");

    private final String proDate;
    private final String expDate;

    public Food(String name, String brand, double price, String description,
                String proDate, String expDate)
    {
        super(name, brand, price, description);
        this.proDate = proDate;
        this.expDate = expDate;
    }

    public void editInfo(Food newFood)
    {
        this.setName(newFood.getName());
        this.setBrand(newFood.getBrand());
        this.setPrice(newFood.getPrice());
        this.setDescription(newFood.getDescription());
        // dates can not be edited
    }

    public void editInfo(String name, String brand, double price, String description)
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        // dates can not be edited
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", proDate='" + proDate + '\'' +
                ", expDate='" + expDate + '\'' +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        if (filter.equals("proDate")) {

            for (Product food : foodCategory.getProducts())
                if (((Food) food).proDate.equals(feature))
                    filtered.add(food);

        } else if (filter.equals("expDate")) {

            for (Product food : foodCategory.getProducts())
                if (((Food) food).expDate.equals(feature))
                    filtered.add(food);
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getProDate() {
        return proDate;
    }

    public String getExpDate() {
        return expDate;
    }
}
