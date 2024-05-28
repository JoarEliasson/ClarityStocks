package alphaVantage;

import common.data.global.GlobalMarketInfo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalDataSaver {

  private static final String FILE_PATH = "";

  public static void saveGlobalMarketInfo(GlobalMarketInfo globalMarketInfo) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      oos.writeObject(globalMarketInfo);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static GlobalMarketInfo getGlobalMarketInfo() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      return (GlobalMarketInfo) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}
