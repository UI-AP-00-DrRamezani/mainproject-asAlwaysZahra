package com.example.demo1.UI;

import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Accounts.Seller.SellerManager;
import com.example.demo1.Exceptions.InvalidEmailException;
import com.example.demo1.Exceptions.InvalidPhoneNumberException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EditInfo extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\feres\\IdeaProjects\\demo1\\src\\main\\resources\\com\\example\\demo1\\EditInfo.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Edit Information");
        stage.setScene(scene);
        stage.show();
    }

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
    RadioButton sellerType;
    @FXML
    VBox mainVBox;
    @FXML
    Button btn_signup;
    @FXML
    TextField txt_company;
    @FXML
    Label lbl_company;

    // Methods ---------------------------------------------------------------------
    public void edit() {
        try {

            if (customerType.isSelected()) {

                if (CustomerManager.editInfo(txt_username.getText(), txt_fname.getText(), txt_lname.getText(),
                        txt_email.getText(), txt_phone.getText(), txt_password.getText())) {

                    new Alert(Alert.AlertType.INFORMATION, "Your information changed successfully").show();
                    new CustomerPanel().start((Stage) lbl_company.getScene().getWindow());

                } else
                    new Alert(Alert.AlertType.ERROR, "This username is not available").show();

            } else if (sellerType.isSelected()) {

                if (SellerManager.editInfo(txt_username.getText(), txt_fname.getText(), txt_lname.getText(),
                        txt_email.getText(), txt_phone.getText(), txt_password.getText(), txt_company.getText())) {

                    new Alert(Alert.AlertType.INFORMATION, "Your request is sent to admin successfully").show();
                    new SellerPanel().start((Stage) lbl_company.getScene().getWindow());

                } else
                    new Alert(Alert.AlertType.ERROR, "This username is not available").show();

            } else {

                if (AdminManager.editInfo(txt_username.getText(), txt_fname.getText(), txt_lname.getText(),
                        txt_email.getText(), txt_phone.getText(), txt_password.getText())) {

                    new Alert(Alert.AlertType.INFORMATION, "Your information changed successfully").show();
                    new AdminPanel().start((Stage) lbl_company.getScene().getWindow());

                } else
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // -----------------------------------------------------------------------------
    public void getCompany() {
        hbx_company = new HBox();
        hbx_company.getChildren().add(lbl_company);
        hbx_company.getChildren().add(txt_company);
        mainVBox.getChildren().add(mainVBox.getChildren().size() - 1, hbx_company);
    }

    // -----------------------------------------------------------------------------
    public void remove() {
        mainVBox.getChildren().remove(hbx_company);
    }

    // -----------------------------------------------------------------------------
    public void back() throws IOException {

        if (AdminManager.adminModel != null)
            new AdminPanel().start((Stage) lbl_company.getScene().getWindow());

        if (CustomerManager.customerModel != null)
            new CustomerPanel().start((Stage) lbl_company.getScene().getWindow());

        if (SellerManager.sellerModel != null)
            new SellerPanel().start((Stage) lbl_company.getScene().getWindow());
    }

    // -----------------------------------------------------------------------------
}
