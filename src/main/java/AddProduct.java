import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class AddProduct {
    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement Statement;

        String url = "jdbc:mysql://localhost:3306/salesmanagementsystem";
        String user = "root";
        String password = "HBdeLA@2004";

        String loop = "";
        Product product = new Product();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();


            //  -----------Add PRODUCTS TO DATABASE----------- //


            do {
                product.addProduct();

                try {
                    Statement = connection.prepareStatement("INSERT INTO PRODUCTS VALUES(?,?,?,?)");

                    Statement.setInt(1, product.getProductId());
                    Statement.setString(2, product.getProductName());
                    Statement.setFloat(3, product.getPurchasePrice());
                    Statement.setFloat(4, product.getSellingPrice());
                    Statement.executeUpdate();

                    System.out.println("Inserted product successfully");

                } catch (Exception e) {
                    System.out.println(e);
                }


                // -----SET QUANTITY----- //


                Statement = connection.prepareStatement("INSERT INTO STOCK VALUES (?,?)");
                Statement.setInt(1, product.getProductId());
                Statement.setInt(2, product.getQuantity());
                Statement.executeUpdate();

                System.out.println("Do you want to continue? [Y/N]");
                loop = scanner.nextLine();
                loop = loop.toUpperCase();


            } while (loop.equals("Y"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
