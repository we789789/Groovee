/*package broowsky;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AddProduct addProduct = new AddProduct();
        SellProduct sellProduct = new SellProduct();
        GetIncomes getIncomes = new GetIncomes();

        String loop="";
        int process;

        do{
            System.out.println("Choose an option");
            System.out.println("\t1. Add Product");
            System.out.println("\t2. Sell Product");
            System.out.println("\t3. Show incomes");
            System.out.println("\t4. Exit");
            System.out.println();
            System.out.print("Option: ");

            process = scanner.nextInt();

            switch (process) {
                case 1:
                    addProduct.addProduct();
                    break;
                case 2:
                    sellProduct.sellProduct();
                    break;
                case 3:
                    getIncomes.getIncome();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
            }
            System.out.println("Do you want to go main menu[Y/N]: ");
            loop = scanner.next();
            loop = loop.toUpperCase();
        }while(loop.equals("Y"));
    }
}*/
