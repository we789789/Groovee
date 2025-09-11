package FrontEnd;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
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
import BackEnd.dailyProfit;
import javafx.util.Duration;

import static Controller.dailyProfitController.getDailyProfitsList;
import static Controller.dailyProfitController.setDailyProfitsList;
import static Controller.dailyProfitController.getTotalProfit;
import static javafx.collections.FXCollections.observableArrayList;

public class dailyProfits {

    public Label lbl_message;
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

        lbl_dailyTotalProfit.setText(String.valueOf(getTotalProfit()));
    }
    private void refreshTableData(LocalDate d1, LocalDate d2){
        setDailyProfitsList(d1, d2);
        dailyProfitObservableList = observableArrayList(dailyProfitList);
        tbl_dailyProfit.setItems(dailyProfitObservableList);
        tbl_dailyProfit.refresh();
        visualizeTableData();

        lbl_dailyTotalProfit.setText(String.valueOf(getTotalProfit()));
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
        assert tablePanel != null : "fx:id=\"tablePanel\" was not injected: check your FXML file 'dailyIncome.fxml'.";
        assert tbl_dailyProfit != null : "fx:id=\"tbl_dailyProfit\" was not injected: check your FXML file 'dailyIncome.fxml'.";

        FadeTransition fade = new FadeTransition(Duration.millis(800), tablePanel);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        refreshTableData();

    }


}

