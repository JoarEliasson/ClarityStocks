package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.*;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.skins.MFXButtonSkin;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class GUIHomeController {
    private GUIMainApplication application;
    @FXML
    private BorderPane layout;
    @FXML
    private Rectangle menuBar;
    @FXML
    private Rectangle menuBarLine;
    @FXML
    private VBox mainVBox;
    @FXML
    private MFXButton stockButton;
    @FXML
    private MFXButton homeButton;

    //For testing purposes, will be removed later
    @FXML
    private Label welcomeText;
    @FXML
    private Label testDataInfo;
    @FXML
    private Label testData;
    @FXML
    private MFXButton updateTestData;

    public void initialize(){
        VBox.setVgrow(layout,javafx.scene.layout.Priority.ALWAYS);
        homeButton.setText("Home");
        stockButton.setText("Stock");
        //homeButton.getStyleClass().setAll("mfx-button");
        menuBar.widthProperty().bind(mainVBox.widthProperty());
        menuBarLine.widthProperty().bind(mainVBox.widthProperty());

        welcomeText.setText("WORK IN PROGRESS!!!\nThis is the home view.");
        testDataInfo.setText("To set testdata, make a method that calls the setTestData() method in the Test class.");
        testData.setText("This is the test data.");
        updateTestData.setText("Update test data");

    }
    public void setApplication(GUIMainApplication application){
        this.application = application;
    }
    @FXML
    public void goToStockView(){
        application.goToStockView();
    }
    @FXML
    public void updateTestData(){
        testData.setText(application.getTestData());
    }
}