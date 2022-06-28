package com.example.demo1.Products;

import com.example.demo1.Accounts.Seller.Seller;
import com.example.demo1.Products.Product.Product;

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

    @Override
    public String toString() {

        if (isAdd())
            return "AddProductRequest{" + '\n' +
                    "Product=" + product.toString() + '\n' +
                    "seller=" + seller.toString() + '\n' +
                    '}';

        if (isChange())
            return "ChangeProductRequest{" + '\n' +
                    "oldProduct=" + oldProduct.toString() + '\n' +
                    "newProduct=" + product.toString() + '\n' +
                    "seller=" + seller.toString() + '\n' +
                    '}';

        if (isRemove())
            return "RemoveProductRequest{" + '\n' +
                    "Product=" + product.toString() + '\n' +
                    "seller=" + seller.toString() + '\n' +
                    '}';

        return null;
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
