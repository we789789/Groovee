package BackEnd;

import java.time.LocalDate;

public class dailyProfit {
    LocalDate date;
    float dailyProfit;

    public dailyProfit(LocalDate date, float dailyProfit) {
        this.date = date;
        this.dailyProfit = dailyProfit;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public float getDailyProfit() {
        return dailyProfit;
    }
    public void setDailyProfit(float dailyProfit) {
        this.dailyProfit = dailyProfit;
    }
}
