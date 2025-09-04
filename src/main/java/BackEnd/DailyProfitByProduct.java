package BackEnd;

import java.time.LocalDate;

public class DailyProfitByProduct extends Product {

    LocalDate date;
    float income;

    public DailyProfitByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity, LocalDate date, float income) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
        this.date = date;
        this.income = income;
    }


    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public float getIncome() {
        return income;
    }
    public void setIncome(float income) {
        this.income = income;
    }

}
