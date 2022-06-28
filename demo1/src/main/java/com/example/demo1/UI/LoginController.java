package com.example.demo1.UI;

import com.example.demo1.Accounts.Account;
import com.example.demo1.Accounts.Admin.Admin;
import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Accounts.Seller.Seller;
import com.example.demo1.Accounts.Seller.SellerManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField username_field;
    @FXML
    TextField password_field;

    public void login() throws IOException {

        for (Account acc : AdminManager.allUsers)
            if (username_field.getText().equals(acc.getUsername()) &&
                    password_field.getText().equals(acc.getPassword())) {

                if (acc instanceof Admin) {
                    AdminManager.login(username_field.getText(), password_field.getText());
                    new AdminPanel().start((Stage) username_field.getScene().getWindow());

                } else if (acc instanceof Customer) {
                    CustomerManager.login((Customer) acc);
                    new CustomerPanel().start((Stage) username_field.getScene().getWindow());

                } else if (acc instanceof Seller) {
                    SellerManager.login(username_field.getText(), password_field.getText());
                    new SellerPanel().start((Stage) username_field.getScene().getWindow());
                }
            }
    }

    public void back() throws IOException {
        new Home().start((Stage) username_field.getScene().getWindow());
    }

}