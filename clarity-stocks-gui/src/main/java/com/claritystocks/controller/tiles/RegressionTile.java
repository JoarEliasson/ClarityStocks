package com.claritystocks.controller.tiles;

import analysis.regression.RegressionAnalysis;
import analysis.regression.RegressionResult;
import com.claritystocks.controller.GUIStockViewController;
import common.evaluations.DividendEvaluation;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.TileColor;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;
import model.stock.StockData;

/**
 * @author Joar Eliasson
 */
public class RegressionTile {

  @FXML
  private VBox regressionTileBox;
  @FXML
  private VBox radialTileContainer;
  @FXML
  private VBox gaugeTileContainer;

  private RegressionAnalysis regressionAnalysis;

  private DividendEvaluation dividendEvaluation;

  private GUIStockViewController controller;

  public void setStockData(StockData stockData) {
    this.regressionAnalysis = stockData.getRegressionAnalysis();
    this.dividendEvaluation = stockData.getDividendEvaluation();
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
    Tile radialChartTile = TileBuilder.create()
        .skinType(SkinType.RADIAL_CHART)
        .title("Regression Analysis Top 3 R-Squared")
        .textAlignment(TextAlignment.CENTER)
        .chartData(createChartData(topResults.get(0), 0), createChartData(topResults.get(1), 1),
            createChartData(topResults.get(2), 2))
        .animated(true)
        .prefSize(300, 300)
        .build();
    adjustTheme(radialChartTile, false);
    radialTileContainer.getChildren().add(radialChartTile);
  }

  private ChartData createChartData(RegressionResult result, int index) {
    Tile.TileColor tileColor = getTileColor(index);
    return new ChartData(result.getVariable(), (result.getRSquare() * 100), tileColor.color);
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
      adjustTheme(gaugeTile, false);
      gaugeTileContainer.getChildren().add(gaugeTile);
    }
  }

  private Tile createGaugeTile(RegressionResult result) {
    double percentageDifference = result.getLatestPrediction().getRating();
    String variableName = result.getVariable();
    Color ratingColor = getColorIndicator(percentageDifference);
    String description = "Price / Predicted ";

    return TileBuilder.create()
        .skinType(SkinType.GAUGE2)
        .prefSize(200, 240)
        .title(variableName)
        .text(description + result.getLatestPrediction().getPredictionDate())
        .unit("%")
        .textVisible(true)
        .value(percentageDifference * 100)
        .minValue(0)
        .maxValue(200)
        .threshold(100)
        .barColor(ratingColor)
        .thresholdVisible(true)
        .thresholdColor(TileColor.RED.color)
        .gradientStops(new Stop(0, Tile.BLUE),
            new Stop(0.25, Tile.GREEN),
            new Stop(0.5, Tile.YELLOW),
            new Stop(0.75, Tile.ORANGE),
            new Stop(1, Tile.RED))
        .strokeWithGradient(true)
        .animated(false)
        .build();
  }

  private void adjustTheme(Tile tile, boolean darkTheme) {
    if (darkTheme) {
      tile.setBackgroundColor(Color.DARKGRAY);
      tile.setTitleColor(Color.WHITE);
      tile.setUnitColor(Color.WHITE);
      tile.setValueColor(Color.WHITE);
      tile.setTextColor(Color.WHITE);
      tile.setDescriptionColor(Color.WHITE);
    } else {
      tile.setBackgroundColor(Color.TRANSPARENT);
      tile.setTitleColor(Color.BLACK);
      tile.setUnitColor(Color.BLACK);
      tile.setValueColor(Color.BLACK);
      tile.setTextColor(Color.BLACK);
      tile.setDescriptionColor(Color.BLACK);
    }
  }

  private Color getColorIndicator(double rating) {
    List<ColorRange> colorRanges = new ArrayList<>();
    colorRanges.add(new ColorRange(0.0, 0.90, Color.DARKSEAGREEN));
    colorRanges.add(new ColorRange(0.90, 0.95, Color.GREEN));
    colorRanges.add(new ColorRange(0.95, 0.975, Color.LIGHTGREEN));
    colorRanges.add(new ColorRange(0.975, 1.0, Color.GREENYELLOW));
    colorRanges.add(new ColorRange(1.0, 1.025, Color.LIGHTYELLOW));
    colorRanges.add(new ColorRange(1.025, 1.05, Color.YELLOW));
    colorRanges.add(new ColorRange(1.05, 1.075, Color.GOLD));
    colorRanges.add(new ColorRange(1.075, 1.1, Color.ORANGE));
    colorRanges.add(new ColorRange(1.1, 1.15, Color.DARKORANGE));
    colorRanges.add(new ColorRange(1.15, 1.2, Color.RED));
    colorRanges.add(new ColorRange(1.2, Double.MAX_VALUE, Color.DARKRED));

    for (ColorRange range : colorRanges) {
      if (rating < range.upperBound) {
        return range.color;
      }
    }

    return Color.DARKRED;
  }
  @FXML
  public void showExplanation(){
    controller.showExplanationPage("regression");
  }

  public void setController(GUIStockViewController controller){
    this.controller = controller;
  }

  private static class ColorRange {
    double lowerBound;
    double upperBound;
    Color color;

    ColorRange(double lowerBound, double upperBound, Color color) {
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
      this.color = color;
    }
  }
}