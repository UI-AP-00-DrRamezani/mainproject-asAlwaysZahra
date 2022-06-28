package com.example.demo1.UI;

import com.example.demo1.DataBase.ProductsTable;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProductsPage extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\ProductsPage.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        ProductsPageController pageController = fxmlLoader.getController();
        pageController.addProduct();
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ProductsTable.readProducts();
        for (Product p : ProductManager.allProducts)
            System.out.println(p);
        launch();
    }
}
