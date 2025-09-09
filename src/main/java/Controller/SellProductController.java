package Controller;

import BackEnd.Quantity;
import BackEnd.Sale;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Date;

import static BackEnd.SetIncome.setIncome;

public class SellProductController {
    static ArrayList<Sale> saleList = new ArrayList<>();
    static ArrayList<String> suggestedNameList = new ArrayList<>();
    static ArrayList<Quantity>  quantity= new ArrayList<>();

    static int productID = 0;
    static float rate = 0;
    static float price = 0;
    static int currentQuantity = 0;
    static int newSaleID;

    public SellProductController() {}

    public static void addSale(int saleID, int productID, int quantity) {

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        String productName="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM products WHERE PRODUCT_ID=?");
            Statement.setInt(1, productID);
            ResultSet amount = Statement.executeQuery();

            if (amount.next()) {
                rate = amount.getFloat("SELLING_PRICE");
                price = rate*quantity;
            } else {
                System.out.println("Product not found.");
            }



                Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS  WHERE PRODUCT_ID=?");
                Statement.setInt(1, productID);
                ResultSet name = Statement.executeQuery();
                if (name.next()) {
                    productName =  name.getString("NAME");
                }else{
                    System.out.println("Product not found.");
                }
                    saleList.add(new Sale(saleID, productID, quantity, rate, price, productName));



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addSale(int saleID, String productName, int quantity) {

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT PRODUCT_ID FROM PRODUCTS  WHERE NAME=?");
            Statement.setString(1, productName);
            ResultSet ID = Statement.executeQuery();
            if (ID.next()) {
                productID = ID.getInt("PRODUCT_ID");
            } else {
                System.out.println("Product not found.");
            }

            Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM products WHERE PRODUCT_ID=?");
            Statement.setInt(1, productID);
            ResultSet amount = Statement.executeQuery();

            if (amount.next()) {
                price = amount.getFloat("SELLING_PRICE") * quantity;
            } else {
                System.out.println("Product not found.");
            }


            if (currentQuantity >= quantity) {
                saleList.add(new Sale(saleID, productID, quantity, rate, price, productName));
                currentQuantity -= quantity;
            } else {
                return;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNewSaleID() {

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        int LastSaleID = 0;
        String productName = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT SALE_ID FROM SALES");
            ResultSet existsSales = Statement.executeQuery();
            while (existsSales.next()) {
                LastSaleID = existsSales.getInt("SALE_ID");
            }
            newSaleID = LastSaleID + 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newSaleID;
    }

    public static ArrayList<String> getSuggestedNameList() {

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        String productName = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;


            Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS");
            ResultSet suggestingNames = Statement.executeQuery();
            while (suggestingNames.next()) {
                productName = suggestingNames.getString("NAME");
                suggestedNameList.add(productName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestedNameList;
    }

    public static ArrayList<Sale> getSaleList() {
        return saleList;

    }

    public static String getProductName(int productID) {
        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        String productName = "";
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS WHERE PRODUCT_ID=?");
            Statement.setInt(1, productID);
            ResultSet name = Statement.executeQuery();
            if (name.next()) {
                productName = name.getString("NAME");
            }else{
                return "Product Not Found";
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return productName;
    }

    public static void setQuantity(int productID, int Quantity) {
        quantity.add(new Quantity(productID,Quantity));
    }


    public static int getQuantity(int productID) {

        int billQuantity =0;

        for(int i = 0; i < quantity.size(); i++){
            if(quantity.get(i).getProductID() == productID){
                billQuantity += quantity.get(i).getQuantity();
            }
        }

        return billQuantity;
    }


    public static int getStock(int productID) {
        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int stock = 0;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID=?");
            Statement.setInt(1, productID);
            ResultSet stocks = Statement.executeQuery();
            if (stocks.next()) {
                stock = stocks.getInt("QUANTITY");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return stock;
    }

    public static float getPrice(int productID) {
        float price = 0;
        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM PRODUCTS WHERE PRODUCT_ID=?");
            Statement.setInt(1, productID);
            ResultSet prices = Statement.executeQuery();
            if (prices.next()) {
                price = prices.getFloat("SELLING_PRICE");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return price;
    }

    public static void clear(){
        quantity.clear();
        saleList.clear();
    }

    public static float getSubTotal(){
        float subTotal = 0;
        for(int i = 0; i < saleList.size(); i++){
            subTotal += saleList.get(i).getPrice();
        }
        return subTotal;
    }

    public static void remove(int indexID){
        saleList.remove(indexID);
        quantity.remove(indexID);
    }



    public static int doneSale(){

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        int doneSale =0;

        int saleID = 0;
        int productID = 0;
        float total = 0;
        int quantity = 0;
        int currentQuantity = 0;

        for(int i=0;i<saleList.size();i++){
            saleID = saleList.get(i).getSaleId();
            productID = saleList.get(i).getProductId();
            total = saleList.get(i).getPrice();
            quantity = saleList.get(i).getQuantity();

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement Statement;

                Statement = connection.prepareStatement("INSERT INTO SALES VALUES (?, ?, ?, ?, ?, ?)");
                LocalDateTime time = LocalDateTime.now();
                Timestamp Time = Timestamp.valueOf(time);
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                Statement.setDate(1, sqlDate);
                Statement.setInt(2, productID);
                Statement.setInt(3, quantity);
                Statement.setFloat(4, total);
                Statement.setTimestamp(5, Time);
                Statement.setInt(6, saleID);
                Statement.execute();

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID=?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();
                if (stock.next()) {
                    currentQuantity = stock.getInt("QUANTITY");
                }

                Statement = connection.prepareStatement("UPDATE STOCK SET QUANTITY=? WHERE PRODUCT_ID=?");
                Statement.setInt(1, currentQuantity - quantity);
                Statement.setInt(2, productID);
                Statement.execute();

                doneSale = 1;

            }catch(Exception e){
                e.printStackTrace();
                doneSale = 0;
            }
            setIncome(productID,quantity);
        }
        return doneSale;
    }
}