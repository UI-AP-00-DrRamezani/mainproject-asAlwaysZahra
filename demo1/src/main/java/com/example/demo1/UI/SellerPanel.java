package com.example.demo1.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SellerPanel extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\SellerPanel.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Seller Panel");

        SellerPanelController panelController = fxmlLoader.getController();
        panelController.setInfo();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}