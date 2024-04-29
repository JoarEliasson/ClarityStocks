package SE.ClarityStocksGUI.controller.UserController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import userModel.UserProfileManager;

import java.io.IOException;

public class RegisterController {
    @FXML private TextField newUsernameField;
    private static final String USER_PROFILE_FILE_PATH = "ClarityStocksUser/userInfo.json";


    public void handleRegister() {
        String newUsername = newUsernameField.getText().trim();
        if (newUsername.isEmpty()) {
            showAlert("Username cannot be empty.");
            return;
        }
        if (UserProfileManager.createUserProfile(newUsername, USER_PROFILE_FILE_PATH)) {
            showAlert("Username created successfully.");
            backToLoginPage();
        } else {
            showAlert("Username could not be created, please try a different one.");
        }
    }

    private void backToLoginPage() {
        try {
            Stage stage = (Stage) newUsernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClarityStocksGUI/src/main/resources/SE/ClarityStocksGUI/view/login.fxml"));
            Pane loginRoot = loader.load();
            Scene scene = new Scene(loginRoot);
            stage.setScene(scene);
            stage.setTitle("Log in");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load the login page.");
        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
