package BackEnd;

public class MonthlyProfitByProduct extends YearlyProfitByProduct {

    private int month;
    private float monthlyIncome;

    public MonthlyProfitByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity, float income, int month, int year) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
        this.month = month;
        this.year = year;
        this.monthlyIncome = income;
    }

    public void setMonth(int month) {

        this.month = month;
    }
    public int getMonth()
    {
        return month;
    }
    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    public float getMonthlyIncome()
    {
        return monthlyIncome;
    }

}
