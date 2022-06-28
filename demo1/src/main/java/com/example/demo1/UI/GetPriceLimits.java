package com.example.demo1.UI;

import com.example.demo1.Products.Product.ProductManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class GetPriceLimits extends Application {

    @FXML
    Label lbl1;
    @FXML
    Label lbl2;
    @FXML
    TextField field1;
    @FXML
    TextField field2;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\GetPriceLimits.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
//        stage.setFullScreen(true);
//        stage.setTitle("Home");
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickOK() throws IOException {
        int min = Integer.parseInt(field1.getText());
        int max = Integer.parseInt(field2.getText());

        new ProductsPage2(ProductManager.filterByPrice(min, max, ProductManager.allCategory)).start((Stage) lbl1.getScene().getWindow());
    }

    public void onClickCancel() throws IOException {
        new Home().start((Stage) lbl1.getScene().getWindow());
    }

}
