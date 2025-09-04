package BackEnd;

public class YearlyIncomeByProduct extends Product {

    int year;

    public YearlyIncomeByProduct(int productID, String productName, float purchasePrice, float sellingPrice, int quantity) {
        super(productID, productName, purchasePrice, sellingPrice, quantity);
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }
}
