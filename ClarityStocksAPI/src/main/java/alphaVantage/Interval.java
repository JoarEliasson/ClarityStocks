package alphaVantage;

public enum Interval {

    DAILY("TIME_SERIES_DAILY"),
    WEEKLY("TIME_SERIES_WEEKLY"),
    MONTHLY("TIME_SERIES_MONTHLY");

    private final String interval;

    Interval(String timeSeries) {
        this.interval = timeSeries;
    }

    public String getUrlParameter() {
        return interval;
    }
}
