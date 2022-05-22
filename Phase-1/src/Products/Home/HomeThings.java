package Products.Home;

import Products.Category;
import Products.Product;

public abstract class HomeThings extends Product {

    public static Category homeCategory = new Category("home");

    private String energyLabel;
    private boolean guarantee;

    public HomeThings(String name, String brand, double price, String description,
                      String energyLabel, boolean guarantee)
    {
        super(name, brand, price, description);
        this.energyLabel = energyLabel;
        this.guarantee = guarantee;
    }

    // Getters and Setters ================================================

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