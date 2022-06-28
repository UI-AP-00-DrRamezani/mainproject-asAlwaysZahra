package com.example.demo1.UI;

import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ProductsPageController2 {

    //    int page = 1;
    int productNumber = -1;
    ArrayList<Product> products = ProductManager.allProducts;

    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<>(products);
    }

    @FXML
    VBox info;
    @FXML
    ListView<Product> productsList;

    public void addProducts() {

//        int startIndex = page * 9 - 9;
//        int endIndex = page * 9;

        // add items to ListView
        for (Product product : products) productsList.getItems().add(product);

        // listener for showing information
        productsList.getSelectionModel().selectedItemProperty().addListener((observableValue, product, t1) -> {
            productNumber = productsList.getSelectionModel().getSelectedIndex();
            showInfo(t1);
        });
    }

    public void showInfo(Product t1) {
        info.getChildren().clear();

        info.getChildren().add(new Label("Name:  " + t1.getName()));

        info.getChildren().add(new Label("Brand:  " + t1.getBrand()));

        info.getChildren().add(new Label("Price:  " + t1.getPrice()));

        info.getChildren().add(new Label("Description:"));
        info.getChildren().add(new Label(t1.getDescription()));

        t1.addToGUI(info, t1);
    }

    public void nextPage() {
        if (productNumber < products.size() - 1)
            showInfo(productsList.getItems().get(++productNumber));
    }

    public void previousPage() {
        if (productNumber > 0)
            showInfo(productsList.getItems().get(--productNumber));
    }

    public void back() throws IOException {
        new Home().start((Stage) productsList.getScene().getWindow());
    }
}
