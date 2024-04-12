package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.AlphaVantageClient;
import alphaVantage.AlphaVantageStock;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.AlphaVantageListing;
import model.SearchList;
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
  private Rectangle descBackground;
  @FXML
  private Rectangle statBackground;
  private AlphaVantageListing currentStock;
  @FXML
  private SearchableComboBox<AlphaVantageListing> searchField;
  @FXML
  private VBox stockStatsBox;
  private AlphaVantageStock stock;

  @FXML
  private ProgressBar progress;

  public void initialize() {
    progress.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    setupComboBox();
    homeButton.setText("Home");
    stockButton.setText("Stock");


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


    menuBar.widthProperty().bind(mainVBox.widthProperty());
    menuBarLine.widthProperty().bind(mainVBox.widthProperty());

    descBackground.setEffect(getDropShadow());
    statBackground.setEffect(getDropShadow());
    graphBackground.setEffect(getDropShadow());
  }

  public void setApplication(GUIMainApplication application) {
    this.application = application;
  }

  public void goToHomeView() {
    application.goToHomeView();
  }

  private DropShadow getDropShadow() {
    DropShadow dropShadow = new DropShadow();
    dropShadow.setRadius(20);
    dropShadow.setOffsetX(0);
    dropShadow.setOffsetY(0);
    dropShadow.setSpread(0.001);
    dropShadow.setBlurType(BlurType.GAUSSIAN);
    dropShadow.setColor(Color.LIGHTGRAY);
    return dropShadow;
  }

  public void loadStockView(String stockSymbol) {
    progress.setVisible(true);
    new Thread(() -> {
      AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
      stock = alphaVantageClient.getStock(stockSymbol);

      Platform.runLater(() -> {
        GUIStockLineGraphController.getInstance().loadStockData(stock);
        nameLabel.setText(stock.getCompanyOverview().getName());
        peEvaluationText.setText(stock.getPERatioEvaluation().getDescription());
        progress.setVisible(false);
      });
    }).start();

  }

  public void changeButtonColor() {
    homeButton.setStyle("-fx-background-color: #d9d9d9;");
    stockButton.setStyle("-fx-background-color: #339ACC");
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
