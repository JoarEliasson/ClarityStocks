package yahooFinance;

public class LoggerFactory {

  static Logger logger;

  public static Logger getLogger(Class<Stock> stockClass) {

    return logger;
  }
}
