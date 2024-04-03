package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.AlphaVantageClient;
import alphaVantage.Stock;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.StockInfo;
import model.StockInfoList;
import org.controlsfx.control.SearchableComboBox;
import org.kordamp.bootstrapfx.BootstrapFX;

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
    private StockInfo currentStock;
    @FXML
    private SearchableComboBox<StockInfo> searchField;
    @FXML
    private VBox stockStatsBox;
    private Stock stock;

    @FXML
    private ProgressBar progress;


    public void initialize(){

        progress.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        VBox.setVgrow(layout,javafx.scene.layout.Priority.ALWAYS);
        setupComboBox();
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

    public void loadStockView(String stockSymbol){
        progress.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
                stock = alphaVantageClient.getStock(stockSymbol);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GUIStockLineGraphController.getInstance().loadStockData(stock);
                        nameLabel.setText(stock.getCompanyOverview().getName());
                        peEvaluationText.setText(stock.getPERatioEvaluation());
                        progress.setVisible(false);
                    }
                });
            }
        }).start();

    }

    public void changeButtonColor(){
        homeButton.setStyle("-fx-background-color: #d9d9d9;");
        stockButton.setStyle("-fx-background-color: #339ACC");
    }

    private void setupComboBox(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StockInfoList sil = new StockInfoList();
                searchField.setEditable(true);
                searchField.setItems(FXCollections.observableList(sil.getStockInfoList()));
                searchField.setConverter(new StringConverter<StockInfo>() {
                    @Override
                    public String toString(StockInfo stockInfo) {
                        return stockInfo.getName() + " (" + stockInfo.getSymbol() + ")";
                    }

                    @Override
                    public StockInfo fromString(String s) {
                        return null;
                    }
                });
            }
        });

    }

    public void loadStock(){
        if(searchField.getValue() != null && currentStock != searchField.getValue()){
            System.out.println(((StockInfo) searchField.getValue()).getName());
            currentStock = searchField.getValue();
            System.out.println(currentStock.getSymbol());
            application.goToStockView(currentStock.getSymbol());
        }
    }

}
