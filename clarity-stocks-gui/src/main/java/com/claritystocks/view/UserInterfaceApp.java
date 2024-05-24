package com.claritystocks.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.model.UserProfile;
import user.model.UserProfileManager;


public class UserInterfaceApp extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UserInterfaceApp.primaryStage = primaryStage;

        TextField userNameField = new TextField();
        userNameField.setPromptText("User Name");

        Button saveButton = getButton(userNameField);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(userNameField, saveButton);

        Scene scene = new Scene(layout, 250, 125);
        primaryStage.setTitle("User name");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Button getButton(TextField userNameField) {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->{
            String userName= userNameField.getText().trim();
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
            welcomeAlert.setTitle("Welcome");
            welcomeAlert.setHeaderText(null);
            welcomeAlert.setContentText("Welcome " + userName);
            welcomeAlert.showAndWait();

            primaryStage.close();
            startMainApplication();
        });
        return saveButton;
    }
    private static void startMainApplication() {
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
