import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float purchasePrice;
    private float sellingPrice;
    private int quantity;

    public void addProduct(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID: ");
        this.productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product name: ");
        this.productName = scanner.nextLine();
        System.out.print("Enter product price: ");
        this.purchasePrice = scanner.nextFloat();
        System.out.print("Enter product selling price: ");
        this.sellingPrice = scanner.nextFloat();
        System.out.print("Enter product quantity: ");
        this.quantity = scanner.nextInt();
        scanner.nextLine();
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
