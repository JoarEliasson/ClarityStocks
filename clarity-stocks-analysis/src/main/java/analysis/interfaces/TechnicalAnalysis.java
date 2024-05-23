package analysis.interfaces;

/**
 * Interface for performing technical analysis on stock data.
 * <p>
 * This interface provides methods for analyzing stock data and obtaining detailed results of the
 * analysis.
 *
 * @author Joar Eliasson
 */
public interface TechnicalAnalysis {

  /**
   * Method for performing the technical analysis.
   * <p>
   * This method runs the analysis on the stock data.
   */
  void analyze();

  /**
   * Method for getting the symbol of the stock.
   *
   * @return the symbol of the stock.
   */
  String getSymbol();

  /**
   * Method for getting the title of the analysis.
   * <p>
   * The title corresponds to the type of analysis that is performed.
   *
   * @return the title of the analysis.
   */
  String getAnalysisTitle();

  /**
   * Method for getting the subtitle of the analysis.
   * <p>
   * The subtitle is a short description of the data that the analysis is based on.
   *
   * @return the subtitle of the analysis.
   */
  String getGeneralAnalysisInfo();

  /**
   * Method for getting the analysis method.
   * <p>
   * The analysis method is a short description of how the analysis is performed.
   *
   * @return the analysis method.
   */
  String getAnalysisMethodInfo();

  /**
   * Method for getting the description of the analysis results.
   * <p>
   * The description is a detailed explanation of the analysis, including what the analysis means
   * for the stock.
   *
   * @return the description of the analysis.
   */
  String getResultDescription();

}
