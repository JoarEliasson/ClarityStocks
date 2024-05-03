package SE.ClarityStocksGUI.model;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Effects {

  private static DropShadow dropShadow;

  public static DropShadow getDropShadow() {
    if (dropShadow == null) {
      dropShadow = new DropShadow();
      dropShadow.setRadius(20);
      dropShadow.setOffsetX(0);
      dropShadow.setOffsetY(0);
      dropShadow.setSpread(0.001);
      dropShadow.setBlurType(BlurType.GAUSSIAN);
      dropShadow.setColor(Color.LIGHTGRAY);
    }
    return dropShadow;
  }
}
