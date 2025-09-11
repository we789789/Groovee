package FrontEnd;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import BackEnd.YearlyProfit;
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

import static Controller.YearlyProfitController.getYearlyProfitsList;
import static Controller.YearlyProfitController.setYearlyProfitsList;
import static Controller.YearlyProfitController.getTotalProfit;
import static javafx.collections.FXCollections.observableArrayList;

public class YearlyProfits {

    @FXML
    TableView<YearlyProfit> tbl_yearlyProfit;
    @FXML
    TableColumn<YearlyProfits, Integer> col_year;

    @FXML
    TableColumn<YearlyProfits, Float> col_profit;

    ArrayList<YearlyProfit> yearlyProfitList;
    {
        yearlyProfitList = getYearlyProfitsList();
    }
    ObservableList<YearlyProfit> yearlyProfitObservableList = observableArrayList(yearlyProfitList);

    private void visualizeTableData(){
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }
    private void refreshTableData(){
        setYearlyProfitsList();
        yearlyProfitObservableList = observableArrayList(yearlyProfitList);
        tbl_yearlyProfit.setItems(yearlyProfitObservableList);
        tbl_yearlyProfit.refresh();

        visualizeTableData();
        lbl_yearlyTotalProfit.setText(String.valueOf(getTotalProfit()));
    }
    private void refreshTableData(LocalDate d1, LocalDate d2){
        setYearlyProfitsList(d1, d2);
        yearlyProfitObservableList = observableArrayList(yearlyProfitList);
        tbl_yearlyProfit.setItems(yearlyProfitObservableList);
        tbl_yearlyProfit.refresh();

        visualizeTableData();
        lbl_yearlyTotalProfit.setText(String.valueOf(getTotalProfit()));
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private AnchorPane filterPanel;

    @FXML
    private Label lbl_yearlyTotalProfit;

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
        assert col_profit != null : "fx:id=\"col_profit\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert col_year != null : "fx:id=\"col_year\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert date1 != null : "fx:id=\"date1\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert date2 != null : "fx:id=\"date2\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert filterPanel != null : "fx:id=\"filterPanel\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert lbl_yearlyTotalProfit != null : "fx:id=\"lbl_dailyTotalProfit\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert lbl_message != null : "fx:id=\"lbl_message\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert tablePanel != null : "fx:id=\"tablePanel\" was not injected: check your FXML file 'YearlyProfit.fxml'.";
        assert tbl_yearlyProfit != null : "fx:id=\"tbl_yearlyProfit\" was not injected: check your FXML file 'YearlyProfit.fxml'.";

        FadeTransition fade = new FadeTransition(Duration.millis(800), tablePanel);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        refreshTableData();

    }


}

