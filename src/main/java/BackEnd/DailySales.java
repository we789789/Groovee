package BackEnd;

public class DailySales {
    int year;
    int month;
    int day;
    int quantity;
    float income;

    public DailySales(int year, int month, int day, int quantity, float income) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.quantity = quantity;
        this.income = income;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getIncome() {
        return income;
    }
    public void setIncome(float income) {
        this.income = income;
    }
}
