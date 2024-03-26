package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NasdaqStockholmCompanyData {

    private List<ListedCompanyData> companies = new ArrayList<>();

    public NasdaqStockholmCompanyData(){
        readCompaniesFromCSV();
    }

    private void readCompaniesFromCSV(){
        File file = new File("ClarityStocksData/src/main/resources/nasdaq-stockholm-companies.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                String NAME = attributes[0];
                String SYMBOL = attributes[1].replace(" ", "_");
                String CURRENCY = attributes[2];
                String ISIN = attributes[3];
                String SECTOR = attributes[4];
                int ICB_CODE = Integer.parseInt(attributes[5]);

                companies.add(new ListedCompanyData(NAME, SYMBOL, CURRENCY, ISIN, SECTOR, ICB_CODE));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ListedCompanyData> getCompanies() {
        return companies;
    }

    public List<ListedCompanyData> searchCompaniesByName(String query) {
        List<ListedCompanyData> matchedCompanies = new ArrayList<>();
        String queryLowerCase = query.toLowerCase();

        for (ListedCompanyData company : companies) {
            if (company.getNAME().toLowerCase().contains(queryLowerCase)) {
                matchedCompanies.add(company);
            }
        }

        return matchedCompanies;
    }

}
