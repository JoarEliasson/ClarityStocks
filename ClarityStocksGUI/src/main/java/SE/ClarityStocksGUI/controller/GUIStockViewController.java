package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graphControllers.GUIStockLineGraphController;
import alphaVantage.AlphaVantageClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.StockInfo;
import org.kordamp.bootstrapfx.BootstrapFX;

public class GUIStockViewController {

  private GUIMainController controller;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox mainVBox;
  @FXML
  private Label nameLabel;
  @FXML
  private Label peEvaluationText;
  @FXML
  private Rectangle graphBackground;
  @FXML
  private Rectangle descBackground;
  @FXML
  private Rectangle statBackground;
  private StockInfo currentStock;
  @FXML
  private VBox stockStatsBox;
  private Stock stock;

  @FXML
  private ProgressBar progress;


  public void initialize() {

    progress.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);

    descBackground.setEffect(getDropShadow());
    statBackground.setEffect(getDropShadow());
    graphBackground.setEffect(getDropShadow());
  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }

  private DropShadow getDropShadow() {
    DropShadow dropShadow = new DropShadow();
    dropShadow.setRadius(20);
    dropShadow.setOffsetX(0);
    dropShadow.setOffsetY(0);
    dropShadow.setSpread(0.001);
    dropShadow.setBlurType(BlurType.GAUSSIAN);
    dropShadow.setColor(Color.LIGHTGRAY);
    return dropShadow;
  }

  public void loadStockView(String stockSymbol) {
    progress.setVisible(true);
    new Thread(new Runnable() {
      @Override
      public void run() {
        AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
        stock = alphaVantageClient.getStock(stockSymbol);

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            GUIStockLineGraphController.getInstance().loadStockData(stock);
            nameLabel.setText(stock.getCompanyOverview().getName());
            peEvaluationText.setText(stock.getPERatioEvaluation());
            progress.setVisible(false);
          }
        });
      }
    }).start();

  }
}
