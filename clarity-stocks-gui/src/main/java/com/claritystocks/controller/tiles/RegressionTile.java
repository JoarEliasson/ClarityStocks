package com.claritystocks.controller.tiles;

import analysis.regression.RegressionAnalysis;
import analysis.regression.RegressionResult;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.TextSize;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Medium;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
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
    String date = topResults.getFirst().getLatestPrediction().getPredictionDate();
    radialChartTile = TileBuilder.create()
        .skinType(SkinType.RADIAL_CHART)
        .title("Regression Analysis Top 3 R-Squared")
        .text("Actual price vs. Predicted price " + date)
        .chartData(createChartData(topResults.get(0), 0), createChartData(topResults.get(1), 1), createChartData(topResults.get(2), 2))
        .animated(true)
        .backgroundColor(Color.TRANSPARENT)
        .titleColor(Color.BLACK)
        .valueColor(Color.BLACK)
        .unitColor(Color.BLACK)
        .descriptionColor(Color.BLACK)
        .textColor(Color.BLACK)
        .needleColor(Color.BLACK)
        .prefSize(300, 300)
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

    Color ratingColor = getRatingColor(rating);

    return TileBuilder.create()
        .skinType(SkinType.GAUGE2)
        .prefSize(250, 220)
        .title(variableName)
        .text("of prediction")
        .unit("%")
        .textSize(TextSize.BIGGER)
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
        .gradientStops(
            new Stop(0.0, Medium.RED),
            new Stop(0.8, Medium.ORANGE_RED),
            new Stop(1, Medium.ORANGE),
            new Stop(1.05, Medium.YELLOW_ORANGE),
            new Stop(1.10, Medium.YELLOW),
            new Stop(1.15, Medium.GREEN_YELLOW),
            new Stop(1.3, Medium.GREEN),
            new Stop(1.5, Medium.BLUE_GREEN))
        .strokeWithGradient(true)
        .animated(true)
        .build();
  }

  private Color getRatingColor(double rating) {
    if (rating < 0.90) {
      return Color.DARKSEAGREEN;
    } else if (rating < 0.95) {
      return Color.GREEN;
    } else if (rating < 0.975) {
      return Color.LIGHTGREEN;
    } else if (rating < 1.0) {
      return Color.GREENYELLOW;
    } else if (rating < 1.025) {
      return Color.LIGHTYELLOW;
    } else if (rating < 1.05) {
      return Color.YELLOW;
    } else if (rating < 1.075) {
      return Color.GOLD;
    } else if (rating < 1.1) {
      return Color.ORANGE;
    } else if (rating < 1.15) {
      return Color.DARKORANGE;
    } else if (rating < 1.2) {
      return Color.RED;
    } else {
      return Color.DARKRED;
    }
  }

  private double getRSquare(RegressionResult result) {
    return result.getRSquare();
  }

  private String getVariable(RegressionResult result) {
    return result.getVariable();
  }
}