package FrontEnd;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SellProduct {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_price;

    @FXML
    private TableColumn<?, ?> col_quntity;

    @FXML
    private TableColumn<?, ?> col_rate;

    @FXML
    private Label lbl_invoiceNo;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_nameInvoiceNo;

    @FXML
    private Label lbl_nameName;

    @FXML
    private Label lbl_nameQuantity;

    @FXML
    private Label lbl_nameQuantityStatus;

    @FXML
    private Label lbl_nameTotal;

    @FXML
    private Label lbl_quantity;

    @FXML
    private Label lbl_subTotal;

    @FXML
    private Label lbl_total;

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
    private TableView<?> tbl_bill;

    @FXML
    private AutoCompleteTextField<?> txt_name;

    @FXML
    private TextField txt_nameQuantity;

    @FXML
    private TextField txt_productID;

    @FXML
    void btn_nameAdd(MouseEvent event) {

    }

    @FXML
    void btn_newSale(MouseEvent event) {

    }

    @FXML
    void btn_productID(KeyEvent event) {

    }

    @FXML
    void btn_remove(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert col_name != null : "fx:id=\"col_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_price != null : "fx:id=\"col_price\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_quntity != null : "fx:id=\"col_quntity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert col_rate != null : "fx:id=\"col_rate\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_invoiceNo != null : "fx:id=\"lbl_invoiceNo\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_name != null : "fx:id=\"lbl_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_nameInvoiceNo != null : "fx:id=\"lbl_nameInvoiceNo\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_nameName != null : "fx:id=\"lbl_nameName\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_nameQuantity != null : "fx:id=\"lbl_nameQuantity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_nameQuantityStatus != null : "fx:id=\"lbl_nameQuantityStatus\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_nameTotal != null : "fx:id=\"lbl_nameTotal\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_quantity != null : "fx:id=\"lbl_quantity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_subTotal != null : "fx:id=\"lbl_subTotal\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert lbl_total != null : "fx:id=\"lbl_total\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tabPanel != null : "fx:id=\"tabPanel\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tab_name != null : "fx:id=\"tab_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tab_productID != null : "fx:id=\"tab_productID\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tablePanel != null : "fx:id=\"tablePanel\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert tbl_bill != null : "fx:id=\"tbl_bill\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_nameQuantity != null : "fx:id=\"txt_nameQuantity\" was not injected: check your FXML file 'SellProduct.fxml'.";
        assert txt_productID != null : "fx:id=\"txt_productID\" was not injected: check your FXML file 'SellProduct.fxml'.";

    }

}
