package Factors;

<<<<<<< HEAD
import Accounts.Customer.Customer;
import Products.Product.Product;
=======
import Products.Product;
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleFactor {
    private static int ID_COUNTER = 1;

    private int id;
    private String date;
    private double price;
    private Product product;
<<<<<<< HEAD
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
=======
    private String buyerName;
    private boolean isPosted;

    public SaleFactor(double price, Product product, String buyerName) {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");

        this.id = ID_COUNTER++;
        this.date = ft.format(now);
        this.price = price;
        this.product = product;
        this.buyerName = buyerName;
    }

    public void printFactor() {
        System.out.println("ID: " + this.id);
        System.out.println("Date: " + this.date);
        System.out.println("Price: " + this.price + "$");
        System.out.println(product.getId() + " - " + product.getName() + " - " +
                product.getPrice() + "$ - Buyer: " + this.buyerName);
        System.out.println("Posted: " + this.isPosted);
        System.out.println("================================");
    }

    // Setters and Getters
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a

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

<<<<<<< HEAD
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
=======
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
>>>>>>> 4ca75fff15a03808040f7f0e4912f53be1fb340a
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }
}
