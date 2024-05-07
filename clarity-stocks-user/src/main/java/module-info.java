module clarity.stocks.user {

    requires com.google.gson;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    exports user.model;
    opens user.model to com.google.gson;
}