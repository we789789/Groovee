package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static Controller.SalesController.*;
import static javafx.collections.FXCollections.observableArrayList;

public class Sales {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Sales, String> col_date;

    @FXML
    private TableColumn<Sales, Integer> col_invoiceNo;

    @FXML
    private TableColumn<Sales, Integer> col_productID;

    @FXML
    private TableColumn<Sales, String> col_productName;

    @FXML
    private TableColumn<Sales, Integer> col_quantity;

    @FXML
    private TableColumn<Sales, Float> col_total;

    @FXML
    private Label lbl_date;

    @FXML
    private Label lbl_invoiceNo;

    @FXML
    private Label lbl_quantity;

    @FXML
    private Label lbl_time;

    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_status;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TableView<BackEnd.Sales> tbl_salesData;

    @FXML
    private TextField txt_invoiceNo;

    @FXML
    void clear(MouseEvent event) {
        refreshTable();
        txt_invoiceNo.clear();
        lbl_invoiceNo.setText("");
        lbl_quantity.setText("");
        lbl_date.setText("");
        lbl_time.setText("");
        lbl_total.setText("");
        lbl_status.setText("");
    }

    @FXML
    void filter(MouseEvent event) {
        if(txt_invoiceNo.getText() == null || txt_invoiceNo.getText().isEmpty()|| Integer.parseInt(txt_invoiceNo.getText()) <= 0) {
            lbl_status.setText("Please Enter Valid Invoice No");
        }else {
            lbl_status.setText("");
            int salesID = Integer.parseInt(txt_invoiceNo.getText());
            refreshTable(salesID);
            lbl_invoiceNo.setText(String.valueOf(salesData.getFirst().getSalesID()));
            lbl_date.setText(salesData.getFirst().getDate());
            lbl_time.setText(salesData.getFirst().getTime());
            lbl_quantity.setText(String.valueOf(getCount()));
            lbl_total.setText(String.valueOf(getTotal()));
        }
    }

    ArrayList<BackEnd.Sales> salesData = new ArrayList<>();
    {
        salesData = getSales();
    }
    ObservableList<BackEnd.Sales> productList = observableArrayList(salesData);

    private void visualizeTable(){
        col_invoiceNo.setCellValueFactory(new PropertyValueFactory<>("salesID"));
        col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void refreshTable(){
        setSales();
        productList = observableArrayList(salesData);
        tbl_salesData.setItems(productList);
        tbl_salesData.refresh();
        visualizeTable();

    }

    private void refreshTable(int salesID){
        setSales(salesID);
        productList = observableArrayList(salesData);
        tbl_salesData.setItems(productList);
        tbl_salesData.refresh();
        visualizeTable();

    }
    @FXML
    public void Back(MouseEvent mouseEvent) {
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
        assert col_date != null : "fx:id=\"col_date\" was not injected: check your FXML file 'Sales.fxml'.";
        assert col_invoiceNo != null : "fx:id=\"col_invoiceNo\" was not injected: check your FXML file 'Sales.fxml'.";
        assert col_productID != null : "fx:id=\"col_productID\" was not injected: check your FXML file 'Sales.fxml'.";
        assert col_productName != null : "fx:id=\"col_productName\" was not injected: check your FXML file 'Sales.fxml'.";
        assert col_quantity != null : "fx:id=\"col_quantity\" was not injected: check your FXML file 'Sales.fxml'.";
        assert col_total != null : "fx:id=\"col_total\" was not injected: check your FXML file 'Sales.fxml'.";
        assert lbl_date != null : "fx:id=\"lbl_date\" was not injected: check your FXML file 'Sales.fxml'.";
        assert lbl_invoiceNo != null : "fx:id=\"lbl_invoiceNo\" was not injected: check your FXML file 'Sales.fxml'.";
        assert lbl_quantity != null : "fx:id=\"lbl_quantity\" was not injected: check your FXML file 'Sales.fxml'.";
        assert lbl_time != null : "fx:id=\"lbl_time\" was not injected: check your FXML file 'Sales.fxml'.";
        assert lbl_total != null : "fx:id=\"lbl_total\" was not injected: check your FXML file 'Sales.fxml'.";
        assert lbl_status != null : "fx:id=\"lbl_status\" was not injected: check your FXML file 'Sales.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'Sales.fxml'.";
        assert tbl_salesData != null : "fx:id=\"tbl_salesData\" was not injected: check your FXML file 'Sales.fxml'.";
        assert txt_invoiceNo != null : "fx:id=\"txt_invoiceNo\" was not injected: check your FXML file 'Sales.fxml'.";

        refreshTable();

    }
}

