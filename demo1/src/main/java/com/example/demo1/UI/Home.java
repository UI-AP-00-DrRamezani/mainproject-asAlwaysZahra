package com.example.demo1.UI;

import com.example.demo1.Accounts.Admin.Admin;
import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.DataBase.CartsTable;
import com.example.demo1.DataBase.ProductsTable;
import com.example.demo1.DataBase.UsersTable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Home extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\Home.fxml").toURI().toURL());
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.setFullScreen(true);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ProductsTable.readProducts();
        UsersTable.readUsers();
        CartsTable.readCarts();
//        for (Product p : ProductManager.allProducts)
//            System.out.println(p);
//        for (Account acc : AdminManager.allUsers)
//            System.out.println(acc);
//        System.out.println(Admin.getAdmin());
        AdminManager.allUsers.add(Admin.getAdmin());
        launch();
    }
}