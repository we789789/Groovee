package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static broowsky.AddProduct.addProduct;

public class AddProduct {

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

    @FXML
    void btn_addProduct(ActionEvent event) {

        String productId = txt_productId.getText();
        String productName = txt_productName.getText();
        String purchasePrice = txt_purchasePrice.getText();
        String sellingPrice = txt_sellingPrice.getText();
        String quantity = txt_quantity.getText();

        int productIdInt = Integer.parseInt(productId);
        int quantityInt = Integer.parseInt(quantity);
        float sellingPriceFloat = Float.parseFloat(sellingPrice);
        float purchasePriceFloat = Float.parseFloat(purchasePrice);


        lbl_Success.setText(addProduct(productIdInt, productName, sellingPriceFloat, purchasePriceFloat, quantityInt));

    }

    @FXML
    void btn_mainMenu(ActionEvent event) {
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
    void initialize() {
        assert lbl_Success != null : "fx:id=\"lbl_Success\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_productId != null : "fx:id=\"txt_productId\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_productName != null : "fx:id=\"txt_productName\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_purchasePrice != null : "fx:id=\"txt_purchasePrice\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_quantity != null : "fx:id=\"txt_quantity\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert txt_sellingPrice != null : "fx:id=\"txt_sellingPrice\" was not injected: check your FXML file 'AddProduct.fxml'.";

    }

}
