package com.example.demo1.UI;

import com.example.demo1.Accounts.Seller.SellerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SellerPanelController {

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
    Label lbl_company;

    public void setInfo() {
        lbl_welcome.setText("Welcome Dear  " + SellerManager.sellerModel.getFirstName());
        lbl_company.setText(SellerManager.sellerModel.getCompany());
        lbl_name.setText(SellerManager.sellerModel.getFirstName() + " " + SellerManager.sellerModel.getLastName());
        lbl_email.setText(SellerManager.sellerModel.getEmail());
        lbl_phone.setText(SellerManager.sellerModel.getPhoneNumber());
        lbl_username.setText(SellerManager.sellerModel.getUsername());
        lbl_password.setText(SellerManager.sellerModel.getPassword());
    }

    public void switchEditInfo() throws IOException {
        new EditInfo().start((Stage) lbl_name.getScene().getWindow());
    }

    public void back() throws IOException {
        new Home().start((Stage) lbl_name.getScene().getWindow());
    }

    public void logout() throws IOException {
        SellerManager.logout();
        new Home().start((Stage) lbl_name.getScene().getWindow());
    }
}
