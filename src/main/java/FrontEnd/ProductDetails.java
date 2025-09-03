package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BackEnd.AddProductController;
import BackEnd.Product;
import BackEnd.ProductDetailsController;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.lang.model.type.NullType;

import static BackEnd.ProductDetailsController.setProductList;
import static javafx.collections.FXCollections.observableArrayList;

public class ProductDetails {

    public TableView<Product> tbl_productDetails;
    public TableColumn<Product, Integer> col_productID;
    public TableColumn<Product, String> col_productName;
    public TableColumn<Product, Float> col_purchasingPrice;
    public TableColumn<Product, Float> col_sellingPrice;
    public TableColumn<Product, Integer> col_quantity;

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
    private AnchorPane mainPanel;

    @FXML
    private TextField txt_searchField;

    @FXML
    void btn_MainMenu(ActionEvent event) {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            mainPanel.getChildren().setAll(mainMenuView);

            AnchorPane.setTopAnchor(mainMenuView, 0.0);
            AnchorPane.setBottomAnchor(mainMenuView, 0.0);
            AnchorPane.setLeftAnchor(mainMenuView, 0.0);
            AnchorPane.setRightAnchor(mainMenuView, 0.0);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), mainMenuView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void btn_search(ActionEvent event) {

        String searchTerm = txt_searchField.getText();

        if (searchTerm == null || searchTerm.isEmpty()) {
            setProductList();
            refreshTableData();

        }else{
            setProductList(searchTerm);
            refreshTableData(searchTerm);
        }
    }
    public void search(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String searchTerm = txt_searchField.getText();

            if (searchTerm == null || searchTerm.isEmpty()) {
                setProductList();
                refreshTableData();

            }else{
                setProductList(searchTerm);
                refreshTableData(searchTerm);
            }
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

    @FXML
    void initialize() {
        assert col_productID != null : "fx:id=\"col_productID\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert col_productName != null : "fx:id=\"col_productName\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert col_purchasingPrice != null : "fx:id=\"col_purchasingPrice\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert col_quantity != null : "fx:id=\"col_quantity\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert col_sellingPrice != null : "fx:id=\"col_sellingPrice\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert tbl_productDetails != null : "fx:id=\"tbl_productDetails\" was not injected: check your FXML file 'ProductDetails.fxml'.";
        assert txt_searchField != null : "fx:id=\"txt_searchField\" was not injected: check your FXML file 'ProductDetails.fxml'.";

        refreshTableData();
    }
}

