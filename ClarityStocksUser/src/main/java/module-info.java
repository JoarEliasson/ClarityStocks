module ClarityStocksUser {
    requires com.google.gson;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    exports controller;
    exports userModel;
    opens userModel to com.google.gson;
}