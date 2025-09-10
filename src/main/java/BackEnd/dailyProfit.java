package BackEnd;

import java.time.LocalDate;

public class dailyProfit {
    String date;
    float dailyProfit;

    public dailyProfit(String date, float dailyProfit) {
        this.date = date;
        this.dailyProfit = dailyProfit;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public float getDailyProfit() {
        return dailyProfit;
    }
    public void setDailyProfit(float dailyProfit) {
        this.dailyProfit = dailyProfit;
    }
}
