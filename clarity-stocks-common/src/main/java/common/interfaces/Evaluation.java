package common.interfaces;

/**
 * Interface for evaluating stock parameters. The evaluation is based on the stock parameters and
 * returns a rating and a description of the evaluation.
 */
public interface Evaluation {

  /**
   * Method for evaluating the stock parameters.
   */
  void evaluate();

  /**
   * Method for getting the symbol of the stock.
   *
   * @return the symbol of the stock.
   */
  String getSymbol();

  /**
   * Method for getting the description of the evaluation.
   *
   * @return the description of the evaluation.
   */
  String getDescription();

}
