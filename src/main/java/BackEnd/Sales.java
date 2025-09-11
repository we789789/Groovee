package BackEnd;

public class Sales {

    String date ;
    int salesID;
    int productID;
    String productName;
    int quantity;
    float price;
    String time;

    public  Sales(String date, int salesID, int productID, String productName, int quantity, float price, String time){
        this.date = date;
        this.salesID = salesID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.time = time;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getSalesID() {
        return salesID;
    }
    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
