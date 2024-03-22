package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.*;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class GUIStockViewController {
    private GUIMainApplication application;
    @FXML
    private BorderPane layout;
    @FXML
    private VBox mainVBox;
    @FXML
    private Label testLabel;
    @FXML
    private Rectangle menuBar;
    @FXML
    private Rectangle menuBarLine;
    @FXML
    private MFXButton stockButton;
    @FXML
    private MFXButton homeButton;



    public void initialize(){
        VBox.setVgrow(layout,javafx.scene.layout.Priority.ALWAYS);

        homeButton.setText("Home");
        stockButton.setText("Stock");

        menuBar.widthProperty().bind(mainVBox.widthProperty());
        menuBarLine.widthProperty().bind(mainVBox.widthProperty());

        testLabel.setText("WORK IN PROGRESS!!!\n This is the stock view.");
    }
    public void setApplication(GUIMainApplication application){
        this.application = application;
    }

    public void goToHomeView(){
        application.goToHomeView();
    }


}
