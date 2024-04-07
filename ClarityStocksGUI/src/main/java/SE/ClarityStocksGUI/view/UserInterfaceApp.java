package SE.ClarityStocksGUI.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import model.Profession;

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

        //Choice of Profession, TO DO "Fix the import of the profession class from ClarityStockUser!"

/*
        ChoiceBox<Profession> professionChoice = new ChoiceBox<>();
        */

        
        Button saveButton =new Button("Save");
        saveButton.setOnAction(e ->{
            String firstName=firstNameField.getText();
            String lastName = lastNameField.getText();
            int age;
            try {
                age= Integer.parseInt(ageField.getText());
            } catch (NumberFormatException ex) {
                System.err.println("Error: put a valid number");
                return;
            }
            //Profession
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(firstNameField, lastNameField, ageField, saveButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setTitle("User Input");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
