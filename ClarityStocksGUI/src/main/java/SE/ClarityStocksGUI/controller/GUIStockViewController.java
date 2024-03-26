package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.AlphaVantageClient;
import alphaVantage.Stock;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.NasdaqStockholmCompanyData;

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
    private Label peEvaluationText;
    @FXML
    private Rectangle graphBackground;
    @FXML
    private Rectangle statBackground;
    private Stock stock;
    private NasdaqStockholmCompanyData companyData;


    public void initialize(){

        VBox.setVgrow(layout,javafx.scene.layout.Priority.ALWAYS);

        homeButton.setText("Home");
        stockButton.setText("Stock");

        menuBar.widthProperty().bind(mainVBox.widthProperty());
        menuBarLine.widthProperty().bind(mainVBox.widthProperty());

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

    public void loadStockView(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
                stock = alphaVantageClient.getStock("TSLA");
                GUIStockLineGraphController.getInstance().loadStockData(stock);
                nameLabel.setText(stock.getCompanyOverview().getName());

                peEvaluationText.setText(stock.getPERatioEvaluation());

            }
        });
    }

    public void changeButtonColor(){
        homeButton.setStyle("-fx-background-color: #d9d9d9;");
        stockButton.setStyle("-fx-background-color: #339ACC");
    }

    public void setCompanyData(NasdaqStockholmCompanyData companyData) {
        this.companyData = companyData;
    }
}
