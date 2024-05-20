package SE.ClarityStocksGUI.controller.tiles;

import SE.ClarityStocksGUI.controller.GUIStockViewController;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.SkinType;
import java.util.ArrayList;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
 * @see SE.ClarityStocksGUI.controller.GUIStockViewController
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
  private Label peEvaluation;
  @FXML
  private Gauge peRatingGauge;
  @FXML
  private Gauge businessPerformanceGauge;
  @FXML
  private Gauge companyGrowthGauge;
  @FXML
  private Gauge companySizeGauge;
  @FXML
  private Label businessPerformance;
  @FXML
  private Label companyGrowth;
  @FXML
  private Label companySize;

  //private ArrayList<Image> ratingImages; TODO MAYBE REMOVE?
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
        getClass().getResource("/SE/ClarityStocksGUI/view/stockDown.png").toExternalForm()));
    upOrDownImage.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/stockUp.png").toExternalForm()));

    /*ratingImages = new ArrayList<>();


    ratingImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/0rating.png").toExternalForm()));
    ratingImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/1rating.png").toExternalForm()));
    ratingImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/2rating.png").toExternalForm()));
    ratingImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/3rating.png").toExternalForm()));
    ratingImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/4rating.png").toExternalForm()));
    ratingImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/5rating.png").toExternalForm()));
     */

  }

  public void setPeEvaluationText(double rating, double peRatio, String description) {
    //peRatingImg.setImage(ratingImages.get(rating));
    peRatingGauge.setValue(peRatio);
    peEvaluation.setText(description);
    installLabelTooltip(peEvaluation, description);
  }
  public void setBusinessPerformance(double rating, String description){
    //businessPerformanceImg.setImage(ratingImages.get(rating));
    businessPerformanceGauge.setValue(rating);
    businessPerformance.setText(description);
    installLabelTooltip(businessPerformance, description);

  }

  public void setCompanyGrowth(double rating, String description){
    //companyGrowthImg.setImage(ratingImages.get(rating));
    companyGrowthGauge.setValue(rating);
    companyGrowth.setText(description);
    installLabelTooltip(companyGrowth, description);
  }

  public void setCompanySize(double rating, String description){
    //companySizeImg.setImage(ratingImages.get(rating));
    companyGrowthGauge.setValue(rating);
    companySize.setText(description);
    installLabelTooltip(companySize, description);
  }

  private void initialGaugesSetup(){
    setupGauge(peRatingGauge, 40);
    setupGauge(businessPerformanceGauge, 5);
    setupGauge(companyGrowthGauge, 5);
    setupGauge(companySizeGauge, 5);
  }


  private void setupGauge(Gauge gauge, int maxValue){
    gauge.setSkinType(SkinType.INDICATOR);
    gauge.setBarColor(Color.rgb(51, 154, 204));
    gauge.setMinSize(60, 60);
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

  public void setController(GUIStockViewController controller){
    this.controller = controller;
  }

  // Will be removed after the gauges has been shown to the team.
    /*private void setImageSize(){

    peRatingImg.setFitHeight(50);
    peRatingImg.setFitWidth(50);

    companyGrowthImg.setFitHeight(50);
    companyGrowthImg.setFitWidth(50);

    companySizeImg.setFitHeight(50);
    companySizeImg.setFitWidth(50);

    upOrDownChange.setFitHeight(25);
    upOrDownChange.setFitWidth(25);
  }
      */

}