package FrontEnd;

import BackEnd.Product;
import BackEnd.Sale;
import Controller.ProductDetailsController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controller.ProductDetailsController.setProductList;
import static Controller.SellProductController.*;
import static impl.org.controlsfx.ImplUtils.getChildren;
import static javafx.collections.FXCollections.observableArrayList;

public class SellProduct {

    public Label lbl_quantityStatus;
    ArrayList<Sale> tableData;
    {
        tableData = getSaleList();
    }
    ObservableList<Sale> saleList = observableArrayList(tableData);

    String name;
    int productID;
    int quantity;
    int invoiceNo = getNewSaleID();

    ArrayList<Product> products;

    {
        products = ProductDetailsController.getProductList();
    }

    ObservableList<Product> productData = observableArrayList(products);


    @FXML
    private TableView<Sale> tbl_bill;
    @FXML
    private TableColumn<Sale, String> col_name;
    @FXML
    private TableColumn<Sale, Float> col_price;
    @FXML
    private TableColumn<Sale, Integer> col_quantity;
    @FXML
    private TableColumn<Sale, Float> col_rate;

    @FXML
    TableView<Product> productNames;
    @FXML
    private TableColumn<Product, String> col_productName ;
    @FXML
    private TableColumn<Product, Integer> col_productID;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_invoiceNo;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_quantity;

    @FXML
    private Label lbl_subTotal;

    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_invoiceNo2;

    @FXML
    private Label lbl_name2;


    @FXML
    private Label lbl_nameQuantityStatus2;


    @FXML
    private Label lbl_quantity2;

    @FXML
    private Label lbl_total2;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TabPane tabPanel;

    @FXML
    private Tab tab_name;

    @FXML
    private Tab tab_productID;

    @FXML
    private AnchorPane tablePanel;

    @FXML
    private TextField txt_productID;

    @FXML
    private TextField txt_quantity;

    @FXML
    private TextField txt_quantity2;

    @FXML
    private TextField txt_name2;


    @FXML
    public void btn_productIDAdd(MouseEvent keyEvent) {
        if (Integer.parseInt(txt_quantity.getText()) <= availableStock(Integer.parseInt(txt_productID.getText()))) {
            productID = Integer.parseInt(txt_productID.getText());
            quantity = Integer.parseInt(txt_quantity.getText());

            addSale(invoiceNo, productID, quantity);
            setQuantity(productID, quantity);
            refreshTableData();
            txt_productID.clear();
            txt_quantity.clear();
            txt_quantity2.clear();
            txt_name2.clear();

            lbl_total.setText("");
            lbl_name.setText("");
            lbl_quantity.setText("");
            lbl_total2.setText("");
            lbl_name2.setText("");
            lbl_quantity2.setText("");

            lbl_subTotal.setText(String.valueOf(getSubTotal()));
        }

    }

    @FXML
    void btn_newSale(MouseEvent event) {
        int out = doneSale();
        txt_productID.clear();
        txt_quantity.clear();
        txt_name2.clear();
        lbl_total.setText("");
        lbl_total2.setText("");
        lbl_subTotal.setText("");
        lbl_invoiceNo.setText(String.valueOf(getNewSaleID()));
        clear();

        refreshTableData();
        if(out==0) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/failPopup.fxml"));
                Parent popupRoot = fxmlLoader.load();

                Stage popupStage = new Stage();
                popupStage.setTitle("Add Item");
                popupStage.setScene(new Scene(popupRoot));
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/successPopup.fxml"));
                Parent popupRoot = fxmlLoader.load();

                Stage popupStage = new Stage();
                popupStage.setTitle("Add Item");
                popupStage.setScene(new Scene(popupRoot));
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @FXML
    public void btn_clear(MouseEvent mouseEvent) {

        txt_productID.clear();
        txt_quantity.clear();
        txt_name2.clear();
        txt_quantity2.clear();
        lbl_name2.setText("");
        lbl_quantity2.setText("");
        lbl_name.setText("");
        lbl_quantity.setText("");
        lbl_total.setText("");
        lbl_total2.setText("");
        lbl_subTotal.setText("");
        clear();
        refreshTableData();
    }

    @FXML
    void btn_productID(KeyEvent event) {

        if(txt_productID.getText().equals("") || txt_productID.getText() == null){
            lbl_name.setText("");
            lbl_name2.setText("");
        }else {
            lbl_name.setText(getProductName(Integer.parseInt(txt_productID.getText())));
            lbl_name2.setText(getProductName(Integer.parseInt(txt_productID.getText())));
        }

        if(txt_quantity.getText() != null || !txt_quantity.getText().equals("")) {
            if(Integer.parseInt(txt_quantity.getText()) > availableStock(Integer.parseInt(txt_productID.getText()))) {
                lbl_quantityStatus.setText("Quantity Exceeded");
                lbl_nameQuantityStatus2.setText("Quantity Exceeded");
            }else{
                lbl_quantity.setText(txt_quantity.getText());
                lbl_quantity2.setText(txt_quantity.getText());
                lbl_total.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                lbl_total2.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                lbl_quantityStatus.setText("");
                lbl_nameQuantityStatus2.setText("");
            }
        }

    }
    @FXML
    public void btn_idQuantity(KeyEvent keyEvent) {

        txt_quantity2.setText(txt_quantity.getText());
        if(Integer.parseInt(txt_quantity.getText()) <= 0) {

            lbl_quantityStatus.setText("Invalid Quantity");
            lbl_nameQuantityStatus2.setText("Invalid Quantity");
            lbl_quantity.setText("Invalid Quantity");
            lbl_quantity2.setText("Invalid Quantity");

        }else{
            if (txt_productID.getText() != null || !txt_productID.getText().equals("")) {
                if (Integer.parseInt(txt_quantity.getText()) > availableStock(Integer.parseInt(txt_productID.getText()))) {
                    lbl_quantityStatus.setText("Quantity Exceeded");
                    lbl_quantity.setText("Insufficient Stock");
                    lbl_nameQuantityStatus2.setText("Quantity Exceeded");
                    lbl_quantity2.setText("Insufficient Stock");
                } else {
                    lbl_quantity.setText(txt_quantity.getText());
                    lbl_total.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                    lbl_quantityStatus.setText("");
                    lbl_quantity2.setText(txt_quantity.getText());
                    lbl_total2.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                    lbl_nameQuantityStatus2.setText("");
                }
            } else {
                lbl_quantity.setText("");
                lbl_quantityStatus.setText("");
                lbl_quantity2.setText("");
                lbl_nameQuantityStatus2.setText("");
            }
        }
    }

    @FXML
    void btn_remove(MouseEvent event) {
        int selectID;
        selectID = tbl_bill.getSelectionModel().getSelectedIndex();
        remove(selectID);

        refreshTableData();
        lbl_subTotal.setText(String.valueOf(getSubTotal()));
    }


    private void visualizeTableData() {

        col_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
    }

    private void refreshTableData() {
        saleList = observableArrayList(tableData);
        tbl_bill.setItems(saleList);
        tbl_bill.refresh();

        visualizeTableData();
    }

    private void refreshSuggestList() {

        setProductList();
        productData = observableArrayList(ProductDetailsController.getProductList());
        productNames.setItems(productData);
        productNames.refresh();
        visualizeSuggestList();
    }

    private void refreshSuggestList(String searchTerm) {

        setProductList(searchTerm);
        productData = observableArrayList(ProductDetailsController.getProductList());
        productNames.setItems(productData);
        productNames.refresh();
        visualizeSuggestList();
    }

    public void visualizeSuggestList() {
        col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));


    }
    private int availableStock(int productID) {
        int stock = 0;
        stock = getStock(productID)-getQuantity(productID);
        return stock;
    }

    @FXML
    void initialize() {
        assert col_name != null : "fx:id=\"col_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_price != null : "fx:id=\"col_price\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_productID != null : "fx:id=\"col_productID\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_productName != null : "fx:id=\"col_productName\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_quantity != null : "fx:id=\"col_quantity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_rate != null : "fx:id=\"col_rate\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_invoiceNo != null : "fx:id=\"lbl_invoiceNo\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_invoiceNo2 != null : "fx:id=\"lbl_invoiceNo2\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_name != null : "fx:id=\"lbl_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_name2 != null : "fx:id=\"lbl_name2\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_nameQuantityStatus2 != null : "fx:id=\"lbl_nameQuantityStatus2\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_quantity != null : "fx:id=\"lbl_quantity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_quantity2 != null : "fx:id=\"lbl_quantity2\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_quantityStatus != null : "fx:id=\"lbl_quantityStatus\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_subTotal != null : "fx:id=\"lbl_subTotal\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_total != null : "fx:id=\"lbl_total\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_total2 != null : "fx:id=\"lbl_total2\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert productNames != null : "fx:id=\"productNames\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tabPanel != null : "fx:id=\"tabPanel\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tab_name != null : "fx:id=\"tab_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tab_productID != null : "fx:id=\"tab_productID\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tablePanel != null : "fx:id=\"tablePanel\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tbl_bill != null : "fx:id=\"tbl_bill\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_name2 != null : "fx:id=\"txt_name2\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_productID != null : "fx:id=\"txt_productID\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_quantity != null : "fx:id=\"txt_quantity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_quantity2 != null : "fx:id=\"txt_quantity2\" was not injected: check your FXML file 'SellProduct.fxml'.";

        lbl_invoiceNo.setText(String.valueOf(getNewSaleID()));
        lbl_invoiceNo2.setText(String.valueOf(getNewSaleID()));

    }

    public void productNames(KeyEvent mouseEvent) {
        String searchTerm = txt_name2.getText();

        if (searchTerm == null || searchTerm.isEmpty()) {
            setProductList();
            refreshSuggestList();

        }else {
            setProductList(searchTerm);
            refreshSuggestList(searchTerm);
        }
    }

    public void tblSelect(MouseEvent keyEvent) {
        Product productID;
        productID = productNames.getSelectionModel().getSelectedItem();
        txt_productID.setText(String.valueOf(productID.getProductId()));

        if(txt_productID.getText().equals("") || txt_productID.getText() == null){
            lbl_name.setText("");
            lbl_name2.setText("");
        }else {
            lbl_name.setText(getProductName(Integer.parseInt(txt_productID.getText())));
            lbl_name2.setText(getProductName(Integer.parseInt(txt_productID.getText())));
        }

        if(txt_quantity.getText() != null || !txt_quantity.getText().equals("")) {
            if(Integer.parseInt(txt_quantity.getText()) > availableStock(Integer.parseInt(txt_productID.getText()))) {
                lbl_quantityStatus.setText("Quantity Exceeded");
                lbl_nameQuantityStatus2.setText("Quantity Exceeded");
            }else{
                lbl_quantity.setText(txt_quantity.getText());
                lbl_quantity2.setText(txt_quantity.getText());
                lbl_total.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                lbl_total2.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                lbl_quantityStatus.setText("");
                lbl_nameQuantityStatus2.setText("");
            }
        }

    }

    public void btn_idQuantity2(KeyEvent keyEvent) {
        txt_quantity.setText(txt_quantity2.getText());
        if(Integer.parseInt(txt_quantity.getText()) <= 0) {

            lbl_quantityStatus.setText("Invalid Quantity");
            lbl_nameQuantityStatus2.setText("Invalid Quantity");
            lbl_quantity.setText("Invalid Quantity");
            lbl_quantity2.setText("Invalid Quantity");

        }else{
            if (txt_productID.getText() != null || !txt_productID.getText().equals("")) {
                if (Integer.parseInt(txt_quantity.getText()) > availableStock(Integer.parseInt(txt_productID.getText()))) {
                    lbl_quantityStatus.setText("Quantity Exceeded");
                    lbl_quantity.setText("Insufficient Stock");
                    lbl_nameQuantityStatus2.setText("Quantity Exceeded");
                    lbl_quantity2.setText("Insufficient Stock");
                } else {
                    lbl_quantity.setText(txt_quantity.getText());
                    lbl_total.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                    lbl_quantityStatus.setText("");
                    lbl_quantity2.setText(txt_quantity.getText());
                    lbl_total2.setText(String.valueOf(Integer.parseInt(txt_quantity.getText())*getPrice(Integer.parseInt(txt_productID.getText()))));
                    lbl_nameQuantityStatus2.setText("");
                }
            } else {
                lbl_quantity.setText("");
                lbl_quantityStatus.setText("");
                lbl_quantity2.setText("");
                lbl_nameQuantityStatus2.setText("");
            }
        }
    }
}
