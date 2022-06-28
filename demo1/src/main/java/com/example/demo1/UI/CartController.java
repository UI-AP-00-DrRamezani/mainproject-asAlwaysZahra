package com.example.demo1.UI;

import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Exceptions.MoneyInventoryException;
import com.example.demo1.Exceptions.ProductInventoryException;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CartController {

    int productNumber = -1;
    ArrayList<Product> products = ProductManager.allProducts;

    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<>(products);
    }

    @FXML
    VBox info;
    @FXML
    ListView<Product> productsList;
    @FXML
    Label lbl_sum;
    @FXML
    Label lbl_credit;

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

    public void switchShippingPage() {
        try {
            CustomerManager.buy();
        } catch (MoneyInventoryException | ProductInventoryException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void setSum() {
        double s = 0;
        for (Product p: CustomerManager.customerModel.getCart())
            s += p.getPrice();
        lbl_sum.setText("Sum: "+s);
        lbl_credit.setText("Credit: "+CustomerManager.customerModel.getCredit());
    }

    public void back() throws IOException {
//        new Home().start((Stage) productsList.getScene().getWindow());
        ((Stage) productsList.getScene().getWindow()).close();
    }
}
