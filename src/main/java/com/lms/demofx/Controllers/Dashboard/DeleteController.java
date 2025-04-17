package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.CustomUi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DeleteController extends DashboardController {

    @FXML
    private Label deleteHeading;

    @FXML
    private Button deleteProduct;

    @FXML
    private ComboBox<Integer> productIdInput;

    @FXML
    private Label productIdInputLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboList();
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        sql = "DELETE FROM products WHERE product_id = ?";

        try {

            conn = Database.Conn();
            ps = conn.prepareStatement(sql);
            id = productIdInput.getValue();
            ps.setInt(1, id);

            int rowCount = ps.executeUpdate();
            if(rowCount>0) {
                CustomUi.popUpErrorMessage("Product deleted with ID " + id + " Successfully", Alert.AlertType.INFORMATION);
                productIdInput.setValue(0);
            }else
                CustomUi.popUpErrorMessage("Product Deletion Failed", Alert.AlertType.ERROR);
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

}
