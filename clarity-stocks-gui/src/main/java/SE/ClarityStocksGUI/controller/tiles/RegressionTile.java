package SE.ClarityStocksGUI.controller.tiles;

import analysis.regression.RegressionAnalysis;
import analysis.regression.RegressionResult;
import common.data.series.DailyDataPoint;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.TileColor;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.stock.StockData;

public class RegressionTile {

  @FXML
  private VBox regressionTileBox;
  @FXML
  private VBox radialTileContainer;
  @FXML
  private HBox gaugeTileContainer;

  private Tile radialChartTile;
  private StockData stockData;
  private RegressionAnalysis regressionAnalysis;

  public void setStockData(StockData stockData) {
    this.stockData = stockData;
    this.regressionAnalysis = stockData.getRegressionAnalysis();
    initializeTiles();
  }

  private void initializeTiles() {
    List<RegressionResult> topResults = getTopThreeResults();
    clearTiles();
    createRadialChart(topResults);
    createGaugeTiles(topResults);
  }

  private void clearTiles() {
    radialTileContainer.getChildren().clear();
    gaugeTileContainer.getChildren().clear();
  }

  private List<RegressionResult> getTopThreeResults() {
    return regressionAnalysis.getRegressionTop();
  }

  private void createRadialChart(List<RegressionResult> topResults) {
    radialChartTile = TileBuilder.create()
        .skinType(SkinType.RADIAL_CHART)
        .title("Regression Analysis")
        .text("Top 3 R-squared values")
        .chartData(createChartData(topResults.get(0), 0), createChartData(topResults.get(1), 1), createChartData(topResults.get(2), 2))
        .animated(true)
        .backgroundColor(Color.TRANSPARENT)
        .titleColor(Color.BLACK)
        .valueColor(Color.BLACK)
        .unitColor(Color.BLACK)
        .descriptionColor(Color.BLACK)
        .textColor(Color.BLACK)
        .needleColor(Color.BLACK)
        .prefSize(400, 400)
        .build();
    radialTileContainer.getChildren().add(radialChartTile);
  }

  private ChartData createChartData(RegressionResult result, int index) {
    Tile.TileColor tileColor = getTileColor(index);
    return new ChartData(getVariable(result), (getRSquare(result) * 100), tileColor.color);
  }

  private Tile.TileColor getTileColor(double index) {
    if (index == 0) {
      return Tile.TileColor.BLUE;
    } else if (index == 1) {
      return Tile.TileColor.GREEN;
    } else {
      return Tile.TileColor.ORANGE;
    }
  }

  private void createGaugeTiles(List<RegressionResult> topResults) {
    for (RegressionResult result : topResults) {
      Tile gaugeTile = createGaugeTile(result);
      gaugeTileContainer.getChildren().add(gaugeTile);
    }
  }

  private Tile createGaugeTile(RegressionResult result) {
    double rating = result.getLatestPrediction().getRating();
    String variableName = result.getVariable();
    String date = result.getLatestPrediction().getPredictionDate();
    Color ratingColor = getRatingColor(rating);

    return TileBuilder.create()
        .skinType(SkinType.GAUGE2)
        .prefSize(200, 200)
        .title(variableName)
        .text("Latest Prediction")
        .unit("%")
        .description("Date: " + date)
        .maxValue(200)
        .minValue(0)
        .value(rating * 100)
        .barColor(ratingColor)
        .backgroundColor(Color.TRANSPARENT)
        .titleColor(Color.BLACK)
        .valueColor(Color.BLACK)
        .unitColor(Color.BLACK)
        .descriptionColor(Color.BLACK)
        .textColor(Color.BLACK)
        .needleColor(Color.BLACK)
        .animated(true)
        .build();
  }

  private Color getRatingColor(double rating) {
    if (rating < 0.95) {
      return Color.RED;
    } else if (rating < 1.025) {
      return Color.YELLOW;
    } else {
      return Color.GREEN;
    }
  }

  private double getRSquare(RegressionResult result) {
    return result.getRSquare();
  }

  private String getVariable(RegressionResult result) {
    return result.getVariable();
  }
}