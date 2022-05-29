package Products.Digital;

<<<<<<< HEAD
import Products.Category;
import Products.Product.Product;

public abstract class Digital extends Product {

    public static Category digitalCategory = new Category("Digital");
=======
import UserInterface.Category;
import Products.Product;

import java.util.ArrayList;

public abstract class Digital extends Product {
    public static ArrayList<Digital> allDigitalProducts = new ArrayList<>();
    public static Category digital = new Category("digital");
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    private int memoryCapacity;
    private int RAM;
    private String OS;
    private double weight;
    private int size;

<<<<<<< HEAD
    public Digital(String name, String brand, double price, String description,
                   int memoryCapacity, int RAM, String OS, double weight, int size)
=======
    public Digital(String name,
            String brand,
            double price,
            String description,
            int memoryCapacity,
            int RAM,
            String OS,
            double weight,
            int size)
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    {
        super(name, brand, price, description);
        this.memoryCapacity = memoryCapacity;
        this.RAM = RAM;
        this.OS = OS;
        this.weight = weight;
        this.size = size;
<<<<<<< HEAD
    }

    // Getters and Setters ================================================
=======

        allDigitalProducts.add(this);
        digital.addProduct(this);
    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
