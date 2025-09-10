package Controller;

import BackEnd.dailyProfit;

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

        String url = "jdbc:sqlite:data.sqlite";

        String date;
        float profit = 0;

        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM DAILY_INCOMES");
            ResultSet dailyProfit = Statement.executeQuery();

            while (dailyProfit.next()) {
                date = dailyProfit.getString("DATE");
                profit = dailyProfit.getFloat("INCOME");
                totalProfit += profit;

                dailyProfits.add(new dailyProfit(date,profit));
            }
            dailyProfit.close();
            Statement.close();
            connection.close();

        }catch(Exception e){
            System.out.println("Connection Failed! Check output console");
        }
    }

    public static void setDailyProfitsList(LocalDate date1, LocalDate date2){

        dailyProfits.clear();
        totalProfit = 0;

        String url = "jdbc:sqlite:data.sqlite";

        LocalDate date;
        float profit = 0;

        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT * FROM DAILY_INCOMES WHERE FILTER BETWEEN ? AND ?");

            int filter1 = date1.getYear()*10000+date1.getMonthValue()*100+date1.getDayOfMonth();
            int filter2 = date2.getYear()*10000+date2.getMonthValue()*100+date2.getDayOfMonth();

            Statement.setInt(1, filter1);
            Statement.setInt(2, filter2);

            ResultSet dailyProfit = Statement.executeQuery();

            while (dailyProfit.next()) {
                date = dailyProfit.getDate("DATE").toLocalDate();
                profit = dailyProfit.getFloat("INCOME");
                totalProfit += profit;

                dailyProfits.add(new dailyProfit(String.valueOf(date),profit));
            }
            dailyProfit.close();
            Statement.close();
            connection.close();

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
