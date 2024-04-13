package SE.ClarityStocksGUI.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.AlphaVantageListing;
import model.SearchList;
import org.controlsfx.control.SearchableComboBox;

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
  private StackPane mainStackPane;
  @FXML
  private SearchableComboBox<AlphaVantageListing> searchField;
  private AlphaVantageListing currentStock;
  private static Views currentView;

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
      System.out.println("STOCKVIEW");
      System.out.println(currentView);
      homeButton.setStyle("-fx-background-color: #ffffff");
      stockButton.setStyle("-fx-background-color: #c6daff");
      controller.goToStockView();
      currentView = Views.STOCK;
    }
  }

  @FXML
  public void goToHomeView() {
    if (currentView != Views.HOME) {
      System.out.println("HOMEVIEW");
      homeButton.setStyle("-fx-background-color: #c6daff");
      stockButton.setStyle("-fx-background-color: #ffffff");
      controller.goToHomeView();
      currentView = Views.HOME;
      resetSearchBar();
    }
  }

  @FXML
  public void loadStock() {
    if (searchField.getValue() != null && currentStock != searchField.getValue()) {
      System.out.println(((AlphaVantageListing) searchField.getValue()).getName());
      currentStock = searchField.getValue();
      System.out.println(currentStock.getSymbol());
      currentView = Views.STOCK;
      homeButton.setStyle("-fx-background-color: #ffffff");
      stockButton.setStyle("-fx-background-color: #c6daff");
      controller.goToStockView(currentStock.getSymbol());
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
        searchField.setConverter(new StringConverter<AlphaVantageListing>() {
          @Override
          public String toString(AlphaVantageListing stockInfo) {
            if (stockInfo == null) {
              return "";
            }
            return stockInfo.getName() + " (" + stockInfo.getSymbol() + ")";
          }

          @Override
          public AlphaVantageListing fromString(String s) {
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

}