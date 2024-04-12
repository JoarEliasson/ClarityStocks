package SE.ClarityStocksGUI.controller.stockViewTiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoTile {
    @FXML
    private Label nameLabel;
    @FXML
    private Label sectorLabel;
    @FXML
    private Label descriptionLabel;

    public void initialize(){
        nameLabel.setText("Teva Pharmaceutical Industries Limited"); //TODO Remove when API is working
        sectorLabel.setText("Technology - Consumer Electronics"); //TODO Remove when API is working

    }

    public void setCompanyName(String text){
        nameLabel.setText(text);
    }

    public void setSector(String text){
        sectorLabel.setText(text);
    }
    public void setDescription(String text){
        descriptionLabel.setText(text);
    }
}
