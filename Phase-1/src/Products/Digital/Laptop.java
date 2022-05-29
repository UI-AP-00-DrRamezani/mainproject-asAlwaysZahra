package Products.Digital;

import Products.Category;
import Products.Product.Product;

import java.util.ArrayList;

public class Laptop extends Digital {

    public static ArrayList<Laptop> laptops = new ArrayList<>();
    public static Category laptopCategory = new Category("Laptop");

    private String processor;
    private boolean isGaming;

    public Laptop (String name, String brand, double price, String description,
                  int memoryCapacity, int RAM, String OS, double weight, int size,
                  String processor, boolean isGaming)
    {
        super(name, brand, price, description, memoryCapacity, RAM, OS, weight, size);
        this.processor = processor;
        this.isGaming = isGaming;
    }

    public void editInfo(String name, String brand, double price, String description,
                         int memoryCapacity, int RAM, String OS, double weight, int size,
                         String processor, boolean isGaming)
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
        this.processor = processor;
        this.isGaming = isGaming;
    }

    public void editInfo(Laptop newProduct)
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
        this.processor = newProduct.processor;
        this.isGaming = newProduct.isGaming;
    }

    @Override
    public String toString() {
        return "Laptop{" +
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
                ", processor='" + processor + '\'' +
                ", isGaming='" + isGaming + '\'' +
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

            case "processor":
                for (Laptop laptop : laptops)
                    if (laptop.getProcessor().contains(feature))
                        filtered.add(laptop);
                break;

            case "isGaming":
                for (Laptop laptop : laptops)
                    if (laptop.isGaming == Boolean.parseBoolean(feature))
                        filtered.add(laptop);
                break;
        }

        return filtered;
    }

    // Getters and Setters ================================================

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public boolean isGaming() {
        return isGaming;
    }

    public void setGaming(boolean gaming) {
        isGaming = gaming;
    }
}