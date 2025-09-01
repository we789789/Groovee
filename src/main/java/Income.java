import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

public class Income {
    Scanner scanner = new Scanner(System.in);
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    LocalDate currentDate = LocalDate.now();
    PreparedStatement Statement;

    String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
    String user = "root";
    String password = "HBdeLA@2004";

    int month = currentDate.getMonthValue();
    int year = currentDate.getYear();
    int day = currentDate.getDayOfMonth();

    public void setIncome(int productId, int quantity) {

        float income;
        float monthlyIncome;
        float profit;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            Statement = connection.prepareStatement("SELECT PURCHASE_PRICE, SELLING_PRICE FROM products WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet Profit = Statement.executeQuery();

            if (Profit.next()) {
                profit = Profit.getFloat(2) - Profit.getFloat(1);
                income = profit * quantity;
                monthlyIncome = income;


                    // ----------SET DAILY INCOME BY PRODUCT------------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM INCOME WHERE DATE =? AND PRODUCT_ID =?");
                Statement.setDate(1, sqlDate);
                Statement.setInt(2, productId);
                ResultSet Income = Statement.executeQuery();

                if (Income.next()) {
                    income += Income.getFloat("INCOME");
                    Statement = connection.prepareStatement("UPDATE INCOME SET INCOME=? WHERE PRODUCT_ID=? AND DATE =?");
                    Statement.setFloat(1, income);
                    Statement.setInt(2, productId);
                    Statement.setDate(3, sqlDate);
                    Statement.executeUpdate();
                }
                else{
                    Statement = connection.prepareStatement("INSERT INTO INCOME VALUES (?,?,?)");
                    Statement.setDate(1, sqlDate);
                    Statement.setInt(2, productId);
                    Statement.setFloat(3, income);
                    Statement.executeUpdate();
                }
                        // --------- SET MONTHLY INCOME --------- //
                Statement = connection.prepareStatement("SELECT INCOME FROM MONTHLY_INCOMES WHERE YEAR = ? AND MONTH = ?");
                Statement.setInt(1, year);
                Statement.setInt(2, month);
                ResultSet MonthlyIncome = Statement.executeQuery();

                if (MonthlyIncome.next()) {
                    monthlyIncome += MonthlyIncome.getFloat("INCOME");
                    Statement = connection.prepareStatement("UPDATE MONTHLY_INCOMES SET INCOME=? WHERE YEAR = ? AND MONTH = ?");
                    Statement.setFloat(1, monthlyIncome);
                    Statement.setInt(2, year);
                    Statement.setInt(3, month);
                    Statement.executeUpdate();
                }else{
                    Statement = connection.prepareStatement("INSERT INTO MONTHLY_INCOMES VALUES (?,?,?)");
                    Statement.setInt(1, year);
                    Statement.setInt(2, month);
                    Statement.setFloat(3, monthlyIncome);
                    Statement.executeUpdate();
                }

            }
            else{
                System.out.println("Product details not found. INCOME table update fail.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getDailyIncome(){
        
    }

}
