package SE.ClarityStocksGUI.view;

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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField userNameField = new TextField();
        userNameField.setPromptText("User Name");

        Button saveButton = getButton(userNameField, primaryStage);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(userNameField, saveButton);

        Scene scene = new Scene(layout, 250, 125);
        primaryStage.setTitle("User name");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Button getButton(TextField userNameField, Stage primaryStage) {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->{
            String userName= userNameField.getText();
            UserProfile userProfile = new UserProfile(userName);
            UserProfileManager userProfileManager = new UserProfileManager();
            userProfileManager.saveUserInformation(userProfile, "ClarityStocksUser/userInfo.json");

            Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
            welcomeAlert.initOwner(primaryStage);
            welcomeAlert.setTitle("Welcome");
            welcomeAlert.setHeaderText(null);
            welcomeAlert.setContentText("Welcome " + userNameField.getText());
            welcomeAlert.showAndWait();

            Platform.exit();

        });
        return saveButton;
    }
}
