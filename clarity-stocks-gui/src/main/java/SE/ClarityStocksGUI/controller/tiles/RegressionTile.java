package SE.ClarityStocksGUI.controller.tiles;

import analysis.regression.PricePrediction;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.stock.StockData;

public class RegressionTile {

  @FXML
  private VBox regressionTileBox;

  private Tile radialChartTile;

  private StockData stockData;
  private RegressionAnalysis regressionAnalysis;
  private List<RegressionResult> topResults;
  private List<DailyDataPoint> monthlyPriceData;

  public void setStockData(StockData stockData) {
    this.stockData = stockData;
    this.regressionAnalysis = stockData.getRegressionAnalysis();
    initializeTiles();

  }

  private double getRSquare(RegressionResult result) {
    return result.getRSquare();
  }

  private double[] getCoefficients(RegressionResult result) {
    return result.getCoefficients();
  }

  private String getVariable(RegressionResult result) {
    return result.getVariable();
  }

  private String getDescription(RegressionResult result) {
    return result.getDescription();
  }

  private PricePrediction getPricePrediction(RegressionResult result) {
    return result.getPricePrediction();
  }

  private double[] getObservedValues(RegressionResult result) {
    return result.getObservedValues();
  }

  private void initializeTiles() {
    createRadialChart(getTopThreeResults());
    createGaugeTiles(getTopThreeResults());
  }

  private void createRadialChart(List<RegressionResult> topResults) {
    radialChartTile = TileBuilder.create()
        .skinType(SkinType.RADIAL_CHART)
        .title("Regression Analysis")
        .text("Top 3 R-squared values")
        .chartData(createChartData(topResults.get(0), 0), createChartData(topResults.get(1), 1), createChartData(topResults.get(2), 2))
        .animated(true)
        .build();
    regressionTileBox.getChildren().add(radialChartTile);
  }

  private ChartData createChartData(RegressionResult result, int index) {
    Tile.TileColor tileColor = getTileColor(index);
    return new ChartData(getVariable(result), (getRSquare(result) * 100), tileColor.color);
  }

  private Tile.TileColor getTileColor(double index) {
    if (index == 0) {
      return TileColor.BLUE;
    } else if (index == 1) {
      return TileColor.GREEN;
    } else {
      return TileColor.ORANGE;
    }
  }

  private void createGaugeTiles(List<RegressionResult> topResults) {
    for (RegressionResult result : topResults) {
      Tile gaugeTile = createGaugeTile(result);
      regressionTileBox.getChildren().add(gaugeTile);
    }
  }

  private Tile createGaugeTile(RegressionResult result) {
    double rating = result.getPricePrediction().getRating();
    String variableName = result.getVariable();
    String date = result.getPricePrediction().getPredictionDate();
    Color ratingColor = getRatingColor(rating);

    return TileBuilder.create()
        .skinType(Tile.SkinType.GAUGE)
        .title(variableName)
        .text("Rating")
        .unit("%")
        .description(date)
        .maxValue(200)
        .value(rating * 100)
        .barColor(ratingColor)
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


  private List<RegressionResult> getTopThreeResults() {
    return regressionAnalysis.getRegressionTop();
  }


}