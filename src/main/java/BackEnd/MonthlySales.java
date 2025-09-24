package BackEnd;

public class MonthlySales {
    int year;
    String month;
    int quantity;
    float income;

    public MonthlySales(int year, String month, int quantity, float income) {
        this.year = year;
        this.month = month;
        this.quantity = quantity;
        this.income = income;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setIncome(float income) {
        this.income = income;
    }
    public int getYear() {
        return year;
    }
    public String getMonth() {
        return month;
    }
    public int getQuantity() {
        return quantity;
    }
    public float getIncome() {
        return income;
    }
}
