package Products.Food;

<<<<<<< HEAD
import Products.Category;
import Products.Product.Product;
=======
import Products.Product;
import UserInterface.Category;
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

import java.util.ArrayList;

public class Food extends Product {
<<<<<<< HEAD

    public static Category foodCategory = new Category("Food");
=======
    public static ArrayList<Food> allFoods = new ArrayList<>();
    public static Category food = new Category("food");
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    private final String proDate;
    private final String expDate;

<<<<<<< HEAD
    public Food(String name, String brand, double price, String description,
                String proDate, String expDate)
=======
    public Food(String name,
                String brand,
                double price,
                String description,
                String proDate,
                String expDate)
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    {
        super(name, brand, price, description);
        this.proDate = proDate;
        this.expDate = expDate;
<<<<<<< HEAD
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
=======

        allFoods.add(this);
        food.addProduct(this);
    }

    public void changeInfo(String name, String brand, double price, String description)
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
<<<<<<< HEAD
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
=======
    }

    @Override
    public void specialFeatures() {
        System.out.println("Production date: " + this.proDate);
        System.out.println("Expiration date: " + this.expDate);
    }

    @Override
    public void categoryFilter() {

    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    public String getProDate() {
        return proDate;
    }

    public String getExpDate() {
        return expDate;
    }
}
