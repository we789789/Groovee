package FrontEnd;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Overview {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    void overallProfit(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/dailyIncome.fxml")));
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
    void profitByProduct(MouseEvent event) {

        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/DailyProfitByProduct.fxml")));
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
    void profitGraph(MouseEvent event) {

    }

    @FXML
    void salesGraph(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/SalesGraphicalView.fxml")));
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
    void salesTable(MouseEvent event) {
        try {
            Parent addProductView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Sales.fxml")));
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
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'Overview.fxml'.";

    }

}


