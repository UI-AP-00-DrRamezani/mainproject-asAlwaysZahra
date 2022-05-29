package UserInterface;

import Accounts.Buyer;
import Products.Product;

public class Score {
    private Buyer user;
    private int score;
    private Product product;

    public Score(Buyer user, int score, Product product) {
        this.user = user;
        this.score = score;
        this.product = product;
    }

    // Setters and Getters

    public Buyer getUser() {
        return user;
    }

    public void setUser(Buyer user) {
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