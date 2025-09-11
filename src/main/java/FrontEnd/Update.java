package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BackEnd.Product;
import Controller.ProductDetailsController;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static Controller.ProductDetailsController.setProductList;
import static javafx.collections.FXCollections.observableArrayList;
import static Controller.ProductDetailsController.getProductList;
import static Controller.ProductDetailsController.updateProduct;

public class Update {

    public TableView<Product> tbl_productDetails;
    public TableColumn<Product, Integer> col_productID;
    public TableColumn<Product, String> col_productName;
    public TableColumn<Product, Float> col_purchasingPrice;
    public TableColumn<Product, Float> col_sellingPrice;
    public TableColumn <Product, Integer> col_quantity;

    ArrayList<Product> products;

    {
        products = ProductDetailsController.getProductList();
    }

    ObservableList<Product> productData = observableArrayList(products);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_fail;

    @FXML
    private Label lbl_success;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TextField txt_productID;

    @FXML
    private TextField txt_productName;

    @FXML
    private TextField txt_purchasePrice;

    @FXML
    private TextField txt_quantity;

    @FXML
    private TextField txt_searchField;

    @FXML
    private TextField txt_sellingPrice;

    @FXML
    public void search(KeyEvent keyEvent) {
        String searchTerm = txt_searchField.getText();

        if (searchTerm == null || searchTerm.isEmpty()) {
            setProductList();
            refreshTableData();

        }else {
            setProductList(searchTerm);
            refreshTableData(searchTerm);
        }
    }

    private void refreshTableData() {

        setProductList();
        productData = observableArrayList(ProductDetailsController.getProductList());
        tbl_productDetails.setItems(productData);
        tbl_productDetails.refresh();
        visualizeTableData();
    }

    private void refreshTableData(String searchTerm) {

        setProductList(searchTerm);
        productData = observableArrayList(ProductDetailsController.getProductList());
        tbl_productDetails.setItems(productData);
        tbl_productDetails.refresh();
        visualizeTableData();
    }

    public void visualizeTableData() {
        col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_purchasingPrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        col_sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    }
    public void select(MouseEvent mouseEvent) {
        Product productDetails = tbl_productDetails.getSelectionModel().getSelectedItem();
        txt_productID.setText(String.valueOf(productDetails.getProductId()));
        txt_productName.setText(productDetails.getProductName());
        txt_purchasePrice.setText(String.valueOf(productDetails.getPurchasePrice()));
        txt_sellingPrice.setText(String.valueOf(productDetails.getSellingPrice()));
        txt_quantity.setText(String.valueOf(productDetails.getQuantity()));

        lbl_success.setText("");
        lbl_fail.setText("");
    }

    public void update(MouseEvent mouseEvent) {

        int productId = Integer.parseInt(txt_productID.getText());
        String productName = txt_productName.getText();
        float purchasePrice = Float.parseFloat(txt_purchasePrice.getText());
        float sellingPrice = Float.parseFloat(txt_sellingPrice.getText());
        int quantity = Integer.parseInt(txt_quantity.getText());

        if(txt_productID.getText().isEmpty() || txt_sellingPrice.getText().isEmpty() || txt_sellingPrice.getText().isEmpty() || txt_productName.getText().isEmpty() || txt_quantity.getText().isEmpty() ||
           txt_productID == null || txt_purchasePrice == null || txt_sellingPrice == null || txt_quantity == null || txt_productName == null) {

            lbl_fail.setText("Please fill all the fields");
            lbl_success.setText("");

        }else{
            if(quantity > 0 && purchasePrice > 0 && sellingPrice > 0){

                int result = updateProduct(productId, productName, purchasePrice, sellingPrice, quantity);

                if(result == 0){
                    lbl_fail.setText("Update Failed");
                    lbl_success.setText("");
                }else {
                    lbl_success.setText("Update Success");
                    lbl_fail.setText("");
                }
            }else{
                lbl_fail.setText("Invalid Input");
                lbl_success.setText("");
            }

        }
        txt_searchField.clear();
        txt_productID.clear();
        txt_productName.clear();
        txt_purchasePrice.clear();
        txt_sellingPrice.clear();
        txt_quantity.clear();

        refreshTableData();
    }

    @FXML
    void initialize() {
        assert col_productID != null : "fx:id=\"col_productID\" was not injected: check your FXML file 'Update.fxml'.";
        assert col_productName != null : "fx:id=\"col_productName\" was not injected: check your FXML file 'Update.fxml'.";
        assert col_purchasingPrice != null : "fx:id=\"col_purchasingPrice\" was not injected: check your FXML file 'Update.fxml'.";
        assert col_quantity != null : "fx:id=\"col_quantity\" was not injected: check your FXML file 'Update.fxml'.";
        assert col_sellingPrice != null : "fx:id=\"col_sellingPrice\" was not injected: check your FXML file 'Update.fxml'.";
        assert lbl_fail != null : "fx:id=\"lbl_fail\" was not injected: check your FXML file 'Update.fxml'.";
        assert lbl_success != null : "fx:id=\"lbl_success\" was not injected: check your FXML file 'Update.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'Update.fxml'.";
        assert tbl_productDetails != null : "fx:id=\"tbl_productDetails\" was not injected: check your FXML file 'Update.fxml'.";
        assert txt_productID != null : "fx:id=\"txt_productID\" was not injected: check your FXML file 'Update.fxml'.";
        assert txt_productName != null : "fx:id=\"txt_productName\" was not injected: check your FXML file 'Update.fxml'.";
        assert txt_purchasePrice != null : "fx:id=\"txt_purchasePrice\" was not injected: check your FXML file 'Update.fxml'.";
        assert txt_quantity != null : "fx:id=\"txt_quantity\" was not injected: check your FXML file 'Update.fxml'.";
        assert txt_searchField != null : "fx:id=\"txt_searchField\" was not injected: check your FXML file 'Update.fxml'.";
        assert txt_sellingPrice != null : "fx:id=\"txt_sellingPrice\" was not injected: check your FXML file 'Update.fxml'.";



        refreshTableData();
    }

}

