package broowsky;

import java.util.Scanner;

public class Product{
    private int productId;
    private String productName;
    private float purchasePrice;
    private float sellingPrice;
    private int quantity;

    public Product(int productID, String productName, float purchasePrice, float sellingPrice, int quantity) {
        this.productId = productID;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }
    public float getSellingPrice() {
        return sellingPrice;
    }
    public int getQuantity() {
        return quantity;
    }
}
