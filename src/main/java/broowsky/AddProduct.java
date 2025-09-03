package broowsky;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class AddProduct {


    public static String addProduct(int productID, String productName, float sellingPrice, float purchasePrice, int quantity) {


        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement Statement;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        String output;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);


            //  -----------Add PRODUCTS TO DATABASE----------- //

                try {
                    Statement = connection.prepareStatement("INSERT INTO PRODUCTS VALUES(?,?,?,?)");

                    Statement.setInt(1, productID);
                    Statement.setString(2, productName);
                    Statement.setFloat(3, purchasePrice);
                    Statement.setFloat(4, sellingPrice);
                    Statement.executeUpdate();

                 output = "Product Added";

                } catch (Exception e) {
                    System.out.println(e);
                    output = e.getMessage();
                }


                // -----SET QUANTITY----- //


                Statement = connection.prepareStatement("INSERT INTO STOCK VALUES (?,?)");
                Statement.setInt(1, productID);
                Statement.setInt(2, quantity);
                Statement.executeUpdate();



        } catch (Exception e) {
            System.out.println(e);
           output = e.getMessage();
        }
        return output;
    }

    public static void getData(){

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int productID;
        String productName;
        float sellingPrice;
        float purchasePrice;
        int quantity;

        ArrayList<Product> productList = new ArrayList();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet products = Statement.executeQuery();

            while(products.next()){
                productID = products.getInt("productID");
                productName = products.getString("productName");
                sellingPrice = products.getFloat("sellingPrice");
                purchasePrice = products.getFloat("purchasePrice");

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE productID = ?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();

                quantity = 0;
                if(stock.next()){
                    quantity = stock.getInt("quantity");
                }


                productList.add(new Product(productID, productName, purchasePrice, sellingPrice, quantity));
            }

            Statement.close();
            connection.close();


        }catch(Exception e){
            System.out.println(e);
        }

    }
}
