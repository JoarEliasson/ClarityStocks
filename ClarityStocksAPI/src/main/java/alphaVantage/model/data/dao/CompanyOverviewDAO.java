package alphaVantage.model.data.dao;

import alphaVantage.model.data.fundamental.CompanyOverview;
import org.jooq.DSLContext;
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

                transactionContext.execute("CALL insertFundamental(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                                "?, ?)",
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


}
