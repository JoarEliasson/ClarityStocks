package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import SE.ClarityStocksGUI.controller.stockViewTiles.RatingsTile;
import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.AlphaVantageClient;
import alphaVantage.AlphaVantageStock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.StockInfo;
import org.kordamp.bootstrapfx.BootstrapFX;

public class GUIStockViewController {

  private GUIMainController controller;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox mainVBox;
  @FXML
  private Label nameLabel;
  @FXML
  private GUIStockLineGraphController graphController;
  @FXML
  private Rectangle graphBackground;
  @FXML
  private Rectangle descBackground;
  @FXML
  private Rectangle statBackground;
  private StockInfo currentStock;
  @FXML
  private RatingsTile ratingsTileController;
  @FXML
  private VBox stockStatsBox;
  @FXML
  private ScrollPane scrollPane;
  private AlphaVantageStock stock;

  @FXML
  private ProgressBar progress;


  public void initialize() {
    progress.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);

    descBackground.setEffect(Effects.getDropShadow());
    statBackground.setEffect(Effects.getDropShadow());
    graphBackground.setEffect(Effects.getDropShadow());
  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }


  public void setupScrollbar(){
    scrollPane.minWidthProperty().bind(controller.getWidthProperty());
    scrollPane.minHeightProperty().bind(controller.getHeightProperty());
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setEffect(null);
  }

  public void loadStockView(String stockSymbol) {
    progress.setVisible(true);
    new Thread(new Runnable() {
      @Override
      public void run() {
        AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
        stock = alphaVantageClient.getStock(stockSymbol);

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            graphController.loadStockData(stock);
            nameLabel.setText(stock.getCompanyOverview().name());
            ratingsTileController.setPeEvaluationText(stock.getPERatioEvaluation());
            progress.setVisible(false);
          }
        });
      }
    }).start();

  }
}
