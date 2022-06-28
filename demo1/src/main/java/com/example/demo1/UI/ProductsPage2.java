package com.example.demo1.UI;

import com.example.demo1.Products.Product.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProductsPage2 extends Application {

    ArrayList<Product> productList;

    public ProductsPage2(ArrayList<Product> products) {
        this.productList = new ArrayList<>(products);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\ProductsPage2.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Products");
        stage.setScene(scene);

        ProductsPageController2 pageController = fxmlLoader.getController();
        pageController.setProducts(this.productList);
        pageController.addProducts();

        stage.show();
    }

//    public static void main(String[] args) {
//        launch();
//    }
}
