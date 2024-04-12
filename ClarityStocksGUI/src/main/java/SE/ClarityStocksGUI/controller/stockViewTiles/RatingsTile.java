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
    private Label peEvaluation;
    @FXML
    private ImageView peRatingImg;
    private ArrayList<Image> ratingImages;

    public void initialize(){
        loadImages();
        peRatingImg.setFitHeight(35);
        peRatingImg.setFitWidth(35);
    }

    private void loadImages(){
        ratingImages = new ArrayList<>();
        ratingImages.add(new Image(getClass().getResource("/SE/ClarityStocksGUI/view/0rating.png").toExternalForm()));
        ratingImages.add(new Image(getClass().getResource("/SE/ClarityStocksGUI/view/1rating.png").toExternalForm()));
        ratingImages.add(new Image(getClass().getResource("/SE/ClarityStocksGUI/view/2rating.png").toExternalForm()));
        ratingImages.add(new Image(getClass().getResource("/SE/ClarityStocksGUI/view/3rating.png").toExternalForm()));
        ratingImages.add(new Image(getClass().getResource("/SE/ClarityStocksGUI/view/4rating.png").toExternalForm()));
        ratingImages.add(new Image(getClass().getResource("/SE/ClarityStocksGUI/view/5rating.png").toExternalForm()));
    }

    public void setPeEvaluationText(int rating){
        peRatingImg.setImage(ratingImages.get(rating));
        peEvaluation.setText("P/E Rating " + rating);
    }
}
