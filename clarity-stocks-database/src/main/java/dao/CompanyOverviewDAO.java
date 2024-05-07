package dao;

import common.data.fundamental.CompanyOverview;
import java.math.BigDecimal;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import java.sql.Date;
import org.postgresql.util.PSQLException;

/**
 * The class facilitates interaction with the database to manage company fundamental data.
 * <p>
 * It provides methods to insert and retrieve details through a CompanyOverview object.
 * This class uses the jOOQ library to create a connection context that ensures SQL operations are
 * safe and efficient.
 * </p>
 * <p>The main functionalities include:</p>
 * <ul>
 *  <li>Inserting fundamental data of a company into the database.</li>
 *  <li>Retrieving fundamental data of a company from the database.</li>
 * </ul>
 * @author Kasper Schröder
 */
public class CompanyOverviewDAO {
  private final DSLContext connectionContext;

  public CompanyOverviewDAO(DSLContext connection) {
    this.connectionContext = connection;
  }

  public void insertFundamentalData(CompanyOverview companyOverview) {
    try {
      connectionContext.insertInto(DSL.table("fundamentaldata"),
              DSL.field("stockSymbol"),
              DSL.field("assetType"),
              DSL.field("name"),
              DSL.field("description"),
              DSL.field("cik"),
              DSL.field("marketSymbol"),
              DSL.field("currency"),
              DSL.field("country"),
              DSL.field("sector"),
              DSL.field("industry"),
              DSL.field("address"),
              DSL.field("fiscalYearEnd"),
              DSL.field("latestQuarter"),
              DSL.field("marketCapitalization"),
              DSL.field("ebitda"),
              DSL.field("peRatio"),
              DSL.field("pegRatio"),
              DSL.field("bookValue"),
              DSL.field("dividendPerShare"),
              DSL.field("dividendYield"),
              DSL.field("eps"),
              DSL.field("revenuePerShareTTM"),
              DSL.field("profitMargin"),
              DSL.field("operationMarginTTM"),
              DSL.field("returnOnAssetsTTM"),
              DSL.field("returnOnEquityTTM"),
              DSL.field("revenueTTM"),
              DSL.field("grossProfitTTM"),
              DSL.field("dilutedEPSTTM"),
              DSL.field("quarterlyEarningsGrowthYOY"),
              DSL.field("quarterlyRevenueGrowthYOY"),
              DSL.field("analystTargetPrice"),
              DSL.field("trailingPE"),
              DSL.field("forwardPE"),
              DSL.field("priceToSalesRatioTTM"),
              DSL.field("priceToBookRatio"),
              DSL.field("evToRevenue"),
              DSL.field("evToEBITDA"),
              DSL.field("beta"),
              DSL.field("week52High"),
              DSL.field("week52Low"),
              DSL.field("day50movingaverage"),
              DSL.field("day200movingaverage"),
              DSL.field("sharesOutstanding"),
              DSL.field("dividendDate"),
              DSL.field("exDividendDate"),
              DSL.field("dateRetrieved"))
          .values(
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
              Date.valueOf(companyOverview.getExDividendDate()),
              DSL.currentDate()
          )
          .onConflict(DSL.field("stockSymbol"))
          .doNothing()
          .execute();
    } catch (DataAccessException e) {
      if (e.getCause() instanceof PSQLException psqlException) {
        if (psqlException.getSQLState().equals("23505")) {
          System.out.println("A record with the same stock symbol already exists in the database.");
        } else {
          System.out.println("Database error: " + e.getMessage());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public CompanyOverview getCompanyOverviewQuery(String symbol) {
    try {
      CompanyOverview companyOverview = new CompanyOverview();

      Result<Record> result = connectionContext.fetch(
        "SELECT * " +
            "FROM fundamentaldata where stockSymbol = ?",
        symbol
      );

      companyOverview.setSymbol(result.getValue(0, DSL.field(
          "stocksymbol", String.class))
      );
      companyOverview.setAssetType(result.getValue(0, DSL.field(
          "assettype", String.class))
      );
      companyOverview.setName(result.getValue(0, DSL.field(
          "name", String.class))
      );
      companyOverview.setDescription(result.getValue(0, DSL.field(
          "description", String.class))
      );
      companyOverview.setCIK(result.getValue(0, DSL.field(
          "cik", String.class))
      );
      companyOverview.setExchange(result.getValue(0, DSL.field(
          "marketsymbol", String.class))
      );
      companyOverview.setCurrency(result.getValue(0, DSL.field(
          "currency", String.class)));
      companyOverview.setCountry(result.getValue(0, DSL.field("country", String.class))
      );
      companyOverview.setSector(result.getValue(0, DSL.field(
          "sector", String.class)));
      companyOverview.setIndustry(result.getValue(0, DSL.field("industry", String.class))
      );
      companyOverview.setAddress(result.getValue(0, DSL.field(
          "address", String.class))
      );
      companyOverview.setFiscalYearEnd(result.getValue(0, DSL.field(
          "fiscalyearend", String.class))
      );

      Date latestQuarterValue = result.getValue(0, DSL.field(
          "latestquarter", Date.class)
      );
      companyOverview.setLatestQuarter(
          latestQuarterValue != null ? latestQuarterValue.toString() : null
      );

      companyOverview.setMarketCapitalization(result.getValue(0, DSL.field(
          "marketcapitalization", Long.class))
      );

      BigDecimal ebitdaValue = result.getValue(0, DSL.field(
          "ebitda", BigDecimal.class)
      );
      companyOverview.setEBITDA(
          ebitdaValue != null ? ebitdaValue.doubleValue() : null
      );

      BigDecimal peratioValue = result.getValue(0, DSL.field(
          "peratio", BigDecimal.class)
      );
      companyOverview.setPERatio(
          peratioValue != null ? peratioValue.doubleValue() : null
      );

      BigDecimal pegRatioValue = result.getValue(0, DSL.field(
          "pegratio", BigDecimal.class)
      );
      companyOverview.setPEGRatio(
          pegRatioValue != null ? pegRatioValue.doubleValue() : null
      );

      BigDecimal bookValueValue = result.getValue(0, DSL.field(
          "bookvalue", BigDecimal.class)
      );
      companyOverview.setBookValue(
          bookValueValue != null ? bookValueValue.doubleValue() : null
      );

      BigDecimal dividendPerShareValue = result.getValue(0, DSL.field(
          "dividendpershare", BigDecimal.class)
      );
      companyOverview.setDividendPerShare(
          dividendPerShareValue != null ? dividendPerShareValue.doubleValue() : null
      );

      BigDecimal dividendYieldValue = result.getValue(0, DSL.field(
          "dividendyield", BigDecimal.class)
      );
      companyOverview.setDividendYield(
          dividendYieldValue != null ? dividendYieldValue.doubleValue() : null
      );

      BigDecimal epsValue = result.getValue(0, DSL.field(
          "eps", BigDecimal.class)
      );
      companyOverview.setEPS(
          epsValue != null ? epsValue.doubleValue() : null
      );

      BigDecimal revenuePerShareTTMValue = result.getValue(0, DSL.field(
          "revenuepersharettm", BigDecimal.class)
      );
      companyOverview.setRevenuePerShareTTM(
          revenuePerShareTTMValue != null ? revenuePerShareTTMValue.doubleValue() : null
      );

      BigDecimal profitMarginValue = result.getValue(0, DSL.field(
          "profitmargin", BigDecimal.class)
      );
      companyOverview.setProfitMargin(
          profitMarginValue != null ? profitMarginValue.doubleValue() : null
      );

      BigDecimal operatingMarginTTMValue = result.getValue(0, DSL.field(
          "operationmarginttm", BigDecimal.class)
      );
      companyOverview.setOperatingMarginTTM(
          operatingMarginTTMValue != null ? operatingMarginTTMValue.doubleValue() : null
      );

      BigDecimal returnOnAssetsTTMValue = result.getValue(0, DSL.field(
          "returnonassetsttm", BigDecimal.class)
      );
      companyOverview.setReturnOnAssetsTTM(
          returnOnAssetsTTMValue != null ? returnOnAssetsTTMValue.doubleValue() : null
      );

      BigDecimal returnOnEquityTTMValue = result.getValue(0, DSL.field(
          "returnonequityttm", BigDecimal.class)
      );
      companyOverview.setReturnOnEquityTTM(
          returnOnEquityTTMValue != null ? returnOnEquityTTMValue.doubleValue() : null
      );

      companyOverview.setRevenueTTM(result.getValue(0, DSL.field(
          "revenuettm", Long.class))
      );
      companyOverview.setGrossProfitTTM(result.getValue(0, DSL.field(
          "grossprofitttm", Long.class))
      );

      BigDecimal dilutedEPSTTMValue = result.getValue(0, DSL.field(
          "dilutedepsttm", BigDecimal.class)
      );
      companyOverview.setDilutedEPSTTM(
          dilutedEPSTTMValue != null ? dilutedEPSTTMValue.doubleValue() : null
      );

      BigDecimal quarterlyEarningsGrowthYOYValue = result.getValue(
          0, DSL.field("quarterlyearningsgrowthyoy", BigDecimal.class)
      );
      companyOverview.setQuarterlyEarningsGrowthYOY(
          quarterlyEarningsGrowthYOYValue != null ? quarterlyEarningsGrowthYOYValue.doubleValue()
              : null
      );

      BigDecimal quarterlyRevenueGrowthYOYValue = result.getValue(0, DSL.field(
          "quarterlyrevenuegrowthyoy", BigDecimal.class)
      );
      companyOverview.setQuarterlyRevenueGrowthYOY(
          quarterlyRevenueGrowthYOYValue != null ? quarterlyRevenueGrowthYOYValue.doubleValue()
              : null
      );

      BigDecimal analystTargetPriceValue = result.getValue(0, DSL.field(
          "analysttargetprice", BigDecimal.class)
      );
      companyOverview.setAnalystTargetPrice(
          analystTargetPriceValue != null ? analystTargetPriceValue.doubleValue() : null
      );

      BigDecimal trailingPEValue = result.getValue(0, DSL.field(
          "trailingpe", BigDecimal.class)
      );
      companyOverview.setTrailingPE(
          trailingPEValue != null ? trailingPEValue.doubleValue() : null
      );

      BigDecimal forwardPEValue = result.getValue(0, DSL.field(
          "forwardpe", BigDecimal.class)
      );
      companyOverview.setForwardPE(
          forwardPEValue != null ? forwardPEValue.doubleValue() : null
      );

      BigDecimal priceToSalesRatioTTMValue = result.getValue(0, DSL.field(
          "pricetosalesratiottm", BigDecimal.class)
      );
      companyOverview.setPriceToSalesRatioTTM(
          priceToSalesRatioTTMValue != null ? priceToSalesRatioTTMValue.doubleValue() : null
      );

      BigDecimal priceToBookRatioValue = result.getValue(0, DSL.field(
          "pricetobookratio", BigDecimal.class)
      );
      companyOverview.setPriceToBookRatio(
          priceToBookRatioValue != null ? priceToBookRatioValue.doubleValue() : null
      );

      BigDecimal evToRevenueValue = result.getValue(0, DSL.field(
          "evtorevenue", BigDecimal.class)
      );
      companyOverview.setEVToRevenue(
          evToRevenueValue != null ? evToRevenueValue.doubleValue() : null
      );

      BigDecimal evToEBITDAValue = result.getValue(0, DSL.field(
          "evtoebitda", BigDecimal.class)
      );
      companyOverview.setEVToEBITDA(
          evToEBITDAValue != null ? evToEBITDAValue.doubleValue() : null
      );

      BigDecimal betaValue = result.getValue(0, DSL.field(
          "beta", BigDecimal.class)
      );
      companyOverview.setBeta(
          betaValue != null ? betaValue.doubleValue() : null
      );

      BigDecimal week52HighValue = result.getValue(0, DSL.field(
          "week52high", BigDecimal.class)
      );
      companyOverview.setWeek52High(
          week52HighValue != null ? week52HighValue.doubleValue() : null
      );

      BigDecimal week52LowValue = result.getValue(0, DSL.field(
          "week52low", BigDecimal.class)
      );
      companyOverview.setWeek52Low(
          week52LowValue != null ? week52LowValue.doubleValue() : null
      );

      BigDecimal movingAverage50Value = result.getValue(0, DSL.field(
          "day50movingaverage", BigDecimal.class)
      );
      companyOverview.setMovingAverage50(
          movingAverage50Value != null ? movingAverage50Value.doubleValue() : null
      );

      BigDecimal movingAverage200Value = result.getValue(0, DSL.field(
          "day200movingaverage", BigDecimal.class)
      );
      companyOverview.setMovingAverage200(
          movingAverage200Value != null ? movingAverage200Value.doubleValue() : null
      );

      companyOverview.setSharesOutstanding(result.getValue(0, DSL.field(
          "sharesoutstanding", Long.class))
      );

      Date dividendDateValue = result.getValue(0, DSL.field(
          "dividenddate", Date.class)
      );
      companyOverview.setDividendDate(
          dividendDateValue != null ? dividendDateValue.toString() : null
      );

      Date exDividendDateValue = result.getValue(0, DSL.field(
          "exdividenddate", Date.class)
      );
      companyOverview.setExDividendDate(
          exDividendDateValue != null ? exDividendDateValue.toString() : null
      );

      return companyOverview;
    } catch (Exception e) {
      System.out.println("Error when fetching company overview data");
      e.printStackTrace();
      return null;
    }
  }

  public String fetchLatestUpdate(String symbol) {
    try {
      Result<Record> result = connectionContext.fetch(
          "select dateretrieved "
              + "from fundamentaldata "
              + "where stockSymbol = ?",
          symbol
      );

      Date dateRetrieved = result.getValue(0, DSL.field("dateretrieved", Date.class));
      return dateRetrieved.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }


}