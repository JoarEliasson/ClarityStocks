package common.interfaces;

/**
 * Interface for evaluating stock parameters. The evaluation is based on the stock parameters and
 * returns a rating and a description of the evaluation.
 *
 * @author Joar Eliasson
 */
public interface Evaluation {

  /**
   * Method for evaluating the stock parameters.
   * <p>
   * This method runs the evaluation and processes the stock parameters to generate an evaluation.
   */
  void evaluate();

  /**
   * Method for getting the symbol of the stock.
   *
   * @return the symbol of the stock.
   */
  String getSymbol();

  /**
   * Method for getting the title of the evaluation.
   * <p>
   * The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the evaluation.
   */
  String getEvaluationTitle();

  /**
   * Method for getting the subtitle of the evaluation.
   * <p>
   * The subtitle contains a description of the data that the evaluation is based on.
   *
   * @return the subtitle of the evaluation.
   */
  String getGeneralEvaluationInfo();

  /**
   * Method for getting the evaluation method.
   * <p>
   * This method contains a description of how the evaluation is performed.
   *
   * @return the evaluation method.
   */
  String getEvaluationMethodInfo();

  /**
   * Method for getting the description of the evaluation results.
   * <p>
   * The description is a detailed explanation of the evaluation, including what the evaluation
   * means for the stock.
   *
   * @return the description of the evaluation.
   */
  String getResultDescription();

}
