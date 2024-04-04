package Database;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseInserts {
    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(TestDB.class.getName());
    }

    private static Properties loadApplicationProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(TestDB.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties;
    }

    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties properties = loadApplicationProperties();
        Connection conn = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + conn.getCatalog());
        return conn;
    }

    public  void insertDailyStockPrices(int[] stockIds, LocalDate[] priceDates, double[] openPrices, double[] highPrices, double[] lowPrices, double[] closePrices, long[] volumes) {
        String query = "INSERT INTO daily_stock_price (stock_id, price_date, open_price, high_price, low_price, close_price, volume) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectToDatabase();
             PreparedStatement statement = conn.prepareStatement(query)) {

            for (int i = 0; i < stockIds.length; i++) {
                statement.setInt(1, stockIds[i]);
                statement.setDate(2, Date.valueOf(priceDates[i]));
                statement.setDouble(3, openPrices[i]);
                statement.setDouble(4, highPrices[i]);
                statement.setDouble(5, lowPrices[i]);
                statement.setDouble(6, closePrices[i]);
                statement.setLong(7, volumes[i]);

                statement.addBatch();

                if ((i + 1) % 100 == 0 || i == stockIds.length - 1) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void insertWeeklyStockPrices(int[] stockIds, LocalDate[] startDates, double[] openPrices, double[] highPrices, double[] lowPrices, double[] closePrices, long[] volumes) {
        String query = "INSERT INTO weekly_stock_prices (stock_id, start_date, open_price, high_price, low_price, close_price, volume) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectToDatabase();
             PreparedStatement statement = conn.prepareStatement(query)) {

            for (int i = 0; i < stockIds.length; i++) {
                statement.setInt(1, stockIds[i]);
                statement.setDate(2, Date.valueOf(startDates[i]));
                statement.setDouble(3, openPrices[i]);
                statement.setDouble(4, highPrices[i]);
                statement.setDouble(5, lowPrices[i]);
                statement.setDouble(6, closePrices[i]);
                statement.setLong(7, volumes[i]);

                statement.addBatch();

                if ((i + 1) % 100 == 0 || i == stockIds.length - 1) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void insertMonthlyStockPrices(int[] stockIds, LocalDate[] startDates, double[] openPrices, double[] highPrices, double[] lowPrices, double[] closePrices, long[] volumes) {
        String query = "INSERT INTO monthly_stock_prices (stock_id, start_date, open_price, high_price, low_price, close_price, volume) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectToDatabase();
             PreparedStatement statement = conn.prepareStatement(query)) {

            for (int i = 0; i < stockIds.length; i++) {
                statement.setInt(1, stockIds[i]);
                statement.setDate(2, Date.valueOf(startDates[i]));
                statement.setDouble(3, openPrices[i]);
                statement.setDouble(4, highPrices[i]);
                statement.setDouble(5, lowPrices[i]);
                statement.setDouble(6, closePrices[i]);
                statement.setLong(7, volumes[i]);

                statement.addBatch();

                if ((i + 1) % 100 == 0 || i == stockIds.length - 1) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStockPrices(String tableName, String[] symbols, LocalDate[] dates, double[] openPrices, double[] highPrices, double[] lowPrices, double[] closePrices, long[] volumes) {
        // Dynamically build the SQL statement to insert data into the specified table.
        // Note: The table name is directly used in the SQL command, ensure this method is called with trusted input only.
        String query = String.format(
                "INSERT INTO %s (stock_id, start_date, open_price, high_price, low_price, close_price, volume) " +
                        "VALUES ((SELECT id FROM stock WHERE symbol = ?), ?, ?, ?, ?, ?, ?)", tableName);

        try (Connection conn = connectToDatabase();
             PreparedStatement statement = conn.prepareStatement(query)) {

            for (int i = 0; i < symbols.length; i++) {
                statement.setString(1, symbols[i]); // Use the stock symbol to find the stock_id
                statement.setDate(2, Date.valueOf(dates[i]));
                statement.setDouble(3, openPrices[i]);
                statement.setDouble(4, highPrices[i]);
                statement.setDouble(5, lowPrices[i]);
                statement.setDouble(6, closePrices[i]);
                statement.setLong(7, volumes[i]);

                statement.addBatch();

                if ((i + 1) % 100 == 0 || i == symbols.length - 1) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Integer fetchClosingPriceBySymbolAndDate(String symbol, LocalDate date) {
        Integer closingPrice = null;
        String query = "SELECT dsp.close_price " +
                "FROM daily_stock_price dsp JOIN stock s ON dsp.stock_id = s.id " +
                "WHERE s.symbol = ? AND dsp.price_date = ?";

        try (Connection conn = connectToDatabase();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, symbol);
            statement.setDate(2, Date.valueOf(date));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Assuming close_price is stored as a numeric type that might have a fractional part.
                // Convert to int as requested. Consider rounding or truncating based on your needs.
                closingPrice = resultSet.getBigDecimal("close_price").intValue();
            }
        } catch (SQLException | IOException e) {
            log.severe("Error fetching closing stock price: " + e.getMessage());
        }

        return closingPrice;
    }


}
