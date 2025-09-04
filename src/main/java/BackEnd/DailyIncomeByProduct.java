package BackEnd;

import java.time.LocalDate;

public class DailyIncomeByProduct extends Product {

    LocalDate date;
    float income;

    public DailyIncomeByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
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
