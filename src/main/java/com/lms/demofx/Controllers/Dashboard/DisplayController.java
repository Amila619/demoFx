package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayController extends DashboardController {

    @FXML
    private TableColumn<?, String> productName;

    @FXML
    private TableColumn<?, Double> productPrice;

    @FXML
    private TableColumn<?, Integer> productQuantity;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Label displayHeading;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
