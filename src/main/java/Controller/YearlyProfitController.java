package Controller;

import BackEnd.YearlyProfit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class YearlyProfitController {

    static ArrayList<YearlyProfit> yearlyProfits = new ArrayList<>();
    static float totalProfit = 0;

    public static void setYearlyProfitsList(){

        yearlyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        int year;
        float profit = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOMES");
            ResultSet monthlyProfit = Statement.executeQuery();

            while (monthlyProfit.next()) {
                year = monthlyProfit.getInt("YEAR");
                profit = monthlyProfit.getFloat("INCOME");
                totalProfit += profit;

                yearlyProfits.add(new  YearlyProfit(year,profit));
            }

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static void setYearlyProfitsList(LocalDate date1, LocalDate date2){

        yearlyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        int filterYear1 = date1.getYear();
        int filterYear2 = date2.getYear();

        int year;
        float profit = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOMES WHERE FILTER BETWEEN ? AND ?");
            Statement.setInt(1, filterYear1);
            Statement.setInt(2, filterYear2);
            ResultSet monthlyProfit = Statement.executeQuery();

            while (monthlyProfit.next()) {

                year = monthlyProfit.getInt("YEAR");
                profit = monthlyProfit.getFloat("INCOME");
                totalProfit += profit;

                yearlyProfits.add(new YearlyProfit(year, profit));
            }

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static ArrayList<YearlyProfit> getYearlyProfitsList(){
        return yearlyProfits;
    }
    public static float getTotalProfit(){
        return totalProfit;
    }
}
