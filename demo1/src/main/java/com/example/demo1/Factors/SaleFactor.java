package com.example.demo1.Factors;

import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Products.Product.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleFactor {
    private static int ID_COUNTER = 1;

    private final int id;
    private final String date;
    private final double price;
    private final Product product;
    private final Customer customer;
    private boolean isPosted;

    public SaleFactor(double price, Product product, Customer customer) {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        this.date = ft.format(now);

        this.id = ID_COUNTER++;
        this.price = price;
        this.product = product;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "SaleFactor{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", product=" + product +
                ", customer=" + customer +
                ", isPosted=" + isPosted +
                '}';
    }

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }
}
