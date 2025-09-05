package Controller;

import BackEnd.MonthlyProfitByProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MonthlyProfitByProductController {

    static ArrayList<MonthlyProfitByProduct> monthlyIncomeByProduct = new ArrayList<>();
    static float totalIncome = 0;

    public static void setMonthlyIncomeByProductList() {

        monthlyIncomeByProduct.clear();

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int year;
        int month;
        int productID = -1;
        String productName = "";
        float sellingPrice = -1;
        float purchasePrice= -1;
        int quantity = 0;
        float income;
        totalIncome = 0;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOME_BY_PRODUCT");
            ResultSet monthlyIncomeByProducts = Statement.executeQuery();

            while(monthlyIncomeByProducts.next()){
                year = monthlyIncomeByProducts.getInt("YEAR");
                month = monthlyIncomeByProducts.getInt("MONTH");
                productID = monthlyIncomeByProducts.getInt("PRODUCT_ID");
                income = monthlyIncomeByProducts.getFloat("INCOME");

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
                monthlyIncomeByProduct.add(new MonthlyProfitByProduct(productID, productName, purchasePrice, sellingPrice, quantity, income, month, year));

                productID = -1;
                productName = "";
                sellingPrice = -1;
                purchasePrice= -1;
                quantity = 0;
                income =0;
            }
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println("error!");
            System.out.println(e);
        }
    }

    public static void setMonthlyIncomeByProductList(String productName) {

        monthlyIncomeByProduct.clear();

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";
        int year = -1;
        int month = -1;
        int productID = -1;
        float sellingPrice = -1;
        float purchasePrice= -1;
        int quantity = 0;
        float income;
        totalIncome = 0;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS  WHERE NAME Like ?");
            Statement.setString(1,"%"+productName+"%");
            ResultSet monthlyIncomeByProducts = Statement.executeQuery();

            while(monthlyIncomeByProducts.next()){
                productID = monthlyIncomeByProducts.getInt("PRODUCT_ID");
                productName = monthlyIncomeByProducts.getString("Name");
                sellingPrice = monthlyIncomeByProducts.getFloat("SELLING_Price");
                purchasePrice = monthlyIncomeByProducts.getFloat("PURCHASE_Price");

                Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOME_BY_PRODUCT Where PRODUCT_ID = ?");
                Statement.setInt(1, productID);
                ResultSet dailyIncomeByProducts = Statement.executeQuery();
                while(dailyIncomeByProducts.next()){

                    year = dailyIncomeByProducts.getInt("YEAR");
                    month = dailyIncomeByProducts.getInt("MONTH");
                    income = dailyIncomeByProducts.getFloat("INCOME");

                    Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE PRODUCT_ID = ?");
                    Statement.setInt(1, productID);
                    ResultSet stock = Statement.executeQuery();

                    quantity = 0;
                    if(stock.next()){
                        quantity = stock.getInt("quantity");
                    }
                    totalIncome += income;
                    monthlyIncomeByProduct.add(new MonthlyProfitByProduct(productID, productName, purchasePrice, sellingPrice, quantity, income, month, year));

                    productID = -1;
                    productName = "";
                    sellingPrice = -1;
                    purchasePrice= -1;
                    quantity = 0;
                    income =0;
                }

            }
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println("error!");
            System.out.println(e);
        }
    }

    public static ArrayList<MonthlyProfitByProduct> getMonthlyIncomeByProductList() {
        return monthlyIncomeByProduct;
    }
    public static float getTotalIncome() {
        return totalIncome;
    }
}
