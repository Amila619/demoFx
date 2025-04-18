package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.CustomUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsertController extends DashboardController {


    private String productName;
    private int productQuantity;
    private double productPrice;

    @FXML
    private Button addProduct;

    @FXML
    private Label insertHeading;

    @FXML
    private TextField productNameInput;

    @FXML
    private Label productNameInputLabel;

    @FXML
    private TextField productPriceInput;

    @FXML
    private Label productPriceInputLabel;

    @FXML
    private TextField productQuantityInput;

    @FXML
    private Label productQuantityInputLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void insertProduct(ActionEvent event) {
        sql = "INSERT INTO products (product_name, product_quantity, product_price) values(?,?,?)";
        if(validateInputs()){
            try {
                conn = Database.Conn();
                ps = conn.prepareStatement(sql);

                ps.setString(1, productName);
                ps.setInt(2, productQuantity);
                ps.setDouble(3, productPrice);

                int rowCount = ps.executeUpdate();
                if (rowCount > 0){
                    clearInputs();
                    CustomUi.popUpErrorMessage("Product Added Successfully", "Added Success", Alert.AlertType.INFORMATION);
                }else{
                    CustomUi.popUpErrorMessage("Product Added Failed", "Added Failed", Alert.AlertType.ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error in closing the Connection..." + e.getMessage());
                }
            }
        }
    }

    private boolean validateInputs() {
        try {
            productName = productNameInput.getText().trim();
            productQuantity = Integer.parseInt(productQuantityInput.getText().trim());
            productPrice = Double.parseDouble(productPriceInput.getText().trim());

            if (productName.isEmpty()) {
                CustomUi.popUpErrorMessage("Fields cannot be empty!", "Product Input Error", Alert.AlertType.ERROR);
                return false;
            }
            if (productQuantity <= 0 || productPrice <= 0) {
                CustomUi.popUpErrorMessage("Quantity/Price must be > 0", "Product Input Error", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            CustomUi.popUpErrorMessage("Quantity/Price must be Number", "Product Input Error", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void clearInputs() {
        productNameInput.setText("");
        productQuantityInput.setText("");
        productPriceInput.setText("");
    }
}
