package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.CustomUi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UpdateController extends DashboardController {

    private String productName;
    private int productQuantity;
    private double productPrice;

    @FXML
    private Label updateHeading;

    @FXML
    private ComboBox<Integer> productIdInput;

    @FXML
    private Label productIdInputLabel1;

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

    @FXML
    private Button updateProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboList();
    }

    @FXML
    private void updateInputFields(ActionEvent event) {
        sql = "SELECT product_name, product_quantity, product_price FROM products WHERE product_id = ?";

        try {

            conn = Database.Conn();
            ps = conn.prepareStatement(sql);
            id = productIdInput.getValue();
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()) {
                productNameInput.setText(rs.getString("product_name"));
                productQuantityInput.setText(rs.getString("product_quantity"));
                productPriceInput.setText(rs.getString("product_price"));
            }

        } catch (SQLException e) {
            System.out.println("Error in delete a record..."+e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..."+ e.getMessage());
            }
        }

    }


    private void setComboList() {
        ObservableList<Integer> numbers = FXCollections.observableArrayList();
        sql = "SELECT product_id FROM products";

        try {
            conn = Database.Conn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                numbers.add(rs.getInt("product_id"));
            }

            productIdInput.setItems(numbers);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..."+ e.getMessage());
            }
        }
    }

    @FXML
    private void updateProduct(ActionEvent event) {
        sql = "UPDATE products SET product_name = ?, product_quantity = ?, product_price = ? WHERE product_id = ?";

        if(validateInputs()){
            try {
                conn = Database.Conn();
                ps = conn.prepareStatement(sql);

                ps.setString(1, productName);
                ps.setInt(2, productQuantity);
                ps.setDouble(3, productPrice);
                ps.setInt(4, id);

                int rowCount = ps.executeUpdate();
                if (rowCount > 0){
                    clearInputs();
                    CustomUi.popUpErrorMessage("Product with ID " + id + " Successfully Updated", Alert.AlertType.INFORMATION);
                }else{
                    CustomUi.popUpErrorMessage("Product Update Failed", Alert.AlertType.ERROR);
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
                CustomUi.popUpErrorMessage("Fields cannot be empty!", Alert.AlertType.ERROR);
                return false;
            }
            if (productQuantity <= 0 || productPrice <= 0) {
                CustomUi.popUpErrorMessage("Quantity/Price must be > 0", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            CustomUi.popUpErrorMessage("Quantity/Price must be Number", Alert.AlertType.ERROR);
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
