package Requests;

import Accounts.AdminPanel;
import Enums.Status;
import Products.Product;

public class ChangeProductRequest {
    private static int ID_COUNTER = 1;

    private int id;
    private Product productToChange;
    private Product newProduct;
    private Status stat;

    public ChangeProductRequest(Product productToChange, Product newProduct) {
        this.id = ID_COUNTER++;
        this.productToChange = productToChange;
        this.newProduct = newProduct;
        this.stat = Status.WAITING;

        AdminPanel.changeProductRequests.add(this);
    }

    public void printRequest () {

        System.out.println("OLD PRODUCT");
        System.out.println("Name: " + this.productToChange.getName());
        System.out.println("Brand: " + this.productToChange.getBrand());
        System.out.println("Price: " + this.productToChange.getPrice());
        System.out.println("Description: " + this.productToChange.getDescription());
        System.out.println("\"Special features\"");
        this.productToChange.specialFeatures();

        System.out.println("|||||||||||||||||||||||||||||||||||");

        System.out.println("NEW PRODUCT");
        System.out.println("Name: " + this.newProduct.getName());
        System.out.println("Brand: " + this.newProduct.getBrand());
        System.out.println("Price: " + this.newProduct.getPrice());
        System.out.println("Description: " + this.newProduct.getDescription());
        System.out.println("\"Special features\"");
        this.newProduct.specialFeatures();

        System.out.println("*"+this.getStat());
        System.out.println("-----------------------------------");

    }

    // setters and getters

    public Product getProductToChange() {
        return productToChange;
    }

    public void setProductToChange(Product productToChange) {
        this.productToChange = productToChange;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }
}
