package Products;

import Accounts.Account;

public class Comment {

    private static int ID_COUNTER = 1;
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
    }

    public enum Status {
        WAITING, ACCEPTED, UNCONFIRMED
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", user='" + user.getUsername() + '\'' +
                ", product='" + product.getName() + '\'' +
                ", text='" + text + '\'' +
                ", status='" + stat + '\'' +
                ", didBuy='" + didBuy + '\'' +
                '}';
    }

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

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
}
