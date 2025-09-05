package Controller;

import BackEnd.dailyProfit;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

public class dailyProfitController {

    static ArrayList<dailyProfit> dailyProfits = new ArrayList<>();
    static float totalProfit;

    public static void setDailyProfitsList(){

        dailyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        LocalDate date;
        float profit = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM DAILY_INCOMES");
            ResultSet dailyProfit = Statement.executeQuery();

            while (dailyProfit.next()) {
                date = dailyProfit.getDate("DATE").toLocalDate();
                profit = dailyProfit.getFloat("INCOME");
                totalProfit += profit;

                dailyProfits.add(new dailyProfit(date,profit));
            }

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static void setDailyProfitsList(LocalDate date1, LocalDate date2){

        dailyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        LocalDate date;
        float profit = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM DAILY_INCOMES WHERE DATE BETWEEN ? AND ?");
            Statement.setDate(1, java.sql.Date.valueOf(date1));
            Statement.setDate(2, java.sql.Date.valueOf(date2));
            ResultSet dailyProfit = Statement.executeQuery();

            while (dailyProfit.next()) {
                date = dailyProfit.getDate("DATE").toLocalDate();
                profit = dailyProfit.getFloat("INCOME");
                totalProfit += profit;

                dailyProfits.add(new dailyProfit(date,profit));
            }

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static ArrayList<dailyProfit> getDailyProfitsList(){
        return dailyProfits;
    }
    public static float getTotalProfit(){
        return totalProfit;
    }

}
