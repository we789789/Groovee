import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AddProduct addProduct = new AddProduct();
        SellProduct sellProduct = new SellProduct();

        String loop="";
        int process;

        {/* Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement Statement;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        String loop = "";
        Product product = new Product();
        Sale sale = new Sale();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();


            //  -----------Add PRODUCTS TO DATABASE----------- //


            do {
                product.addProduct();

                try {
                    Statement = connection.prepareStatement("INSERT INTO PRODUCTS VALUES(?,?,?,?)");

                    Statement.setInt(1,product.getProductId());
                    Statement.setString(2,product.getProductName());
                    Statement.setFloat(3,product.getPurchasePrice());
                    Statement.setFloat(4,product.getSellingPrice());
                    Statement.executeUpdate();

                    System.out.println("Inserted product successfully");

                } catch (Exception e) {
                    System.out.println(e);
                }


                // -----SET QUANTITY----- //


                Statement = connection.prepareStatement("INSERT INTO STOCK VALUES (?,?)");
                Statement.setInt(1,product.getProductId());
                Statement.setInt(2,product.getQuantity());
                Statement.executeUpdate();

                System.out.println("Do you want to continue? [Y/N]");
                loop = scanner.nextLine();
                loop = loop.toUpperCase();

            } while (loop.equals("Y"));


            // -----------SELL PRODUCTS AND ADDING TO DATABASE----------- //


            {
                System.out.print("Enter sales ID: ");
                scanner.nextLine();
                sale.setSaleId();

                do {
                    sale.setProductId();
                    sale.setQuantity();

                    try {


                        // ----------------ADD PRODUCT TO SALES ID------------------- //


                        //query = new StringBuilder().append("SELECT SELLING_PEIZE FROM products WHERE PRODUCT_ID=").append(sale.getProductId()).toString();
                        Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM products WHERE PRODUCT_ID=?");
                        Statement.setInt(1,sale.getProductId());
                        ResultSet price = Statement.executeQuery();

                        if (price.next()) {
                            float sellingPrice = price.getFloat("SELLING_PRICE");
                            sale.setPrice(sale.getQuantity(), sellingPrice);
                        } else {
                            System.out.println("Product not found.");
                        }


                    } catch (Exception e) {
                        System.out.println(e);


                    }// ---------------UPDATE THE QUANTITY OF PRODUCTS------------------- //


                    Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID=?");
                    Statement.setInt(1, sale.getProductId());
                    ResultSet quantity = Statement.executeQuery();

                    if (quantity.next()) {

                        int quantityInStock = quantity.getInt(1);

                        if(quantityInStock < sale.getQuantity()) {
                            System.out.print("Insufficient stock. Selling is not recorded.");


                        }else{

                            // -----CREATE QUERY----- //

                            quantityInStock -= sale.getQuantity();
                            Statement = connection.prepareStatement("INSERT INTO sales VALUES(?,?,?,?,?,?)");
                            Statement.setDate(1, sqlDate);
                            Statement.setInt(2, sale.getProductId());
                            Statement.setInt(3, sale.getQuantity());
                            Statement.setFloat(4, sale.getPrice());
                            LocalDateTime time = LocalDateTime.now();
                            Timestamp Time = Timestamp.valueOf(time);
                            Statement.setTimestamp(5, Time);
                            Statement.setInt(6, sale.getSaleId());
                            Statement.executeUpdate();


                            Statement = connection.prepareStatement("UPDATE STOCK SET QUANTITY=? WHERE PRODUCT_ID=?");
                            Statement.setInt(1, quantityInStock);
                            Statement.setInt(2, sale.getProductId());
                            Statement.executeUpdate();
                        }
                    }
                    else{
                        System.out.println("Quantity not found.");
                    }

                    System.out.println("Do you want to continue? [Y/N]");

                    loop = scanner.nextLine();
                    loop = loop.toUpperCase();


                } while (loop.equals("Y"));
            }

        }catch (Exception e) {
            System.out.println(e);
        }*/}
        do{
            System.out.println("Choose an option");
            System.out.println("\t1. Add Product");
            System.out.println("\t2. Sell Product");
            System.out.println("\t3. Exit");
            System.out.println();
            System.out.print("Option: ");

            process = scanner.nextInt();

            switch (process) {
                case 1:
                    addProduct.addProduct();
                    break;
                case 2:
                    sellProduct.sellProduct();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
            }
            System.out.println("Do you want to go main menu[Y/N]: ");
            loop = scanner.next();
            loop = loop.toUpperCase();
        }while(loop.equals("Y"));
    }
}
