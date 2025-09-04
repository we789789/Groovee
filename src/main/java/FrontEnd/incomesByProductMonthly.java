package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import BackEnd.MonthlyProfitByProduct;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static Controller.MonthlyProfitByProductController.getMonthlyIncomeByProductList;
import static Controller.MonthlyProfitByProductController.setMonthlyIncomeByProductList;
import static javafx.collections.FXCollections.observableArrayList;

public class incomesByProductMonthly{

    ArrayList<MonthlyProfitByProduct> products;
    {
        products = getMonthlyIncomeByProductList();
    }
    ObservableList<MonthlyProfitByProduct> monthlyProfitByProduct =  observableArrayList(products);


    public TableView<MonthlyProfitByProduct> tbl_monthlyProfit;
    public TableColumn<MonthlyProfitByProduct, Integer> col_monthlyProfitYear;
    public TableColumn<MonthlyProfitByProduct, Integer> col_monthlyProfitMonth;
    public TableColumn<MonthlyProfitByProduct, Integer> col_monthlyProfitProductID;
    public TableColumn<MonthlyProfitByProduct, String> col_monthlyProfitProductName;
    public TableColumn<MonthlyProfitByProduct, Float> col_monthlyProfitPurchasingPrice;
    public TableColumn<MonthlyProfitByProduct, Float> col_monthlyProfitSellingPrice;
    public TableColumn<MonthlyProfitByProduct, Float> col_monthlyProfit;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label lbl_monthlyProfit;

    @FXML
    private AnchorPane mainPanel;


    @FXML
    private TextField txt_searchField;

    @FXML
    void btnDaily(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/DailyProfitByProduct.fxml")));
            mainPanel.getChildren().setAll(addProductView);

            AnchorPane.setTopAnchor(addProductView, 0.0);
            AnchorPane.setBottomAnchor(addProductView, 0.0);
            AnchorPane.setLeftAnchor(addProductView, 0.0);
            AnchorPane.setRightAnchor(addProductView, 0.0);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), addProductView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void btnYearly(MouseEvent event) {

        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/YearlyProfitByProduct.fxml")));
            mainPanel.getChildren().setAll(addProductView);

            AnchorPane.setTopAnchor(addProductView, 0.0);
            AnchorPane.setBottomAnchor(addProductView, 0.0);
            AnchorPane.setLeftAnchor(addProductView, 0.0);
            AnchorPane.setRightAnchor(addProductView, 0.0);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), addProductView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btn_MainMenu(ActionEvent event) {

        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            mainPanel.getChildren().setAll(mainMenuView);

            AnchorPane.setTopAnchor(mainMenuView, 0.0);
            AnchorPane.setBottomAnchor(mainMenuView, 0.0);
            AnchorPane.setLeftAnchor(mainMenuView, 0.0);
            AnchorPane.setRightAnchor(mainMenuView, 0.0);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), mainMenuView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void search(KeyEvent event) {

        String searchField = txt_searchField.getText();
        if(searchField == null || searchField.isEmpty()) {
            refreshMonthlyIncomeByProduct();
        }else{
            refreshMonthlyIncomeByProduct(searchField);
        }
    }

    void visualizeMonthlyIncomeByProduct(){
        col_monthlyProfitYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_monthlyProfitMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_monthlyProfitProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        col_monthlyProfitProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_monthlyProfitPurchasingPrice.setCellValueFactory(new PropertyValueFactory<>("purchasingPrice"));
        col_monthlyProfitSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        col_monthlyProfit.setCellValueFactory(new PropertyValueFactory<>("monthlyProfit"));
    }

    void refreshMonthlyIncomeByProduct() {

        setMonthlyIncomeByProductList();
        monthlyProfitByProduct = observableArrayList(products);
        tbl_monthlyProfit.setItems(monthlyProfitByProduct);
        tbl_monthlyProfit.refresh();
        visualizeMonthlyIncomeByProduct();

    }

    void refreshMonthlyIncomeByProduct(String searchField) {

        setMonthlyIncomeByProductList(searchField);
        monthlyProfitByProduct = observableArrayList(products);
        tbl_monthlyProfit.setItems(monthlyProfitByProduct);
        tbl_monthlyProfit.refresh();
        visualizeMonthlyIncomeByProduct();

    }


        @FXML
        void initialize() {
            assert col_monthlyProfit != null : "fx:id=\"col_monthlyProfit\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert col_monthlyProfitMonth != null : "fx:id=\"col_monthlyProfitMonth\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert col_monthlyProfitProductID != null : "fx:id=\"col_monthlyProfitProductID\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert col_monthlyProfitProductName != null : "fx:id=\"col_monthlyProfitProductName\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert col_monthlyProfitPurchasingPrice != null : "fx:id=\"col_monthlyProfitPurchasingPrice\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert col_monthlyProfitSellingPrice != null : "fx:id=\"col_monthlyProfitSellingPrice\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert col_monthlyProfitYear != null : "fx:id=\"col_monthlyProfitYear\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert lbl_monthlyProfit != null : "fx:id=\"lbl_monthlyProfit\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert tbl_monthlyProfit != null : "fx:id=\"tbl_monthlyProfit\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";
            assert txt_searchField != null : "fx:id=\"txt_searchField\" was not injected: check your FXML file 'MonthlyProfitByProduct.fxml'.";

            refreshMonthlyIncomeByProduct();

        }

}
