package Controller;

import BackEnd.YearlyProfitByProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class YearlyProfitByProductController {

    static ArrayList<YearlyProfitByProduct> yearlyIncomeByProduct = new ArrayList<>();
    static float totalIncome = 0;

    public static void setYearlyIncomeByProductList() {

        yearlyIncomeByProduct.clear();

        String url = "jdbc:sqlite:data.sqlite";
        String user = "root";
        String password = "HBdeLA@2004";
        int year;
        int productID = -1;
        String productName = "";
        float sellingPrice = 0;
        float purchasePrice = 0;
        int quantity = 0;
        float income;
        totalIncome = 0;

        try{

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOME_BY_PRODUCT");
            ResultSet yearlyIncomeByProducts = Statement.executeQuery();

            while(yearlyIncomeByProducts.next()){
                year = yearlyIncomeByProducts.getInt("YEAR");
                productID = yearlyIncomeByProducts.getInt("PRODUCT_ID");
                income = yearlyIncomeByProducts.getFloat("INCOME");

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet yearlyIncomeByProductsDetails = Statement.executeQuery();

                if(yearlyIncomeByProductsDetails.next()) {

                    productName = yearlyIncomeByProductsDetails.getString("NAME");
                    sellingPrice = yearlyIncomeByProductsDetails.getFloat("SELLING_PRICE");
                    purchasePrice = yearlyIncomeByProductsDetails.getFloat("PURCHASE_PRICE");
                }

                Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet stock = Statement.executeQuery();

                if(stock.next()){
                    quantity = stock.getInt("QUANTITY");
                }

                totalIncome += income;
                yearlyIncomeByProduct.add(new YearlyProfitByProduct(productID, productName, purchasePrice, sellingPrice, quantity));
                yearlyIncomeByProduct.getLast().setYear(year);
                yearlyIncomeByProduct.getLast().setYearlyProfit(income);

                productID = -1;
                productName = "";
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

    public static void setYearlyIncomeByProductList(String productName) {

        yearlyIncomeByProduct.clear();

        String url = "jdbc:sqlite:data.sqlite";
        String user = "root";
        String password = "HBdeLA@2004";
        int year = -1;
        int productID = -1;
        float sellingPrice = -1;
        float purchasePrice= -1;
        int quantity = 0;
        float income;
        totalIncome = 0;

        try{

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE NAME Like ?");
            Statement.setString(1,"%"+productName+"%");
            ResultSet yearlyIncomeByProducts = Statement.executeQuery();

            while(yearlyIncomeByProducts.next()){
                productID = yearlyIncomeByProducts.getInt("PRODUCT_ID");
                productName = yearlyIncomeByProducts.getString("Name");
                sellingPrice = yearlyIncomeByProducts.getFloat("SELLING_Price");
                purchasePrice = yearlyIncomeByProducts.getFloat("PURCHASE_Price");

                Statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOME_BY_PRODUCT Where CAST(PRODUCT_ID AS INTEGER) = ?");
                Statement.setInt(1, productID);
                ResultSet YearlyIncomeByProducts = Statement.executeQuery();
                while(YearlyIncomeByProducts.next()){

                    year = YearlyIncomeByProducts.getInt("YEAR");
                    income = YearlyIncomeByProducts.getFloat("INCOME");

                    Statement = connection.prepareStatement("SELECT QUANTITY FROM STOCK WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
                    Statement.setInt(1, productID);
                    ResultSet stock = Statement.executeQuery();

                    quantity = 0;
                    if(stock.next()){
                        quantity = stock.getInt("quantity");
                    }

                    totalIncome += income;
                    yearlyIncomeByProduct.add(new YearlyProfitByProduct(productID, productName, purchasePrice, sellingPrice, quantity));
                    yearlyIncomeByProduct.getLast().setYear(year);
                    yearlyIncomeByProduct.getLast().setYearlyProfit(income);

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

    public static ArrayList<YearlyProfitByProduct> getYearlyIncomeByProductList() {

        return yearlyIncomeByProduct;
    }
    public static float getTotalIncome() {
        return totalIncome;
    }

}