import java.sql.*;


public class GetIncomes {

    PreparedStatement Statement;

    String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
    String user = "root";
    String password = "HBdeLA@2004";

    public void getDailyIncomes(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM DAILY_INCOMES");
            ResultSet dailyIncomes = Statement.executeQuery();

            if(dailyIncomes.next()) {
                System.out.print(dailyIncomes.getDate("DATE"));
                System.out.println(" : " + dailyIncomes.getFloat("INCOME"));
                while (dailyIncomes.next()) {
                    System.out.print(dailyIncomes.getDate("DATE"));
                    System.out.println(" : " + dailyIncomes.getFloat("INCOME"));
                }
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getDailyIncomesByProduct(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM INCOME");
            ResultSet dailyIncomesByProduct = Statement.executeQuery();

            if(dailyIncomesByProduct.next()) {
                System.out.print(dailyIncomesByProduct.getDate("DATE"));
                System.out.print(" | " + dailyIncomesByProduct.getInt("PRODUCT_ID"));
                System.out.println(" | " + dailyIncomesByProduct.getFloat("INCOME"));
                while (dailyIncomesByProduct.next()) {
                    System.out.print(dailyIncomesByProduct.getDate("DATE"));
                    System.out.print(" | " + dailyIncomesByProduct.getInt("PRODUCT_ID"));
                    System.out.println(" | " + dailyIncomesByProduct.getFloat("INCOME"));
                }
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getDailyIncomesByProduct(int productId){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM INCOME WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet dailyIncomesByProduct_Filter = Statement.executeQuery();

            if(dailyIncomesByProduct_Filter.next()) {

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productId);
                ResultSet productsDetails = Statement.executeQuery();

                if(productsDetails.next()) {
                    System.out.println("Product ID: " + productsDetails.getInt("PRODUCT_ID"));
                    System.out.println("Product Name: " + productsDetails.getString("PRODUCT_NAME"));
                    System.out.println("Product Purchasing Price: " + productsDetails.getFloat("PURCHASE_PRICE"));
                    System.out.println("Product Quantity: " + productsDetails.getFloat("SELLING_PRICE"));
                }else{
                    System.out.println("Product details not fount.");
                }
                System.out.print("\t" + dailyIncomesByProduct_Filter.getDate("DATE"));
                System.out.println(" | " + dailyIncomesByProduct_Filter.getFloat("INCOME"));

                while (dailyIncomesByProduct_Filter.next()) {
                    System.out.print("\t" + dailyIncomesByProduct_Filter.getDate("DATE"));
                    System.out.println(" | " + dailyIncomesByProduct_Filter.getFloat("INCOME"));
                }
            }else{
                System.out.println("Not data found.");
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getMonthlyIncomes() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOMES");
            ResultSet monthlyIncomes = Statement.executeQuery();

            if(monthlyIncomes.next()) {
                System.out.print(monthlyIncomes.getInt("YEAR"));
                System.out.println(" | " + monthlyIncomes.getInt("MONTH"));
                System.out.println(" | " + monthlyIncomes.getFloat("INCOME"));
                while (monthlyIncomes.next()) {
                    System.out.print(monthlyIncomes.getInt("YEAR"));
                    System.out.println(" | " + monthlyIncomes.getInt("MONTH"));
                    System.out.println(" | " + monthlyIncomes.getFloat("INCOME"));
                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getMonthlyIncomesByProduct(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOME_BY_PRODUCT");
            ResultSet monthlyIncomesByProduct = Statement.executeQuery();

            if(monthlyIncomesByProduct.next()) {
                System.out.print(monthlyIncomesByProduct.getInt("YEAR"));
                System.out.print(" | " + monthlyIncomesByProduct.getInt("MONTH"));
                System.out.print(" | " + monthlyIncomesByProduct.getInt("PRODUCT_ID"));
                System.out.println(" | " + monthlyIncomesByProduct.getFloat("INCOME"));
                while (monthlyIncomesByProduct.next()) {
                    System.out.print(monthlyIncomesByProduct.getInt("YEAR"));
                    System.out.print(" | " + monthlyIncomesByProduct.getInt("MONTH"));
                    System.out.print(" | " + monthlyIncomesByProduct.getInt("PRODUCT_ID"));
                    System.out.println(" | " + monthlyIncomesByProduct.getFloat("INCOME"));
                }
            }

            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getMonthlyIncomesByProduct(int productId){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOME_BY_PRODUCT WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet monthlyIncomesByProduct_Filter = Statement.executeQuery();

            if(monthlyIncomesByProduct_Filter.next()) {

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productId);
                ResultSet productsDetails = Statement.executeQuery();

                if(productsDetails.next()) {
                    System.out.println("Product ID: " + productsDetails.getInt("PRODUCT_ID"));
                    System.out.println("Product Name: " + productsDetails.getString("PRODUCT_NAME"));
                    System.out.println("Product Purchasing Price: " + productsDetails.getFloat("PURCHASE_PRICE"));
                    System.out.println("Product Quantity: " + productsDetails.getFloat("SELLING_PRICE"));
                }else{
                    System.out.println("Product details not fount.");
                }
                System.out.print("\t" + monthlyIncomesByProduct_Filter.getInt("YEAR"));
                System.out.print(" | " + monthlyIncomesByProduct_Filter.getInt("MONTH"));
                System.out.println(" | " + monthlyIncomesByProduct_Filter.getFloat("INCOME"));
                while (monthlyIncomesByProduct_Filter.next()) {
                    System.out.print("\t" + monthlyIncomesByProduct_Filter.getInt("YEAR"));
                    System.out.print(" | " + monthlyIncomesByProduct_Filter.getInt("MONTH"));
                    System.out.println(" | " + monthlyIncomesByProduct_Filter.getFloat("INCOME"));
                }
            }

            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getYearlyIncomes() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM    YEARLY_INCOMES");
            ResultSet yearlyIncomes = Statement.executeQuery();

            if(yearlyIncomes.next()) {
                System.out.print(yearlyIncomes.getInt("YEAR"));
                System.out.println(" | " + yearlyIncomes.getFloat("INCOME"));
                while (yearlyIncomes.next()) {
                    System.out.print(yearlyIncomes.getInt("YEAR"));
                    System.out.println(" | " + yearlyIncomes.getFloat("INCOME"));
                }
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getYearlyIncomesByProduct(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOME_BY_PRODUCT");
            ResultSet yearlyIncomesByProduct = Statement.executeQuery();

            if(yearlyIncomesByProduct.next()) {
                System.out.print(yearlyIncomesByProduct.getDate("DATE"));
                System.out.print(" | " + yearlyIncomesByProduct.getInt("PRODUCT_ID"));
                System.out.println(" | " + yearlyIncomesByProduct.getFloat("INCOME"));
                while (yearlyIncomesByProduct.next()) {
                    System.out.print(yearlyIncomesByProduct.getDate("DATE"));
                    System.out.print(" | " + yearlyIncomesByProduct.getInt("PRODUCT_ID"));
                    System.out.println(" | " + yearlyIncomesByProduct.getFloat("INCOME"));
                }
            }

            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getYearlyIncomesByProduct(int productId){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOME_BY_PRODUCT WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet yearlyIncomesByProduct_Filter = Statement.executeQuery();

            if(yearlyIncomesByProduct_Filter.next()) {

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productId);
                ResultSet productsDetails = Statement.executeQuery();

                if(productsDetails.next()) {
                    System.out.println("Product ID: " + productsDetails.getInt("PRODUCT_ID"));
                    System.out.println("Product Name: " + productsDetails.getString("PRODUCT_NAME"));
                    System.out.println("Product Purchasing Price: " + productsDetails.getFloat("PURCHASE_PRICE"));
                    System.out.println("Product Quantity: " + productsDetails.getFloat("SELLING_PRICE"));
                }else{
                    System.out.println("Product details not fount.");
                }
                System.out.print("\t" + yearlyIncomesByProduct_Filter.getDate("DATE"));
                System.out.println(" | " + yearlyIncomesByProduct_Filter.getFloat("INCOME"));
                while (yearlyIncomesByProduct_Filter.next()) {
                    System.out.print("\t" + yearlyIncomesByProduct_Filter.getDate("DATE"));
                    System.out.println(" | " + yearlyIncomesByProduct_Filter.getFloat("INCOME"));
                }
            }

            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
