package Factors;

import Accounts.Customer.Customer;
import Products.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleFactor {
    private static int ID_COUNTER = 1;

    private int id;
    private String date;
    private double price;
    private Product product;
    private Customer customer;
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
                "id='" + id + '\'' +
                " ,date='" + date + '\'' +
                ", price='" + price + '\'' + '\n' +
                ", product='" + product.toString() + '\'' + '\n' +
                ", customer='" + customer.toString() + '\'' + '\n' +
                ", isPosted='" + isPosted + '\'' +
                '}';
    }

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProducts(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }
}
