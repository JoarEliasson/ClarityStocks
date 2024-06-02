package com.claritystocks.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.model.UserProfile;
import user.model.UserProfileManager;

/**
 * UserInterfaceApp is the entry point for setting name of the user.
 * <p>
 * It displays a simple interface for entering and saving the username. The username is saved in a
 * JSON file in the user's home directory.
 * </p>
 *
 * @author Ibrahim Tafankaji
 */
public class UserInterfaceApp extends Application {
    private static Stage primaryStage;

    /**
     * Main method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the User Interface Application.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        UserInterfaceApp.primaryStage = primaryStage;

        Label label = new Label("Please enter your name to proceed!");
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter your name here");

        Button saveButton = getButton(userNameField);

        VBox layout = new VBox(10);
        layout.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(label, userNameField, saveButton);

        Scene scene = new Scene(layout, 250, 125);
        primaryStage.setTitle("Clarity Stocks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates and returns the save button with its action handler.
     *
     * @param userNameField the text field for entering the username
     * @return the configured save button
     */
    private Button getButton(TextField userNameField) {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String userName = userNameField.getText().trim();
            if (userName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("User name cannot be empty.");
                alert.showAndWait();
                return;
            }
            UserProfile userProfile = UserProfileManager.loadUserInformation("clarity-stocks-user/userInfo.json");
            if (userProfile == null) {
                userProfile = new UserProfile();
            }
            userProfile.setUserName(userName);
            UserProfileManager.saveUserInformation(userProfile, "clarity-stocks-user/userInfo.json");

            Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
            welcomeAlert.setTitle("Clarity Stocks");
            welcomeAlert.setHeaderText(null);
            welcomeAlert.setContentText("Welcome " + userName + "!");
            welcomeAlert.showAndWait();

            primaryStage.close();
            startMainApplication();
        });
        return saveButton;
    }

    /**
     * Starts the main application after the username has been set.
     */
    private void startMainApplication() {
        Platform.runLater(() -> {
            GUIMainApplication mainApp = new GUIMainApplication();
            try {
                mainApp.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
