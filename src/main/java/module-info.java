module com.example.hw42 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hw42 to javafx.fxml;
    exports com.example.hw42;
}