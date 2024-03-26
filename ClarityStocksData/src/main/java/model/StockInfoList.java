package model;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StockInfoList {

    private List<StockInfo> stockInfoList = new ArrayList<>();

    public StockInfoList() {
        readUSAStockInfoFromCSV();
        readStockholmStockInfoFromCSV();
    }

    private void readUSAStockInfoFromCSV() {
        Path path = Paths.get("ClarityStocksData/src/main/resources/usa_stock_info.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String symbol = attributes[0];
                String name = attributes[1];
                String exchange = attributes[2];

                addStockInfo(new StockInfo(symbol, name, exchange));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readStockholmStockInfoFromCSV() {
        Path path = Paths.get("ClarityStocksData/src/main/resources/stockholm_stock_info.csv");
        Charset charset = StandardCharsets.ISO_8859_1;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            String line = reader.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                String symbol = attributes[0];
                String name = attributes[1];
                String exchange = "ST";

                addStockInfo(new StockInfo(symbol, name, exchange));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addStockInfo(StockInfo stockInfo) {
        stockInfoList.add(stockInfo);
    }

    public List<StockInfo> getStockInfoList() {
        return stockInfoList;
    }

    public List<StockInfo> searchStockInfoByName(String query) {
        List<StockInfo> matchedStockInfo = new ArrayList<>();
        String queryLowerCase = query.toLowerCase();

        for (StockInfo stockInfo : stockInfoList) {
            if (stockInfo.getName().toLowerCase().contains(queryLowerCase)) {
                matchedStockInfo.add(stockInfo);
            }
        }

        return matchedStockInfo;
    }

    public static void main(String[] args) {
        StockInfoList stockInfoList = new StockInfoList();
        List<StockInfo> stockInfoList1 = stockInfoList.getStockInfoList();
        for (StockInfo stockInfo : stockInfoList1) {
            System.out.println(stockInfo.getName());
        }
    }

}
