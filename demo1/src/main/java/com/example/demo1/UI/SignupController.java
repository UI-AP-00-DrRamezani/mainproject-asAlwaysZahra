package com.example.demo1.UI;

import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Accounts.Seller.SellerManager;
import com.example.demo1.Exceptions.InvalidEmailException;
import com.example.demo1.Exceptions.InvalidPhoneNumberException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SignupController {

    private static HBox hbx_credit;
    private static HBox hbx_company;

    @FXML
    TextField txt_fname;
    @FXML
    TextField txt_lname;
    @FXML
    TextField txt_email;
    @FXML
    TextField txt_phone;
    @FXML
    TextField txt_username;
    @FXML
    TextField txt_password;
    @FXML
    RadioButton customerType;
    @FXML
    VBox mainVBox;
    @FXML
    Button btn_signup;
    @FXML
    TextField txt_company;
    @FXML
    Label lbl_company;
    @FXML
    TextField txt_credit;
    @FXML
    Label lbl_credit;

    // Methods ---------------------------------------------------------------------
    public void signup() {

        try {

            if (customerType.isSelected()) {
                // todo if credit was not a number
                if (CustomerManager.addCustomer(txt_username.getText(), txt_fname.getText(), txt_lname.getText(),
                        txt_email.getText(), txt_phone.getText(), txt_password.getText(), Double.parseDouble(txt_credit.getText())))
                    new Alert(Alert.AlertType.INFORMATION, "Your account created successfully").show();
                else
                    new Alert(Alert.AlertType.ERROR, "This username is not available").show();

            } else {
                if (SellerManager.addSeller(txt_username.getText(), txt_fname.getText(), txt_lname.getText(),
                        txt_email.getText(), txt_phone.getText(), txt_password.getText(), txt_company.getText()))
                    new Alert(Alert.AlertType.INFORMATION, "Your request is sent to admin successfully").show();
                else
                    new Alert(Alert.AlertType.ERROR, "This username is not available").show();
            }

        } catch (InvalidEmailException | InvalidPhoneNumberException ex) {
            // insert to Data Base
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/shopDataBase", "zahra", "zahra")) {

                Class.forName("com.mysql.cj.jdbc.Driver");
                String sql = String.format("INSERT INTO `errorlogs` (section, error) VALUES ('%s', '%s');",
                        "Signup", ex.getMessage());
                Statement s = connection.prepareStatement(sql);
                s.execute(sql);

            } catch (Exception ignored) {
            }

            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }

    // -----------------------------------------------------------------------------
    public void getCompany() {
        mainVBox.getChildren().remove(hbx_credit);
        hbx_company = new HBox();
        hbx_company.getChildren().add(lbl_company);
        hbx_company.getChildren().add(txt_company);
        mainVBox.getChildren().add(mainVBox.getChildren().size() - 1, hbx_company);
    }

    // -----------------------------------------------------------------------------
    public void getCredit() {
        mainVBox.getChildren().remove(hbx_company);
        hbx_credit = new HBox();
        hbx_credit.getChildren().add(lbl_credit);
        hbx_credit.getChildren().add(txt_credit);
        mainVBox.getChildren().add(mainVBox.getChildren().size() - 1, hbx_credit);
    }

    // -----------------------------------------------------------------------------
    public void back() throws IOException {
        new Home().start((Stage) lbl_company.getScene().getWindow());
    }
}