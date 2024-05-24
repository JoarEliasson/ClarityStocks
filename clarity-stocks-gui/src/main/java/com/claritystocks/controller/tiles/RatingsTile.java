package com.claritystocks.controller.tiles;

import com.claritystocks.controller.GUIStockViewController;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.SkinType;
import java.util.ArrayList;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class handles the ratings tile which includes the price of the stock and different ratings
 * about the stock depending on different analysis that has been done.
 * <p>
 * It's parent class is the Stock-view.
 * @author Douglas Almö Thorsell
 * @see GUIStockViewController
 */
public class RatingsTile {

  private GUIStockViewController controller;
  @FXML
  private Label currentPrice;
  @FXML
  private Label percentageChange;
  @FXML
  private ImageView upOrDownChange;
  @FXML
  private Gauge peRatingGauge;
  @FXML
  private Gauge businessPerformanceGauge;
  @FXML
  private Gauge companyGrowthGauge;
  @FXML
  private Gauge companySizeGauge;
  @FXML
  private Gauge highAndLowGauge;
  @FXML
  private Gauge analystPredictionGauge;
  @FXML
  private Label peEvaluation;
  @FXML
  private Label businessPerformance;
  @FXML
  private Label companyGrowth;
  @FXML
  private Label companySize;
  @FXML
  private Label highAndLow;
  @FXML
  private Label analystPrediction;
  private ArrayList<Image> upOrDownImage;

  public void initialize() {
    loadImages();
    initialGaugesSetup();
  }

  private void loadImages() {
    upOrDownChange.setFitHeight(25);
    upOrDownChange.setFitWidth(25);

    upOrDownImage = new ArrayList<>();

    upOrDownImage.add(new Image(
        getClass().getResource("/com/claritystocks/view/stockDown.png").toExternalForm()));
    upOrDownImage.add(new Image(
        getClass().getResource("/com/claritystocks/view/stockUp.png").toExternalForm()));
  }

  public void setPeEvaluationText(double rating, String description, String name) {
    peRatingGauge.setValue(rating);
    peEvaluation.setText(name + "\nMarket comparison: " + description);
    installLabelTooltip(peEvaluation, description);
  }
  public void setBusinessPerformance(double rating, String description, String name){
    businessPerformanceGauge.setValue(rating);
    businessPerformance.setText(name + "\nMarket comparison: " + description);
    installLabelTooltip(businessPerformance, description);

  }

  public void setCompanyGrowth(double rating, String description, String name){
    companyGrowthGauge.setValue(rating);
    companyGrowth.setText(name + "\nMarket comparison: " + description);
    installLabelTooltip(companyGrowth, description);
  }

  public void setCompanySize(double rating, String description, String name){
    companySizeGauge.setValue(rating);
    companySize.setText(name + "\nMarket comparison: " + description);
    installLabelTooltip(companySize, description);
  }
  public void setHighAndLow(double rating, String description, String name){
    highAndLowGauge.setValue(rating);
    highAndLow.setText(name + "\nMarket comparison: " + description);
    installLabelTooltip(highAndLow, description);
  }

  public void setAnalystPrediction(double rating, String description, String name){
    analystPredictionGauge.setValue(rating);
    analystPrediction.setText(name + "\nMarket comparison: " + description);
    installLabelTooltip(analystPrediction, description);
  }

  private void initialGaugesSetup(){
    setupGauge(peRatingGauge, 0.0,2.5);
    setupGauge(businessPerformanceGauge, 0.0, 0.3);
    setupGauge(companyGrowthGauge, 0.0, 60.0);
    setupGauge(companySizeGauge, 0.0, 5.0);
    setupGauge(highAndLowGauge, 0.0, 1.0);
    setupGauge(analystPredictionGauge, 0.0, 2.5);
  }


  private void setupGauge(Gauge gauge, double minValue, double maxValue){
    gauge.setSkinType(SkinType.INDICATOR);
    gauge.setBarColor(Color.rgb(51, 154, 204));
    gauge.setMinSize(60, 60);
    gauge.setMaxSize(60, 60);
    gauge.setMinValue(minValue);
    gauge.setMaxValue(maxValue);
    gauge.setAreaTextVisible(false);
  }

  private void installLabelTooltip(Label label, String text){
    Tooltip tooltip = new Tooltip(text);
    tooltip.setStyle("-fx-font-size: 14");
    tooltip.setShowDelay(Duration.millis(250));
    tooltip.setHideDelay(Duration.millis(250));
    tooltip.setShowDuration(Duration.seconds(20));
    tooltip.setMaxWidth(400);
    tooltip.setWrapText(true);
    label.setTooltip(tooltip);
  }
  public void setCurrentPrice(double todayPrice, double comparePrice){
    currentPrice.setText("$" +  todayPrice);

    if(todayPrice > comparePrice){
      upOrDownChange.setImage(upOrDownImage.get(1));
      percentageChange.setStyle("-fx-text-fill: #33c481");
    }else {
      upOrDownChange.setImage(upOrDownImage.get(0));
      percentageChange.setStyle("-fx-text-fill: #e05757");
    }
    percentageChange.setText(getPercentage(todayPrice, comparePrice));
  }

  private String getPercentage(double todayPrice, double comparePrice){
    double percentage = ((todayPrice-comparePrice)/comparePrice)*100;
    if(todayPrice < comparePrice){
      percentage = Math.abs(percentage);
    }

    return String.format(Locale.US, "%.2f", percentage) + "%";
  }

  @FXML
  public void peExplanation(){
    controller.showExplanationPage("PE");
  }
  @FXML
  public void businessPerformanceExplanation(){
    controller.showExplanationPage("busPer");
  }
  @FXML
  public void companyGrowthExplanation(){
    controller.showExplanationPage("compGrowth");
  }
  @FXML
  public void companySizeExplanation(){
    controller.showExplanationPage("compSize");
  }
  public void highAndLowExplanation(){
    controller.showExplanationPage("highAndLow");
  }
  public void analystPredictionExplanation(){
    controller.showExplanationPage("analystPred");
  }


  public void setController(GUIStockViewController controller){
    this.controller = controller;
  }
}