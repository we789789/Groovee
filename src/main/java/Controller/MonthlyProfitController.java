package Controller;

import BackEnd.MonthlyProfit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyProfitController {
    static ArrayList<MonthlyProfit> monthlyProfits = new ArrayList<>();
    static float totalProfit = 0;

    public static void setMonthlyProfitsList(){

        monthlyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:sqlite:data.sqlite";

        int year;
        int month;
        float profit = 0;

        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOMES");
            ResultSet monthlyProfit = Statement.executeQuery();

            while (monthlyProfit.next()) {
                year = monthlyProfit.getInt("YEAR");
                month = monthlyProfit.getInt("MONTH");
                profit = monthlyProfit.getFloat("INCOME");
                totalProfit += profit;

                monthlyProfits.add(new  MonthlyProfit(year,month,profit));
            }
            monthlyProfit.close();
            Statement.close();

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static void setMonthlyProfitsList(LocalDate date1, LocalDate date2){

        monthlyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:sqlite:data.sqlite";

        int filterYear1 = date1.getYear();
        int filterMonth1 = date1.getMonthValue();
        int filterYear2 = date2.getYear();
        int filterMonth2 = date2.getMonthValue();

        int year;
        int month;
        float profit = 0;

        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOMES WHERE CAST(FILTER AS INTEGER) BETWEEN ? AND ?");
            Statement.setInt(1, filterYear1*100+filterMonth1);
            Statement.setInt(2, filterYear2*100+filterMonth2);
            ResultSet monthlyProfit = Statement.executeQuery();

            while (monthlyProfit.next()) {

                year = monthlyProfit.getInt("YEAR");
                month = monthlyProfit.getInt("MONTH");
                profit = monthlyProfit.getFloat("INCOME");
                totalProfit += profit;

                monthlyProfits.add(new MonthlyProfit(year, month, profit));
            }
            monthlyProfit.close();
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static ArrayList<MonthlyProfit> getMonthlyProfitsList(){
        return monthlyProfits;
    }
    public static float getTotalProfit(){
        return totalProfit;
    }
}
