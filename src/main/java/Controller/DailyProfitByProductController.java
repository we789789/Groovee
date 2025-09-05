package Controller;

import BackEnd.DailyProfitByProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyProfitByProductController {

    static ArrayList<DailyProfitByProduct> dailyIncomeByProduct = new ArrayList<>();
    static float totalIncome;

    public static void setDailyIncomeByProduct() {
        dailyIncomeByProduct.clear();

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int productID = -1;
        String productName = "";
        float sellingPrice = -1;
        float purchasePrice= -1;
        int quantity = 0;
        float income;
        LocalDate date = null;
        totalIncome = 0;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM INCOME");
            ResultSet dailyIncomeByProducts = Statement.executeQuery();

            while(dailyIncomeByProducts.next()){
                date = dailyIncomeByProducts.getDate("DATE").toLocalDate();
                productID = dailyIncomeByProducts.getInt("PRODUCT_ID");
                income = dailyIncomeByProducts.getFloat("INCOME");

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productID);
                ResultSet dailyIncomeByProductsDetails = Statement.executeQuery();

                if(dailyIncomeByProductsDetails.next()) {
                    productName = dailyIncomeByProductsDetails.getString("NAME");
                    sellingPrice = dailyIncomeByProductsDetails.getFloat("SELLING_PRICE");
                    purchasePrice = dailyIncomeByProductsDetails.getFloat("PURCHASE_PRICE");
                }

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();

                if(stock.next()){
                    quantity = stock.getInt("QUANTITY");
                }

                totalIncome += income;
                dailyIncomeByProduct.add(new DailyProfitByProduct(productID, productName, purchasePrice, sellingPrice, quantity, date, income));

                productID = -1;
                productName = "";
                sellingPrice = -1;
                purchasePrice= -1;
                quantity = 0;
                income =0;
                date = null;
            }
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println("error!");
            System.out.println(e);
        }
    }

    public static void setDailyIncomeByProduct(String productName) {
        dailyIncomeByProduct.clear();

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int productID = -1;
        float sellingPrice = -1;
        float purchasePrice= -1;
        int quantity = 0;
        float income;
        LocalDate date = null;
        totalIncome = 0;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS  WHERE NAME Like ?");
            Statement.setString(1,"%"+productName+"%");
            ResultSet products = Statement.executeQuery();

            while(products.next()){
                productID = products.getInt("PRODUCT_ID");
                productName = products.getString("Name");
                sellingPrice = products.getFloat("SELLING_Price");
                purchasePrice = products.getFloat("PURCHASE_Price");

                Statement = connection.prepareStatement("SELECT * FROM INCOME Where PRODUCT_ID = ?");
                Statement.setInt(1, productID);
                ResultSet dailyIncomeByProducts = Statement.executeQuery();
                while(dailyIncomeByProducts.next()){
                    date = dailyIncomeByProducts.getDate("DATE").toLocalDate();
                    income = dailyIncomeByProducts.getFloat("INCOME");

                    Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID = ?");
                    Statement.setInt(1, productID);
                    ResultSet stock = Statement.executeQuery();

                    quantity = 0;
                    if(stock.next()){
                        quantity = stock.getInt("quantity");
                    }
                    totalIncome += income;
                    dailyIncomeByProduct.add(new DailyProfitByProduct(productID, productName, purchasePrice, sellingPrice, quantity, date, income));

                    productID = -1;
                    productName = "";
                    sellingPrice = -1;
                    purchasePrice= -1;
                    quantity = 0;
                    income =0;
                    date = null;
                }

            }

            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static ArrayList<DailyProfitByProduct> getDailyIncomeByProduct() {
        return dailyIncomeByProduct;
    }

    public static float getTotalDailyIncome() {

        return totalIncome;
    }
}
