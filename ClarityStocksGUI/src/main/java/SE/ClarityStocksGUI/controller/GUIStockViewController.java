package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLinegraphController;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.DataPoint;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;

import java.awt.*;
import java.io.IOException;

public class GUIStockViewController {
    private GUIMainApplication application;
    @FXML
    private BorderPane layout;
    @FXML
    private VBox mainVBox;
    @FXML
    private Label nameLabel;
    @FXML
    private Rectangle menuBar;
    @FXML
    private Rectangle menuBarLine;
    @FXML
    private MFXButton stockButton;
    @FXML
    private MFXButton homeButton;
    @FXML
    private Label currentPriceLabel;
    @FXML
    private Rectangle graphBackground;
    @FXML
    private Rectangle statBackground;



    public void initialize(){
        VBox.setVgrow(layout,javafx.scene.layout.Priority.ALWAYS);

        homeButton.setText("Home");
        stockButton.setText("Stock");

        menuBar.widthProperty().bind(mainVBox.widthProperty());
        menuBarLine.widthProperty().bind(mainVBox.widthProperty());

        nameLabel.setText("NAME");
        currentPriceLabel.setText("CURRENT PRICE");

        statBackground.setEffect(getDropShadow());
        graphBackground.setEffect(getDropShadow());
    }
    public void setApplication(GUIMainApplication application){
        this.application = application;
    }

    public void goToHomeView(){
        application.goToHomeView();
    }

    private DropShadow getDropShadow(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setSpread(0.001);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        dropShadow.setColor(Color.LIGHTGRAY);
        return dropShadow;
    }


}