package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.StockInfo;
import model.StockInfoList;
import org.controlsfx.control.SearchableComboBox;

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
  @FXML
  private SearchableComboBox<StockInfo> searchField;

  //For testing purposes, will be removed later
  @FXML
  private Label welcomeText;
  @FXML
  private Label testDataInfo;
  @FXML
  private Label testData;
  @FXML
  private MFXButton updateTestData;

  private StockInfo currentStock;

  public void initialize() {
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    homeButton.setText("Home");
    stockButton.setText("Stock");

    setupComboBox();

    changeButtonColor();
    //homeButton.getStyleClass().setAll("mfx-button");
    menuBar.widthProperty().bind(mainVBox.widthProperty());
    menuBarLine.widthProperty().bind(mainVBox.widthProperty());

    welcomeText.setText("WORK IN PROGRESS!!!\nThis is the home view.");
    testDataInfo.setText(
        "To set testdata, make a method that calls the setTestData() method in the Test class.");
    testData.setText("This is the test data.");
    updateTestData.setText("Update test data");
        /*
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            performSearch(newValue); // Method to perform search
        });

         */
  }

  public void setApplication(GUIMainApplication application) {
    this.application = application;
  }

  public void changeButtonColor() {
    homeButton.setStyle("-fx-background-color: #339ACC;");
    stockButton.setStyle("-fx-background-color: #d9d9d9");
  }

  @FXML
  public void goToStockView() {
    application.goToStockView();
  }

  public void resetSearchBar() {
    searchField.hide();
    searchField.setValue(null);
    searchField.setPromptText("Search...");
  }

  private void setupComboBox() {
    Platform.runLater(() -> {
      StockInfoList sil = new StockInfoList();
      searchField.setEditable(true);
      searchField.setItems(FXCollections.observableList(sil.getStockInfoList()));
      searchField.setPromptText("Search...");
      searchField.setConverter(new StringConverter<StockInfo>() {
        @Override
        public String toString(StockInfo stockInfo) {
          if (stockInfo == null) {
            return "";
          }
          return stockInfo.getName() + " (" + stockInfo.getSymbol() + ")";
        }

        @Override
        public StockInfo fromString(String s) {
          return null;
        }
      });
    });

  }

  public void loadStock() {
    if (searchField.getValue() != null && currentStock != searchField.getValue()) {
      System.out.println(searchField.getValue().getName());
      currentStock = searchField.getValue();
      System.out.println(currentStock.getSymbol());
      application.goToStockView(currentStock.getSymbol());
    }
  }
}