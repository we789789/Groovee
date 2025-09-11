package Controller;

import BackEnd.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDetailsController {

    public static ArrayList<Product> productList = new ArrayList<>();

    static {
        setProductList();
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

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();

                quantity = 0;
                if(stock.next()){
                    quantity = stock.getInt("quantity");
                }


                productList.add(new Product(productID, productName, purchasePrice, sellingPrice, quantity));
            }
            products.close();
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void setProductList(String searchTerm){

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

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS  WHERE NAME Like ?");
            Statement.setString(1,"%"+searchTerm+"%");
            ResultSet products = Statement.executeQuery();

            while(products.next()){
                productID = products.getInt("PRODUCT_ID");
                productName = products.getString("Name");
                sellingPrice = products.getFloat("SELLING_Price");
                purchasePrice = products.getFloat("PURCHASE_Price");

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();

                quantity = 0;
                if(stock.next()){
                    quantity = stock.getInt("quantity");
                }


                productList.add(new Product(productID, productName, purchasePrice, sellingPrice, quantity));
            }
            products.close();
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static int updateProduct(int productID, String productName, float sellingPrice, float purchasePrice, int quantity){

        int result = 0;

        try{
            String url = "jdbc:sqlite:data.sqlite";

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("UPDATE PRODUCTS SET NAME = ?, PURCHASE_PRICE = ?, SELLING_PRICE = ? WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
            Statement.setString(1,productName);
            Statement.setFloat(2, sellingPrice);
            Statement.setFloat(3, purchasePrice);
            Statement.setInt(4, productID);
            Statement.execute();

            Statement = connection.prepareStatement("UPDATE STOCK SET QUANTITY = ? WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
            Statement.setInt(1, quantity);
            Statement.setInt(2, productID);
            Statement.execute();

            Statement.close();
            connection.close();

            result = 1;

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Product> getProductList() {
        return productList;
    }

}
