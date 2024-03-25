package model;

public class ListedCompanyData {

    private final String NAME;
    private final String SYMBOL;
    private final String CURRENCY;
    private final String ISIN;
    private final String SECTOR;
    private final int ICB_CODE;

    public ListedCompanyData(String NAME, String SYMBOL, String CURRENCY, String ISIN, String SECTOR, int ICB_CODE){
        this.NAME = NAME;
        this.SYMBOL = SYMBOL;
        this.CURRENCY = CURRENCY;
        this.ISIN = ISIN;
        this.SECTOR = SECTOR;
        this.ICB_CODE = ICB_CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    public String getISIN() {
        return ISIN;
    }

    public String getSECTOR() {
        return SECTOR;
    }

    public int getICB_CODE() {
        return ICB_CODE;
    }
}
