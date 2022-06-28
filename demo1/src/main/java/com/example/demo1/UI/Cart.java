package com.example.demo1.UI;

import com.example.demo1.Products.Product.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cart extends Application {

    ArrayList<Product> productList;

    public Cart(ArrayList<Product> products) {
        this.productList = new ArrayList<>(products);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\Cart.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());

        CartController pageController = fxmlLoader.getController();
        pageController.setProducts(this.productList);
        pageController.addProducts();
        pageController.setSum();

        stage.setTitle("Cart");
        stage.setScene(scene);
        stage.show();
    }
}
