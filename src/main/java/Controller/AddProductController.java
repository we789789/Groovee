package Controller;

import BackEnd.Product;

import java.sql.*;
import java.util.ArrayList;

public class AddProductController {

    public static ArrayList<Product> productList = new ArrayList<>();

    static {
        setProductList();
    }

    public static String addProduct(int productID, String productName, float sellingPrice, float purchasePrice, int quantity) {

        PreparedStatement Statement;

        String url = "jdbc:sqlite:data.sqlite";
        String output;

        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);


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

            setProductList();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
           output = e.getMessage();
        }
        return output;
    }

    public static void setProductList(){
        productList.clear();

        String url = "jdbc:sqlite:data.sqlite";

        int productID;
        String productName;
        float sellingPrice;
        float purchasePrice;
        int quantity;

        try{

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet products = Statement.executeQuery();

            while(products.next()){
                productID = products.getInt("PRODUCT_ID");
                productName = products.getString("Name");
                sellingPrice = products.getFloat("SELLING_Price");
                purchasePrice = products.getFloat("PURCHASE_Price");

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID = ?");
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

    public static ArrayList<Product> getProductList() {
        return productList;
    }
}
