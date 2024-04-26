package analysis.model.evaluations;

import analysis.model.interfaces.Evaluation;

/** Class for evaluating the size of the company.
 * @author Olivia Svensson
 * */
public class CompanySize implements Evaluation {
    String symbol;
    long revenueTTM;
    String description = "";
    long smallCompany = 1000000000L;
    long bigCompany = 10000000000L;


    public CompanySize(String symbol, long revenueTTM) {
        this.symbol = symbol;
        this.revenueTTM = revenueTTM;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Evaluation depends on:
     * Small company < 1 billion US
     *
     * Medium company < 10 billion USD
     *
     * Large > 10 billion USD
     *
     * Giant company > 50 billion USD- Among the 200 biggest companies in the world
     * */

    @Override
    public void evaluate() {
        if(revenueTTM < smallCompany) {
            description = symbol +  " is considered a small sized company judging by the revenue for the past twelve" +
                    " months.";
        } else if (revenueTTM > 1000000000 && revenueTTM < bigCompany) {
            description = symbol +  " is considered a medium sized company judging by the revenue for the past twelve" +
                    " months.";
        } else if (revenueTTM >= bigCompany) {
            description = symbol + " is considered a larger sized company judging by the revenue for the past twelve" +
                    " months.";
        } else {
            description = "Something went wrong with the company size evaluation";
        }
    }
    public String getDescription() {
        return description;
    }

}
