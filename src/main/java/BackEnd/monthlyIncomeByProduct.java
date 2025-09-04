package BackEnd;

public class monthlyIncomeByProduct extends YearlyIncomeByProduct {

    private int month;

    public monthlyIncomeByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
    }

    public void setMonth(int month) {
        this.month = month;
    }
    public int getMonth() {
        return month;
    }
}
