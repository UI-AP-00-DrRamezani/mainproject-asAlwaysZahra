module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.demo1 to javafx.fxml;
//    exports com.example.demo1.UI;
    exports com.example.demo1.UI;
    opens com.example.demo1.UI to javafx.fxml;
}