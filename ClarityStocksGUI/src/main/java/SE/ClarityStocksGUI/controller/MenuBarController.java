package SE.ClarityStocksGUI.controller;

import alphaVantage.Stock;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.StockInfo;
import model.StockInfoList;
import org.controlsfx.control.SearchableComboBox;

public class MenuBarController {
    @FXML
    private Rectangle menuBar;
    @FXML
    private Rectangle menuBarLine;
    @FXML
    private MFXButton stockButton;
    @FXML
    private MFXButton homeButton;
    @FXML
    private StackPane mainStackPane;
    @FXML
    private SearchableComboBox<StockInfo> searchField;

    public void initialize(){
        setupComboBox();
        homeButton.setText("Home");
        stockButton.setText("Stock");
        menuBar.widthProperty().bind(mainStackPane.widthProperty());
        menuBarLine.widthProperty().bind(mainStackPane.widthProperty());
    }

    public void changeButtonColor(){
        homeButton.setStyle("-fx-background-color: #339ACC;");
        stockButton.setStyle("-fx-background-color: #d9d9d9");
    }

    @FXML
    public void goToStockView(){

    }

    @FXML
    public void loadStock(){

    }

    private void setupComboBox(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StockInfoList sil = new StockInfoList();
                searchField.setEditable(true);
                searchField.setItems(FXCollections.observableList(sil.getStockInfoList()));
                searchField.setPromptText("Search...");
                searchField.setConverter(new StringConverter<StockInfo>() {
                    @Override
                    public String toString(StockInfo stockInfo) {
                        if(stockInfo == null){
                            return "";
                        }
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
}
