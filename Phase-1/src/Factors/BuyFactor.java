package Factors;

<<<<<<< HEAD
import Accounts.Seller.Seller;
import Products.Product.Product;
=======
import Products.Product;
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuyFactor {
    private static int ID_COUNTER = 1;

<<<<<<< HEAD
    private final int id;
    private String date;
    private double payment;
    private ArrayList<Product> products;
    private ArrayList<Seller> sellers = new ArrayList<>();
    private boolean isDelivered;

    public BuyFactor(double payment, ArrayList<Product> products)
    {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        this.date = ft.format(now);

        this.id = ID_COUNTER++;
        this.payment = payment;
        this.products = products;
        // this.isDelivered = isDelivered; ??
    }

    @Override
    public String toString() {
        return "BuyFactor{" +
                "id='" + id + '\'' +
                " ,date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", isDelivered='" + isDelivered + '\'' +
                '}';
    }

    // Getters and Setters ================================================
=======
    private int id;
    private String date;
    private double payment;
    private ArrayList<Product> products;
    private ArrayList<String> sellersNames = new ArrayList<>();
    private boolean isDelivered;

    public BuyFactor(double payment, ArrayList<Product> products) {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        this.id = ID_COUNTER++;
        this.date = ft.format(now);
        this.payment = payment;
        this.products = products;
        // this.isDelivered = isDelivered;
    }

    public void printFactor() {
        System.out.println("ID: " + this.id);
        System.out.println("Date: " + this.date);
        System.out.println("Payment: " + this.payment+"$");
        System.out.println("Products ------------------------");
        int i = 1;
        for (Product p: this.products)
            System.out.println(i++ + ". " + p.getId() + " - " + p.getName() + " - " +p.getPrice() + "$ - Seller: " + p.getSeller().getUsername());
        System.out.println("Delivered: " + this.isDelivered);
        System.out.println("================================");
    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return payment;
    }

    public void setPrice(double price) {
        this.payment = price;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products.add(products);
    }

<<<<<<< HEAD
    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
=======
    public ArrayList<String> getSellersNames() {
        return sellersNames;
    }

    public void addSellerName(String seller) {
        this.sellersNames.add(seller);
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
