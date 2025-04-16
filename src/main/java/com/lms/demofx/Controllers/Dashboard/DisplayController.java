package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Models.Product;
import com.lms.demofx.Services.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DisplayController extends DashboardController {

    private String sql;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    @FXML
    private TableColumn<?, String> productName;

    @FXML
    private TableColumn<?, Double> productPrice;

    @FXML
    private TableColumn<?, Integer> productId;

    @FXML
    private TableColumn<?, Integer> productQuantity;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Label displayHeading;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
    }

    private ObservableList<Product> getTableData() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        sql = "SELECT * FROM products";

        try {
            conn = Database.Conn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                products.add(new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("product_quantity"), rs.getDouble("product_price")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..." + e.getMessage());
            }
        }

        return products;
    }

    private void setTable() {
        productId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        productQuantity.setCellValueFactory(new PropertyValueFactory<>("product_quantity"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("product_price"));

        productTable.setItems(getTableData());
    }

}
