package com.claritystocks.controller;

import com.claritystocks.controller.tiles.ExplanationTile;
import com.claritystocks.view.GUIMainApplication;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * This is the main controller class for the GUI. It's the main container of all the different views
 * and handles the logic between them. The class is a controller for the Main-view.fxml file.
 * <p>
 * The GUI is designed to have a hierarchy between the classes with the Main-view at the top of the
 * hierarchy and then includes or views under it.
 * </p>
 *
 * <p>
 * The children for this view is the following, Home-view, Stock-view and Menu-bar.
 * </p>
 *
 * @see GUIHomeController
 * @see GUIStockViewController
 * @see MenuBarController
 * @see ExplanationTile
 *
 * @author Douglas Almö Thorsell
 * @author Ibrahim Tafankaji
 */
public class GUIMainController {

  private GUIMainApplication application;
  @FXML
  private VBox stockView;
  @FXML
  private VBox homeView;
  @FXML
  private VBox explanationTile;
  @FXML
  private BorderPane mainBorderPane;
  @FXML
  private MenuBarController menuBarController;
  @FXML
  private GUIStockViewController stockViewController;
  @FXML
  private GUIHomeController homeViewController;
  @FXML
  private ExplanationTile explanationTileController;
  private static GUIMainController guiMainController;
  @FXML
  private Dialog<String> stockNotLoadedError;

  public void initialize() {
    homeViewController.setController(this);
    stockViewController.setController(this);
    menuBarController.setController(this);
    explanationTileController.setController(this);
    guiMainController = this;

    menuBarController.setWidthAndHeightProperty();
    explanationTileController.setupScrollbar();
    stockViewController.setupScrollbar();

    homeView.setVisible(true);
    stockView.setVisible(false);
    explanationTile.setVisible(false);


    setUpStockNotLoadedError();
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

  public void showExplanationPage(String mainTitle, String generalText, String companyTitle,
      String companyText){
    explanationTileController.setMainTitle(mainTitle);
    explanationTileController.setGeneralText(generalText);
    explanationTileController.setCompanyTitle(companyTitle);
    explanationTileController.setCompanyText(companyText);
    explanationTile.setVisible(true);
  }


  public void closeExplanationPage(){
    explanationTile.setVisible(false);
  }

  public void showStockNotLoaded(){
    stockNotLoadedError.showAndWait();
  }

  private void setUpStockNotLoadedError(){
    stockNotLoadedError = new Dialog<>();
    stockNotLoadedError.setTitle("You haven't selected a stock!");
    stockNotLoadedError.setContentText("You haven't selected a stock.\n"
        + "Please search for a stock in the search bar.");
    ButtonType button = new ButtonType("Ok", ButtonData.OK_DONE);
    stockNotLoadedError.getDialogPane().getButtonTypes().add(button);
  }

  public ReadOnlyDoubleProperty getWidthProperty() {
    return mainBorderPane.widthProperty();
  }

  public ReadOnlyDoubleProperty getHeightProperty() {
    return mainBorderPane.heightProperty();
  }

  public static GUIMainController getInstance(){
    return guiMainController;
  }

}