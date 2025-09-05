package FrontEnd;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class mainMenu {

    public AnchorPane mainFrame;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public Label lbl;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    void btn_addProduct(ActionEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/AddProduct.fxml")));
            mainPanel.getChildren().setAll(addProductView); // replaces all children in mainPanel

            AnchorPane.setTopAnchor(addProductView, 0.0);
            AnchorPane.setBottomAnchor(addProductView, 0.0);
            AnchorPane.setLeftAnchor(addProductView, 0.0);
            AnchorPane.setRightAnchor(addProductView, 0.0);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), addProductView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void btn_getDailyIncomes(ActionEvent event) {
    }

    @FXML
    void btn_getDailyIncomesByProduct(ActionEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/DailyProfitByProduct.fxml")));
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
            System.out.println(e);
        }
    }

    @FXML
    void btn_getMonthlyIncomes(ActionEvent event) {

    }

    @FXML
    void btn_getMonthlyIncomesByProduct(ActionEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MonthlyProfitByProduct.fxml")));
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
    void btn_getYearlyIncomes(ActionEvent event) {

    }
    @FXML
    void btn_getYearlyIncomesByProduct(ActionEvent event) {

        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/YearlyProfitByProduct.fxml")));
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
    void btn_sellProducts(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

    public void btn_productDetails(ActionEvent actionEvent) {

        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/ProductDetails.fxml")));
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

}
