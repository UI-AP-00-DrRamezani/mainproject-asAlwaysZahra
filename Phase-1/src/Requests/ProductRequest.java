package Requests;

import Accounts.AdminPanel;
import Accounts.Seller;
import Enums.Status;
import Products.Product;

public class ProductRequest {
    private static int ID_COUNTER = 1;

    private int id;
    private Seller seller;
    private Product product;
    private boolean change;
    private boolean add;
    private boolean remove;
    private Status stat = Status.WAITING;

    public ProductRequest (Product product, Seller seller, boolean add, boolean remove)
    {
        this.id = ID_COUNTER++;
        this.product = product;
        this.seller = seller;
        this.change = change;
        this.add = add;
        this.remove = remove;

        AdminPanel.productRequests.add(this);
    }

    public void printRequest () {
        System.out.println("Name: " + this.product.getName());
        System.out.println("Brand: " + this.product.getBrand());
        System.out.println("Price: " + this.product.getPrice());
        System.out.println("Description: " + this.product.getDescription());
        System.out.println("\"Special features\"");
        this.product.specialFeatures();
        System.out.println("*"+this.getStat());
        System.out.println("-----------------------------------");
    }

    // Setters and Getters

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
