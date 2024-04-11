package SE.ClarityStocksGUI.controller.stockViewTiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoTile {
    @FXML
    private Label nameLabel;
    @FXML
    private Label sectorLabel;

    public void initialize(){

    }

    public void setCompanyName(String text){
        nameLabel.setText(text);
        nameLabel.setText("Apple Inc. (AAPL)"); //TODO Remove when API is working
    }

    public void setSector(String text){
        sectorLabel.setText(text);
        sectorLabel.setText("Technology - Consumer Electronics"); //TODO Remove when API is working
    }
}
