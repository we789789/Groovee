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

        String url = "jdbc:sqlite:data.sqlite";

        String productName="";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM products WHERE CAST(PRODUCT_ID AS INTEGER)=?");
            Statement.setInt(1, productID);
            ResultSet amount = Statement.executeQuery();

            if (amount.next()) {
                rate = amount.getFloat("SELLING_PRICE");
                price = rate*quantity;
            } else {
                System.out.println("Product not found.");
            }



            Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS  WHERE CAST(PRODUCT_ID AS INTEGER)=?");
            Statement.setInt(1, productID);
            ResultSet name = Statement.executeQuery();
            if (name.next()) {
                productName =  name.getString("NAME");
            }else{
                System.out.println("Product not found.");
            }
            name.close();
            saleList.add(new Sale(saleID, productID, quantity, rate, price, productName));

            amount.close();
            Statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addSale(int saleID, String productName, int quantity) {

        String url = "jdbc:sqlite:data.sqlite";


        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT PRODUCT_ID FROM PRODUCTS  WHERE NAME=?");
            Statement.setString(1, productName);
            ResultSet ID = Statement.executeQuery();
            if (ID.next()) {
                productID = ID.getInt("PRODUCT_ID");
            } else {
                System.out.println("Product not found.");
            }

            Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM products WHERE CAST(PRODUCT_ID AS INTEGER)=?");
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

        String url = "jdbc:sqlite:data.sqlite";

        int LastSaleID = 0;
        String productName = "";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT SALE_ID FROM SALES");
            ResultSet existsSales = Statement.executeQuery();
            while (existsSales.next()) {
                LastSaleID = existsSales.getInt("SALE_ID");
            }
            newSaleID = LastSaleID + 1;
            existsSales.close();
            Statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newSaleID;
    }

    public static ArrayList<String> getSuggestedNameList() {

        String url = "jdbc:sqlite:data.sqlite";

        String productName = "";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
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

        String url = "jdbc:sqlite:data.sqlite";

        String productName = "";
        try{

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS WHERE CAST(PRODUCT_ID AS INTEGER)=?");
            Statement.setInt(1, productID);
            ResultSet name = Statement.executeQuery();
            if (name.next()) {
                productName = name.getString("NAME");
            }else{
                return "Product Not Found";
            }
            name.close();
            Statement.close();
            connection.close();


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

        String url = "jdbc:sqlite:data.sqlite";
        int stock = 0;
        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE CAST(PRODUCT_ID AS INTEGER)=?");
            Statement.setInt(1, productID);
            ResultSet stocks = Statement.executeQuery();
            if (stocks.next()) {
                stock = stocks.getInt("QUANTITY");
            }
            stocks.close();
            Statement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return stock;
    }

    public static float getPrice(int productID) {

        float price = 0;
        String url = "jdbc:sqlite:data.sqlite";

        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT SELLING_PRICE FROM PRODUCTS WHERE CAST(PRODUCT_ID AS INTEGER)=?");
            Statement.setInt(1, productID);
            ResultSet prices = Statement.executeQuery();
            if (prices.next()) {
                price = prices.getFloat("SELLING_PRICE");
            }
            prices.close();
            Statement.close();
            connection.close();

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

        String url = "jdbc:sqlite:data.sqlite";;

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

                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement Statement;

                Statement = connection.prepareStatement("INSERT INTO SALES VALUES (?, ?, ?, ?, ?, ?,?)");
                LocalDateTime time = LocalDateTime.now();
                Timestamp Time = Timestamp.valueOf(time);

                int YYYY = time.getYear();
                int MM = time.getMonthValue();
                int DD = time.getDayOfMonth();

                Statement.setString(1, String.valueOf(YYYY*1000+MM*100+DD));
                Statement.setInt(2, productID);
                Statement.setInt(3, quantity);
                Statement.setFloat(4, total);
                Statement.setString(5, String.valueOf(Time));
                Statement.setInt(6, saleID);
                Statement.setInt(7, YYYY*1000+MM*100+DD);
                Statement.execute();

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE CAST(PRODUCT_ID AS INTEGER)=?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();
                if (stock.next()) {
                    currentQuantity = stock.getInt("QUANTITY");
                }
                stock.close();

                Statement = connection.prepareStatement("UPDATE STOCK SET QUANTITY=? WHERE CAST(PRODUCT_ID AS INTEGER)=?");
                Statement.setInt(1, currentQuantity - quantity);
                Statement.setInt(2, productID);
                Statement.execute();

                doneSale = 1;

                Statement.close();
                connection.close();

            }catch(Exception e){
                e.printStackTrace();
                doneSale = 0;
            }
            setIncome(productID,quantity);
        }
        return doneSale;
    }
}