package alphaVantage;

import common.data.fundamental.CompanyOverview;
import common.data.global.GlobalMarketInfo;
import common.data.global.MarketStatus;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Class for saving and loading global market information directly when retrieved.
 * <p>
 * This class is used to save and load global market information directly when retrieved from the
 * Alpha Vantage API.
 * </p>
 *
 * <p>
 * The class can be used when offline usage is required, or to reduce the number of API calls.
 * </p>
 *
 * <ul>
 *   <li>{@code FILE_PATH} - The file path for the saved global market information.</li>
 * </ul>
 *
 * @see GlobalMarketInfo
 * @see AlphaVantageClient
 * @see AlphaVantageParser
 * @see MarketStatus
 * @see DailyTopLists
 * @see GlobalMarketInfo
 *
 * @author Joar Eliason
 */
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
