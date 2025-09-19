package Controller;

import BackEnd.DailySales;
import BackEnd.MonthlySales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SalesGraphicalViewController {

    static String url = "jdbc:sqlite:data.sqlite";

    public static ArrayList<MonthlySales> getMonthlySales(int year, String month) {
        ArrayList<MonthlySales> salesList = new ArrayList<>();

        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            int YYYY;
            String MM;
            int quantity;
            float income;

            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_SALES WHERE YEAR = ? AND MONTH LIKE ?;");
            Statement.setInt(1, year);
            Statement.setString(2, month);
            ResultSet sales = Statement.executeQuery();

            while(sales.next()){

                YYYY = sales.getInt("YEAR");
                MM = sales.getString("MONTH");
                quantity = sales.getInt("QUANTITY");
                income = sales.getFloat("INCOME");
                salesList.add(new MonthlySales(YYYY, MM, quantity, income));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return  salesList;
    }

    public static ArrayList<DailySales> getDailySales(int year, String month) {
        ArrayList<DailySales> salesList = new ArrayList<>();

        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            int YYYY;
            String MM;
            int DD;
            int quantity;
            float income;

            Statement = connection.prepareStatement("SELECT * FROM DAILY_SALES WHERE YEAR = ? AND MONTH LIKE ?;");
            Statement.setInt(1, year);
            Statement.setString(2, month);
            ResultSet sales = Statement.executeQuery();

            while(sales.next()){

                YYYY = sales.getInt("YEAR");
                MM = sales.getString("MONTH");
                DD = sales.getInt("DAY");
                quantity = sales.getInt("QUANTITY");
                income = sales.getFloat("INCOME");
                salesList.add(new DailySales(YYYY, MM, DD, quantity, income));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return  salesList;
    }
}
