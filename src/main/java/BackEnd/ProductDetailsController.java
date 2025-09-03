package BackEnd;

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

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int productID;
        String productName;
        float sellingPrice;
        float purchasePrice;
        int quantity;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
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

    public static void setProductList(String searchTerm){
        productList.clear();

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int productID;
        String productName;
        float sellingPrice;
        float purchasePrice;
        int quantity;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS  WHERE NAME Like ?");
            Statement.setString(1,"%"+searchTerm+"%");
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
