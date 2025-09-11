package FrontEnd;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import BackEnd.MonthlyProfit;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static javafx.collections.FXCollections.observableArrayList;
import static Controller.MonthlyProfitController.setMonthlyProfitsList;
import static Controller.MonthlyProfitController.getMonthlyProfitsList;
import static Controller.MonthlyProfitController.getTotalProfit;

public class MonthlyProfits {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    TableView<MonthlyProfit> tbl_monthlyProfit;
    @FXML
    TableColumn<MonthlyProfits, Integer> col_year;
    @FXML
    TableColumn<MonthlyProfits, String> col_month;
    @FXML
    TableColumn<MonthlyProfits, Float> col_profit;

    ArrayList<MonthlyProfit> monthlyProfitList;
    {
        monthlyProfitList = getMonthlyProfitsList();
    }
    ObservableList<MonthlyProfit> dailyProfitObservableList = observableArrayList(monthlyProfitList);

    private void visualizeTableData(){
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("monthName"));
        col_profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    private void refreshTableData(){
        setMonthlyProfitsList();
        dailyProfitObservableList = observableArrayList(monthlyProfitList);
        tbl_monthlyProfit.setItems(dailyProfitObservableList);
        tbl_monthlyProfit.refresh();

        visualizeTableData();
        lbl_monthlyTotalProfit.setText(String.valueOf(getTotalProfit()));
    }

    private void refreshTableData(LocalDate d1, LocalDate d2){
        setMonthlyProfitsList(d1,d2);
        dailyProfitObservableList = observableArrayList(monthlyProfitList);
        tbl_monthlyProfit.setItems(dailyProfitObservableList);
        tbl_monthlyProfit.refresh();

        visualizeTableData();
        lbl_monthlyTotalProfit.setText(String.valueOf(getTotalProfit()));
    }


    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private AnchorPane filterPanel;

    @FXML
    private Label lbl_monthlyTotalProfit;

    @FXML
    private Label lbl_message;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private AnchorPane tablePanel;


    @FXML
    void btnMonthly(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MonthlyProfit.fxml")));
            mainPanel.getChildren().setAll(addProductView);

            AnchorPane.setTopAnchor(addProductView, 0.0);
            AnchorPane.setBottomAnchor(addProductView, 0.0);
            AnchorPane.setLeftAnchor(addProductView, 0.0);
            AnchorPane.setRightAnchor(addProductView, 0.0);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnYearly(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/YearlyProfit.fxml")));
            mainPanel.getChildren().setAll(addProductView);

            AnchorPane.setTopAnchor(addProductView, 0.0);
            AnchorPane.setBottomAnchor(addProductView, 0.0);
            AnchorPane.setLeftAnchor(addProductView, 0.0);
            AnchorPane.setRightAnchor(addProductView, 0.0);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btn_clear(MouseEvent event) {
        lbl_message.setText("");
        date1.setValue(null);
        date2.setValue(null);
        refreshTableData();
    }

    @FXML
    void btn_daily(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/dailyIncome.fxml")));
            mainPanel.getChildren().setAll(addProductView);

            AnchorPane.setTopAnchor(addProductView, 0.0);
            AnchorPane.setBottomAnchor(addProductView, 0.0);
            AnchorPane.setLeftAnchor(addProductView, 0.0);
            AnchorPane.setRightAnchor(addProductView, 0.0);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btn_filter(MouseEvent event) {
        LocalDate d1 = date1.getValue();
        LocalDate d2 = date2.getValue();

        if(d1 == null || d2 == null){
            lbl_message.setText("Please select both start and end dates.");
        }
        else{
            if(d1.isBefore(d2)){
                lbl_message.setText("");
                refreshTableData(d1,d2);
            }else{
                lbl_message.setText("Please select dates correctly.");
            }
        }
    }
    public void back(MouseEvent mouseEvent) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Overview.fxml")));
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
    void initialize() {
        assert col_profit != null : "fx:id=\"col_dailyProfit\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert col_month != null : "fx:id=\"col_month\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert col_year != null : "fx:id=\"col_year\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert date1 != null : "fx:id=\"date1\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert date2 != null : "fx:id=\"date2\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert filterPanel != null : "fx:id=\"filterPanel\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert lbl_monthlyTotalProfit != null : "fx:id=\"lbl_dailyTotalProfit\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert lbl_message != null : "fx:id=\"lbl_message\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert tablePanel != null : "fx:id=\"tablePanel\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";
        assert tbl_monthlyProfit != null : "fx:id=\"tbl_mothlyProfit\" was not injected: check your FXML file 'MonthlyProfit.fxml'.";

        refreshTableData();

        FadeTransition fade = new FadeTransition(Duration.millis(800), tablePanel);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

    }


}

