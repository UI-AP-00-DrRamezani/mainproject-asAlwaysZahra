package com.example.demo1.UI;

import com.example.demo1.Accounts.Customer.CustomerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerPanelController {

    @FXML
    Label lbl_name;
    @FXML
    Label lbl_email;
    @FXML
    Label lbl_phone;
    @FXML
    Label lbl_username;
    @FXML
    Label lbl_password;
    @FXML
    Label lbl_welcome;
    @FXML
    Label lbl_credit;

    public void setInfo() {
        lbl_welcome.setText("Welcome Dear  " + CustomerManager.customerModel.getFirstName());
        lbl_credit.setText("" + CustomerManager.customerModel.getCredit());
        lbl_name.setText(CustomerManager.customerModel.getFirstName() + " " + CustomerManager.customerModel.getLastName());
        lbl_email.setText(CustomerManager.customerModel.getEmail());
        lbl_phone.setText(CustomerManager.customerModel.getPhoneNumber());
        lbl_username.setText(CustomerManager.customerModel.getUsername());
        lbl_password.setText(CustomerManager.customerModel.getPassword());
    }

    public void switchEditInfo() throws IOException {
        new EditInfo().start((Stage) lbl_name.getScene().getWindow());
    }

    public void switchCart() throws IOException {
//        new Cart(CustomerManager.customerModel.getCart()).start((Stage) lbl_name.getScene().getWindow());
        new Cart(CustomerManager.customerModel.getCart()).start(new Stage());
    }

    public void back() throws IOException {
        new Home().start((Stage) lbl_name.getScene().getWindow());
    }

    public void logout() throws IOException {
        CustomerManager.logout();
        new Home().start((Stage) lbl_name.getScene().getWindow());
    }
}
