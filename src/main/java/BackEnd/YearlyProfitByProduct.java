package BackEnd;

public class YearlyProfitByProduct extends Product {

    int year;
    float yearlyProfit;

    public YearlyProfitByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
    }
    public void setYear(int year) {

        this.year = year;
    }
    public int getYear() {

        return year;
    }
    public void setYearlyProfit(float yearlyProfit) {
        this.yearlyProfit = yearlyProfit;
    }
    public float getYearlyProfit() {
        return yearlyProfit;
    }
}
