package common.data.fundamental;

/**
 * Represents an {@code IncomeStatement} that stores comprehensive financial data for a company
 * retrieved via an API. This class encapsulates financial results such as revenue, costs, and
 * profits to analyze the company's financial health in a structured manner.
 *
 * <p>Key financial metrics stored in this class include:
 * <ul>
 *   <li>{@code symbol} - Unique identifier for the company's stock.</li>
 *   <li>{@code fiscalDateEnding} - Marks the end of the reported fiscal period.</li>
 *   <li>{@code reportedCurrency} - Currency in which financial statements are reported.</li>
 *   <li>{@code grossProfit} - Calculated as total revenue minus the cost of goods sold.</li>
 *   <li>{@code totalRevenue} - Total income from business activities before deductions.</li>
 *   <li>{@code costOfRevenue} - Direct costs associated with generating revenue.</li>
 *   <li>{@code costOfGoodsAndServicesSold} - Direct costs related to production of goods and
 *       services.</li>
 *   <li>{@code operatingIncome} - Profits from core business activities after operating
 *        expenses.</li>
 *   <li>{@code sellingGeneralAndAdministrative} - Overhead costs like marketing and administrative
 *       salaries.</li>
 *   <li>{@code researchAndDevelopment} - Expenses for developing new products or technologies.</li>
 *   <li>{@code operatingExpenses} - Total expenses incurred from business operations.</li>
 *   <li>{@code investmentIncomeNet} - Net income from financial investments.</li>
 *   <li>{@code netInterestIncome} - Difference between interest earned and paid.</li>
 *   <li>{@code interestIncome} - Income from interest-bearing assets.</li>
 *   <li>{@code interestExpense} - Costs incurred from interest on borrowed funds.</li>
 *   <li>{@code nonInterestIncome} - Income from non-interest related business activities.</li>
 *   <li>{@code otherNonOperatingIncome} - Income from non-core business activities.</li>
 *   <li>{@code depreciation} - Expense for wear and tear on tangible assets.</li>
 *   <li>{@code depreciationAndAmortization} - Expenses for depreciation and amortization of both
 *       tangible and intangible assets.</li>
 *   <li>{@code incomeBeforeTax} - Profitability before accounting for income taxes.</li>
 *   <li>{@code incomeTaxExpense} - Taxes charged against profits.</li>
 *   <li>{@code interestAndDebtExpense} - Total interest and related costs on debts.</li>
 *   <li>{@code netIncomeFromContinuingOperations} - Net earnings from ongoing business
 *        activities.</li>
 *   <li>{@code comprehensiveIncomeNetOfTax} - Changes in equity excluding transactions from
 *        owners.</li>
 *   <li>{@code ebit} - Earnings before interest and taxes, indicating operational
 *        profitability.</li>
 *   <li>{@code ebitda} - Earnings before interest, taxes, depreciation, and amortization.</li>
 *   <li>{@code netIncome} - Total net profit after all deductions.</li>
 * </ul>
 *
 * <p>This class provides constructors to initialize key components and methods to retrieve or
 * update the financial metrics accordingly.
 *
 * @author Joar Eliasson
 */
public class IncomeStatement {

  /**
   * Unique identifier for the company's stock, typically represented by a ticker symbol.
   */
  private final String symbol;

  /**
   * Represents the last day of the financial reporting period, marking the end of a fiscal quarter
   * or year.
   */
  private String fiscalDateEnding;

  /**
   * Currency in which all financial transactions and balances are reported, affecting the
   * interpretation of financial results.
   */
  private String reportedCurrency;

  /**
   * Calculated as total revenue minus the direct costs associated with producing goods or services.
   * This metric highlights the profitability of core business activities before administrative and
   * other indirect costs.
   */
  private long grossProfit;

  /**
   * Total income generated from the company's primary business operations, such as sales of
   * products or services, before deducting any expenses.
   */
  private long totalRevenue;

  /**
   * Total direct costs involved in generating revenue, which includes both the costs of producing
   * goods (materials and labor) and costs related to delivering services.
   */
  private long costOfRevenue;

  /**
   * Direct costs related solely to the production and sale of goods, including raw materials and
   * labor. For service providers, this may also encompass expenses directly tied to service
   * fulfillment.
   */
  private long costOfGoodsAndServicesSold;

  /**
   * Earnings from primary business operations after deducting direct and indirect operational
   * expenses but before interest and taxes.
   */
  private long operatingIncome;

  /**
   * Overhead expenses not directly linked to production or service delivery, such as marketing,
   * administration, and general office expenses. This category is crucial for understanding the
   * company’s operational efficiency.
   */
  private long sellingGeneralAndAdministrative;

  /**
   * Expenditures on the development of new products or technologies, which are essential for
   * sustaining innovation and maintaining competitive market positions, particularly in
   * tech-driven industries.
   */
  private long researchAndDevelopment;

  /**
   * Total expenses incurred from all business activities, excluding the direct cost of goods sold,
   * which includes both SG&A and R&D expenses among others.
   */
  private long operatingExpenses;
  /**
   * Net earnings obtained from investments such as stocks, bonds, and other securities,
   * minus related expenses. This reflects the company’s capacity to generate additional
   * income from its investment activities.
   */
  private long investmentIncomeNet;

  /**
   * The net amount of interest earned over interest paid on loans, bonds, or other
   * debt instruments. A critical measure for financial institutions highlighting the
   * profitability of their core lending and investment activities.
   */
  private long netInterestIncome;

  /**
   * Income generated from assets that accrue interest, such as bank deposits, bonds,
   * and loans issued by the company to other parties.
   */
  private long interestIncome;

  /**
   * Expenses incurred from interest on borrowed money, including loans, bonds, and other
   * financial instruments. This is crucial for understanding the financial burden of the
   * company’s debt.
   */
  private long interestExpense;

  /**
   * Revenue from activities unrelated to the company's main business operations and not
   * involving interest payments, such as rental income, service fees, and commissions.
   * Important for diversifying the company's revenue streams.
   */
  private long nonInterestIncome;

  /**
   * Gains derived from secondary activities not part of the core business operations, such
   * as the sale of long-term assets, settlements from lawsuits, or foreign exchange gains.
   * These can provide insights into non-recurring income sources.
   */
  private long otherNonOperatingIncome;

  /**
   * Systematic allocation of the cost of tangible assets over their useful lives, reflecting
   * the consumption of the asset value due to use or obsolescence.
   */
  private long depreciation;

  /**
   * Represents the combined expenses related to both depreciation of physical assets and
   * amortization of intangible assets. This provides a fuller view of how asset values
   * are consumed over time in the business.
   */
  private long depreciationAndAmortization;

  /**
   * The profit a company makes before any deductions for interest and taxes, calculated
   * by subtracting all operating and non-operating expenses from total revenue. A key
   * indicator of a company's pre-tax operational efficiency.
   */
  private long incomeBeforeTax;

  /**
   * The estimated total taxes a company is liable to pay as per applicable tax rates,
   * based on its taxable income. It shows the impact of taxation on corporate earnings.
   */
  private long incomeTaxExpense;

  /**
   * All expenses associated with managing the company’s debt, such as interest payments
   * on bond issues and bank loans. This variable is vital for assessing the cost implications
   * of financial leverage.
   */
  private long interestAndDebtExpense;

  /**
   * Net income calculated from continuing operations, excluding outcomes from discontinued
   * operations or extraordinary items. This metric provides clarity on earnings from
   * the company's ongoing primary business activities.
   */
  private long netIncomeFromContinuingOperations;

  /**
   * Includes all changes in equity during a period that are not from transactions with owners or
   * distributions to owners. It encompasses recognized and unrecognized income and expenses, such
   * as gains or losses from revaluation of marketable securities and adjustments due to foreign
   * currency translations.
   */
  private long comprehensiveIncomeNetOfTax;

  /**
   * Earnings Before Interest and Taxes (EBIT). This is a measure of the firm's profit that includes
   * all incomes and operational expenses but excludes interest and income tax expenses. EBIT provides
   * insight into the operational efficiency of the business without the impact of financial structure
   * and tax obligations.
   */
  private long EBIT;

  /**
   * Earnings Before Interest, Taxes, Depreciation, and Amortization (EBITDA). This metric extends
   * EBIT by also excluding non-cash charges of depreciation and amortization, offering a clearer
   * view of the company's operational profitability and cash flow generated from core business
   * activities before the influence of financial and accounting decisions.
   */
  private long EBITDA;

  /**
   * The total net profit or loss for the period after all expenses, including taxes, interest,
   * depreciation, and amortization have been subtracted from total revenues. This figure represents
   * the amount of earnings available to shareholders and is a crucial indicator of the company’s
   * overall financial health.
   */
  private long netIncome;

  public IncomeStatement(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Retrieves the value of a specific financial variable from the income statement.
   *
   * @param variableName The name of the financial variable to retrieve.
   * @return The value of the specified financial variable.
   */
  public long getVariable(String variableName) {
    return switch (variableName) {
      case "grossProfit" -> grossProfit;
      case "totalRevenue" -> totalRevenue;
      case "costOfRevenue" -> costOfRevenue;
      case "costOfGoodsAndServicesSold" -> costOfGoodsAndServicesSold;
      case "operatingIncome" -> operatingIncome;
      case "sellingGeneralAndAdministrative" -> sellingGeneralAndAdministrative;
      case "researchAndDevelopment" -> researchAndDevelopment;
      case "operatingExpenses" -> operatingExpenses;
      case "investmentIncomeNet" -> investmentIncomeNet;
      case "netInterestIncome" -> netInterestIncome;
      case "interestIncome" -> interestIncome;
      case "interestExpense" -> interestExpense;
      case "nonInterestIncome" -> nonInterestIncome;
      case "otherNonOperatingIncome" -> otherNonOperatingIncome;
      case "depreciation" -> depreciation;
      case "depreciationAndAmortization" -> depreciationAndAmortization;
      case "incomeBeforeTax" -> incomeBeforeTax;
      case "incomeTaxExpense" -> incomeTaxExpense;
      case "interestAndDebtExpense" -> interestAndDebtExpense;
      case "netIncomeFromContinuingOperations" -> netIncomeFromContinuingOperations;
      case "comprehensiveIncomeNetOfTax" -> comprehensiveIncomeNetOfTax;
      case "ebit" -> EBIT;
      case "ebitda" -> EBITDA;
      case "netIncome" -> netIncome;
      default -> 0;
    };
  }

  public String getSymbol() {
    return symbol;
  }

  public String getFiscalDateEnding() {
    return fiscalDateEnding;
  }

  public void setFiscalDateEnding(String fiscalDateEnding) {
    this.fiscalDateEnding = fiscalDateEnding;
  }

  public String getReportedCurrency() {
    return reportedCurrency;
  }

  public void setReportedCurrency(String reportedCurrency) {
    this.reportedCurrency = reportedCurrency;
  }

  public long getGrossProfit() {
    return grossProfit;
  }

  public void setGrossProfit(long grossProfit) {
    this.grossProfit = grossProfit;
  }

  public long getTotalRevenue() {
    return totalRevenue;
  }

  public void setTotalRevenue(long totalRevenue) {
    this.totalRevenue = totalRevenue;
  }

  public long getCostOfRevenue() {
    return costOfRevenue;
  }

  public void setCostOfRevenue(long costOfRevenue) {
    this.costOfRevenue = costOfRevenue;
  }

  public long getCostOfGoodsAndServicesSold() {
    return costOfGoodsAndServicesSold;
  }

  public void setCostOfGoodsAndServicesSold(long costOfGoodsAndServicesSold) {
    this.costOfGoodsAndServicesSold = costOfGoodsAndServicesSold;
  }

  public long getOperatingIncome() {
    return operatingIncome;
  }

  public void setOperatingIncome(long operatingIncome) {
    this.operatingIncome = operatingIncome;
  }

  public long getSellingGeneralAndAdministrative() {
    return sellingGeneralAndAdministrative;
  }

  public void setSellingGeneralAndAdministrative(long sellingGeneralAndAdministrative) {
    this.sellingGeneralAndAdministrative = sellingGeneralAndAdministrative;
  }

  public long getResearchAndDevelopment() {
    return researchAndDevelopment;
  }

  public void setResearchAndDevelopment(long researchAndDevelopment) {
    this.researchAndDevelopment = researchAndDevelopment;
  }

  public long getOperatingExpenses() {
    return operatingExpenses;
  }

  public void setOperatingExpenses(long operatingExpenses) {
    this.operatingExpenses = operatingExpenses;
  }

  public long getInvestmentIncomeNet() {
    return investmentIncomeNet;
  }

  public void setInvestmentIncomeNet(long investmentIncomeNet) {
    this.investmentIncomeNet = investmentIncomeNet;
  }

  public long getNetInterestIncome() {
    return netInterestIncome;
  }

  public void setNetInterestIncome(long netInterestIncome) {
    this.netInterestIncome = netInterestIncome;
  }

  public long getInterestIncome() {
    return interestIncome;
  }

  public void setInterestIncome(long interestIncome) {
    this.interestIncome = interestIncome;
  }

  public long getInterestExpense() {
    return interestExpense;
  }

  public void setInterestExpense(long interestExpense) {
    this.interestExpense = interestExpense;
  }

  public long getNonInterestIncome() {
    return nonInterestIncome;
  }

  public void setNonInterestIncome(long nonInterestIncome) {
    this.nonInterestIncome = nonInterestIncome;
  }

  public long getOtherNonOperatingIncome() {
    return otherNonOperatingIncome;
  }

  public void setOtherNonOperatingIncome(long otherNonOperatingIncome) {
    this.otherNonOperatingIncome = otherNonOperatingIncome;
  }

  public long getDepreciation() {
    return depreciation;
  }

  public void setDepreciation(long depreciation) {
    this.depreciation = depreciation;
  }

  public long getDepreciationAndAmortization() {
    return depreciationAndAmortization;
  }

  public void setDepreciationAndAmortization(long depreciationAndAmortization) {
    this.depreciationAndAmortization = depreciationAndAmortization;
  }

  public long getIncomeBeforeTax() {
    return incomeBeforeTax;
  }

  public void setIncomeBeforeTax(long incomeBeforeTax) {
    this.incomeBeforeTax = incomeBeforeTax;
  }

  public long getIncomeTaxExpense() {
    return incomeTaxExpense;
  }

  public void setIncomeTaxExpense(long incomeTaxExpense) {
    this.incomeTaxExpense = incomeTaxExpense;
  }

  public long getInterestAndDebtExpense() {
    return interestAndDebtExpense;
  }

  public void setInterestAndDebtExpense(long interestAndDebtExpense) {
    this.interestAndDebtExpense = interestAndDebtExpense;
  }

  public long getNetIncomeFromContinuingOperations() {
    return netIncomeFromContinuingOperations;
  }

  public void setNetIncomeFromContinuingOperations(long netIncomeFromContinuingOperations) {
    this.netIncomeFromContinuingOperations = netIncomeFromContinuingOperations;
  }

  public long getComprehensiveIncomeNetOfTax() {
    return comprehensiveIncomeNetOfTax;
  }

  public void setComprehensiveIncomeNetOfTax(long comprehensiveIncomeNetOfTax) {
    this.comprehensiveIncomeNetOfTax = comprehensiveIncomeNetOfTax;
  }

  public long getEBIT() {
    return EBIT;
  }

  public void setEBIT(long EBIT) {
    this.EBIT = EBIT;
  }

  public long getEBITDA() {
    return EBITDA;
  }

  public void setEBITDA(long EBITDA) {
    this.EBITDA = EBITDA;
  }

  public long getNetIncome() {
    return netIncome;
  }

  public void setNetIncome(long netIncome) {
    this.netIncome = netIncome;
  }

  @Override
  public String toString() {
    return String.format(
        "%n[Income Statement (%s) %s]%n%n\t"
        + "Reported Currency: %s%n\t"
        + "Gross Profit: %d%n\t"
        + "Total Revenue: %d%n\t"
        + "Cost of Revenue: %d%n\t"
        + "Cost of Goods and Services Sold: %d%n\t"
        + "Operating Income: %d%n\t"
        + "Selling, General and Administrative: %d%n\t"
        + "Research and Development: %d%n\t"
        + "Operating Expenses: %d%n\t"
        + "Investment Income Net: %d%n\t"
        + "Net Interest Income: %d%n\t"
        + "Interest Income: %d%nInterest Expense: %d%n\t"
        + "Non-Interest Income: %d%n\t"
        + "Other Non-Operating Income: %d%n\t"
        + "Depreciation: %d%n\t"
        + "Depreciation and Amortization: %d%n\t"
        + "Income Before Tax: %d%n\t"
        + "Income Tax Expense: %d%n\t"
        + "Interest and Debt Expense: %d%n\t"
        + "Net Income from Continuing Operations: %d%n\t"
        + "Comprehensive Income Net of Tax: %d%n\t"
        + "EBIT: %d%n\t"
        + "EBITDA: %d%n\t"
        + "Net Income: %d%n",
        symbol, fiscalDateEnding, reportedCurrency, grossProfit, totalRevenue, costOfRevenue,
        costOfGoodsAndServicesSold, operatingIncome, sellingGeneralAndAdministrative,
        researchAndDevelopment, operatingExpenses, investmentIncomeNet, netInterestIncome,
        interestIncome, interestExpense, nonInterestIncome, otherNonOperatingIncome, depreciation,
        depreciationAndAmortization, incomeBeforeTax, incomeTaxExpense, interestAndDebtExpense,
        netIncomeFromContinuingOperations, comprehensiveIncomeNetOfTax, EBIT, EBITDA, netIncome
    );
  }
}