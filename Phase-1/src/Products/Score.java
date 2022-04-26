package Products;

import Accounts.Customer.Customer;

public class Score {

    private Customer user;
    private int score;
    private Product product;

    public Score(Customer user, int score, Product product) {
        this.user = user;
        this.score = score;
        this.product = product;
    }

    // Getters and Setters ================================================

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
