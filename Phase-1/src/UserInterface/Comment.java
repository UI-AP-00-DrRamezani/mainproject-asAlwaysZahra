package UserInterface;

import Accounts.*;
import Enums.Status;
import Products.Product;

public class Comment {
    private static int ID_COUNTER;

    private final int id;
    private Account user;
    private Product product;
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

        //AdminPanel.allComments.add(this);
    }

    void printComment() {
        System.out.println("User: " + this.user.getUsername());
        if (didBuy)
            System.out.println("*(this user didnt buy the product)");
        else
            System.out.println("*(this user has bought the product)");
        System.out.println(this.text);
    }

    // Setters and Getters

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public int getId() {
        return id;
    }
}
