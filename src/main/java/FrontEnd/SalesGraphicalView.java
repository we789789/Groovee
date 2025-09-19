package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BackEnd.DailySales;
import BackEnd.MonthlySales;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static Controller.SalesGraphicalViewController.getDailySales;
import static Controller.SalesGraphicalViewController.getMonthlySales;

public class SalesGraphicalView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_status;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private ChoiceBox<String> month;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private LineChart<Integer, Float> lineChart;

    @FXML
    void clear(MouseEvent event) {

    }

    @FXML
    void show(MouseEvent event) {
        setLineChartData();
    }
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    ArrayList<DailySales> dailySaleList = new ArrayList<>();

    private static Integer[] setYears(){

        Integer[] yearList = new Integer[50];
        int Year = 2010;

        for (int i = 0; i < 50; i++) {

            yearList[i] = Year;
            Year++;
        }
        return yearList;
    }

    private void setLineChartData(){

        lineChart.getData().clear();
        dailySaleList.clear();
        lbl_status.setText("");

        if(month.getValue().isEmpty() || year.getValue() == null){
            lbl_status.setText("Month or Year is empty");
            return;
        }

        dailySaleList = getDailySales(year.getValue(), month.getValue());
        if(dailySaleList.isEmpty()){
            lbl_status.setText("No daily sales found");
        }
        XYChart.Series<Integer, Float> series1 = new XYChart.Series<>();

        for(int i = 0; i < dailySaleList.size(); i++){
            series1.getData().add(new XYChart.Data<>(dailySaleList.get(i).getDay(), dailySaleList.get(i).getIncome()));
        }

        lineChart.getData().add(series1);
    }

    @FXML
    void initialize() {

        assert lbl_status != null : "fx:id=\"lbl_status\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lineChart != null : "fx:id=\"lineChart\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert month != null : "fx:id=\"month\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";

        month.getItems().addAll(months);
        year.getItems().addAll(setYears());

        lineChart.getXAxis().setLabel("Month");
        lineChart.getYAxis().setLabel("Income");

    }

}
