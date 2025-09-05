package BackEnd;

public class MonthlyProfit extends YearlyProfit{

    int month;
    String monthName;

    public MonthlyProfit(int year, int month, float profit) {
        super(year, profit);
        this.month = month;
        new IntToMonth(month);
        this.monthName = IntToMonth.getMonth();
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getMonthNumber() {
        return month;
    }
    public String getMonthName() {
        return monthName;
    }
}
