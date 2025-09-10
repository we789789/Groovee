package BackEnd;

import java.sql.*;
import java.util.Scanner;

public class GetIncomes {

    PreparedStatement Statement;

    String url = "jdbc:sqlite:data.sqlite";

    public void getDailyIncomes(){

        try{

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM DAILY_INCOMES");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM INCOME");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM INCOME WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet dailyIncomesByProduct_Filter = Statement.executeQuery();

            if(dailyIncomesByProduct_Filter.next()) {

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productId);
                ResultSet productsDetails = Statement.executeQuery();

                if(productsDetails.next()) {
                    System.out.println("broowsky.Product ID: " + productsDetails.getInt("PRODUCT_ID"));
                    System.out.println("broowsky.Product Name: " + productsDetails.getString("PRODUCT_NAME"));
                    System.out.println("broowsky.Product Purchasing Price: " + productsDetails.getFloat("PURCHASE_PRICE"));
                    System.out.println("broowsky.Product Quantity: " + productsDetails.getFloat("SELLING_PRICE"));
                }else{
                    System.out.println("broowsky.Product details not fount.");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOMES");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOME_BY_PRODUCT");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM MONTHLY_INCOME_BY_PRODUCT WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet monthlyIncomesByProduct_Filter = Statement.executeQuery();

            if(monthlyIncomesByProduct_Filter.next()) {

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productId);
                ResultSet productsDetails = Statement.executeQuery();

                if(productsDetails.next()) {
                    System.out.println("broowsky.Product ID: " + productsDetails.getInt("PRODUCT_ID"));
                    System.out.println("broowsky.Product Name: " + productsDetails.getString("PRODUCT_NAME"));
                    System.out.println("broowsky.Product Purchasing Price: " + productsDetails.getFloat("PURCHASE_PRICE"));
                    System.out.println("broowsky.Product Quantity: " + productsDetails.getFloat("SELLING_PRICE"));
                }else{
                    System.out.println("broowsky.Product details not fount.");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOMES");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOME_BY_PRODUCT");
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

            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.prepareStatement("SELECT * FROM YEARLY_INCOME_BY_PRODUCT WHERE PRODUCT_ID = ?");
            Statement.setInt(1, productId);
            ResultSet yearlyIncomesByProduct_Filter = Statement.executeQuery();

            if(yearlyIncomesByProduct_Filter.next()) {

                Statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                Statement.setInt(1, productId);
                ResultSet productsDetails = Statement.executeQuery();

                if(productsDetails.next()) {
                    System.out.println("broowsky.Product ID: " + productsDetails.getInt("PRODUCT_ID"));
                    System.out.println("broowsky.Product Name: " + productsDetails.getString("PRODUCT_NAME"));
                    System.out.println("broowsky.Product Purchasing Price: " + productsDetails.getFloat("PURCHASE_PRICE"));
                    System.out.println("broowsky.Product Quantity: " + productsDetails.getFloat("SELLING_PRICE"));
                }else{
                    System.out.println("broowsky.Product details not fount.");
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

    public void getIncome(){

        Scanner scanner = new Scanner(System.in);
        String loop;
        do {
            int option;
            int productId;

            System.out.println("Choose the type of income");
            System.out.println("\t1. Daily broowsky.Income");
            System.out.println("\t2. Monthly broowsky.Income");
            System.out.println("\t3. Yearly broowsky.Income");
            System.out.println("\t4. Daily broowsky.Income By broowsky.Product");
            System.out.println("\t5. Monthly broowsky.Income By broowsky.Product");
            System.out.println("\t6. Yearly broowsky.Income By broowsky.Product");

            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    getDailyIncomes();
                    break;
                case 2:
                    getMonthlyIncomes();
                    break;
                case 3:
                    getYearlyIncomes();
                    break;
                case 4:
                    System.out.println("Choose an option");
                    System.out.println("\t1. All products");
                    System.out.println("\t2. One product");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            getDailyIncomesByProduct();
                            break;
                        case 2:
                            System.out.print("Enter product ID: ");
                            productId = scanner.nextInt();
                            getDailyIncomesByProduct(productId);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 5:
                    System.out.println("Choose an option");
                    System.out.println("\t1. All products");
                    System.out.println("\t2. One product");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            getMonthlyIncomesByProduct();
                            break;
                        case 2:
                            System.out.print("Enter product ID: ");
                            productId = scanner.nextInt();
                            getMonthlyIncomesByProduct(productId);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 6:
                    System.out.println("Choose an option");
                    System.out.println("\t1. All products");
                    System.out.println("\t2. One product");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            getYearlyIncomesByProduct();
                            break;
                        case 2:
                            System.out.print("Enter product ID: ");
                            productId = scanner.nextInt();
                            getYearlyIncomesByProduct(productId);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

            System.out.print("Do you want to get another income [Y/N] ? ");

            loop = scanner.next();
            loop = loop.toUpperCase();

        }while(loop.equals("Y"));

    }
}
