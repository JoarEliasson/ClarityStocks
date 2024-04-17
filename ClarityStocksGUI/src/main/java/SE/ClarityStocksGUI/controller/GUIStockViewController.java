package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import SE.ClarityStocksGUI.controller.stockViewTiles.InfoTile;
import SE.ClarityStocksGUI.controller.stockViewTiles.RatingsTile;
import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.AlphaVantageClient;
import alphaVantage.AlphaVantageStock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import model.AlphaVantageListing;
import org.kordamp.bootstrapfx.BootstrapFX;

public class GUIStockViewController {

  private GUIMainController controller;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox mainVBox;
  @FXML
  private GUIStockLineGraphController graphController;
  @FXML
  private Rectangle graphBackground;
  @FXML
  private Rectangle descBackground;
  @FXML
  private Rectangle statBackground;
  @FXML
  private Rectangle dialTileBackground;
  private AlphaVantageListing currentStock;
  @FXML
  private RatingsTile ratingsTileController;
  @FXML
  private InfoTile infoTileController;
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
    //dialTileBackground.setEffect(Effects.getDropShadow());
    System.out.println("Stock view controller initialized");
  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }


  public void setupScrollbar() {
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
            setInfoTile();
            setRatingsTile();
            progress.setVisible(false);
          }
        });
      }
    }).start();

  }

  private void setInfoTile() {
    infoTileController.setCompanyName(
        stock.getCompanyOverview().getName() + " (" + stock.getCompanyOverview().getSymbol() + ")");
    infoTileController.setSector(
        stock.getCompanyOverview().getSector() + " - " + stock.getCompanyOverview().getIndustry());
    infoTileController.setDescription(stock.getCompanyOverview().getDescription());
  }

  private void setRatingsTile(){
    ratingsTileController.setCurrentPrice(stock.getTimeSeries().get(0).getClose());
    ratingsTileController.setPeEvaluationText(stock.getPERatioEvaluation().getRating(), stock.getCompanyOverview().getPERatio(), stock.getPERatioEvaluation().getDescription());
    ratingsTileController.setBusinessPerformance(5, stock.getBusinessPerformanceEvaluation().getDescription()); //TODO THIS IS WORK IN PROGRESS
    ratingsTileController.setGoldenCross(5, stock.getGoldenCross().getDescription());
  }

  @FXML
  public void showGoldenCross(){
    graphController.showGoldenCross();
  }
}
