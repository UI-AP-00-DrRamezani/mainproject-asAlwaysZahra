package Products.Home;

<<<<<<< HEAD
import Products.Category;
import Products.Product.Product;

public abstract class HomeThings extends Product {

    public static Category homeCategory = new Category("Home");

    private String energyLabel; // TODO enum??
    private boolean guarantee;

    public HomeThings(String name, String brand, double price, String description,
                      String energyLabel, boolean guarantee)
=======
import Products.Product;
import UserInterface.Category;

import java.util.ArrayList;

public abstract class HomeThings extends Product {
    public static ArrayList<HomeThings> allHomeThings = new ArrayList<>();
    public static Category home = new Category("home");

    private String energyLabel;
    private boolean guarantee;

    public HomeThings(String name,
               String brand,
               double price,
               String description,
               String energyLabel,
               boolean guarantee)
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    {
        super(name, brand, price, description);
        this.energyLabel = energyLabel;
        this.guarantee = guarantee;
<<<<<<< HEAD
    }

    // Getters and Setters ================================================
=======

        allHomeThings.add(this);
        home.addProduct(this);
    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    public String getEnergyLabel() {
        return energyLabel;
    }

    public void setEnergyLabel(String energyLabel) {
        this.energyLabel = energyLabel;
    }

    public boolean isGuarantee() {
        return guarantee;
    }

    public void setGuarantee(boolean guarantee) {
        this.guarantee = guarantee;
    }
}