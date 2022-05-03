package Accounts;

import Accounts.Seller.Seller;
import Products.Product;

public class ProductRequest {

    private final Product product;
    private final Product oldProduct; // for change requests
    private final Seller seller;
    private final boolean add;
    private final boolean remove;
    private final boolean change;


    public ProductRequest(Product product, Product oldProduct, Seller seller,
                          boolean add, boolean remove, boolean change)
    {
        this.product = product;
        this.oldProduct = oldProduct;
        this.seller = seller;
        this.add = add;
        this.remove = remove;
        this.change = change;
    }

    // Getters and Setters ================================================

    public Product getProduct() {
        return product;
    }

    public Seller getSeller() {
        return seller;
    }

    public boolean isAdd() {
        return add;
    }

    public boolean isRemove() {
        return remove;
    }

    public boolean isChange() {
        return change;
    }

    public Product getOldProduct() {
        return oldProduct;
    }
}
