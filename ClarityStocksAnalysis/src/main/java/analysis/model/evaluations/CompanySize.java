package analysis.model.evaluations;

import analysis.model.interfaces.Evaluation;

/** Class for evaluating the size of the company. Company size evaluations are based on statistics from
 * https://www.investopedia.com/insights/understanding-small-and-big-cap-stocks/
 * @author Olivia Svensson
 * */
public class CompanySize implements Evaluation {
    String symbol;
    long revenueTTM;
    String description = "";
    long nanoCap = 50000000L;
    long microCap = 250000000L;
    long midCap = 2000000000L;
    long bigCap = 10000000000L;
    long megaCap = 200000000000L;

    public CompanySize(String symbol, long revenueTTM) {
        this.symbol = symbol;
        this.revenueTTM = revenueTTM;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Mega-cap: Market cap of $200 billion and greater
     10
     Big-cap: $10 billion and greater, up to $200 billion
     Mid-cap: $2 billion to $10 billion
     Small-cap: $250 million to $2 billion
     1
     Micro-cap: $50 million to $250 million
     Nano-cap: Under $50 million
     11
     U.S. Securities and Exchange. "Microcap Stock: A Guide for Investors."
     * */

    @Override
    public void evaluate() {
        if(revenueTTM < nanoCap) {
            description = symbol +  " is considered a Nano-cap company judging by the revenue for the past twelve" +
            " months.";
        } else if (revenueTTM > 50000000 && revenueTTM < microCap) {
            description = symbol +  " is considered a Micro-cap company judging by the revenue for the past twelve" +
            " months.";
        } else if (revenueTTM >= microCap && revenueTTM < midCap) {
            description = symbol + " is considered a Small-cap company judging by the revenue for the past twelve" +
            " months.";
        } else if(revenueTTM >= midCap && revenueTTM < bigCap) {
            description = symbol + " is considered a Mid-cap company judging by the revenue for the past twelve" +
            " months.";
        } else if(revenueTTM >= bigCap && revenueTTM < megaCap) {
            description = symbol + " is considered a Big-cap company judging by the revenue for the past twelve" +
            " months.";
        } else if(revenueTTM >= megaCap) {
            description = symbol + " is considered a Mega-cap company judging by the revenue for the past twelve" +
            " months.";
        }
        else {
            description = "Something went wrong with the company size evaluation";
        }
    }
    public String getDescription() {
        return description;
    }

}
