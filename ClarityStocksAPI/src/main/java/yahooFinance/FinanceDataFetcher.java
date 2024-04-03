package yahooFinance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FinanceDataFetcher {

    public static String fetchFinancialData() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "ClarityStocksAPI/src/main/python/test.py");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                return output.toString();
            } else {
                // Handle errors
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return null;
    }

    public static void main(String[] args) {
        String jsonData = fetchFinancialData();
        System.out.println(jsonData);
    }
}