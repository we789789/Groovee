package broowsky;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class SellProduct {
    public static void sellProduct() {

        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        Income income = new Income();

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement Statement;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        String loop = "";
        Sale sale = new Sale();
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // -----------SELL PRODUCTS AND ADDING TO DATABASE----------- //


                sale.setSaleId();

                do {
                    sale.setProductId();
                    sale.setQuantity();

                    try {


                        // ----------------ADD PRODUCT TO SALES ID------------------- //


                        //query = new StringBuilder().append("SELECT SELLING_PEIZE FROM products WHERE PRODUCT_ID=").append(sale.getProductId()).toString();
                        Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM products WHERE PRODUCT_ID=?");
                        Statement.setInt(1, sale.getProductId());
                        ResultSet price = Statement.executeQuery();

                        if (price.next()) {
                            float sellingPrice = price.getFloat("SELLING_PRICE");
                            sale.setPrice(sale.getQuantity(), sellingPrice);
                        } else {
                            System.out.println("broowsky.Product not found.");
                        }


                    } catch (Exception e) {
                        System.out.println(e);


                    }// ---------------UPDATE THE QUANTITY OF PRODUCTS------------------- //


                    Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID=?");
                    Statement.setInt(1, sale.getProductId());
                    ResultSet quantity = Statement.executeQuery();

                    if (quantity.next()) {

                        int quantityInStock = quantity.getInt(1);

                        if (quantityInStock < sale.getQuantity()) {
                            System.out.print("Insufficient stock. Selling is not recorded.");


                        } else {

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

                            income.setIncome(sale.getProductId(), sale.getQuantity());

                        }
                    } else {
                        System.out.println("Quantity not found.");
                    }

                    System.out.println("Do you want to continue? [Y/N]");

                    loop = scanner.nextLine();
                    loop = loop.toUpperCase();


                } while (loop.equals("Y"));
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
