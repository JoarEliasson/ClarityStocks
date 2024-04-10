package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.StockInfo;
import model.StockInfoList;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.bootstrapfx.BootstrapFX;

public class GUIHomeController {
    private GUIMainController controller;
    @FXML
    private BorderPane layout;
    @FXML
    private VBox mainVBox;
    //For testing purposes, will be removed later
    @FXML
    private Label welcomeText;


    public void initialize() {
        VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);

        welcomeText.setText("Clarity Stocks");

    }

    public void setController(GUIMainController controller) {
        this.controller = controller;
    }

}