package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.AlphaVantageListing;
import model.SearchList;
import org.controlsfx.control.SearchableComboBox;
import javafx.scene.image.*;
import java.io.*;

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
  private SearchableComboBox<AlphaVantageListing> searchField;

  //For testing purposes, will be removed later
  @FXML
  private Label welcomeText;
  @FXML
  private Label testDataInfo;
  @FXML
  private Label testData;
  @FXML
  private MFXButton updateTestData;

  private AlphaVantageListing currentStock;

  public void initialize() throws FileNotFoundException {
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    homeButton.setText("Home");
    stockButton.setText("Stock");


    // create a input stream
   // FileInputStream input = new FileInputStream("claritystocksbackground.jpg");

    // create a image


    Image image = new Image("lighttheme.jpg");

    // create a background image
    BackgroundImage backgroundimage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    // create Background
    Background background = new Background(backgroundimage);

    // set background
    mainVBox.setBackground(background);



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


    //color for blue theme #bcdaeb ,  #2c3a42
    welcomeText.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    testDataInfo.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    testData.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    updateTestData.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");

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
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        SearchList searchList = new SearchList();
        searchField.setEditable(true);
        searchField.setItems(FXCollections.observableList(searchList.getSearchList()));
        searchField.setPromptText("Search...");
        searchField.setConverter(new StringConverter<AlphaVantageListing>() {
          @Override
          public String toString(AlphaVantageListing listing) {
            if (listing == null) {
              return "";
            }
            return listing.getName() + " (" + listing.getSymbol() + ")";
          }

          @Override
          public AlphaVantageListing fromString(String s) {
            return null;
          }
        });
      }
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