package SE.ClarityStocksGUI.controller.tiles;

import SE.ClarityStocksGUI.controller.GUIMainController;
import SE.ClarityStocksGUI.controller.GUIStockViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ExplanationTile {

  @FXML
  private Label mainTitle;
  @FXML
  private Label generalText;
  @FXML
  private Label companyTitle;
  @FXML
  private Label companyText;
  @FXML
  private ImageView closeButton;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox explanationTile;
  @FXML
  private ScrollPane scrollPane;
  private GUIMainController controller;

  public void initialize(){
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    explanationTile.prefWidthProperty().bind(layout.widthProperty().multiply(0.5));

    //FOR TESTING PURPOSES ONLY
    generalText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Faucibus interdum posuere lorem ipsum dolor. Vel fringilla est ullamcorper eget nulla facilisi. Eget dolor morbi non arcu risus. Diam vel quam elementum pulvinar. Suscipit adipiscing bibendum est ultricies. Tortor aliquam nulla facilisi cras fermentum odio eu feugiat. Potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Felis bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Tristique sollicitudin nibh sit amet commodo nulla. Est ultricies integer quis auctor elit sed. Gravida neque convallis a cras semper auctor neque vitae. Quis viverra nibh cras pulvinar mattis nunc sed blandit. Tortor aliquam nulla facilisi cras fermentum odio. Ultricies leo integer malesuada nunc vel risus commodo viverra maecenas. Sem fringilla ut morbi tincidunt augue.\n"
        + "\n"
        + "Aenean et tortor at risus viverra adipiscing at in. Nibh venenatis cras sed felis eget. Lorem ipsum dolor sit amet consectetur adipiscing elit. Ultricies mi quis hendrerit dolor magna eget. Eget magna fermentum iaculis eu non diam phasellus vestibulum. Dignissim suspendisse in est ante in. Amet est placerat in egestas. Id aliquet risus feugiat in ante metus dictum. Amet consectetur adipiscing elit pellentesque. Faucibus interdum posuere lorem ipsum dolor. Eu nisl nunc mi ipsum faucibus vitae. Nulla facilisi morbi tempus iaculis urna id. Non nisi est sit amet facilisis. Libero nunc consequat interdum varius sit amet mattis vulputate.\n"
        + "\n"
        + "Sagittis vitae et leo duis. Sodales neque sodales ut etiam sit amet nisl purus in. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque. Eu mi bibendum neque egestas congue quisque egestas diam in. Pellentesque massa placerat duis ultricies. Molestie a iaculis at erat pellentesque adipiscing commodo. Vitae suscipit tellus mauris a diam maecenas. Aliquet risus feugiat in ante metus. Et malesuada fames ac turpis egestas maecenas pharetra convallis posuere. Orci sagittis eu volutpat odio facilisis mauris sit. Morbi quis commodo odio aenean sed adipiscing. Amet nisl purus in mollis nunc sed id semper risus. At imperdiet dui accumsan sit amet nulla facilisi morbi tempus. Lorem sed risus ultricies tristique nulla aliquet. Netus et malesuada fames ac turpis. Eget nullam non nisi est sit amet facilisis magna. Enim sit amet venenatis urna cursus eget nunc scelerisque viverra.");
    setUpCloseButton();
  }

  private void setUpCloseButton(){
    Image image = new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/close.png").toExternalForm());
    closeButton.setImage(image);

    closeButton.setFitWidth(25);
    closeButton.setFitHeight(25);
  }

  @FXML
  public void closeButtonPressed(){
    controller.closeExplanationPage();
  }

  public void setController(GUIMainController controller){
    this.controller = controller;
  }

  public void setMainTitle(String text){
    mainTitle.setText(text);
  }

  public void setGeneralText(String text){
    //generalText.setText(text);

    //Remove when explanations are fully implemented.
    generalText.setText(String.format(
        "Understanding P/E Ratio:%n%n"

            + "The Price-to-Earnings (P/E) ratio is a commonly used metric in financial markets to "
            + "evaluate the valuation of a company's stock. It is calculated by dividing the current "
            + "market price of a stock by its earnings per share (EPS). The P/E ratio provides insight "
            + "into how much investors are willing to pay per dollar of earnings, indicating the "
            + "market's expectations of a company's future financial performance.%n%n"

            + "For example, a high P/E ratio may suggest that the market expects strong future growth "
            + "and earnings, while a low P/E ratio could imply that the stock is undervalued or that "
            + "the company is facing challenges. It is important for investors to compare the P/E ratio"
            + " of a stock with the average P/E ratio of the market or the industry to make informed "
            + "investment decisions."
    )+String.format(
        "Evaluation Method Explained:%n%n"
            + "The evaluation method for determining a stock's rating based on its Price-to-Earnings "
            + "(P/E) ratio involves comparing the stock's P/E ratio to the average P/E ratio of the US "
            + "stock market. This method is a standardized approach to gauge a stock's relative "
            + "valuation and potential performance. The P/E ratio of a stock is divided by the average "
            + "market P/E ratio, yielding a rating that reflects whether the stock is overvalued or "
            + "undervalued relative to the market average.%n%n"

            + "For instance, a rating of 1 indicates that the stock's P/E ratio is exactly the same as "
            + "the market average, suggesting a neutral valuation. Ratings above 1 indicate a higher "
            + "P/E ratio, implying that the stock is considered overvalued relative to the market, "
            + "potentially reflecting strong growth expectations. Conversely, ratings below 1 suggest "
            + "a lower P/E ratio, indicating the stock may be undervalued or that the market has lower "
            + "expectations for its growth.%n%n"

            + "This method provides a clear and objective measure to compare individual stocks against "
            + "a broad market benchmark, thereby facilitating informed investment decisions.%n%n" +

            "Rating Interpretation:%n" +
            "1. **Very Low (Rating < 0.25)**: "
            + "Significantly undervalued, potential buy if fundamentals are strong.%n" +
            "2. **Low (Rating 0.25 - 0.5)**: "
            + "Lower valuation, further investigation needed.%n" +
            "3. **Below Average (Rating 0.5 - 0.75)**: "
            + "Valued below market, could be a bargain or facing challenges.%n" +
            "4. **Average (Rating 0.75 - 1.0)**: "
            + "Valuation aligns with market average, neutral outlook.%n" +
            "5. **Above Average (Rating 1.0 - 1.25)**: "
            + "Slightly overvalued, moderate growth expected.%n" +
            "6. **High (Rating 1.25 - 1.5)**: "
            + "Higher valuation expectations, strong growth potential.%n" +
            "7. **Very High (Rating 1.5 - 1.75)**: "
            + "Considerably overvalued, high growth expectations.%n" +
            "8. **Extremely High (Rating 1.75 - 2.0)**: "
            + "Very high market expectations, ensure growth justifies valuation.%n" +
            "9. **Sky High (Rating > 2.0)**: "
            + "Highly overvalued, strong growth or speculative trading."
    ));
  }
  public void setCompanyTitle(String text){
    companyTitle.setText(text);
  }
  public void setCompanyText(String text){
    companyText.setText(text);
  }

  public void setupScrollbar() {
    scrollPane.minWidthProperty().bind(explanationTile.widthProperty());
    scrollPane.minHeightProperty().bind(controller.getHeightProperty());
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setEffect(null);
  }

  public void setScrollPaneMaxHeight(){
    scrollPane.maxHeightProperty().bind(controller.getHeightProperty());
  }

}
