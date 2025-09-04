package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import BackEnd.YearlyProfitByProduct;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
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

import static Controller.YearlyProfitByProductController.getYearlyIncomeByProductList;
import static Controller.YearlyProfitByProductController.setYearlyIncomeByProductList;
import static javafx.collections.FXCollections.observableArrayList;

public class IncomesByProductYearly {

    ArrayList<YearlyProfitByProduct> yearlyIncomeByProduct;
    {
        yearlyIncomeByProduct = getYearlyIncomeByProductList();
    }
    ObservableList<YearlyProfitByProduct> productList = observableArrayList(yearlyIncomeByProduct);

    public TableView<YearlyProfitByProduct> tbl_yearlyProfit;
    public TableColumn<YearlyProfitByProduct, Integer> col_yearlyProfitYear;
    public TableColumn<YearlyProfitByProduct, Integer> col_yearlyProfitProductID;
    public TableColumn<YearlyProfitByProduct, String> col_yearlyProfitProductName;
    public TableColumn<YearlyProfitByProduct, Float> col_yearlyProfitPurchasingPrice;
    public TableColumn<YearlyProfitByProduct, Float> col_yearlyProfitSellingPrice;
    public TableColumn<YearlyProfitByProduct, Float> col_yearlyProfit;

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
    void btnMonthly(MouseEvent event) {

        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MonthlyProfitByProduct.fxml")));
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
    void btn_MainMenu(ActionEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MainForm.fxml")));
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
    void search(KeyEvent event) {
        String searchField = txt_searchField.getText();
        if(searchField == null || searchField.isEmpty()) {
            refreshYearlyProfitTable();
        }else{
            refreshYearlyProfitTable(searchField);
        }
    }

    private void visualizerYearlyProfitTable(){
        col_yearlyProfitYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_yearlyProfitProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        col_yearlyProfitProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_yearlyProfitPurchasingPrice.setCellValueFactory(new PropertyValueFactory<>("purchasingPrice"));
        col_yearlyProfitSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        col_yearlyProfit.setCellValueFactory(new PropertyValueFactory<>("yearlyProfit"));
    }
    private void refreshYearlyProfitTable(){

        setYearlyIncomeByProductList();
        productList = observableArrayList(yearlyIncomeByProduct);
        tbl_yearlyProfit.setItems(productList);
        tbl_yearlyProfit.refresh();
        visualizerYearlyProfitTable();
    }
    private void refreshYearlyProfitTable(String searchField){

        setYearlyIncomeByProductList(searchField);
        productList = observableArrayList(yearlyIncomeByProduct);
        tbl_yearlyProfit.setItems(productList);
        tbl_yearlyProfit.refresh();
        visualizerYearlyProfitTable();
    }

    @FXML
    void initialize() {
        assert col_yearlyProfit != null : "fx:id=\"col_yearlyProfit\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert col_yearlyProfitProductID != null : "fx:id=\"col_yearlyProfitProductID\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert col_yearlyProfitProductName != null : "fx:id=\"col_yearlyProfitProductName\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert col_yearlyProfitPurchasingPrice != null : "fx:id=\"col_yearlyProfitPurchasingPrice\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert col_yearlyProfitSellingPrice != null : "fx:id=\"col_yearlyProfitSellingPrice\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert col_yearlyProfitYear != null : "fx:id=\"col_yearlyProfitYear\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert lbl_monthlyProfit != null : "fx:id=\"lbl_monthlyProfit\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert tbl_yearlyProfit != null : "fx:id=\"tbl_yearlyProfit\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";
        assert txt_searchField != null : "fx:id=\"txt_searchField\" was not injected: check your FXML file 'YearlyProfitByProduct.fxml'.";

        refreshYearlyProfitTable();

    }

}

