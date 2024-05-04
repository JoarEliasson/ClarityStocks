package SE.ClarityStocksGUI.controller.UserController;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import userModel.UserProfileManager;

import java.io.IOException;


public class LoginController {
    @FXML private TextField usernameField;
    @FXML private Label registerPrompt;
    private static final String USER_PROFILE_FILE_PATH = "ClarityStocksUser/userInfo.json";


    public void handleLogin() {
        String username = usernameField.getText().trim();
        if (UserProfileManager.isUserExist(username, USER_PROFILE_FILE_PATH)) {
            loadHomePage();
        } else {
            showAlert("Not available user. Do you want to register?", Alert.AlertType.CONFIRMATION);
        }
    }

    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SE/ClarityStocksGUI/view/Home-view.fxml"));
            Pane homeRoot = loader.load();
            Scene scene = new Scene(homeRoot);
            Stage primaryStage = (Stage) usernameField.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load the home page.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message);
        if (type == Alert.AlertType.CONFIRMATION) {
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    System.out.println("User chose Yes");
                    handleGoToRegister();
                }
            });
        } else {
            alert.showAndWait();
        }
    }
    public void handleGoToRegister() {
        Stage stage = (Stage) registerPrompt.getScene().getWindow();
        stage.setTitle("Add Username");
        loadRegisterPage(stage);
    }
    private void loadRegisterPage(Stage stage) {
        try {
            // Assuming the registration page FXML is "register.fxml" and located in the same package
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SE/ClarityStocksGUI/view/register.fxml"));
            Pane registerRoot = loader.load();
            Scene scene = new Scene(registerRoot);
            stage.setScene(scene);
            stage.setTitle("Register New User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load the registration page.", Alert.AlertType.ERROR);
        }
    }
}
