package FrontEnd;

import BackEnd.DailyProfitByProduct;
import BackEnd.Product;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static Controller.DailyProfitByProductController.getDailyIncomeByProduct;
import static Controller.DailyProfitByProductController.setDailyIncomeByProduct;
import static Controller.DailyProfitByProductController.getTotalDailyIncome;
import static javafx.collections.FXCollections.observableArrayList;


public class incomesByProductDaily {

    public Label lbl_dailyTotalProfit;

    ArrayList<DailyProfitByProduct>  products;
    {
        products = getDailyIncomeByProduct();
    }
    ObservableList<DailyProfitByProduct> DailyProfitByProduct = observableArrayList(products);

    public TableView<DailyProfitByProduct> tbl_dailyProfit;
    public TableColumn<DailyProfitByProduct, LocalDate> col_dailyProfitDate;
    public TableColumn<DailyProfitByProduct, Integer> col_dailyProfitProductID;
    public TableColumn<DailyProfitByProduct, String> col_dailyProfitProductName;
    public TableColumn<DailyProfitByProduct, Float> col_dailyProfitPurchasingPrice;
    public TableColumn<DailyProfitByProduct, Float> col_dailyProfitSellingPrice;
    public TableColumn<DailyProfitByProduct, Float> col_dailyProfit;


    @FXML
    private AnchorPane mainPanel;



    @FXML
    private TextField txt_searchField;


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
            System.out.println(e.getMessage());
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
            Parent mainMenuView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MainForm.fxml")));
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
    void btn_search(ActionEvent event) {
        String searchField = txt_searchField.getText();
        if(searchField == null || searchField.isEmpty()) {
            refreshDailyIncomeByProduct();
        }else{
            refreshDailyIncomeByProduct(searchField);
        }

    }

    @FXML
    void search(KeyEvent event) {

            String searchField = txt_searchField.getText();
            if(searchField == null || searchField.isEmpty()) {
                refreshDailyIncomeByProduct();
            }else{
                refreshDailyIncomeByProduct(searchField);
            }

    }

    private void visualizeDailyIncomeByProduct(){
        col_dailyProfitDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_dailyProfitProductID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        col_dailyProfitProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_dailyProfitPurchasingPrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        col_dailyProfitSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        col_dailyProfit.setCellValueFactory(new PropertyValueFactory<>("income"));

        lbl_dailyTotalProfit.setText(String.valueOf(getTotalDailyIncome()));
    }

    private void refreshDailyIncomeByProduct() {

        setDailyIncomeByProduct();
        DailyProfitByProduct = observableArrayList(products);
        tbl_dailyProfit.setItems(DailyProfitByProduct);
        tbl_dailyProfit.refresh();
        visualizeDailyIncomeByProduct();
    }
    private void refreshDailyIncomeByProduct(String searchField) {

        setDailyIncomeByProduct(searchField);
        DailyProfitByProduct = observableArrayList(products);
        tbl_dailyProfit.setItems(DailyProfitByProduct);
        tbl_dailyProfit.refresh();
        visualizeDailyIncomeByProduct();
    }

    @FXML
    void initialize() {

        assert col_dailyProfitDate != null : "fx:id=\"col_dailyProfitDate\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert col_dailyProfitProductID != null : "fx:id=\"col_dailyProfitProductID\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert col_dailyProfitProductName != null : "fx:id=\"col_dailyProfitProductName\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert col_dailyProfitPurchasingPrice != null : "fx:id=\"col_dailyProfitPurchasingPrice\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert col_dailyProfit != null : "fx:id=\"col_dailyProfitQuantity\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert col_dailyProfitSellingPrice != null : "fx:id=\"col_dailyProfitSellingPrice\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert lbl_dailyTotalProfit != null : "fx:id=\"lbl_dailyTotalProfit\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert tbl_dailyProfit != null : "fx:id=\"tbl_dailyProfit\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";
        assert txt_searchField != null : "fx:id=\"txt_searchField\" was not injected: check your FXML file 'DailyProfitByProduct.fxml'.";



        refreshDailyIncomeByProduct();

    }

}

