package alphaVantage;

public class DataPoint {

    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double average;
    private long volume;

    public DataPoint(String date, double open, double high, double low, double close, long volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.average = getDailyAverage();
        this.volume = volume;
    }

    public DataPoint(String date, double close) {
        this.date = date;
        this.close = close;
    }

    private double getDailyAverage() {
        return (open + high + low + close) / 4;
    }

    public int getYear() {
        String[] parts = date.split("-");
        return Integer.parseInt(parts[0]);
    }

    public int getMonth() {
        String[] parts = date.split("-");
        return Integer.parseInt(parts[1]);
    }

    public int getDay() {
        String[] parts = date.split("-");
        return Integer.parseInt(parts[2]);
    }

    public int[] getDateArray() {
        String[] parts = date.split("-");
        int[] dateArray = new int[3];
        dateArray[0] = Integer.parseInt(parts[0]);
        dateArray[1] = Integer.parseInt(parts[1]);
        dateArray[2] = Integer.parseInt(parts[2]);
        return dateArray;
    }

    public String getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getAverage() {
        return average;
    }

    public long getVolume() {
        return volume;
    }
}
