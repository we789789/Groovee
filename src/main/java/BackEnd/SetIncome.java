package BackEnd;

import java.sql.*;
import java.util.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SetIncome {

    static String url = "jdbc:sqlite:data.sqlite?busy_timeout=5000";

    public static void setIncome(int productId, int quantity) {
        LocalDate currentDate = LocalDate.now();
        Date date = new Date();
        int filter = date.getYear()*1000+date.getMonth()*100+date.getDay();

        int MM = currentDate.getMonthValue();
        int YYYY = currentDate.getYear();
        int DD = currentDate.getDayOfMonth();
        String sqlDate = IntStream.of(YYYY, MM, DD).mapToObj(String::valueOf).collect(Collectors.joining("-"));

        float income;
        float monthlyIncome;
        float dailyIncome;
        float monthlyIncomeByProduct;
        float yearlyIncomeByProduct;
        float yearlyIncome;
        float profit;


        try {

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement Statement;

            Statement = connection.prepareStatement("SELECT PURCHASE_PRICE, SELLING_PRICE FROM products WHERE CAST(PRODUCT_ID AS INTEGER) = ?");
            Statement.setInt(1, productId);
            ResultSet Profit = Statement.executeQuery();

            if (Profit.next()) {

                profit = Profit.getFloat(2) - Profit.getFloat(1);
                income = profit * quantity;
                monthlyIncome = income;
                dailyIncome = income;
                monthlyIncomeByProduct = income;
                yearlyIncomeByProduct = income;
                yearlyIncome = income;


                // ----------SET DAILY INCOME BY PRODUCT------------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM INCOME WHERE FILTER =? AND CAST(PRODUCT_ID AS INTEGER) =?");
                Statement.setInt(1, filter);
                Statement.setInt(2, productId);
                ResultSet Income = Statement.executeQuery();

                if (Income.next()) {

                    income += Income.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE INCOME SET INCOME=? WHERE PRODUCT_ID=? AND FILTER =?");
                    Statement.setFloat(1, income);
                    Statement.setInt(2, productId);
                    Statement.setInt(3, filter);
                    Statement.executeUpdate();
                } else {
                    Statement = connection.prepareStatement("INSERT INTO INCOME VALUES (?,?,?,?)");

                    Statement.setString(1, sqlDate);
                    Statement.setInt(2, productId);
                    Statement.setFloat(3, income);
                    Statement.setInt(4, filter);
                    Statement.executeUpdate();
                }
                Income.close();

                // --------- SET DAILY INCOME ----------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM DAILY_INCOMES WHERE FILTER =?");
                Statement.setInt(1, filter);
                ResultSet dailyIncomes = Statement.executeQuery();

                if (dailyIncomes.next()) {

                    dailyIncome += dailyIncomes.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE DAILY_INCOMES SET INCOME = ? WHERE FILTER =?");
                    Statement.setFloat(1, dailyIncome);
                    Statement.setInt(2, filter);
                    Statement.executeUpdate();

                } else {

                    Statement = connection.prepareStatement("INSERT INTO DAILY_INCOMES VALUES (?,?,?)");
                    Statement.setString(1, sqlDate);
                    Statement.setFloat(2, income);
                    Statement.setInt(3, filter);
                    Statement.executeUpdate();
                }
                dailyIncomes.close();


                // --------- STE MONTHLY INCOME BY PRODUCT -----------//


                Statement = connection.prepareStatement("SELECT INCOME FROM MONTHLY_INCOME_BY_PRODUCT WHERE CAST(YEAR AS INTEGER)=? AND CAST(MONTH AS INTEGER) =? AND CAST(PRODUCT_ID AS INTEGER) =?");
                Statement.setInt(1, YYYY);
                Statement.setInt(2, MM);
                Statement.setInt(3, productId);
                ResultSet monthlyIncomes = Statement.executeQuery();

                if (monthlyIncomes.next()) {

                    monthlyIncomeByProduct += monthlyIncomes.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE MONTHLY_INCOME_BY_PRODUCT SET INCOME =? WHERE CAST(YEAR AS INTEGER)=? AND CAST(MONTH AS INTEGER) =? AND CAST(PRODUCT_ID AS INTEGER) =?");
                    Statement.setFloat(1, monthlyIncomeByProduct);
                    Statement.setInt(2, YYYY);
                    Statement.setInt(3, MM);
                    Statement.setInt(4, productId);
                    Statement.executeUpdate();

                } else {

                    Statement = connection.prepareStatement("INSERT INTO MONTHLY_INCOME_BY_PRODUCT VALUES (?,?,?,?)");
                    Statement.setInt(1, YYYY);
                    Statement.setInt(2, MM);
                    Statement.setInt(3, productId);
                    Statement.setFloat(4, income);
                    Statement.executeUpdate();
                }
                monthlyIncomes.close();

                // --------- SET MONTHLY INCOME --------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM MONTHLY_INCOMES WHERE YEAR = ? AND MONTH = ?");
                Statement.setInt(1, YYYY);
                Statement.setInt(2, MM);
                ResultSet MonthlyIncome = Statement.executeQuery();

                if (MonthlyIncome.next()) {

                    monthlyIncome += MonthlyIncome.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE MONTHLY_INCOMES SET INCOME=? WHERE YEAR = ? AND MONTH = ?");
                    Statement.setFloat(1, monthlyIncome);
                    Statement.setInt(2, YYYY);
                    Statement.setInt(3, MM);
                    Statement.executeUpdate();

                } else {

                    Statement = connection.prepareStatement("INSERT INTO MONTHLY_INCOMES VALUES (?,?,?,?)");
                    Statement.setInt(1, YYYY);
                    Statement.setInt(2, MM);
                    Statement.setFloat(3, monthlyIncome);
                    Statement.setInt(4, YYYY * 100 + MM);
                    Statement.executeUpdate();
                }
                MonthlyIncome.close();


                // ------------ SET YEARLY INCOME BY PRODUCT --------------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM YEARLY_INCOME_BY_PRODUCT WHERE YEAR =? AND PRODUCT_ID =? ");
                Statement.setInt(1, YYYY);
                Statement.setInt(2, productId);
                ResultSet YearlyIncomeByProduct = Statement.executeQuery();

                if (YearlyIncomeByProduct.next()) {

                    yearlyIncomeByProduct += YearlyIncomeByProduct.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE YEARLY_INCOME_BY_PRODUCT SET INCOME=? WHERE YEAR =? AND PRODUCT_ID =?");
                    Statement.setFloat(1, yearlyIncomeByProduct);
                    Statement.setInt(2, YYYY);
                    Statement.setInt(3, productId);
                    Statement.executeUpdate();

                } else {

                    Statement = connection.prepareStatement("INSERT INTO YEARLY_INCOME_BY_PRODUCT VALUES (?,?,?)");
                    Statement.setInt(1, YYYY);
                    Statement.setInt(2, productId);
                    Statement.setFloat(3, income);
                    Statement.executeUpdate();

                }
                YearlyIncomeByProduct.close();


                //  ------------- SET YEARLY INCOME ------------- //


                Statement = connection.prepareStatement("SELECT INCOME FROM YEARLY_INCOMES WHERE YEAR =? ");
                Statement.setInt(1, YYYY);
                ResultSet YearlyIncomes = Statement.executeQuery();

                if (YearlyIncomes.next()) {

                    yearlyIncome += YearlyIncomes.getFloat("INCOME");

                    Statement = connection.prepareStatement("UPDATE YEARLY_INCOMES SET INCOME=? WHERE YEAR =? ");
                    Statement.setFloat(1, yearlyIncome);
                    Statement.setInt(2, YYYY);
                    Statement.executeUpdate();

                } else {

                    Statement = connection.prepareStatement("INSERT INTO YEARLY_INCOMES VALUES (?,?)");
                    Statement.setInt(1, YYYY);
                    Statement.setFloat(2, income);
                    Statement.executeUpdate();
                }
                YearlyIncomes.close();


            } else {
                System.out.println("Product details not found. INCOME table update fail.");
            }

            Profit.close();
            Statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
