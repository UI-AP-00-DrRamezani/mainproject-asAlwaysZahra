package com.example.demo1.Products;

import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Products.Product.Product;

public class Score {

    private final Customer user;
    private final int score;
    private final Product product;

    public Score(Customer user, int score, Product product) {
        this.user = user;

        if (score > 5 || score < 0)
            this.score = 0;
        else
            this.score = score;

        this.product = product;
    }

    @Override
    public String toString() {
        return "Score{" +
                "user=" + user +
                ", score=" + score +
                ", product=" + product +
                '}';
    }

    // Getters and Setters ================================================

    public Customer getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public Product getProduct() {
        return product;
    }
}
