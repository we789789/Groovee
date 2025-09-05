package FrontEnd;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import BackEnd.dailyProfit;

import static Controller.dailyProfitController.getDailyProfitsList;
import static Controller.dailyProfitController.setDailyProfitsList;
import static javafx.collections.FXCollections.observableArrayList;

public class dailyProfits {

    ArrayList<dailyProfit> dailyProfitList;
    {
        dailyProfitList = getDailyProfitsList();
    }
    ObservableList<dailyProfit> dailyProfitObservableList = observableArrayList(dailyProfitList);


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public TableView<dailyProfit> tbl_dailyProfit;
    public TableColumn<dailyProfit,Float> col_dailyProfit;
    public TableColumn<dailyProfit, LocalDate> col_dailyProfitDate;


    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private AnchorPane filterPanel;

    @FXML
    private Label lbl_dailyTotalProfit;

    @FXML
    private AnchorPane mainPanel;


    @FXML
    void btnMonthly(MouseEvent event) {

    }

    @FXML
    void btnYearly(MouseEvent event) {

    }

    @FXML
    void btn_clear(MouseEvent event) {

    }

    @FXML
    void btn_filter(MouseEvent event) {

    }

    private void visualizeTableData(){

        col_dailyProfit.setCellValueFactory(new PropertyValueFactory<>("dailyProfit"));
        col_dailyProfitDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    private void refreshTableData(){
        setDailyProfitsList();
        dailyProfitObservableList = observableArrayList(dailyProfitList);
        tbl_dailyProfit.setItems(dailyProfitObservableList);
        tbl_dailyProfit.refresh();
        visualizeTableData();
    }

    @FXML
    void initialize() {
        assert col_dailyProfit != null : "fx:id=\"col_dailyProfit\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert col_dailyProfitDate != null : "fx:id=\"col_dailyProfitDate\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert date1 != null : "fx:id=\"date1\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert date2 != null : "fx:id=\"date2\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert filterPanel != null : "fx:id=\"filterPanel\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert lbl_dailyTotalProfit != null : "fx:id=\"lbl_dailyTotalProfit\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert tbl_dailyProfit != null : "fx:id=\"tbl_dailyProfit\" was not injected: check your FXML file 'dailyIncome.fxml'.";

        refreshTableData();

    }

}

