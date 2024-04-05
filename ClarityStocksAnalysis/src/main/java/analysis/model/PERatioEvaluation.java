package analysis.model;

public class PERatioEvaluation {

  private final String symbol;
  private final String name;
  private final int rating;
  private final String description;

  public PERatioEvaluation(String symbol, String name, int rating, String description) {
    this.symbol = symbol;
    this.name = name;
    this.rating = rating;
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("%s (%s) -> Rating: %d/5%n%s", name, symbol, rating, description);
  }
}
