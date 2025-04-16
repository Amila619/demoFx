package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Models.Product;
import com.lms.demofx.Services.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteController extends DashboardController {

    private String sql;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    @FXML
    private Label deleteHeading;

    @FXML
    private Button deleteProduct;

    @FXML
    private ComboBox<?> productIdInput;

    @FXML
    private Label productIdInputLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void deleteProduct() {

    }

    private void fillComboList() {
        sql = "SELECT product_id FROM products";

        try {
            conn = Database.Conn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
