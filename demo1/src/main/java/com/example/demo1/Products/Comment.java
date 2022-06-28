package com.example.demo1.Products;

import com.example.demo1.Accounts.Account;
import com.example.demo1.Products.Product.Product;

public class Comment {

    private static int ID_COUNTER = 1;
    private final int id;
    private final Account user;
    private final Product product;
    private String text;
    private Status stat;
    private boolean didBuy;

    public Comment(Account user, Product product, String text, boolean didBuy) {
        this.id = ID_COUNTER++;
        this.user = user;
        this.product = product;
        this.text = text;
        this.stat = Status.WAITING;
        this.didBuy = didBuy;
    }

    public enum Status {
        WAITING, ACCEPTED, UNCONFIRMED
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", text='" + text + '\'' +
                ", stat=" + stat +
                ", didBuy=" + didBuy +
                '}';
    }

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

    public Account getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }

    public boolean getDidBuy() {
        return didBuy;
    }

    public void setDidBuy(boolean didBuy) {
        this.didBuy = didBuy;
    }
}
