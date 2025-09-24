package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BackEnd.DailySales;
import BackEnd.MonthlySales;
import BackEnd.YearlySales;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static Controller.SalesGraphicalViewController.*;
import static java.lang.Float.parseFloat;

public class SalesGraphicalView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> MonthlyYear;

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_statusMonthly;

    @FXML
    private Label lbl_statusYearly;

    @FXML
    private LineChart<String, Float> lineChart;

    @FXML
    private LineChart<String, Float> lineChartMonthly;

    @FXML
    private LineChart<String, Float> lineChartYearly;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private ChoiceBox<String> month;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private ChoiceBox<Integer> yearlyYearFrom;

    @FXML
    private ChoiceBox<Integer> yearlyYearTo;

    @FXML
    void clear(MouseEvent event) {

    }
    @FXML
    public void yearlyShow(MouseEvent mouseEvent) {
        lineChartYearly.getData().clear();
        lbl_statusYearly.setText("");
        yearlySaleList.clear();

        if(yearlyYearFrom.getValue()== null || yearlyYearTo.getValue()==null) {
            lbl_statusYearly.setText("Please Select Both Start And End Year");
            return;
        }
        if(yearlyYearFrom.getValue() > yearlyYearTo.getValue()){
            lbl_statusYearly.setText("Please Select Years Correctly");
            return;
        }
        yearlySaleList = getYearlySales(yearlyYearFrom.getValue(), yearlyYearTo.getValue());
        if(yearlySaleList.isEmpty()) {
            lbl_statusYearly.setText("No Sales found");
            lineChartYearly.getData().clear();
            return;
        }
        setLineChartYearlyData();
    }

    @FXML
    public void monthlyShow(MouseEvent mouseEvent) {

        lineChartMonthly.getData().clear();
        monthlySaleList.clear();
        lbl_statusMonthly.setText("");

        if(MonthlyYear.getValue() == null){
            lineChartMonthly.getData().clear();
            lbl_statusMonthly.setText("Year is empty");
            return;
        }

        monthlySaleList = getMonthlySales(MonthlyYear.getValue());
        if(monthlySaleList.isEmpty()){
            lineChartMonthly.getData().clear();
            lbl_statusMonthly.setText("No Sales found");
            return;
        }

        setLineChartMonthlyData();
    }

    @FXML
    void show(MouseEvent event) {

        lineChart.getData().clear();
        dailySaleList.clear();
        lbl_status.setText("");

        if(month.getValue().isEmpty() || month.getValue().equals("") || year.getValue() == null){
            lineChart.getData().clear();
            lbl_status.setText("Month or Year is empty");
            return;
        }

        dailySaleList = getDailySales(year.getValue(), month.getValue());
        if(dailySaleList.isEmpty()){
            lineChart.getData().clear();
            lbl_status.setText("No daily sales found");
            return;
        }

        setLineChartData();
    }

    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    ArrayList<DailySales> dailySaleList = new ArrayList<>();
    ArrayList<MonthlySales> monthlySaleList = new ArrayList<>();
    ArrayList<YearlySales> yearlySaleList = new ArrayList<>();

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

        XYChart.Series<String, Float> series1 = new XYChart.Series<>();
        int endDate;
        if(month.getValue().equals(months[1]) ||
                month.getValue().equals(months[3]) ||
                month.getValue().equals(months[5]) ||
                month.getValue().equals(months[7]) ||
                month.getValue().equals(months[8]) ||
                month.getValue().equals(months[10]) ||
                month.getValue().equals(months[12])){
            endDate = 31;
        }else if(month.getValue().equals(months[2]) && year.getValue()%4 != 0){
            endDate = 28;
        }else if(month.getValue().equals(months[2]) && year.getValue()%4 == 0){
            endDate = 29;
        }else{
            endDate = 30;
        }

        for(int i = 1; i <= endDate; i++) {
            boolean found = false;
            for (int j = 0; j < dailySaleList.size(); j++) {
                if (dailySaleList.get(j).getDay() == i) {
                    series1.getData().add(new XYChart.Data<>(String.valueOf(i), dailySaleList.get(j).getIncome()));
                    found = true;
                    break;
                }
            }
            if(!found){
                series1.getData().add(new XYChart.Data<>(String.valueOf(i), parseFloat("0")));
            }
        }

        lineChart.getData().add(series1);
    }
    private void setLineChartMonthlyData(){

        XYChart.Series<String, Float> series1 = new XYChart.Series<>();
        for(int i=0; i<12; i++){
            boolean found = false;

            for (int j = 0; j < monthlySaleList.size(); j++) {
                if (months[i].equals(monthlySaleList.get(j).getMonth())) {
                    series1.getData().add(new XYChart.Data<>(monthlySaleList.get(j).getMonth(), monthlySaleList.get(j).getIncome()));
                    found = true;
                    break;
                }
            }
            if(!found){
                series1.getData().add(new XYChart.Data<>(months[i], parseFloat("0")));
            }
        }

        lineChartMonthly.getData().add(series1);

    }

    private void setLineChartYearlyData(){
        XYChart.Series<String, Float> income = new XYChart.Series<>();
        int year1 = yearlyYearFrom.getValue();
        int year2 = yearlyYearTo.getValue();

        for(int i=year1; i<=year2; i++){
            boolean found = false;
            for(int j = 0; j < yearlySaleList.size(); j++){
                if(yearlySaleList.get(j).getYear() == i){
                    income.getData().add(new XYChart.Data<>(String.valueOf(i), yearlySaleList.get(j).getIncome()));
                    found = true;
                    break;
                }
            }if(!found){
                income.getData().add(new XYChart.Data<>(String.valueOf(i), parseFloat("0")));
            }
        }
        lineChartYearly.getData().add(income);

    }

    @FXML
    void initialize() {

        assert MonthlyYear != null : "fx:id=\"MonthlyYear\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lbl_status != null : "fx:id=\"lbl_status\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lbl_statusMonthly != null : "fx:id=\"lbl_statusMonthly\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lbl_statusYearly != null : "fx:id=\"lbl_statusYearly\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lineChart != null : "fx:id=\"lineChart\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lineChartMonthly != null : "fx:id=\"lineChartMonthly\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert lineChartYearly != null : "fx:id=\"lineChartYearly\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert month != null : "fx:id=\"month\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert yearlyYearFrom != null : "fx:id=\"yearlyYearFrom\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert yearlyYearTo != null : "fx:id=\"yearlyYearTo\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";

        month.getItems().addAll(months);
        year.getItems().addAll(setYears());
        MonthlyYear.getItems().addAll(setYears());
        yearlyYearFrom.getItems().addAll(setYears());
        yearlyYearTo.getItems().addAll(setYears());

    }

}
