package SE.ClarityStocksGUI.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.search.SearchList;
import model.stock.StockInfo;
import org.controlsfx.control.SearchableComboBox;

/**
 * This controller handles the menu bar in the GUI. It is the controller to the Menu-bar.fxml file.
 * <p>
 * It also handles the search function and the search bar that is contained in the menu bar.
 * <p>
 * The class have one parent-class which is the Main-view.
 *
 * @author Douglas Almö Thorsell
 * @see GUIMainController
 */
public class MenuBarController {

  private GUIMainController controller;
  @FXML
  private Rectangle menuBar;
  @FXML
  private Rectangle menuBarLine;
  @FXML
  private MFXButton stockButton;
  @FXML
  private MFXButton homeButton;
  @FXML
  private SearchableComboBox<StockInfo> searchField;
  private StockInfo currentStock;
  private static Views currentView;
  private boolean stockIsLoaded = false;

  public void initialize() {
    homeButton.setStyle("-fx-background-color: #c6daff");
    stockButton.setStyle("-fx-background-color: #ffffff");
    currentView = Views.HOME;
    setupComboBox();
    homeButton.setText("Home");
    stockButton.setText("Stock");
  }

  @FXML
  public void goToStockView() {
    if (currentView != Views.STOCK) {
      if(stockIsLoaded){
        controller.goToStockView();
      }else {
        controller.showStockNotLoaded();
      }
    }
  }

  @FXML
  public void goToHomeView() {
    if (currentView != Views.HOME) {
      controller.goToHomeView();
      resetSearchBar();
    }
  }

  public void setHomeButtonActive(){
    currentView = Views.HOME;
    homeButton.setStyle("-fx-background-color: #c6daff");
    stockButton.setStyle("-fx-background-color: #ffffff");
  }
  public void setStockButtonActive(){
    currentView = Views.STOCK;
    homeButton.setStyle("-fx-background-color: #ffffff");
    stockButton.setStyle("-fx-background-color: #c6daff");
  }

  @FXML
  public void loadStock() {
    if (searchField.getValue() != null && currentStock != searchField.getValue()) {
      currentStock = searchField.getValue();
      currentView = Views.STOCK;
      homeButton.setStyle("-fx-background-color: #ffffff");
      stockButton.setStyle("-fx-background-color: #c6daff");
      stockIsLoaded = true;
      controller.goToStockView(currentStock.symbol());
    }
  }

  public void setWidthAndHeightProperty() {
    menuBar.widthProperty().bind(controller.getWidthProperty());
    menuBarLine.widthProperty().bind(controller.getWidthProperty());
  }

  public void resetSearchBar() {
    searchField.hide();
    searchField.setValue(null);
    searchField.setPromptText("Search...");
  }

  private void setupComboBox() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        SearchList searchList = new SearchList();
        searchField.setEditable(true);
        searchField.setItems(FXCollections.observableList(searchList.getSearchList()));
        searchField.setPromptText("Search...");
        searchField.setConverter(new StringConverter<StockInfo>() {
          @Override
          public String toString(StockInfo stockInfo) {
            if (stockInfo == null) {
              return "";
            }
            return stockInfo.name() + " (" + stockInfo.symbol() + ")";
          }

          @Override
          public StockInfo fromString(String s) {
            return null;
          }
        });
      }
    });

  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }

  private enum Views {
    STOCK,
    HOME

  }

  public void setCurrentStock(StockInfo stock){
    currentStock = stock;
  }

}