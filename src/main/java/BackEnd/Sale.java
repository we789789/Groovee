package BackEnd;

import java.util.Scanner;

public class Sale {
    private int saleId;
    private int productId;
    private int quantity;
    private float rate;
    private float price;
    private String productName;

    public Sale(int saleId, int productId, int quantity, float rate, float price, String productName) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.rate = rate;
        this.price = price;
        this.productName = productName;
        this.productName = productName;
    }


    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    public void setPrice(int quantity, float rate) {
        this.price = rate*quantity;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSaleId() {
        return saleId;
    }
    public int getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public float getRate() {
        return rate;
    }
    public float getPrice() {
        return price;
    }
    public String getProductName() {
        return productName;
    }
}