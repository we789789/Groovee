/**
 * Sample Skeleton for 'DailyIncomeByProduct.fxml' Controller Class
 */

package FrontEnd;

import BackEnd.DailyIncomeByProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class incomesByProduct{

    DailyIncomeByProduct dailyIncomeByProduct = new DailyIncomeByProduct();

    @FXML
    private TableColumn<?, ?> col_dailyProfitProductID; // Value injected by FXMLLoader

    @FXML // fx:id="col_dailyProfitProductName"
    private TableColumn<?, ?> col_dailyProfitProductName; // Value injected by FXMLLoader

    @FXML // fx:id="col_dailyProfitPurchasingPrice"
    private TableColumn<?, ?> col_dailyProfitPurchasingPrice; // Value injected by FXMLLoader

    @FXML // fx:id="col_dailyProfitQuantity"
    private TableColumn<?, ?> col_dailyProfitQuantity; // Value injected by FXMLLoader

    @FXML // fx:id="col_dailyProfitSellingPrice"
    private TableColumn<?, ?> col_dailyProfitSellingPrice; // Value injected by FXMLLoader

    @FXML // fx:id="mainPanel"
    private AnchorPane mainPanel; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_dailyProfit"
    private TableView<?> tbl_dailyProfit; // Value injected by FXMLLoader

    @FXML // fx:id="txt_searchField"
    private TextField txt_searchField; // Value injected by FXMLLoader

    @FXML
    void btn_MainMenu(ActionEvent event) {

    }

    @FXML
    void btn_search(ActionEvent event) {

    }

    @FXML
    void search(KeyEvent event) {

    }

}

