package com.example.demo1.Products.Home;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class TV extends HomeThings {

    public static ArrayList<TV> tvs = new ArrayList<>();
    public static Category tvCategory = new Category("TV");

    private String pictureQuality;
    private int screenSize;

    public TV(String name, String brand, double price, String description,
              String energyLabel, boolean guarantee,
              String pictureQuality, int screenSize) {
        super(name, brand, price, description, energyLabel, guarantee);
        this.pictureQuality = pictureQuality;
        this.screenSize = screenSize;
    }

    public TV(String name, String brand, double price, String description,
              String energyLabel, int guarantee,
              String pictureQuality, int screenSize) {
        super(name, brand, price, description, energyLabel, guarantee == 1);
        this.pictureQuality = pictureQuality;
        this.screenSize = screenSize;
    }

    public void editInfo(String name, String brand, double price, String description,
                         String energyLabel, boolean guarantee,
                         String pictureQuality, int screenSize) {
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
        this.setDescription(description);
        this.setEnergyLabel(energyLabel);
        this.setGuarantee(guarantee);
        this.pictureQuality = pictureQuality;
        this.screenSize = screenSize;
    }


    public void editInfo(TV newProduct) {
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
    public void addToGUI(VBox info, Product t1) {

        info.getChildren().add(new Label("TV"));

        info.getChildren().add(new Label("Energy Label:  " + ((TV) t1).getEnergyLabel()));

        if (isGuarantee())
            info.getChildren().add(new Label("is Guarantee:  YES"));
        else
            info.getChildren().add(new Label("is Guarantee:  NO"));

        info.getChildren().add(new Label("Picture Quality:  " + ((TV) t1).pictureQuality));

        info.getChildren().add(new Label("Screen Size:  " + ((TV) t1).screenSize));
    }

    public static void readFromDB(String url, String user, String pass) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `products` inner join `tvs` on products.id=tvs.id WHERE products.category='tv'";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                TV p = new TV(rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("energyLabel"),
                        rs.getInt("guarantee"),
                        rs.getString("pictureQuality"),
                        rs.getInt("screenSize"));

                ProductManager.addProduct(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertTV() {

        if (isGuarantee())
            return String.format("INSERT INTO `tvs` (`id`, `energyLabel`, `guarantee`, `pictureQuality`, `screenSize`) " +
                            "VALUES ('%d', '%s', '%d', '%s', '%d')",
                    getId(), getEnergyLabel(), 1, pictureQuality, screenSize);
        else
            return String.format("INSERT INTO `tvs` (`id`, `energyLabel`, `guarantee`, `pictureQuality`, `screenSize`) " +
                            "VALUES ('%d', '%s', '%d', '%s', '%d')",
                    getId(), getEnergyLabel(), 0, pictureQuality, screenSize);
    }

    //    @Override
    public String toString2() {
        return "TV{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", energyLabel='" + getEnergyLabel() + '\'' +
                ", guarantee=" + isGuarantee() +
                ", pictureQuality='" + pictureQuality + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }

    public static ArrayList<Product> categoryFilter(String filter, String feature) {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (filter) {
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
