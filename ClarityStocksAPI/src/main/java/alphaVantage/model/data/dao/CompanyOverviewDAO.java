package alphaVantage.model.data.dao;

import alphaVantage.model.data.fundamental.CompanyOverview;
import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import java.sql.Date;
import java.time.LocalDate;

public class CompanyOverviewDAO {
  private final DSLContext connectionContext;

  public CompanyOverviewDAO(DSLContext connection) {
    this.connectionContext = connection;
  }

  public void insertFundamentalData(CompanyOverview companyOverview) {
    try {
      connectionContext.transaction(configuration -> {
        DSLContext transactionContext = DSL.using(configuration);

        transactionContext.execute(
            "CALL insertFundamental(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            companyOverview.getSymbol(),
            companyOverview.getAssetType(),
            companyOverview.getName(),
            companyOverview.getDescription(),
            companyOverview.getCIK(),
            companyOverview.getExchange(),
            companyOverview.getCurrency(),
            companyOverview.getCountry(),
            companyOverview.getSector(),
            companyOverview.getIndustry(),
            companyOverview.getAddress(),
            companyOverview.getFiscalYearEnd(),
            Date.valueOf(companyOverview.getLatestQuarter()),
            companyOverview.getMarketCapitalization(),
            companyOverview.getEBITDA(),
            companyOverview.getPERatio(),
            companyOverview.getPEGRatio(),
            companyOverview.getBookValue(),
            companyOverview.getDividendPerShare(),
            companyOverview.getDividendYield(),
            companyOverview.getEPS(),
            companyOverview.getRevenuePerShareTTM(),
            companyOverview.getProfitMargin(),
            companyOverview.getOperatingMarginTTM(),
            companyOverview.getReturnOnAssetsTTM(),
            companyOverview.getReturnOnEquityTTM(),
            companyOverview.getRevenueTTM(),
            companyOverview.getGrossProfitTTM(),
            companyOverview.getDilutedEPSTTM(),
            companyOverview.getQuarterlyEarningsGrowthYOY(),
            companyOverview.getQuarterlyRevenueGrowthYOY(),
            companyOverview.getAnalystTargetPrice(),
            companyOverview.getTrailingPE(),
            companyOverview.getForwardPE(),
            companyOverview.getPriceToSalesRatioTTM(),
            companyOverview.getPriceToBookRatio(),
            companyOverview.getEVToRevenue(),
            companyOverview.getEVToEBITDA(),
            companyOverview.getBeta(),
            companyOverview.getWeek52High(),
            companyOverview.getWeek52Low(),
            companyOverview.getMovingAverage50(),
            companyOverview.getMovingAverage200(),
            companyOverview.getSharesOutstanding(),
            Date.valueOf(companyOverview.getDividendDate()),
            Date.valueOf(companyOverview.getExDividendDate())
        );
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public CompanyOverview getCompanyOverview(String symbol) {
    try {
      CompanyOverview companyOverview = new CompanyOverview();

      Result<Record> result = connectionContext.fetch(
          "SELECT * " +
              "FROM getfundamentaldata(?)",
          symbol
      );

      companyOverview.setSymbol(result.getValue(0, DSL.field("stockSymbol", String.class)));
      companyOverview.setAssetType(result.getValue(0, DSL.field("assetType", String.class)));
      companyOverview.setName(result.getValue(0, DSL.field("name", String.class)));
      companyOverview.setDescription(result.getValue(0, DSL.field("description",
          String.class)));
      companyOverview.setCIK(result.getValue(0, DSL.field("cik", String.class)));
      companyOverview.setExchange(result.getValue(0, DSL.field("marketSymbol",
          String.class)));
      companyOverview.setCurrency(result.getValue(0, DSL.field("currency", String.class)));
      companyOverview.setCountry(result.getValue(0, DSL.field("country", String.class)));
      companyOverview.setSector(result.getValue(0, DSL.field("sector", String.class)));
      companyOverview.setIndustry(result.getValue(0, DSL.field("industry", String.class)));
      companyOverview.setAddress(result.getValue(0, DSL.field("address", String.class)));
      companyOverview.setFiscalYearEnd(result.getValue(0, DSL.field("fiscalYearEnd",
          String.class)));
      companyOverview.setLatestQuarter(result.getValue(0, DSL.field("latestQuarter",
          String.class)));
      companyOverview.setMarketCapitalization(result.getValue(0, DSL.field(
          "marketCapitalization", Long.class)));
      companyOverview.setEBITDA(result.getValue(0, DSL.field("ebitda", Double.class)));
      companyOverview.setPERatio(result.getValue(0, DSL.field("peRatio", Double.class)));
      companyOverview.setPEGRatio(result.getValue(0, DSL.field("pegRatio", Double.class)));
      companyOverview.setBookValue(result.getValue(0, DSL.field("bookValue", Double.class)));
      companyOverview.setDividendPerShare(result.getValue(0, DSL.field("dividendPerShare",
          Double.class)));
      companyOverview.setDividendYield(result.getValue(0, DSL.field("dividendYield",
          Double.class)));
      companyOverview.setEPS(result.getValue(0, DSL.field("eps", Double.class)));
      companyOverview.setRevenuePerShareTTM(result.getValue(0, DSL.field(
          "revenuePerShareTTM", Double.class)));
      companyOverview.setProfitMargin(result.getValue(0, DSL.field("profitMargin",
          Double.class)));
      companyOverview.setOperatingMarginTTM(result.getValue(0, DSL.field(
          "operatingMarginTTM", Double.class)));
      companyOverview.setReturnOnAssetsTTM(result.getValue(0, DSL.field("returnOnAssetsTTM",
          Double.class)));
      companyOverview.setReturnOnEquityTTM(result.getValue(0, DSL.field("returnOnEquityTTM",
          Double.class)));
      companyOverview.setRevenueTTM(result.getValue(0, DSL.field("revenueTTM", Long.class)));
      companyOverview.setGrossProfitTTM(result.getValue(0, DSL.field("grossProfitTTM",
          Long.class)));
      companyOverview.setDilutedEPSTTM(result.getValue(0, DSL.field("dilutedEPSTTM",
          Double.class)));
      companyOverview.setQuarterlyEarningsGrowthYOY(result.getValue(0, DSL.field(
          "quarterlyEarningsGrowthYOY", Double.class)));
      companyOverview.setQuarterlyRevenueGrowthYOY(result.getValue(0, DSL.field(
          "quarterlyRevenueGrowthYOY", Double.class)));
      companyOverview.setAnalystTargetPrice(result.getValue(0, DSL.field(
          "analystTargetPrice", Double.class)));
      companyOverview.setTrailingPE(result.getValue(0, DSL.field("trailingPE",
          Double.class)));
      companyOverview.setForwardPE(result.getValue(0, DSL.field("forwardPE",
          Double.class)));
      companyOverview.setPriceToSalesRatioTTM(result.getValue(0, DSL.field(
          "priceToSalesRatioTTM", Double.class)));
      companyOverview.setPriceToBookRatio(result.getValue(0, DSL.field("priceToBookRatio",
          Double.class)));
      companyOverview.setEVToRevenue(result.getValue(0, DSL.field("evToRevenue",
          Double.class)));
      companyOverview.setEVToEBITDA(result.getValue(0, DSL.field("evToEBITDA",
          Double.class)));
      companyOverview.setBeta(result.getValue(0, DSL.field("beta", Double.class)));
      companyOverview.setWeek52High(result.getValue(0, DSL.field("week52High",
          Double.class)));
      companyOverview.setWeek52Low(result.getValue(0, DSL.field("week52Low",
          Double.class)));
      companyOverview.setMovingAverage50(result.getValue(0, DSL.field("movingAverage50",
          Double.class)));
      companyOverview.setMovingAverage200(result.getValue(0, DSL.field("movingAverage200",
          Double.class)));
      companyOverview.setSharesOutstanding(result.getValue(0, DSL.field("sharesOutstanding",
          Long.class)));
      companyOverview.setDividendDate(result.getValue(0, DSL.field("dividendDate",
          String.class)));
      companyOverview.setExDividendDate(result.getValue(0, DSL.field("exDividendDate",
          String.class)));

      return companyOverview;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


}
