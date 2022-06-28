package com.example.demo1.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductController {

    @FXML
    TextField txt_name;
    @FXML
    VBox mainVBox;
    @FXML
    Button btn_next;

    // Methods ---------------------------------------------------------------------


    // -----------------------------------------------------------------------------
    public void back() throws IOException {
        new Home().start((Stage) txt_name.getScene().getWindow());
    }
}