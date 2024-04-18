package SE.ClarityStocksGUI.controller.stockViewTiles;

import SE.ClarityStocksGUI.model.Effects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;

public class RatingsTile {

  @FXML
  private Label currentPrice;
  @FXML
  private Label peEvaluation;
  @FXML
  private ImageView peRatingImg;
  @FXML
  private Label businessPerformance;
  @FXML
  private ImageView businessPerformanceImg;
  @FXML
  private Label goldenCrossRating;
  @FXML
  private ImageView goldenCrossImage;
  private ArrayList<Image> ratingImages;

  public void initialize() {
    loadImages();
    setImageSize();
    System.out.println("Ratings tile initialized");
  }

  private void loadImages() {
    ratingImages = new ArrayList<>();
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
  }

  public void setPeEvaluationText(int rating, double peRatio, String description) {

    peRatingImg.setImage(ratingImages.get(rating));
    peEvaluation.setText("P/E Ratio " + peRatio + "\n" + description);
  }

  //TODO THIS IS WORK IN PROGRESS
  public void setBusinessPerformance(int rating, String description){
    businessPerformanceImg.setImage(ratingImages.get(rating));
    businessPerformance.setText("Business performance: " + description);
  }
  public void setGoldenCross(int rating, String description){
    goldenCrossImage.setImage(ratingImages.get(rating));
    goldenCrossRating.setText("Golden Cross Rating: " + description);
  }

  private void setImageSize(){
    peRatingImg.setFitHeight(50);
    peRatingImg.setFitWidth(50);

    businessPerformanceImg.setFitHeight(50);
    businessPerformanceImg.setFitWidth(50);

    goldenCrossImage.setFitHeight(50);
    goldenCrossImage.setFitWidth(50);
  }

  public void setCurrentPrice(double price){
    currentPrice.setText("$" +  price);
  }
}
