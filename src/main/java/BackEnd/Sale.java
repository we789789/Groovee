package BackEnd;

import java.util.Scanner;

public class Sale {
    private int saleId;
    private int productId;
    private int quantity;
    private float price;

    Scanner scanner = new Scanner(System.in);

    public void setSaleId() {
        System.out.print("Enter sales ID: ");
        this.saleId = scanner.nextInt();
    }
    public void setProductId() {
        System.out.print("Enter product ID: ");
        this.productId = scanner.nextInt();
    }
    public void setQuantity() {
        System.out.print("Enter product quantity: ");
        this.quantity = scanner.nextInt();
    }
    public void setPrice(int quantity, float price) {
        this.price = price*quantity;
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
    public float getPrice() {
        return price;
    }
}