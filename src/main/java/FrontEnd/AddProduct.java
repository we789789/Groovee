package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.AddProductController;
import BackEnd.Product;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static Controller.AddProductController.*;
import static java.util.Collections.list;
import static javafx.collections.FXCollections.observableArrayList;
import static BackEnd.Product.*;

public class AddProduct {

    public TableView<Product> tbl_addProduct;
    public TableColumn<Product, Integer> col_productID;
    public TableColumn<Product, String> col_productName;
    public Label lbl_fail;


    ArrayList<Product> products;

    {
        products = AddProductController.getProductList();
    }

    ObservableList<Product> productData = observableArrayList(products);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_Success;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TextField txt_productId;

    @FXML
    private TextField txt_productName;

    @FXML
    private TextField txt_purchasePrice;

    @FXML
    private TextField txt_quantity;

    @FXML
    private TextField txt_sellingPrice;

    private void refreshTableData() {

        setProductList();
        productData = observableArrayList(AddProductController.getProductList());
        tbl_addProduct.setItems(productData);
        tbl_addProduct.refresh();
    }

    @FXML
    void btn_addProduct(ActionEvent event) {

        lbl_Success.setText("");
        lbl_fail.setText("");

        setProductList();

        String productId = txt_productId.getText();
        String productName = txt_productName.getText();
        String purchasePrice = txt_purchasePrice.getText();
        String sellingPrice = txt_sellingPrice.getText();
        String quantity = txt_quantity.getText();


        if (productId.isEmpty() || productName.isEmpty() || purchasePrice.isEmpty() ||
                sellingPrice.isEmpty() || quantity.isEmpty()) {
            lbl_fail.setText("Please fill all fields");
            return;
        }

        for(int i = 0; i < products.size(); i++) {
            ArrayList<Product> list;
            list = products;
            if(list.get(i).getProductId() == Integer.parseInt(productId)) {
                lbl_fail.setText("Product with id " + productId + " already exists");
                return;
            }
        }

        int productIdInt = Integer.parseInt(productId);
        int quantityInt = Integer.parseInt(quantity);
        float sellingPriceFloat = Float.parseFloat(sellingPrice);
        float purchasePriceFloat = Float.parseFloat(purchasePrice);


        lbl_Success.setText(addProduct(productIdInt, productName, sellingPriceFloat, purchasePriceFloat, quantityInt));

        refreshTableData();

        txt_productId.clear();
        txt_productName.clear();
        txt_purchasePrice.clear();
        txt_sellingPrice.clear();
        txt_quantity.clear();

    }
    public void btn_clearProduct(ActionEvent actionEvent) {
        txt_productId.clear();
        txt_productName.clear();
        txt_purchasePrice.clear();
        txt_sellingPrice.clear();
        txt_quantity.clear();
        lbl_fail.setText("");
        lbl_Success.setText("");
    }

    public void visualizeTableData() {
        col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));


    }

    @FXML
    void initialize() {
        assert lbl_Success != null : "fx:id=\"lbl_Success\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_productId != null : "fx:id=\"txt_productId\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_productName != null : "fx:id=\"txt_productName\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_purchasePrice != null : "fx:id=\"txt_purchasePrice\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_quantity != null : "fx:id=\"txt_quantity\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_sellingPrice != null : "fx:id=\"txt_sellingPrice\" was not injected: check your FXML file 'AddProduct.fxml'.";

        refreshTableData();
        visualizeTableData();

    }


}
