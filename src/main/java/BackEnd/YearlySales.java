package BackEnd;

public class YearlySales {
    int year;
    int quantity;
    float income;

    public YearlySales(int year, int quantity, float income) {
        this.year = year;
        this.quantity = quantity;
        this.income = income;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
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
