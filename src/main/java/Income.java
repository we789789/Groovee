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
        float dailyIncome;
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
                dailyIncome = income;


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

                        // --------- SET DAILY INCOME ----------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM DAILY_INCOMES WHERE DATE =?");
                Statement.setDate(1, sqlDate);
                ResultSet dailyIncomes = Statement.executeQuery();

                if (dailyIncomes.next()) {

                    dailyIncome += dailyIncomes.getFloat("INCOME");
                    Statement = connection.prepareStatement("UPDATE DAILY_INCOMES SET INCOME = ? WHERE DATE =?");
                    Statement.setFloat(1, dailyIncome);
                    Statement.setDate(2, sqlDate);
                    Statement.executeUpdate();
                } else{

                    Statement = connection.prepareStatement("INSERT INTO DAILY_INCOMES VALUES (?,?)");
                    Statement.setDate(1, sqlDate);
                    Statement.setFloat(2, income);
                    Statement.executeUpdate();
                }


                        // --------- STE MONTHLY INCOME BY PRODUCT -----------//


                Statement = connection.prepareStatement("SELECT INCOME FROM MONTHLY_INCOME_BY_PRODUCT WHERE YEAR =? AND MONTH =? AND PRODUCT_ID =?");
                Statement.setInt(1, year);
                Statement.setInt(2, month);
                Statement.setInt(3, productId);
                ResultSet monthlyIncomes = Statement.executeQuery();

                if (monthlyIncomes.next()) {

                    monthlyIncomeByProduct += monthlyIncomes.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE MONTHLY_INCOME_BY_PRODUCT SET INCOME =? WHERE YEAR =? AND MONTH =? AND PRODUCT_ID =?");
                    Statement.setFloat(1, monthlyIncomeByProduct);
                    Statement.setInt(2, year);
                    Statement.setInt(3, month);
                    Statement.setInt(4, productId);
                    Statement.executeUpdate();

                }else{

                    Statement = connection.prepareStatement("INSERT INTO MONTHLY_INCOME_BY_PRODUCT VALUES (?,?,?,?)");
                    Statement.setInt(1, year);
                    Statement.setInt(2, month);
                    Statement.setInt(3, productId);
                    Statement.setFloat(4, income);
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


                        // ------------ SET YEARLY INCOME BY PRODUCT --------------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM YEARLY_INCOME_BY_PRODUCT WHERE YEAR =? AND PRODUCT_ID =? ");
                Statement.setInt(1, year);
                Statement.setInt(2, productId);
                ResultSet YearlyIncomeByProduct = Statement.executeQuery();

                if(YearlyIncomeByProduct.next()){

                    yearlyIncomeByProduct += YearlyIncomeByProduct.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE YEARLY_INCOME_BY_PRODUCT SET INCOME=? WHERE YEAR =? AND PRODUCT_ID =?");
                    Statement.setFloat(1,yearlyIncomeByProduct);
                    Statement.setInt(2, year);
                    Statement.setInt(3, productId);
                    Statement.executeUpdate();

                }else{

                    Statement = connection.prepareStatement("INSERT INTO YEARLY_INCOME_BY_PRODUCT VALUES (?,?,?)");
                    Statement.setInt(1, year);
                    Statement.setInt(2, productId);
                    Statement.setFloat(3, income);
                    Statement.executeUpdate();

                }


                        //  ------------- SET YEARLY INCOME ------------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM YEARLY_INCOMES WHERE YEAR =? ");
                Statement.setInt(1, year);
                ResultSet YearlyIncomes = Statement.executeQuery();

                if(YearlyIncomes.next()){

                    yearlyIncome += YearlyIncomes.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE YEARLY_INCOMES SET INCOME=? WHERE YEAR =? ");
                    Statement.setFloat(1, yearlyIncome);
                    Statement.setInt(2, year);
                    Statement.executeUpdate();

                }else{

                    Statement = connection.prepareStatement("INSERT INTO YEARLY_INCOMES VALUES (?,?)");
                    Statement.setInt(1, year);
                    Statement.setFloat(2, income);
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
