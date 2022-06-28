package com.example.demo1.UI;

import com.example.demo1.Products.Product.ProductManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GetBrand extends Application {

    @FXML
    Label lbl1;
    @FXML
    TextField field1;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\GetBrand.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
//        stage.setFullScreen(true);
//        stage.setTitle("Home");
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickOK() throws IOException {
        new ProductsPage2(ProductManager.filterByBrand(field1.getText(), ProductManager.allCategory)).start((Stage) lbl1.getScene().getWindow());
    }

    public void onClickCancel() throws IOException {
        new Home().start((Stage) lbl1.getScene().getWindow());
    }

}
