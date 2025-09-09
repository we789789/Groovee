package BackEnd;

public class Quantity {
    int productID;
    int quantity;
    public Quantity(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
