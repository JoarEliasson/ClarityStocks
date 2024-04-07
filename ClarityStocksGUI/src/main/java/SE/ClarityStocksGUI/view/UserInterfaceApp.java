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
import userModel.UserProfile;
import userModel.UserProfileManager;

public class UserInterfaceApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last name");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        Button saveButton = getButton(firstNameField, lastNameField, ageField, primaryStage);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(firstNameField, lastNameField, ageField, saveButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setTitle("User Input");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Button getButton(TextField firstNameField, TextField lastNameField, TextField ageField, Stage primaryStage) {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->{
            String firstName=firstNameField.getText();
            String lastName = lastNameField.getText();
            int age;
            try {
                age= Integer.parseInt(ageField.getText());
                UserProfile userProfile = new UserProfile(firstName, lastName, age);
                UserProfileManager userProfileManager = new UserProfileManager();
                userProfileManager.saveUserInformation(userProfile, "ClarityStocksUser/userInfo.json");

                Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
                welcomeAlert.initOwner(primaryStage);
                welcomeAlert.setTitle("Welcome");
                welcomeAlert.setHeaderText(null);
                welcomeAlert.setContentText("Welcome " + firstNameField.getText() + " " + lastNameField.getText());
                welcomeAlert.showAndWait();

                Platform.exit();
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.initOwner(primaryStage);
                errorAlert.setTitle("Invalid input");
                errorAlert.setHeaderText("Age must be a number");
                errorAlert.setContentText("Please put a valid number");
                errorAlert.showAndWait();
            }
        });
        return saveButton;
    }
}
