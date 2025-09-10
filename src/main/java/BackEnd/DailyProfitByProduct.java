package BackEnd;

import java.time.LocalDate;

public class DailyProfitByProduct extends Product {

    String date;
    float income;

    public DailyProfitByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity, String date, float income) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
        this.date = date;
        this.income = income;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public float getIncome() {
        return income;
    }
    public void setIncome(float income) {
        this.income = income;
    }

}
