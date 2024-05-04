package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.AlphaVantageListing;

/**
 * This is the main controller class for the GUI. It's the main container of all the different views
 * and handles the logic between them. The class is a controller for the Main-view.fxml file.
 * <p>
 * The GUI is designed to have a hierarchy between the classes with the Main-view at the top of the
 * hierarchy and then includes or views under it.
 * <p>
 * The children for this view is the following, Home-view, Stock-view and Menu-bar.
 *
 * @author Douglas Almö Thorsell
 * @see GUIHomeController
 * @see GUIStockViewController
 * @see MenuBarController
 */
public class GUIMainController {

  private GUIMainApplication application;
  @FXML
  private VBox stockView;
  @FXML
  private VBox homeView;
  @FXML
  private BorderPane mainBorderPane;
  @FXML
  private MenuBarController menuBarController;
  @FXML
  private GUIStockViewController stockViewController;
  @FXML
  private GUIHomeController homeViewController;

  public void initialize() {
    homeViewController.setController(this);
    stockViewController.setController(this);
    menuBarController.setController(this);

    menuBarController.setWidthAndHeightProperty();
    stockViewController.setupScrollbar();

    homeView.setVisible(true);
    stockView.setVisible(false);
  }

  public void setApplication(GUIMainApplication application) {
    this.application = application;
  }

  public void goToStockView() {
    menuBarController.setStockButtonActive();
    stockView.setVisible(true);
    homeView.setVisible(false);
  }

  public void goToStockView(String stockSymbol) {
    menuBarController.setStockButtonActive();
    stockView.setVisible(true);
    homeView.setVisible(false);
    stockViewController.loadStockView(stockSymbol);
  }

  public void goToHomeView() {
    menuBarController.setHomeButtonActive();
    stockView.setVisible(false);
    homeView.setVisible(true);
  }

  public void errorLoadingStock(){
    goToHomeView();
    menuBarController.setCurrentStock(null);
    menuBarController.resetSearchBar();
  }

  public void stockFavoritePressed(boolean stockIsFavorite, String stockSymbol){
    System.out.println(stockSymbol + " favorite status: " + stockIsFavorite);
  }

  public ReadOnlyDoubleProperty getWidthProperty() {
    return mainBorderPane.widthProperty();
  }

  public ReadOnlyDoubleProperty getHeightProperty() {
    return mainBorderPane.heightProperty();
  }
}
