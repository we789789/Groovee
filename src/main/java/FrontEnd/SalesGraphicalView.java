package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SalesGraphicalView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private ChoiceBox<String> month;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    void clear(MouseEvent event) {

    }

    @FXML
    void show(MouseEvent event) {

    }
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private static Integer[] setYears(){

        Integer[] yearList = new Integer[50];
        int Year = 2010;

        for (int i = 0; i < 50; i++) {

            yearList[i] = Year;
            Year++;
        }
        return yearList;
    }

    @FXML
    void initialize() {
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert month != null : "fx:id=\"month\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'SalesGraphicalView.fxml'.";

        month.getItems().addAll(months);
        year.getItems().addAll(setYears());

    }

}
