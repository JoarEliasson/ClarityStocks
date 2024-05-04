package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import SE.ClarityStocksGUI.controller.stockViewTiles.InfoTile;
import SE.ClarityStocksGUI.controller.stockViewTiles.RatingsTile;
import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import alphaVantage.model.data.global.DailyTopLists;
import alphaVantage.model.data.global.TopListDataPoint;
import alphaVantage.model.GlobalMarketInfo;
import java.util.List;
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
    infoTileController.setController(this);
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

        //GlobalMarketInfo motsvarar "stock" objektet fast med global data för home view.
        //Just nu market status och top; gainers, losers och most traded.
        GlobalMarketInfo globalMarketInfo = alphaVantageClient.getGlobalMarketInfo();
        /*
        Om ni vill testa att skriva ut market status så kan det göras med följande kod:
        for (MarketStatus market : globalMarketInfo.getMarketStatus()) {
          System.out.println(market.getRegion());
          System.out.println(market.getPrimaryExchanges());
          System.out.println(market.getCurrentStatus());
        }

         */
        DailyTopLists dailyTopLists = globalMarketInfo.getDailyTopLists();
        List<TopListDataPoint> topGainers = dailyTopLists.getTopGainers();
        List<TopListDataPoint> topLosers = dailyTopLists.getTopLosers();
        List<TopListDataPoint> mostTraded = dailyTopLists.getMostTraded();
        /*
        Om ni vill testa att skriva ut topGainers, topLosers och mostTraded så kan det göras med
        följande kod:
        for (TopListDataPoint topListDataPoint : topGainers) {
          System.out.println("Symbol: " + topListDataPoint.getSymbol());
          System.out.println("Price difference: " + topListDataPoint.getPriceDifference());
          System.out.println("Change amount: " + topListDataPoint.getChangeAmount());
          System.out.println("Change percentage: " + topListDataPoint.getChangePercentage());
          System.out.println("Trading volume: " + topListDataPoint.getTradingVolume());
          System.out.println();
        }
        for (TopListDataPoint topListDataPoint : topLosers) {
          System.out.println("Symbol: " + topListDataPoint.getSymbol());
          System.out.println("Price difference: " + topListDataPoint.getPriceDifference());
          System.out.println("Change amount: " + topListDataPoint.getChangeAmount());
          System.out.println("Change percentage: " + topListDataPoint.getChangePercentage());
          System.out.println("Trading volume: " + topListDataPoint.getTradingVolume());
          System.out.println();
        }
        for (TopListDataPoint topListDataPoint : mostTraded) {
          System.out.println("Symbol: " + topListDataPoint.getSymbol());
          System.out.println("Price difference: " + topListDataPoint.getPriceDifference());
          System.out.println("Change amount: " + topListDataPoint.getChangeAmount());
          System.out.println("Change percentage: " + topListDataPoint.getChangePercentage());
          System.out.println("Trading volume: " + topListDataPoint.getTradingVolume());
          System.out.println();
        }

         */

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
    ratingsTileController.setCurrentPrice(stock.getTimeSeriesDaily().getDailyData().getFirst().getClose());
    ratingsTileController.setPeEvaluationText(stock.getPeRatioEvaluation().getRating(), stock.getCompanyOverview().getPERatio(), stock.getPeRatioEvaluation().getDescription());
    ratingsTileController.setBusinessPerformance(5, stock.getBusinessPerformanceEvaluation().getDescription()); //TODO THIS IS WORK IN PROGRESS
    ratingsTileController.setGoldenCross(5, stock.getGoldenCrossEvaluation().getDescription());
  }

  @FXML
  public void showGoldenCross(){
    graphController.showGoldenCross();
  }

  public void stockFavoritePressed(boolean stockIsFavorite){
    controller.stockFavoritePressed(stockIsFavorite, stock.getCompanyOverview().getSymbol());
  }
}
