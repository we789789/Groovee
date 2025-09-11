package Controller;

import BackEnd.Sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SalesController {

    static ArrayList<Sales> salesList = new ArrayList<>();
    static float total = 0;
    static int count = 0;
    static String url = "jdbc:sqlite:data.sqlite";

    public static void setSales(){

        salesList.clear();

        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;
            Statement = connection.prepareStatement("SELECT * FROM SALES");
            ResultSet sales = Statement.executeQuery();
            while(sales.next()){
                String productName = "";
                String date = sales.getString("DATE");
                int salesID = sales.getInt("SALE_ID");
                int productID  = sales.getInt("PRODUCT_ID");
                int quantity  = sales.getInt("QUANTITY");
                float price   = sales.getFloat("TOTAL");
                String time  = sales.getString("TIME");

                Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet name = Statement.executeQuery();
                while(name.next()){
                    productName = name.getString("NAME");
                }
                name.close();

                salesList.add(new Sales(date,salesID, productID, productName, quantity, price, time));
            }
            sales.close();
            Statement.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSales(int salesID){

        salesList.clear();
        total = 0;

        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;
            Statement = connection.prepareStatement("SELECT * FROM SALES WHERE CAST(SALE_ID AS INTEGER) = ?");
            Statement.setInt(1, salesID);
            ResultSet sales = Statement.executeQuery();

            while(sales.next()){

                String productName = "";
                String date = sales.getString("DATE");
                salesID = sales.getInt("SALE_ID");
                int productID  = sales.getInt("PRODUCT_ID");
                int quantity  = sales.getInt("QUANTITY");
                float price   = sales.getFloat("TOTAL");
                String time  = sales.getString("TIME");

                Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet name = Statement.executeQuery();

                while(name.next()){
                    productName = name.getString("NAME");
                }
                name.close();
                total += price;
                count++;

                salesList.add(new Sales(date,salesID, productID, productName, quantity, price, time));
            }
            sales.close();
            Statement.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Sales> getSales(){
        return salesList;
    }
    public static float getTotal(){
        return total;
    }
    public static int getCount(){
        return count;
    }

}
