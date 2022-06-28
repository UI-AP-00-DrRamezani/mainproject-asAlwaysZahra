package com.example.demo1.UI;

import com.example.demo1.Accounts.Admin.AdminManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanelController {
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

    public void setInfo() {
//        AdminManager.adminModel = Admin.getAdmin();
        lbl_welcome.setText("Welcome Dear  " + AdminManager.adminModel.getFirstName());
        lbl_name.setText(AdminManager.adminModel.getFirstName() + " " + AdminManager.adminModel.getLastName());
        lbl_email.setText(AdminManager.adminModel.getEmail());
        lbl_phone.setText(AdminManager.adminModel.getPhoneNumber());
        lbl_username.setText(AdminManager.adminModel.getUsername());
        lbl_password.setText(AdminManager.adminModel.getPassword());
    }

    public void switchEditInfo() throws IOException {
        new EditInfo().start((Stage) lbl_name.getScene().getWindow());
    }

    public void back() throws IOException {
        new Home().start((Stage) lbl_name.getScene().getWindow());
    }

    public void logout() throws IOException {
        AdminManager.logout();
        new Home().start((Stage) lbl_name.getScene().getWindow());
    }
}
