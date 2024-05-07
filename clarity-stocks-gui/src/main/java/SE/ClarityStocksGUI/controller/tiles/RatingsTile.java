package SE.ClarityStocksGUI.controller.tiles;

import java.util.ArrayList;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  @FXML
  private Label currentPrice;
  @FXML
  private Label percentageChange;
  @FXML
  private ImageView upOrDownChange;
  @FXML
  private Label peEvaluation;
  @FXML
  private ImageView peRatingImg;
  @FXML
  private Label businessPerformance;
  @FXML
  private ImageView businessPerformanceImg;
  @FXML
  private Label companyGrowth;
  @FXML
  private ImageView companyGrowthImg;
  @FXML
  private Label companySize;
  @FXML
  private ImageView companySizeImg;
  private ArrayList<Image> ratingImages;
  private ArrayList<Image> upOrDownImage;

  public void initialize() {
    loadImages();
    setImageSize();
  }

  private void loadImages() {
    ratingImages = new ArrayList<>();
    upOrDownImage = new ArrayList<>();
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

    upOrDownImage.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/stockDown.png").toExternalForm()));
    upOrDownImage.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/stockUp.png").toExternalForm()));
  }

  public void setPeEvaluationText(int rating, double peRatio, String description) {
    peRatingImg.setImage(ratingImages.get(rating));
    peEvaluation.setText("P/E Ratio " + peRatio + "\n" + description);
    installLabelTooltip(peEvaluation, description);
  }
  public void setBusinessPerformance(int rating, String description){
    businessPerformanceImg.setImage(ratingImages.get(rating));
    businessPerformance.setText("Business performance: " + description);
    installLabelTooltip(businessPerformance, description);

  }

  public void setCompanyGrowth(int rating, String description){
    companyGrowthImg.setImage(ratingImages.get(rating));
    companyGrowth.setText("Company growth: " + description);
    installLabelTooltip(companyGrowth, description);
  }

  public void setCompanySize(int rating, String description){
    companySizeImg.setImage(ratingImages.get(rating));
    companySize.setText("Company size: " + description);
    installLabelTooltip(companySize, description);
  }

  private void setImageSize(){
    peRatingImg.setFitHeight(50);
    peRatingImg.setFitWidth(50);

    businessPerformanceImg.setFitHeight(50);
    businessPerformanceImg.setFitWidth(50);

    companyGrowthImg.setFitHeight(50);
    companyGrowthImg.setFitWidth(50);

    companySizeImg.setFitHeight(50);
    companySizeImg.setFitWidth(50);

    upOrDownChange.setFitHeight(25);
    upOrDownChange.setFitWidth(25);
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
}