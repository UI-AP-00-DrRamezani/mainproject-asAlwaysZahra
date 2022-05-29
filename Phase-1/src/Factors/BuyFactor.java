package Factors;

import Accounts.Seller.Seller;
import Products.Product.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuyFactor {
    private static int ID_COUNTER = 1;

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

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
