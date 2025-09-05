package BackEnd;

public class YearlyProfit {
    int year;
    float profit;

    public YearlyProfit(int year, float profit) {
        this.year = year;
        this.profit = profit;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYearlyProfit(float profit) {
        this.profit = profit;
    }

    public float getProfit() {
        return profit;
    }

}
